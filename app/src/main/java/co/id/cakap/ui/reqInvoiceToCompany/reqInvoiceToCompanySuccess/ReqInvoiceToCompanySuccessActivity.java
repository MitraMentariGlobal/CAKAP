package co.id.cakap.ui.reqInvoiceToCompany.reqInvoiceToCompanySuccess;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import co.id.cakap.adapter.ReqInvoiceToBcSuccessAdapter;
import co.id.cakap.data.ReqInvoiceToBcSuccessData;
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

    @BindView(R.id.main_progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.title_toolbar)
    TextView mTitleToolbar;
    @BindView(R.id.nested_scroll)
    NestedScrollView mNestedScroll;
    @BindView(R.id.txt_payment_method)
    TextView mTxtPaymentMethod;
    @BindView(R.id.txt_total_transaction)
    TextView mTxtTotalTransaction;
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

    private String mTitle = "";
    private String mPaymentMethod = "";
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
        mUserActionListener.getData();

        Intent intent = getIntent();
        mTitle = intent.getStringExtra(Constant.TITLE_DETAIL);
        mPaymentMethod = intent.getStringExtra(Constant.PAYMENT_METHOD);
        mTitleToolbar.setText(mTitle);
        mNestedScroll.getParent().requestChildFocus(mNestedScroll, mNestedScroll);

        mTxtPaymentMethod.setText(getResources().getString(R.string.payment_method_string, mPaymentMethod));
        if (mPaymentMethod.equals(getResources().getString(R.string.ewallet_item))) {
            mLinearMustTransfer.setVisibility(View.GONE);
            mLinearRekening.setVisibility(View.GONE);
        }

        hideProgressBar();
    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(ReqInvoiceToCompanySuccessActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.txt_copy_nominal)
    public void actionCopyNominal(View view) {
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
