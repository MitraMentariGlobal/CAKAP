package co.id.cakap.ui.reqInvoiceToCompany;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.ItemShopCompanyAdapter;
import co.id.cakap.data.ItemShopCompanyData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
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

    private ItemShopCompanyAdapter mListAdapter;
    private ReqInvoiceToCompanyActivityContract.UserActionListener mUserActionListener;
    private boolean mIsExpand = true;

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
        mLinearSearch.setVisibility(View.GONE);
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
        mListAdapter = new ItemShopCompanyAdapter(resultData, this);
        mRecyclerView.setAdapter(mListAdapter);
        OverScrollDecoratorHelper.setUpOverScroll(mRecyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

        setupOnFocusListener(mSearchEditText);
        hideProgressBar();
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
}
