package co.id.cakap.ui.dashboard.restock.restockInvoice;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.RestockInvoiceAdapter;
import co.id.cakap.data.RestockInvoiceData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillContract;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillPresenter;
import co.id.cakap.ui.detailTransaction.DetailTransactionActivity;
import co.id.cakap.utils.Logger;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class RestockInvoiceFragment extends Fragment implements RestockInvoiceContract.View, DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener  {
    @Inject
    RestockInvoicePresenter mRestockInvoicePresenter;

    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.month_spinner)
    Spinner mMonthSpinner;
    @BindView(R.id.year_spinner)
    Spinner mYearSpinner;
    @BindView(R.id.linear_empty_data)
    LinearLayout mLinearEmptyData;
    @BindView(R.id.swiperefresh_items)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private View mView;
    private Unbinder mUnbinder;
    private RestockInvoiceAdapter mListAdapter;
    private RestockInvoiceContract.UserActionListener mUserActionListener;
    private List<String> mYearData = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_restock_invoice, container, false);
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
        mUserActionListener = mRestockInvoicePresenter;
        mRestockInvoicePresenter.setView(this);

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
    public void setAdapter(List<RestockInvoiceData> resultData) {
        if (resultData.isEmpty()) {
            mLinearEmptyData.setVisibility(View.VISIBLE);
        } else {
            mLinearEmptyData.setVisibility(View.GONE);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mListAdapter = new RestockInvoiceAdapter(resultData, getContext());
        mRecyclerView.setAdapter(mListAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                if (dy<0 && !mFab.isShown())
                    mFab.show();
                else if(dy>0 && mFab.isShown())
                    mFab.hide();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        hideProgressBar();
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
        try {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openDetailTransaction(RestockInvoiceData restockInvoiceData) {
        Intent intent = new Intent(getContext(), DetailTransactionActivity.class);
        intent.putExtra(Constant.TITLE_DETAIL, getContext().getResources().getString(R.string.invoice));
        intent.putExtra(Constant.URL_LINK_DETAIL, Constant.END_URL_DETAIL_INVOICE);
        intent.putExtra(Constant.ITEM_ID_DETAIL, restockInvoiceData.getItem_id());
        intent.putExtra(Constant.TRANSACTION_ID_DETAIL, restockInvoiceData.getTransaction_id());
        intent.putExtra(Constant.MEMBER_ID_DETAIL, restockInvoiceData.getNo_stc());
        intent.putExtra(Constant.NAME_DETAIL, restockInvoiceData.getNama());
        intent.putExtra(Constant.DATE_DETAIL, restockInvoiceData.getDate());
        intent.putExtra(Constant.TOTAL_DETAIL, restockInvoiceData.getTotal_amount());
        intent.putExtra(Constant.TOTAL_PV_DETAIL, restockInvoiceData.getTotal_pv());
        intent.putExtra(Constant.REMARK_DETAIL, restockInvoiceData.getRemarkapp());

        startActivity(intent);
    }

    @OnClick(R.id.fab)
    public void floatingAction(View view) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = com.borax12.materialdaterangepicker.date.DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setAutoHighlight(true);
        dpd.setAccentColor(getContext().getResources().getColor(R.color.colorPrimaryDark));
        dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {
        String date = "You picked the following date: From- "+dayOfMonth+"/"+(++monthOfYear)+"/"+year+" To "+dayOfMonthEnd+"/"+(++monthOfYearEnd)+"/"+yearEnd;
        Logger.d(date);
    }

    public void initSpinner() {
        mMonthSpinner.setOnItemSelectedListener(this);
        mYearSpinner.setOnItemSelectedListener(this);
        Calendar calendar = Calendar.getInstance();
        String month = new SimpleDateFormat("MMMM").format(calendar.getTime());

        ArrayAdapter<CharSequence> monthAdapter = ArrayAdapter.createFromResource(getActivity(),
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

        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(getActivity(),
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
        mUserActionListener.getData(mYearSpinner.getSelectedItem().toString(), mMonthSpinner.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}