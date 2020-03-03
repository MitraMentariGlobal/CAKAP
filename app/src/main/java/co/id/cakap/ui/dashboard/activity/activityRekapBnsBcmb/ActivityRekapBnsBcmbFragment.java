package co.id.cakap.ui.dashboard.activity.activityRekapBnsBcmb;

import android.app.Dialog;
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

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.ActivityRekapBnsBcmbAdapter;
import co.id.cakap.data.ActivityRekapBnsBcmbData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.dashboard.activity.activityInvToMb.ActivityInvToMbContract;
import co.id.cakap.ui.dashboard.activity.activityInvToMb.ActivityInvToMbPresenter;
import co.id.cakap.ui.detailTransaction.DetailTransactionActivity;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.dialog.PinDialog;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class ActivityRekapBnsBcmbFragment extends Fragment implements ActivityRekapBnsBcmbContract.View, AdapterView.OnItemSelectedListener {
    @Inject
    ActivityRekapBnsBcmbPresenter mActivityRekapBnsBcmbPresenter;

    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;
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
    private ActivityRekapBnsBcmbAdapter mListAdapter;
    private ActivityRekapBnsBcmbContract.UserActionListener mUserActionListener;
    private List<String> mYearData = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_activity_rekap_bns_bcmb, container, false);
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
        mUserActionListener = mActivityRekapBnsBcmbPresenter;
        mActivityRekapBnsBcmbPresenter.setView(this);
        initSpinner();
        mUserActionListener.getData(mYearSpinner.getSelectedItem().toString(), mMonthSpinner.getSelectedItem().toString());

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                mUserActionListener.getData(mYearSpinner.getSelectedItem().toString(), mMonthSpinner.getSelectedItem().toString());
            }
        });
    }

    @Override
    public void setAdapter(List<ActivityRekapBnsBcmbData> resultData) {
        mLinearEmptyData.setVisibility(View.GONE);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mListAdapter = new ActivityRekapBnsBcmbAdapter(resultData, getContext());
        mRecyclerView.setAdapter(mListAdapter);
        OverScrollDecoratorHelper.setUpOverScroll(mRecyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

        hideProgressBar();
    }

    @Override
    public void showProgressBar() {
        mRelativeProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mSwipeRefreshLayout.setRefreshing(false);
        mRelativeProgressBar.setVisibility(View.GONE);
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
    public void openDetailTransaction(String transactionId) {
        Intent intent = new Intent(getContext(), DetailTransactionActivity.class);
        intent.putExtra(Constant.TITLE_DETAIL, getContext().getResources().getString(R.string.rekap_bns_bcmb));
        intent.putExtra(Constant.TRANSACTION_ID_DETAIL, transactionId);
        startActivity(intent);
    }

    @Override
    public void openPinDialog(ActivityRekapBnsBcmbData activityRekapBnsBcmbData) {
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

                mActivityRekapBnsBcmbPresenter.actionTransaction(activityRekapBnsBcmbData, pin, mYearSpinner.getSelectedItem().toString(), mMonthSpinner.getSelectedItem().toString());
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
        mUserActionListener.getData(mYearSpinner.getSelectedItem().toString(), mMonthSpinner.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}