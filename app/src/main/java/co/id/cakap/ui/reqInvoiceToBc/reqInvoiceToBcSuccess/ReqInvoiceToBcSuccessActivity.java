package co.id.cakap.ui.reqInvoiceToBc.reqInvoiceToBcSuccess;

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
import co.id.cakap.adapter.ReqInvoiceToBcSuccessAdapter;
import co.id.cakap.data.CashbillSuccessData;
import co.id.cakap.data.ReqInvoiceToBcSuccessData;
import co.id.cakap.data.SubmitInvoiceToBcData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.cashbill.cashbillSuccess.CashbillSuccessContract;
import co.id.cakap.ui.cashbill.cashbillSuccess.CashbillSuccessPresenter;
import co.id.cakap.ui.dashboard.DashboardActivity;
import co.id.cakap.utils.Utils;
import de.hdodenhof.circleimageview.CircleImageView;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class ReqInvoiceToBcSuccessActivity extends AppCompatActivity implements ReqInvoiceToBcSuccessContract.View {
    private static final String TAG = "DetailTransactionActivity";

    @Inject
    ReqInvoiceToBcSuccessPresenter mReqInvoiceToBcSuccessPresenter;

    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;
    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.title_toolbar)
    TextView mTitleToolbar;
    @BindView(R.id.txt_transaction_id)
    TextView mTransactionIdText;
    @BindView(R.id.nested_scroll)
    NestedScrollView mNestedScroll;
    @BindView(R.id.user_id_name)
    TextView mUserIdName;
    @BindView(R.id.txt_total_transaction)
    TextView mTxtTotalTransaction;
    @BindView(R.id.txt_copy)
    TextView mTxtCopy;
    @BindView(R.id.img_close)
    CircleImageView mImgClose;
    @BindView(R.id.txt_member_id)
    TextView mTxtMemberId;
    @BindView(R.id.txt_date)
    TextView mTxtDate;
    @BindView(R.id.txt_total_amount)
    TextView mTxtTotalAmount;

    private String mTitle = "";
    private String mInfo = "";
    private String mTransactionId = "INV - 123123123123123";
    private SubmitInvoiceToBcData mSubmitInvoiceToBcData;
    private ReqInvoiceToBcSuccessAdapter mListAdapter;
    private ReqInvoiceToBcSuccessContract.UserActionListener mUserActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_invoice_to_bc_success);
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
        mUserActionListener = mReqInvoiceToBcSuccessPresenter;
        mReqInvoiceToBcSuccessPresenter.setView(this);

        Intent intent = getIntent();
        Bundle b = intent.getBundleExtra(Constant.SUCCESS_DATA_OBJECT);

        mTitle = intent.getStringExtra(Constant.TITLE_DETAIL);
        mInfo = intent.getStringExtra(Constant.PAYMENT_INFO);
        mSubmitInvoiceToBcData = b.getParcelable(Constant.SUCCESS_DATA_OBJECT);
        mTitleToolbar.setText(mTitle);

//        mTransactionIdText.setText(mSubmitInvoiceToBcData.getInv());
        mTransactionIdText.setText(mSubmitInvoiceToBcData.getNo_stc() + " - " + mSubmitInvoiceToBcData.getNamastc());
        mTxtMemberId.setText(mSubmitInvoiceToBcData.getNo_stc() + " - " + mSubmitInvoiceToBcData.getNamastc());
        mTxtDate.setText(mSubmitInvoiceToBcData.getTgl());
        mTxtTotalAmount.setText("IDR " + mSubmitInvoiceToBcData.getTotalharga());
//        mRemark.setText(mSubmitInvoiceToBcData.getRemark());
//        if (mSubmitInvoiceToBcData.getRemark().equals("0") || mSubmitInvoiceToBcData.getRemark().length() == 0)
//            mRemark.setText("-");
//        mTransactionId = intent.getStringExtra(Constant.TRANSACTION_ID_DETAIL);
        mTransactionIdText.setText(mTransactionId);
//        mUserIdName.setText(getResources().getString(R.string.please_confirm_bc, "BC014", "YANTI PURWANTI"));
        mUserIdName.setText(mInfo);

        mUserActionListener.getData();
    }

    @Override
    public void setAdapter(List<ReqInvoiceToBcSuccessData> resultData) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
        mNestedScroll.getParent().requestChildFocus(mNestedScroll, mNestedScroll);

        mListAdapter = new ReqInvoiceToBcSuccessAdapter(resultData, this);
        mRecyclerView.setAdapter(mListAdapter);

        hideProgressBar();
    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(ReqInvoiceToBcSuccessActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        mRelativeProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mRelativeProgressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.txt_copy)
    public void actionCopy(View view) {
        Toast.makeText(ReqInvoiceToBcSuccessActivity.this, "Copied!", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_close)
    public void closeScreen(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(this, DashboardActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
