package co.id.cakap.ui.reqInvoiceToCompany.reqInvoiceToCompanySuccess;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
import co.id.cakap.adapter.BankInfoInvoiceCompanyAdapter;
import co.id.cakap.adapter.ReqInvoiceToBcSuccessAdapter;
import co.id.cakap.adapter.ReqInvoiceToCompanySuccessAdapter;
import co.id.cakap.data.BankInfoData;
import co.id.cakap.data.ReqInvoiceToBcSuccessData;
import co.id.cakap.data.ReqInvoiceToCompanySuccessData;
import co.id.cakap.data.SubmitInvoiceToCompanyData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.dashboard.DashboardActivity;
import co.id.cakap.ui.reqInvoiceToBc.reqInvoiceToBcSuccess.ReqInvoiceToBcSuccessContract;
import co.id.cakap.ui.reqInvoiceToBc.reqInvoiceToBcSuccess.ReqInvoiceToBcSuccessPresenter;
import de.hdodenhof.circleimageview.CircleImageView;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class ReqInvoiceToCompanySuccessActivity extends AppCompatActivity implements ReqInvoiceToCompanySuccessContract.View {
    private static final String TAG = "DetailTransactionActivity";

    @Inject
    ReqInvoiceToCompanySuccessPresenter mReqInvoiceToCompanySuccessPresenter;

    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;
    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.recycler_transfer)
    RecyclerView mRecyclerTransfer;
    @BindView(R.id.title_toolbar)
    TextView mTitleToolbar;
    @BindView(R.id.txt_transaction_id)
    TextView mTxtxTransactionId;
    @BindView(R.id.txt_date)
    TextView mTxtDate;
    @BindView(R.id.nested_scroll)
    NestedScrollView mNestedScroll;
    @BindView(R.id.txt_payment_method)
    TextView mTxtPaymentMethod;
    @BindView(R.id.txt_total_transaction)
    TextView mTxtTotalTransaction;
    @BindView(R.id.txt_total_amount)
    TextView mTxtTotalAmount;
    @BindView(R.id.txt_copy_nominal)
    TextView mTxtCopyNominal;
    @BindView(R.id.txt_copy_rekening_1)
    TextView mTxtCopyRekening1;
    @BindView(R.id.txt_copy_rekening_2)
    TextView mTxtCopyRekening2;
    @BindView(R.id.img_close)
    CircleImageView mImgClose;
    @BindView(R.id.linear_must_transfer)
    LinearLayout mLinearMustTransfer;
    @BindView(R.id.linear_rekening)
    LinearLayout mLinearRekening;
    @BindView(R.id.linear_remark)
    LinearLayout mLinearRemark;
    @BindView(R.id.et_remark)
    EditText mRemark;
    @BindView(R.id.txt_info)
    TextView mTxtInfo;

    private String mTitle = "";
    private String mPaymentMethod = "";
    private String mPaymentInfo = "";
    private SubmitInvoiceToCompanyData mSubmitInvoiceToCompanyData;
    private BankInfoInvoiceCompanyAdapter mBankInfoInvoiceCompanyAdapter;
    private ReqInvoiceToCompanySuccessAdapter mListAdapter;
    private ReqInvoiceToCompanySuccessContract.UserActionListener mUserActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_invoice_to_company_success);
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
        mUserActionListener = mReqInvoiceToCompanySuccessPresenter;
        mReqInvoiceToCompanySuccessPresenter.setView(this);

        showProgressBar();
        Intent intent = getIntent();
        Bundle b = intent.getBundleExtra(Constant.SUCCESS_DATA_OBJECT);

        mTitle = intent.getStringExtra(Constant.TITLE_DETAIL);
        mPaymentMethod = intent.getStringExtra(Constant.PAYMENT_METHOD);
        mPaymentInfo = intent.getStringExtra(Constant.PAYMENT_INFO);
        mSubmitInvoiceToCompanyData = b.getParcelable(Constant.SUCCESS_DATA_OBJECT);

        mTitleToolbar.setText(mTitle);
        mTxtInfo.setText(mPaymentInfo);
        mTxtxTransactionId.setText(mSubmitInvoiceToCompanyData.getTgl());
        mTxtDate.setText(mSubmitInvoiceToCompanyData.getTgl());
        mTxtTotalTransaction.setText("IDR " + mSubmitInvoiceToCompanyData.getTotalharga());
        mTxtTotalAmount.setText("IDR " + mSubmitInvoiceToCompanyData.getTotalharga());
        mRemark.setText(mSubmitInvoiceToCompanyData.getRemark());
        if (mSubmitInvoiceToCompanyData.getRemark().equals("0") || mSubmitInvoiceToCompanyData.getRemark().length() == 0)
            mRemark.setText("-");

        mNestedScroll.getParent().requestChildFocus(mNestedScroll, mNestedScroll);

        mTxtPaymentMethod.setText(getResources().getString(R.string.payment_method_string, mPaymentMethod));
        if (mPaymentMethod.equals(getResources().getString(R.string.ewallet_item))) {
            mLinearMustTransfer.setVisibility(View.GONE);
            mLinearRekening.setVisibility(View.GONE);
        }

        mUserActionListener.getData();
    }

    @Override
    public void setAdapter(List<ReqInvoiceToCompanySuccessData> resultData, List<BankInfoData> bankInfoDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
        mNestedScroll.getParent().requestChildFocus(mNestedScroll, mNestedScroll);

        mListAdapter = new ReqInvoiceToCompanySuccessAdapter(resultData, this);
        mRecyclerView.setAdapter(mListAdapter);

        setAdapterBank(bankInfoDataList);
    }

    public void setAdapterBank(List<BankInfoData> bankInfoDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerTransfer.setLayoutManager(layoutManager);
        mRecyclerTransfer.setNestedScrollingEnabled(false);
        mNestedScroll.getParent().requestChildFocus(mNestedScroll, mNestedScroll);

        mBankInfoInvoiceCompanyAdapter = new BankInfoInvoiceCompanyAdapter(bankInfoDataList, this);
        mRecyclerTransfer.setAdapter(mBankInfoInvoiceCompanyAdapter);
        OverScrollDecoratorHelper.setUpOverScroll(mRecyclerTransfer, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

        hideProgressBar();
    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(ReqInvoiceToCompanySuccessActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        mRelativeProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mRelativeProgressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.txt_copy_nominal)
    public void actionCopyNominal(View view) {
        String amount = mSubmitInvoiceToCompanyData.getTotalharga();
        if (amount.contains(",")) {
            amount = amount.replace(",", "");
        } else if (amount.contains(".")) {
            amount = amount.replace(".", "");
        }

        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label", amount);
        clipboard.setPrimaryClip(clip);

        Toast.makeText(ReqInvoiceToCompanySuccessActivity.this, "Copied!", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.txt_copy_rekening_1)
    public void actionCopyRekening1(View view) {
        Toast.makeText(ReqInvoiceToCompanySuccessActivity.this, "Copied!", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.txt_copy_rekening_2)
    public void actionCopyRekening2(View view) {
        Toast.makeText(ReqInvoiceToCompanySuccessActivity.this, "Copied!", Toast.LENGTH_SHORT).show();
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
