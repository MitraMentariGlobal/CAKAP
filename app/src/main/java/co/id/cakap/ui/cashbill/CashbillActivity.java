package co.id.cakap.ui.cashbill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.ItemShopCashbillAdapter;
import co.id.cakap.data.CashbillSuccessData;
import co.id.cakap.data.ItemShopData;
import co.id.cakap.data.OperationUserStatusData;
import co.id.cakap.data.SubmitCashbillData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.cashbill.cashbillSuccess.CashbillSuccessActivity;
import co.id.cakap.ui.detailTransaction.DetailTransactionActivity;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.dialog.PinDialog;
import co.id.cakap.utils.Utils;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class CashbillActivity extends AppCompatActivity implements CashbillActivityContract.View{
    @Inject
    CashbillActivityPresenter mCashbillActivityPresenter;

    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;
    @BindView(R.id.title_toolbar)
    TextView mTitle;
    @BindView(R.id.linear_expand_collapse)
    LinearLayout mLinearExpandCollapse;
    @BindView(R.id.item_thumbnail)
    ImageView mImageIcon;
    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.et_search)
    EditText mSearchEditText;
    @BindView(R.id.linear_search)
    LinearLayout mLinearSearch;
    @BindView(R.id.et_member_id)
    EditText mMemberId;
    @BindView(R.id.et_name)
    EditText mName;
    @BindView(R.id.et_status)
    EditText mStatus;
    @BindView(R.id.et_remark)
    EditText mRemark;
    @BindView(R.id.linear_submit)
    LinearLayout mLinearSubmit;
    @BindView(R.id.relative_member_id)
    RelativeLayout mRelativeMemberId;
    @BindView(R.id.txt_total_item)
    TextView mTxtTotalItem;
    @BindView(R.id.txt_total_pv)
    TextView mTxtTotalPv;
    @BindView(R.id.txt_total_price)
    TextView mTxtTotalPrice;
    @BindView(R.id.card_checkout)
    CardView mCardCheckOut;

    private ItemShopCashbillAdapter mListAdapter;
    private GridLayoutManager mGridLayoutManager;
    private CashbillActivityContract.UserActionListener mUserActionListener;
    private List<ItemShopData> mResultData;
    private boolean mIsExpand = true;

    private int mItem = 0;
    private int mPv = 0;
    private int mBv = 0;
    private double mPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashbill);
        ButterKnife.bind(this);

        setupActivityComponent();
        initializeData();
    }

    private void setupActivityComponent() {
        CoreApp.get()
                .getAppComponent()
                .plus(new MainActivityModule(this))
                .inject(this);
    }

    @Override
    public void initializeData() {
        mUserActionListener = mCashbillActivityPresenter;
        mCashbillActivityPresenter.setView(this);

        mTitle.setText(getString(R.string.cashbill).toUpperCase());
        mLinearSearch.setVisibility(View.GONE);

        hideProgressBar();
    }

    @Override
    public void showProgressBar() {
        mRelativeProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mRelativeProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setAdapter(List<ItemShopData> resultData, OperationUserStatusData operationUserStatusData) {
        mMemberId.setText(operationUserStatusData.getUser_code());
        mName.setText(operationUserStatusData.getUser_name());
        mStatus.setText(operationUserStatusData.getStatus());
        setDataAdapter(resultData);
    }

    public void setDataAdapter(List<ItemShopData> resultData) {
        mGridLayoutManager = new GridLayoutManager(this, 2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mListAdapter = new ItemShopCashbillAdapter(resultData, this);
        mListAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mListAdapter);

        setupOnFocusListener(mSearchEditText);
        hideProgressBar();
    }

    @Override
    public void setCheckoutValue(List<ItemShopData> resultData, ItemShopData itemShopData, int action) {
        mResultData = resultData;

        if (action == 0) {
            mItem -= 1;
            mPv -= Integer.parseInt(itemShopData.getPv());
            mBv -= Integer.parseInt(itemShopData.getBv());
            mPrice -= Double.parseDouble(itemShopData.getHarga());
        } else {
            mItem += 1;
            mPv += Integer.parseInt(itemShopData.getPv());
            mBv += Integer.parseInt(itemShopData.getBv());
            mPrice += Double.parseDouble(itemShopData.getHarga());
        }

        mTxtTotalItem.setText(String.valueOf(mItem));
        mTxtTotalPv.setText(String.valueOf(mPv));
        mTxtTotalPrice.setText(Utils.priceWithoutDecimal(mPrice));
    }

    @Override
    public void successSubmitData(SubmitCashbillData submitCashbillData) {
        Bundle b = new Bundle();
        b.putParcelable(Constant.SUCCESS_DATA_OBJECT, submitCashbillData);

        Intent intent = new Intent(getApplicationContext(), CashbillSuccessActivity.class);
        intent.putExtra(Constant.TITLE_DETAIL, getResources().getString(R.string.cashbill).toUpperCase());
        intent.putExtra(Constant.NAME, mName.getText().toString());
        intent.putExtra(Constant.SUCCESS_DATA_OBJECT, b);
        startActivity(intent);
    }

    @OnClick(R.id.arrow_back)
    public void arrowBack(View view) {
        super.onBackPressed();
    }

    @OnClick(R.id.card_checkout)
    public void checkOut(View view) {
        if (mItem != 0) {
            PinDialog utils = new PinDialog();
            Dialog dialog = utils.showDialog(this);

            PinLockView pinLockView = dialog.findViewById(R.id.pin_lock_view);
            IndicatorDots indicatorDots = dialog.findViewById(R.id.indicator_dots);
            PinLockListener pinLockListener = new PinLockListener() {
                @Override
                public void onComplete(String pin) {
                    Logger.d("Pin complete: " + pin);
                    dialog.hide();
                    dialog.dismiss();

                    mUserActionListener.submitData(
                            pin,
                            String.valueOf(mPrice),
                            String.valueOf(mPv),
                            String.valueOf(mBv),
                            mRemark.getText().toString(),
                            mResultData
                    );
                }

                @Override
                public void onEmpty() {
                    Logger.d("Pin empty");
                }

                @Override
                public void onPinChange(int pinLength, String intermediatePin) {
                    Logger.d("Pin changed, new length " + pinLength + " with intermediate pin " + intermediatePin);
                }
            };

            pinLockView.attachIndicatorDots(indicatorDots);
            pinLockView.setPinLockListener(pinLockListener);

            pinLockView.setPinLength(6);
            pinLockView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

            indicatorDots.setIndicatorType(IndicatorDots.IndicatorType.FILL_WITH_ANIMATION);
        }
    }

    @OnClick(R.id.linear_submit)
    public void submitMemberId(View view) {
        if (mMemberId.getText().length() == 0) {
            mRelativeMemberId.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
        } else {
            mRelativeMemberId.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
            mUserActionListener.getMemberData(mMemberId.getText().toString());
//            mMemberId.setInputType(0);
        }
    }

    @OnClick(R.id.action_expand_collapse)
    public void actionExpandCollapse(View view) {
        if (mIsExpand) {
            Utils.collapse(mLinearExpandCollapse);
            mIsExpand = false;
            mImageIcon.animate().rotation(180).setDuration(500).start();
            mLinearSearch.setVisibility(View.VISIBLE);
        } else {
            Utils.expand(mLinearExpandCollapse);
            mIsExpand = true;
            mImageIcon.animate().rotation(0).setDuration(500).start();
            mLinearSearch.setVisibility(View.GONE);
        }
    }

    private void setupOnFocusListener(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                Logger.d("arg0 : " + arg0);
                String text = editText.getText().toString().toLowerCase(Locale.getDefault());
                if (mListAdapter != null) mListAdapter.getFilter().filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });
    }
}
