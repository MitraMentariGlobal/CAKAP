package co.id.cakap.ui.stockReport.stockCard;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import co.id.cakap.adapter.ItemSearchStockCardAdapter;
import co.id.cakap.adapter.StockCardAdapter;
import co.id.cakap.data.ItemStockCard;
import co.id.cakap.data.StockCardData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.dialog.SearchDataDialog;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class StockCardFragment extends Fragment implements StockCardContract.View, AdapterView.OnItemSelectedListener {
    @Inject
    StockCardPresenter mStockCardPresenter;

    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.main_progress_bar)
    ProgressBar mProgressBar;
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

    private View mView;
    private Unbinder mUnbinder;
    private RecyclerView mRecyclerViewSearch;
    private EditText mSearchEditText;
    private Dialog mDialog;
    private StockCardAdapter mListAdapter;
    private ItemSearchStockCardAdapter mListSearchAdapter;
    private StockCardContract.UserActionListener mUserActionListener;
    private List<String> mYearData = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_stock_card, container, false);
            mUnbinder = ButterKnife.bind(this, mView);

            setupActivityComponent();
            initializeData();
        }

        ButterKnife.bind(this, mView);
        return mView;
    }

    private void setupActivityComponent() {
        CoreApp.get()
                .getAppComponent()
                .plus(new MainActivityModule(this))
                .inject(this);
    }

    @Override
    public void initializeData() {
        mUserActionListener = mStockCardPresenter;
        mStockCardPresenter.setView(this);

        initSpinner();
        hideProgressBar();
    }

    @Override
    public void setAdapter(List<StockCardData> resultData) {
        mLinearRecyclerView.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
        mNestedScroll.getParent().requestChildFocus(mNestedScroll, mNestedScroll);
        mListAdapter = new StockCardAdapter(resultData, getContext());
        mRecyclerView.setAdapter(mListAdapter);
        OverScrollDecoratorHelper.setUpOverScroll(mRecyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
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
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openDialogSearchData(List<ItemStockCard> resultData) {
        SearchDataDialog utils = new SearchDataDialog();
        mDialog = utils.showDialog(getContext());

        mSearchEditText = mDialog.findViewById(R.id.et_search);
        mRecyclerViewSearch = mDialog.findViewById(R.id.main_list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerViewSearch.setLayoutManager(layoutManager);
        mListSearchAdapter = new ItemSearchStockCardAdapter(resultData, getContext());
        mRecyclerViewSearch.setAdapter(mListSearchAdapter);
        OverScrollDecoratorHelper.setUpOverScroll(mRecyclerViewSearch, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
        setupOnFocusListener(mSearchEditText);
    }

    @Override
    public void hideDialogSearchData(ItemStockCard stockCardData) {
        mDialog.dismiss();

        mEtItemCode.setText(stockCardData.getItem_code());
        mEtItemName.setText(stockCardData.getItem_name());
        mEtPrice.setText(stockCardData.getAmount());

        mUserActionListener.getData();
    }

    public void initSpinner() {
        mMonthSpinner.setOnItemSelectedListener(this);
        mYearSpinner.setOnItemSelectedListener(this);
        Calendar calendar = Calendar.getInstance();
        String month = new SimpleDateFormat("MMMM").format(calendar.getTime());

        ArrayAdapter<CharSequence> monthAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.month_array, R.layout.item_spinner);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mMonthSpinner.setAdapter(monthAdapter);

        for (int i = 0; i < mMonthSpinner.getAdapter().getCount(); i++) {
            if(mMonthSpinner.getAdapter().getItem(i).toString().contains(month)) {
                mMonthSpinner.setSelection(i);
            }
        }

        mYearData.add(String.valueOf(getResources().getInteger(R.integer.minimum_year)));
        int now = calendar.get(Calendar.YEAR);
        int totalLoop = now - getResources().getInteger(R.integer.minimum_year);

        for (int i = 0; i < totalLoop; i++) {
            mYearData.add(String.valueOf(now));
        }

        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(getContext(),
                R.layout.item_spinner, android.R.id.text1, mYearData);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mYearSpinner.setAdapter(yearAdapter);

        for (int i = 0; i < mYearSpinner.getAdapter().getCount(); i++) {
            if(mYearSpinner.getAdapter().getItem(i).toString().contains(String.valueOf(now))) {
                mYearSpinner.setSelection(i);
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
}