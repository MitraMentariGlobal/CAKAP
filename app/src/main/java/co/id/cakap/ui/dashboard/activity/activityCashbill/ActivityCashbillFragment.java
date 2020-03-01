package co.id.cakap.ui.dashboard.activity.activityCashbill;

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

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.gordonwong.materialsheetfab.MaterialSheetFab;
import com.gordonwong.materialsheetfab.MaterialSheetFabEventListener;

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
import co.id.cakap.adapter.ActivityCashbillAdapter;
import co.id.cakap.data.ActivityCashbillData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.dashboard.account.AccountContract;
import co.id.cakap.ui.dashboard.account.AccountPresenter;
import co.id.cakap.ui.detailTransaction.DetailTransactionActivity;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.dialog.PinDialog;
import co.id.cakap.utils.widget.Fab;
import me.everything.android.ui.overscroll.HorizontalOverScrollBounceEffectDecorator;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import me.everything.android.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter;

public class ActivityCashbillFragment extends Fragment implements ActivityCashbillContract.View, DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener  {
    @Inject
    ActivityCashbillPresenter mActivityCashbillPresenter;

    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.et_search)
    EditText mSearchEditText;
    @BindView(R.id.fabs)
    Fab mFabs;
    @BindView(R.id.fab_sheet)
    View sheetView;
    @BindView(R.id.overlay)
    View overlay;
    @BindView(R.id.month_spinner)
    Spinner mMonthSpinner;
    @BindView(R.id.year_spinner)
    Spinner mYearSpinner;
    @BindView(R.id.include_spinner_filter)
    View mIncludeSpinnerLayout;
    @BindView(R.id.swiperefresh_items)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private View mView;
    private Unbinder mUnbinder;
    private MaterialSheetFab materialSheetFab;
    private ActivityCashbillAdapter mListAdapter;
    private ActivityCashbillContract.UserActionListener mUserActionListener;
    private List<String> mYearData = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_activity_cashbill, container, false);
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
        mUserActionListener = mActivityCashbillPresenter;
        mActivityCashbillPresenter.setView(this);

        initSpinner();
        mUserActionListener.getData(getContext(), mYearSpinner.getSelectedItem().toString(), mMonthSpinner.getSelectedItem().toString());

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                mUserActionListener.getData(getContext(), mYearSpinner.getSelectedItem().toString(), mMonthSpinner.getSelectedItem().toString());
            }
        });
    }

    @Override
    public void setAdapter(List<ActivityCashbillData> resultData) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mListAdapter = new ActivityCashbillAdapter(resultData, getContext());
        mRecyclerView.setAdapter(mListAdapter);
        OverScrollDecoratorHelper.setUpOverScroll(mRecyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                if (dy<0 && !mFab.isShown()) {
                    mFab.show();
                } else if(dy>0 && mFab.isShown()) {
                    mFab.hide();
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        setupOnFocusListener(mSearchEditText);
        setupFab();

        mSwipeRefreshLayout.setRefreshing(false);
        hideProgressBar();
    }

    @Override
    public void showProgressBar() {
        mRelativeProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mRelativeProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openDetailTransaction(ActivityCashbillData activityCashbillData) {
        Intent intent = new Intent(getContext(), DetailTransactionActivity.class);
        intent.putExtra(Constant.TITLE_DETAIL, getContext().getResources().getString(R.string.cashbill));
        intent.putExtra(Constant.URL_LINK_DETAIL, Constant.END_URL_DETAIL_CASHBILL);
        intent.putExtra(Constant.ITEM_ID_DETAIL, activityCashbillData.getItem_id());
        intent.putExtra(Constant.TRANSACTION_ID_DETAIL, activityCashbillData.getTransaction_id());
        intent.putExtra(Constant.MEMBER_ID_DETAIL, activityCashbillData.getMember_id());
        intent.putExtra(Constant.NAME_DETAIL, activityCashbillData.getName());
        intent.putExtra(Constant.DATE_DETAIL, activityCashbillData.getDate());
        intent.putExtra(Constant.TOTAL_DETAIL, activityCashbillData.getTotal_amount());
        intent.putExtra(Constant.TOTAL_PV_DETAIL, activityCashbillData.getTotal_pv());
        intent.putExtra(Constant.REMARK_DETAIL, activityCashbillData.getRemark());
        startActivity(intent);
    }

    @Override
    public void openPinDialog(ActivityCashbillData activityCashbillData) {
        PinDialog utils = new PinDialog();
        Dialog dialog = utils.showDialog(getContext());

        PinLockView pinLockView = dialog.findViewById(R.id.pin_lock_view);
        IndicatorDots indicatorDots = dialog.findViewById(R.id.indicator_dots);
        PinLockListener pinLockListener = new PinLockListener() {
            @Override
            public void onComplete(String pin) {
                Logger.d("Pin complete: " + pin);
                dialog.hide();
                dialog.dismiss();

                mActivityCashbillPresenter.cancelTransaction(activityCashbillData, pin, getContext(), mYearSpinner.getSelectedItem().toString(), mMonthSpinner.getSelectedItem().toString());
            }

            @Override
            public void onEmpty() {
                Logger.d("Pin empty");
            }

            @Override
            public void onPinChange(int pinLength, String intermediatePin) {
                Logger.d("Pin changed, new length " + pinLength + " with intermediate pin " + intermediatePin);
            }
        };

        pinLockView.attachIndicatorDots(indicatorDots);
        pinLockView.setPinLockListener(pinLockListener);

        pinLockView.setPinLength(6);
        pinLockView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        indicatorDots.setIndicatorType(IndicatorDots.IndicatorType.FILL_WITH_ANIMATION);
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

    private void setupFab() {
        int sheetColor = getResources().getColor(R.color.white);
        int fabColor = getResources().getColor(R.color.colorPrimaryDark);

        // Create material sheet FAB
        materialSheetFab = new MaterialSheetFab<>(mFabs, sheetView, overlay, sheetColor, fabColor);

        // Set material sheet event listener
        materialSheetFab.setEventListener(new MaterialSheetFabEventListener() {
            @Override
            public void onShowSheet() {
                // Save current status bar color
            }

            @Override
            public void onHideSheet() {
                // Restore status bar color
            }
        });
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
        int now = calendar.get(Calendar.YEAR);
        int totalLoop = now - getResources().getInteger(R.integer.minimum_year);

        for (int i = 0; i < totalLoop; i++) {
            mYearData.add(String.valueOf(now));
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
        mUserActionListener.getData(getContext(), mYearSpinner.getSelectedItem().toString(), mMonthSpinner.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}