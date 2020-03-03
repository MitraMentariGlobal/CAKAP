package co.id.cakap.ui.detailTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.BankInfoAdapter;
import co.id.cakap.adapter.DetailTransaksiAdapter;
import co.id.cakap.data.DetailTransactionData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.network.ApiResponseDetailTransaction;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class DetailTransactionActivity extends AppCompatActivity implements DetailTransactionContract.View {
    private static final String TAG = "DetailTransactionActivity";

    @Inject
    DetailTransactionPresenter mDetailTransactionPresenter;

    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;
    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.title_toolbar)
    TextView mTitleToolbar;
    @BindView(R.id.title)
    TextView mTitleText;
    @BindView(R.id.txt_transaction_id)
    TextView mTxtTransactionId;
    @BindView(R.id.txt_member_id)
    TextView mTxtMemberId;
    @BindView(R.id.txt_name)
    TextView mTxtName;
    @BindView(R.id.txt_date)
    TextView mTxtDate;
    @BindView(R.id.txt_total_amount)
    TextView mTxtTotalAmount;
    @BindView(R.id.txt_total_pv)
    TextView mTxtTotalPv;
    @BindView(R.id.et_remark)
    EditText mEtRemark;
    @BindView(R.id.nested_scroll)
    NestedScrollView mNestedScroll;
    @BindView(R.id.linear_info)
    LinearLayout mLinearInfo;
    @BindView(R.id.txt_info)
    TextView mTxtInfo;
    @BindView(R.id.bank_list)
    RecyclerView mbankList;

    private String mTitle = "";
    private String mKodeUnik = "";
    private String mEndpoint = "";
    private String mItemId = "";
    private String mTransactionId = "";
    private String mMemberId = "";
    private String mName = "";
    private String mDate = "";
    private String mTotal = "";
    private String mTotalPv = "";
    private String mRemark = "";

    private DetailTransaksiAdapter mListAdapter;
    private BankInfoAdapter mListBankAdapter;
    private DetailTransactionContract.UserActionListener mUserActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaksi);
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
        mUserActionListener = mDetailTransactionPresenter;
        mDetailTransactionPresenter.setView(this);

        showProgressBar();

        Intent intent = getIntent();
        mTitle = intent.getStringExtra(Constant.TITLE_DETAIL);
        mEndpoint = intent.getStringExtra(Constant.URL_LINK_DETAIL);
        try {
            mKodeUnik = intent.getStringExtra(Constant.KODE_UNIK);
        } catch (Exception e) {
            e.printStackTrace();
            mKodeUnik = "";
        }
        mItemId = intent.getStringExtra(Constant.ITEM_ID_DETAIL);
        mTransactionId = intent.getStringExtra(Constant.TRANSACTION_ID_DETAIL);
        mMemberId = intent.getStringExtra(Constant.MEMBER_ID_DETAIL);
        mName = intent.getStringExtra(Constant.NAME_DETAIL);
        mDate = intent.getStringExtra(Constant.DATE_DETAIL);
        mTotal = intent.getStringExtra(Constant.TOTAL_DETAIL);
        mTotalPv = intent.getStringExtra(Constant.TOTAL_PV_DETAIL);
        mRemark = intent.getStringExtra(Constant.REMARK_DETAIL);
        if (mRemark.equals("0"))
            mRemark = "-";

        mTitleText.setText(mTitle);
        if (mTransactionId.length() == 0) {
            mTxtTransactionId.setVisibility(View.GONE);
        } else {
            mTxtTransactionId.setText(mTransactionId);
        }
        mTxtMemberId.setText(mMemberId + " - " + mName);
        mTxtName.setText(mName);
        mTxtDate.setText(mDate);
        mTxtTotalAmount.setText("IDR " + mTotal);
        mTxtTotalPv.setText(mTotalPv + "pv");
        mEtRemark.setText(mRemark);
        mTitleToolbar.setText(getString(R.string.detail_transaksi).toUpperCase());

        mUserActionListener.getData(mEndpoint, mItemId, mKodeUnik);
    }

    @Override
    public void setAdapter(ApiResponseDetailTransaction apiResponseDetailTransaction) {
        if (apiResponseDetailTransaction.getInfo() != null) {
            setBankAdapter(apiResponseDetailTransaction);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
        mNestedScroll.getParent().requestChildFocus(mNestedScroll, mNestedScroll);

        mListAdapter = new DetailTransaksiAdapter(apiResponseDetailTransaction.getData(), this);
        mRecyclerView.setAdapter(mListAdapter);
        OverScrollDecoratorHelper.setUpOverScroll(mRecyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

        hideProgressBar();
    }

    public void setBankAdapter(ApiResponseDetailTransaction apiResponseDetailTransaction) {
        mLinearInfo.setVisibility(View.VISIBLE);
        mTxtInfo.setText(apiResponseDetailTransaction.getInfo());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mbankList.setLayoutManager(layoutManager);
        mbankList.setNestedScrollingEnabled(false);
        mNestedScroll.getParent().requestChildFocus(mNestedScroll, mNestedScroll);

        mListBankAdapter = new BankInfoAdapter(apiResponseDetailTransaction.getBank(), this);
        mbankList.setAdapter(mListBankAdapter);
        OverScrollDecoratorHelper.setUpOverScroll(mbankList, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        mRelativeProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mRelativeProgressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.arrow_back)
    public void arrowBack(View view) {
        super.onBackPressed();
    }
}
