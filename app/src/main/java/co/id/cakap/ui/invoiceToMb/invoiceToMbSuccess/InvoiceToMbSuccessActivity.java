package co.id.cakap.ui.invoiceToMb.invoiceToMbSuccess;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import co.id.cakap.adapter.CashbillSuccessAdapter;
import co.id.cakap.adapter.InvoiceToMbSuccessAdapter;
import co.id.cakap.data.CashbillSuccessData;
import co.id.cakap.data.InvoiceToMbSuccessData;
import co.id.cakap.data.SubmitInvoiceToMbData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.cashbill.cashbillSuccess.CashbillSuccessContract;
import co.id.cakap.ui.cashbill.cashbillSuccess.CashbillSuccessPresenter;
import co.id.cakap.ui.dashboard.DashboardActivity;
import co.id.cakap.utils.Utils;
import de.hdodenhof.circleimageview.CircleImageView;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class InvoiceToMbSuccessActivity extends AppCompatActivity implements InvoiceToMbSuccessContract.View {
    private static final String TAG = "DetailTransactionActivity";

    @Inject
    InvoiceToMbSuccessPresenter mInvoiceToMbSuccessPresenter;

    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;
    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.title_toolbar)
    TextView mTitleToolbar;
    @BindView(R.id.txt_transaction_id)
    TextView mTransactionIdText;
    @BindView(R.id.txt_member_id)
    TextView mTxtMemberId;
    @BindView(R.id.txt_name)
    TextView mTxtName;
    @BindView(R.id.nested_scroll)
    NestedScrollView mNestedScroll;
    @BindView(R.id.et_mb_id)
    EditText mMbId;
    @BindView(R.id.linear_expand_collapse)
    LinearLayout mLinearExpandCollapse;
    @BindView(R.id.item_thumbnail)
    ImageView mImageIcon;
    @BindView(R.id.img_close)
    CircleImageView mImgClose;
    @BindView(R.id.linear_remark)
    LinearLayout mLinearRemark;
    @BindView(R.id.et_remark)
    EditText mRemark;
    @BindView(R.id.txt_date)
    TextView mTxtDate;
    @BindView(R.id.txt_total_amount)
    TextView mTxtTotalAmount;

    private String mTitle = "";
    private String mName = "";
    private String mTransactionId = "INV - 123123123123123";
    private SubmitInvoiceToMbData mSubmitInvoiceToMbData;
    private InvoiceToMbSuccessAdapter mListAdapter;
    private InvoiceToMbSuccessContract.UserActionListener mUserActionListener;

    private boolean mIsExpand = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_to_mb_success);
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
        mUserActionListener = mInvoiceToMbSuccessPresenter;
        mInvoiceToMbSuccessPresenter.setView(this);

        showProgressBar();
        Intent intent = getIntent();
        Bundle b = intent.getBundleExtra(Constant.SUCCESS_DATA_OBJECT);

        mTitle = intent.getStringExtra(Constant.TITLE_DETAIL);
        mName = intent.getStringExtra(Constant.NAME);
        mSubmitInvoiceToMbData = b.getParcelable(Constant.SUCCESS_DATA_OBJECT);
        mTitleToolbar.setText(mTitle);
        mTransactionIdText.setText(mSubmitInvoiceToMbData.getInv());
        mTxtMemberId.setText(mSubmitInvoiceToMbData.getMember_id() + " - " + mName);
        mTxtName.setText(mSubmitInvoiceToMbData.getMember_id());
        mTxtDate.setText(mSubmitInvoiceToMbData.getTgl());
        mTxtTotalAmount.setText("IDR " + mSubmitInvoiceToMbData.getTotalharga());
        mRemark.setText(mSubmitInvoiceToMbData.getRemark());
        if (mSubmitInvoiceToMbData.getRemark().equals("0") || mSubmitInvoiceToMbData.getRemark().length() == 0)
            mRemark.setText("-");

        mUserActionListener.getData();
    }

    @Override
    public void setAdapter(List<InvoiceToMbSuccessData> resultData) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
        mNestedScroll.getParent().requestChildFocus(mNestedScroll, mNestedScroll);

        mListAdapter = new InvoiceToMbSuccessAdapter(resultData, this);
        mRecyclerView.setAdapter(mListAdapter);

        hideProgressBar();
    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(InvoiceToMbSuccessActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        mRelativeProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mRelativeProgressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_close)
    public void closeScreen(View view) {
        onBackPressed();
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

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(this, DashboardActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
