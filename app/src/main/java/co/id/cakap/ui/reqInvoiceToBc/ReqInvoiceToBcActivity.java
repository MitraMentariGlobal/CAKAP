package co.id.cakap.ui.reqInvoiceToBc;

import androidx.appcompat.app.AppCompatActivity;
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
import co.id.cakap.adapter.ItemShopReqInvToBcAdapter;
import co.id.cakap.adapter.ItemShopReqInvToCompanyAdapter;
import co.id.cakap.data.ItemShopCompanyData;
import co.id.cakap.data.SubmitInvoiceToBcData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.reqInvoiceToBc.reqInvoiceToBcSuccess.ReqInvoiceToBcSuccessActivity;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import co.id.cakap.utils.dialog.PinDialog;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class ReqInvoiceToBcActivity extends AppCompatActivity implements ReqInvoiceToBcActivityContract.View {
    @Inject
    ReqInvoiceToBcActivityPresenter mReqInvoiceToBcActivityPresenter;

    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;
    @BindView(R.id.title_toolbar)
    TextView mTitle;
    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.et_search)
    EditText mSearchEditText;
    @BindView(R.id.linear_search)
    LinearLayout mLinearSearch;
    @BindView(R.id.user_id)
    TextView mUserId;
    @BindView(R.id.user_name)
    TextView mUserName;
    @BindView(R.id.txt_total_item)
    TextView mTxtTotalItem;
    @BindView(R.id.txt_total_pv)
    TextView mTxtTotalPv;
    @BindView(R.id.txt_total_price)
    TextView mTxtTotalPrice;

    private ItemShopReqInvToBcAdapter mListAdapter;
    private GridLayoutManager mGridLayoutManager;
    private List<ItemShopCompanyData> mResultData;
    private ReqInvoiceToBcActivityContract.UserActionListener mUserActionListener;

    private static int mItem = 0;
    private static int mPv = 0;
    private static double mPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_invoice_to_bc);
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
        mUserActionListener = mReqInvoiceToBcActivityPresenter;
        mReqInvoiceToBcActivityPresenter.setView(this);
        mUserActionListener.getData();
        mTitle.setText(getString(R.string.req_invoice_to_bc).toUpperCase());
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
    public void setAdapter(List<ItemShopCompanyData> resultData) {
        mGridLayoutManager = new GridLayoutManager(this, 2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mListAdapter = new ItemShopReqInvToBcAdapter(resultData, this);
        mRecyclerView.setAdapter(mListAdapter);
        OverScrollDecoratorHelper.setUpOverScroll(mRecyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

        setupOnFocusListener(mSearchEditText);
        hideProgressBar();
    }

    @Override
    public void setCheckoutValue(List<ItemShopCompanyData> resultData, ItemShopCompanyData itemShopCompanyData, int action) {
        mResultData = resultData;
        if (action == 0) {
            mItem -= 1;
            mPv -= Integer.parseInt(itemShopCompanyData.getPv());
            mPrice -= Double.parseDouble(itemShopCompanyData.getHarga());
        } else {
            mItem += 1;
            mPv += Integer.parseInt(itemShopCompanyData.getPv());
            mPrice += Double.parseDouble(itemShopCompanyData.getHarga());
        }

        mTxtTotalItem.setText(String.valueOf(mItem));
        mTxtTotalPv.setText(String.valueOf(mPv));
        mTxtTotalPrice.setText(Utils.priceWithoutDecimal(mPrice));
    }

    @Override
    public void successSubmitData(SubmitInvoiceToBcData submitInvoiceToBcData) {
        Bundle b = new Bundle();
        b.putParcelable(Constant.SUCCESS_DATA_OBJECT, submitInvoiceToBcData);

        Intent intent = new Intent(getApplicationContext(), ReqInvoiceToBcSuccessActivity.class);
        intent.putExtra(Constant.TITLE_DETAIL, getResources().getString(R.string.req_invoice_to_bc).toUpperCase());
        intent.putExtra(Constant.SUCCESS_DATA_OBJECT, b);
        startActivity(intent);
    }

    @OnClick(R.id.arrow_back)
    public void arrowBack(View view) {
        super.onBackPressed();
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

    @OnClick(R.id.card_checkout)
    public void checkOut(View view) {
        mUserActionListener.submitData(
                "",
                String.valueOf(mPrice),
                String.valueOf(mPv),
                "",
                "",
                mResultData
        );

//        Intent intent = new Intent(getApplicationContext(), ReqInvoiceToBcSuccessActivity.class);
//        intent.putExtra(Constant.TITLE_DETAIL, getResources().getString(R.string.req_invoice_to_bc).toUpperCase());
//        startActivity(intent);
    }
}
