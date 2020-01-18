package co.id.cakap.ui.detailTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
import co.id.cakap.adapter.DetailTransaksiAdapter;
import co.id.cakap.data.DetailTransactionData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
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
    @BindView(R.id.et_remark)
    EditText mEtRemark;



    @BindView(R.id.nested_scroll)
    NestedScrollView mNestedScroll;

    private String mTitle = "";
    private String mItemId = "";
    private String mTransactionId = "";
    private String mMemberId = "";
    private String mName = "";
    private String mDate = "";
    private String mTotal = "";
    private String mRemark = "";

    private DetailTransaksiAdapter mListAdapter;
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
        mItemId = intent.getStringExtra(Constant.ITEM_ID_DETAIL);
        mTransactionId = intent.getStringExtra(Constant.TRANSACTION_ID_DETAIL);
        mMemberId = intent.getStringExtra(Constant.MEMBER_ID_DETAIL);
        mName = intent.getStringExtra(Constant.NAME_DETAIL);
        mDate = intent.getStringExtra(Constant.DATE_DETAIL);
        mTotal = intent.getStringExtra(Constant.TOTAL_DETAIL);
        mRemark = intent.getStringExtra(Constant.REMARK_DETAIL);

        mTitleText.setText(mTitle);
        mTxtTransactionId.setText(mTransactionId);
        mTxtMemberId.setText(mMemberId + " - " + mName);
        mTxtName.setText(mName);
        mTxtDate.setText(mDate);
        mTxtTotalAmount.setText("IDR " + mTotal);
        mEtRemark.setText(mRemark);
        mTitleToolbar.setText(getString(R.string.detail_transaksi).toUpperCase());

        mUserActionListener.getData(mItemId);
    }

    @Override
    public void setAdapter(List<DetailTransactionData> resultData) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
        mNestedScroll.getParent().requestChildFocus(mNestedScroll, mNestedScroll);

        mListAdapter = new DetailTransaksiAdapter(resultData, this);
        mRecyclerView.setAdapter(mListAdapter);
        OverScrollDecoratorHelper.setUpOverScroll(mRecyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

        hideProgressBar();
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
