package co.id.cakap.ui.reqInvoiceToCompany;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import co.id.cakap.adapter.ItemShopReqInvToCompanyAdapter;
import co.id.cakap.data.ItemShopCompanyData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import co.id.cakap.utils.dialog.PinDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class ReqInvoiceToCompanyActivity extends AppCompatActivity implements ReqInvoiceToCompanyActivityContract.View{
    @Inject
    ReqInvoiceToCompanyActivityPresenter mReqInvoiceToCompanyActivityPresenter;

    @BindView(R.id.main_progress_bar)
    ProgressBar mProgressBar;
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
    @BindView(R.id.saldo_ewallet)
    TextView mSaldoEwallet;
    @BindView(R.id.et_remark)
    EditText mRemark;
    @BindView(R.id.txt_total_item)
    TextView mTxtTotalItem;
    @BindView(R.id.txt_total_pv)
    TextView mTxtTotalPv;
    @BindView(R.id.txt_total_price)
    TextView mTxtTotalPrice;
    @BindView(R.id.txt_pick_up_delivery)
    TextView mTxtPickUpDelivery;
    @BindView(R.id.item_check)
    CircleImageView mItemCheck;
    @BindView(R.id.linear_change_address)
    LinearLayout mLinearChangeAddress;

    private ItemShopReqInvToCompanyAdapter mListAdapter;
    private ReqInvoiceToCompanyActivityContract.UserActionListener mUserActionListener;
    private boolean mIsExpand = true;

    private static int mItem = 0;
    private static int mPv = 0;
    private static double mPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_invoice_to_company);
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
        mUserActionListener = mReqInvoiceToCompanyActivityPresenter;
        mReqInvoiceToCompanyActivityPresenter.setView(this);
        mUserActionListener.getData();

        mTitle.setText(getString(R.string.req_invoice_to_com).toUpperCase());
        mTxtPickUpDelivery.setText("Delivery");
        mLinearSearch.setVisibility(View.GONE);
        mItemCheck.setVisibility(View.GONE);
        mLinearChangeAddress.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setAdapter(List<ItemShopCompanyData> resultData) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mListAdapter = new ItemShopReqInvToCompanyAdapter(resultData, this);
        mRecyclerView.setAdapter(mListAdapter);
        OverScrollDecoratorHelper.setUpOverScroll(mRecyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

        setupOnFocusListener(mSearchEditText);
        hideProgressBar();
    }

    @Override
    public void setCheckoutValue(List<ItemShopCompanyData> resultData, ItemShopCompanyData itemShopCompanyData, int action) {
        if (action == 0) {
            mItem -= 1;
            mPv -= Integer.parseInt(itemShopCompanyData.getPv());
            mPrice -= Double.parseDouble(itemShopCompanyData.getPrice().replace(".",""));
        } else {
            mItem += 1;
            mPv += Integer.parseInt(itemShopCompanyData.getPv());
            mPrice += Double.parseDouble(itemShopCompanyData.getPrice().replace(".",""));
        }

        mTxtTotalItem.setText(String.valueOf(mItem));
        mTxtTotalPv.setText(String.valueOf(mPv));
        mTxtTotalPrice.setText(Utils.priceWithoutDecimal(mPrice));
    }

    @OnClick(R.id.arrow_back)
    public void arrowBack(View view) {
        super.onBackPressed();
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

    @OnClick(R.id.card_checkout)
    public void checkOut(View view) {

    }
}
