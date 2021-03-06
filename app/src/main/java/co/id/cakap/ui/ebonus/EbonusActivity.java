package co.id.cakap.ui.ebonus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.EbonusAdapter;
import co.id.cakap.adapter.ItemSearchEbonusAdapter;
import co.id.cakap.data.EbonusData;
import co.id.cakap.data.ItemEbonusCard;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.network.ApiResponseEbonusMember;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.dialog.SearchDataDialog;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class EbonusActivity extends AppCompatActivity implements EbonusContract.View, AdapterView.OnItemSelectedListener {
    @Inject
    EbonusPresenter mEbonusPresenter;

    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;
    @BindView(R.id.title_toolbar)
    TextView mTitle;
    @BindView(R.id.month_spinner)
    Spinner mMonthSpinner;
    @BindView(R.id.year_spinner)
    Spinner mYearSpinner;
    @BindView(R.id.et_item_code)
    EditText mEtItemCode;
    @BindView(R.id.et_item_name)
    EditText mEtItemName;
    @BindView(R.id.et_price)
    EditText mEtPrice;
    @BindView(R.id.relative_item_code)
    RelativeLayout mRelativeItemCode;
    @BindView(R.id.txt_error_item_code)
    TextView mTxtErrorItemCode;
    @BindView(R.id.nested_scroll)
    NestedScrollView mNestedScroll;
    @BindView(R.id.linear_recyclerView)
    LinearLayout mLinearRecyclerView;
    @BindView(R.id.linear_search_item)
    LinearLayout mLinearSearchItem;
    @BindView(R.id.txt_total_in)
    TextView mTxtTotalIn;
    @BindView(R.id.txt_total_out)
    TextView mTxtTotalOut;
    @BindView(R.id.txt_title_saldo_awal)
    TextView mTxtTitleSaldoAwal;
    @BindView(R.id.txt_colon_saldo_awal)
    TextView mTxtColonSaldoAwal;
    @BindView(R.id.txt_saldo_awal)
    TextView mTxtSaldoAwal;
    @BindView(R.id.txt_title_saldo_akhir)
    TextView mTxtTitleSaldoAkhir;
    @BindView(R.id.txt_colon_saldo_akhir)
    TextView mTxtColonSaldoAkhir;
    @BindView(R.id.txt_saldo_akhir)
    TextView mTxtSaldoAkhir;
    @BindView(R.id.linear_empty_data)
    LinearLayout mLinearEmptyData;
    @BindView(R.id.swiperefresh_items)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private RecyclerView mRecyclerViewSearch;
    private EditText mSearchEditText;
    private Dialog mDialog;
    private EbonusAdapter mListAdapter;
    private ItemSearchEbonusAdapter mListSearchAdapter;
    private EbonusContract.UserActionListener mUserActionListener;
    private List<String> mYearData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebonus);
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
        mUserActionListener = mEbonusPresenter;
        mEbonusPresenter.setView(this);
        mTitle.setText(getString(R.string.ebonus).toUpperCase());
        mLinearSearchItem.setVisibility(View.GONE);
        mTxtTitleSaldoAwal.setVisibility(View.GONE);
        mTxtColonSaldoAwal.setVisibility(View.GONE);
        mTxtSaldoAwal.setVisibility(View.GONE);
        mTxtTitleSaldoAkhir.setVisibility(View.GONE);
        mTxtColonSaldoAkhir.setVisibility(View.GONE);
        mTxtSaldoAkhir.setVisibility(View.GONE);

        initSpinner();
        mUserActionListener.getData(mYearSpinner.getSelectedItem().toString(), mMonthSpinner.getSelectedItem().toString());

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mUserActionListener.getData(mYearSpinner.getSelectedItem().toString(), mMonthSpinner.getSelectedItem().toString());
            }
        });
    }

    @Override
    public void setAdapter(ApiResponseEbonusMember apiResponseEbonusMember) {
        if (apiResponseEbonusMember.getData().isEmpty()) {
            mLinearEmptyData.setVisibility(View.VISIBLE);
        } else {
            mLinearEmptyData.setVisibility(View.GONE);
        }

        mLinearRecyclerView.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
        mNestedScroll.getParent().requestChildFocus(mNestedScroll, mNestedScroll);
        mListAdapter = new EbonusAdapter(apiResponseEbonusMember.getData(), this);
        mRecyclerView.setAdapter(mListAdapter);

        mTxtTotalIn.setText(apiResponseEbonusMember.getTotal_kredit());
        mTxtTotalOut.setText(apiResponseEbonusMember.getTotal_debet());
        hideProgressBar();
    }

    @OnClick(R.id.linear_search_item_code)
    public void searchRecId(View view) {
        if (mEtItemCode.getText().length() < 3) {
            mRelativeItemCode.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
            mTxtErrorItemCode.setVisibility(View.VISIBLE);
        } else {
            mRelativeItemCode.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
            mTxtErrorItemCode.setVisibility(View.GONE);
            mUserActionListener.getItemProduct(mEtItemCode.getText().toString());
            mEtItemCode.setInputType(0);
        }
    }

    @Override
    public void showProgressBar() {
        mRelativeProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        try {
            mSwipeRefreshLayout.setRefreshing(false);
            mRelativeProgressBar.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(EbonusActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openDialogSearchData(List<ItemEbonusCard> resultData) {
        SearchDataDialog utils = new SearchDataDialog();
        mDialog = utils.showDialog(this);

        mSearchEditText = mDialog.findViewById(R.id.et_search);
        mRecyclerViewSearch = mDialog.findViewById(R.id.main_list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewSearch.setLayoutManager(layoutManager);
        mListSearchAdapter = new ItemSearchEbonusAdapter(resultData, this);
        mRecyclerViewSearch.setAdapter(mListSearchAdapter);
        OverScrollDecoratorHelper.setUpOverScroll(mRecyclerViewSearch, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
        setupOnFocusListener(mSearchEditText);
    }

    @Override
    public void hideDialogSearchData(ItemEbonusCard itemEbonusCard) {
        mDialog.dismiss();

        mEtItemCode.setText(itemEbonusCard.getItem_code());
        mEtItemName.setText(itemEbonusCard.getItem_name());
        mEtPrice.setText(itemEbonusCard.getAmount());

//        mUserActionListener.getData();
    }

    public void initSpinner() {
        mMonthSpinner.setOnItemSelectedListener(this);
        mYearSpinner.setOnItemSelectedListener(this);
        Calendar calendar = Calendar.getInstance();
        String month = new SimpleDateFormat("MMMM").format(calendar.getTime());

        ArrayAdapter<CharSequence> monthAdapter = ArrayAdapter.createFromResource(this,
                R.array.month_array, R.layout.item_spinner);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mMonthSpinner.setAdapter(monthAdapter);

        for (int i = 0; i < mMonthSpinner.getAdapter().getCount(); i++) {
            if(mMonthSpinner.getAdapter().getItem(i).toString().contains(month)) {
                mMonthSpinner.setSelection(i);
            }
        }

        mYearData.add(String.valueOf(getResources().getInteger(R.integer.minimum_year)));
        int minimumYear = getResources().getInteger(R.integer.minimum_year);
        int now = calendar.get(Calendar.YEAR);
        int totalLoop = now - minimumYear;
        int year = minimumYear;

        for (int i = 1; i <= totalLoop; i++) {
            year = year + 1;
            mYearData.add(String.valueOf(year));
        }

        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this,
                R.layout.item_spinner, android.R.id.text1, mYearData);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mYearSpinner.setAdapter(yearAdapter);

        for (int i = 0; i < mYearSpinner.getAdapter().getCount(); i++) {
            if(mYearSpinner.getAdapter().getItem(i).toString().contains(String.valueOf(now))) {
                mYearSpinner.setSelection(i);
            }
        }
    }

    private void setupOnFocusListener(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                Logger.d("arg0 : " + arg0);
                String text = editText.getText().toString().toLowerCase(Locale.getDefault());
                if (mListSearchAdapter != null) mListSearchAdapter.getFilter().filter(text);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mUserActionListener.getData(mYearSpinner.getSelectedItem().toString(), mMonthSpinner.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @OnClick(R.id.arrow_back)
    public void arrowBack(View view) {
        super.onBackPressed();
    }
}
