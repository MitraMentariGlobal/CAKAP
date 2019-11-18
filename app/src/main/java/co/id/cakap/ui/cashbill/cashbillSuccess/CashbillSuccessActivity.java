package co.id.cakap.ui.cashbill.cashbillSuccess;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import co.id.cakap.adapter.CashbillSuccessAdapter;
import co.id.cakap.adapter.DetailTransaksiAdapter;
import co.id.cakap.data.CashbillSuccessData;
import co.id.cakap.data.DetailTransaksiData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.dashboard.DashboardActivity;
import co.id.cakap.ui.detailTransaction.DetailTransactionContract;
import co.id.cakap.ui.detailTransaction.DetailTransactionPresenter;
import co.id.cakap.utils.Utils;
import de.hdodenhof.circleimageview.CircleImageView;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class CashbillSuccessActivity extends AppCompatActivity implements CashbillSuccessContract.View {
    private static final String TAG = "DetailTransactionActivity";

    @Inject
    CashbillSuccessPresenter mCashbillSuccessPresenter;

    @BindView(R.id.main_progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.title_toolbar)
    TextView mTitleToolbar;
    @BindView(R.id.txt_transaction_id)
    TextView mTransactionIdText;
    @BindView(R.id.nested_scroll)
    NestedScrollView mNestedScroll;
    @BindView(R.id.et_member_id)
    EditText mMemberId;
    @BindView(R.id.et_name)
    EditText mName;
    @BindView(R.id.et_bonus_date)
    EditText mBonusDate;
    @BindView(R.id.et_status)
    EditText mStatus;
    @BindView(R.id.et_remark)
    EditText mRemark;
    @BindView(R.id.et_alamat_delivery)
    EditText mAlamatDeelivery;
    @BindView(R.id.linear_expand_collapse)
    LinearLayout mLinearExpandCollapse;
    @BindView(R.id.item_thumbnail)
    ImageView mImageIcon;
    @BindView(R.id.img_close)
    CircleImageView mImgClose;

    private String mTitle = "";
    private String mTransactionId = "INV - 123123123123123";
    private CashbillSuccessAdapter mListAdapter;
    private CashbillSuccessContract.UserActionListener mUserActionListener;

    private boolean mIsExpand = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashbill_success);
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
        mUserActionListener = mCashbillSuccessPresenter;
        mCashbillSuccessPresenter.setView(this);
        mUserActionListener.getData();

        Intent intent = getIntent();
        mTitle = intent.getStringExtra(Constant.TITLE_DETAIL);
//        mTransactionId = intent.getStringExtra(Constant.TRANSACTION_ID_DETAIL);
        mTransactionIdText.setText(mTransactionId);
        mTitleToolbar.setText(mTitle);
    }

    @Override
    public void setAdapter(List<CashbillSuccessData> resultData) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
        mNestedScroll.getParent().requestChildFocus(mNestedScroll, mNestedScroll);

        mListAdapter = new CashbillSuccessAdapter(resultData, this);
        mRecyclerView.setAdapter(mListAdapter);
        OverScrollDecoratorHelper.setUpOverScroll(mRecyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

        hideProgressBar();
    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(CashbillSuccessActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.img_close)
    public void closeScreen(View view) {
        finish();
        Intent i = new Intent(this, DashboardActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @OnClick(R.id.action_expand_collapse)
    public void actionExpandCollapse(View view) {
        if (mIsExpand) {
            Utils.collapse(mLinearExpandCollapse);
            mIsExpand = false;
            mImageIcon.animate().rotation(180).setDuration(500).start();
        } else {
            Utils.expand(mLinearExpandCollapse);
            mIsExpand = true;
            mImageIcon.animate().rotation(0).setDuration(500).start();
        }
    }
}
