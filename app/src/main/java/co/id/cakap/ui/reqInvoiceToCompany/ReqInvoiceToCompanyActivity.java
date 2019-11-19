package co.id.cakap.ui.reqInvoiceToCompany;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
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
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.reqInvoiceToCompany.reqInvoiceToCompanySuccess.ReqInvoiceToCompanySuccessActivity;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import co.id.cakap.utils.dialog.PinDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class ReqInvoiceToCompanyActivity extends AppCompatActivity implements ReqInvoiceToCompanyActivityContract.View, AdapterView.OnItemSelectedListener {
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
    @BindView(R.id.payment_method_spinner)
    Spinner mPaymentMethodSpinner;
    @BindView(R.id.linear_ewallet)
    LinearLayout mLinearEwallet;

    private ItemShopReqInvToCompanyAdapter mListAdapter;
    private GridLayoutManager mGridLayoutManager;
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
        initSpinner();
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
        mGridLayoutManager = new GridLayoutManager(this, 2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
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
        Intent intent = new Intent(getApplicationContext(), ReqInvoiceToCompanySuccessActivity.class);
        intent.putExtra(Constant.TITLE_DETAIL, getResources().getString(R.string.req_invoice_to_com).toUpperCase());
        intent.putExtra(Constant.PAYMENT_METHOD, mPaymentMethodSpinner.getSelectedItem().toString());
        startActivity(intent);
    }

    public void initSpinner() {
        mPaymentMethodSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> monthAdapter = ArrayAdapter.createFromResource(this,
                R.array.payment_method_list, R.layout.item_spinner);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPaymentMethodSpinner.setAdapter(monthAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.payment_method_spinner:
                if (position == 0) {
                    mLinearEwallet.setVisibility(View.VISIBLE);
                } else {
                    mLinearEwallet.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
