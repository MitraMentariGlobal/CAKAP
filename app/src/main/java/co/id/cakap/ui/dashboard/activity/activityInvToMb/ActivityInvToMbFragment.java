package co.id.cakap.ui.dashboard.activity.activityInvToMb;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
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
import co.id.cakap.adapter.ActivityInvToMbAdapter;
import co.id.cakap.data.ActivityInvToMbData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.createActivationForm.CreateActivationFormActivity;
import co.id.cakap.ui.dashboard.DashboardActivity;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillContract;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillPresenter;
import co.id.cakap.ui.detailTransaction.DetailTransactionActivity;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.dialog.PinDialog;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class ActivityInvToMbFragment extends Fragment implements ActivityInvToMbContract.View, DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener  {
    @Inject
    ActivityInvToMbPresenter mActivityInvToMbPresenter;

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

    private View mView;
    private Unbinder mUnbinder;
    private ActivityInvToMbAdapter mListAdapter;
    private ActivityInvToMbContract.UserActionListener mUserActionListener;
    private List<String> mYearData = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_activity_inv_to_mb, container, false);
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
        mUserActionListener = mActivityInvToMbPresenter;
        mActivityInvToMbPresenter.setView(this);

        initSpinner();
        mUserActionListener.getData(getContext(), mYearSpinner.getSelectedItem().toString(), mMonthSpinner.getSelectedItem().toString());
    }

    @Override
    public void setAdapter(List<ActivityInvToMbData> resultData) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mListAdapter = new ActivityInvToMbAdapter(resultData, getContext());
        mRecyclerView.setAdapter(mListAdapter);
        OverScrollDecoratorHelper.setUpOverScroll(mRecyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

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
        mRelativeProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openDetailTransaction(ActivityInvToMbData activityInvToMbData) {
        Intent intent = new Intent(getContext(), DetailTransactionActivity.class);
        intent.putExtra(Constant.TITLE_DETAIL, getContext().getResources().getString(R.string.invoice_to_mb));
        intent.putExtra(Constant.URL_LINK_DETAIL, Constant.END_URL_DETAIL_INVOICE_TO_MB);
        intent.putExtra(Constant.ITEM_ID_DETAIL, activityInvToMbData.getItem_id());
        intent.putExtra(Constant.TRANSACTION_ID_DETAIL, activityInvToMbData.getTransaction_id());
        intent.putExtra(Constant.MEMBER_ID_DETAIL, activityInvToMbData.getMember_id());
        intent.putExtra(Constant.NAME_DETAIL, activityInvToMbData.getNama());
        intent.putExtra(Constant.DATE_DETAIL, activityInvToMbData.getDate());
        intent.putExtra(Constant.TOTAL_DETAIL, activityInvToMbData.getTotal_amount());
        intent.putExtra(Constant.TOTAL_PV_DETAIL, activityInvToMbData.getTotal_pv());
        intent.putExtra(Constant.REMARK_DETAIL, activityInvToMbData.getRemark());
        startActivity(intent);
    }

    @Override
    public void createActivationForm(ActivityInvToMbData activityInvToMbData) {
        Bundle b = new Bundle();
        b.putParcelable(Constant.INVOICE_TRANSACTION_DATA, activityInvToMbData);

        Intent intent = new Intent(getContext(), CreateActivationFormActivity.class);
        intent.putExtra(Constant.TITLE_DETAIL, getContext().getResources().getString(R.string.create_activation_form));
        intent.putExtra(Constant.INVOICE_TRANSACTION_DATA, b);
        startActivityForResult(intent, Constant.SUCCESS_TRANSACTION);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constant.SUCCESS_TRANSACTION) {
                ((DashboardActivity) getActivity()).bottomSheetAlert(
                        getResources().getDrawable(R.drawable.ic_check),
                        getResources().getString(R.string.transaksi_berhasil)
                );

                mUserActionListener.getData(getContext(), mYearSpinner.getSelectedItem().toString(), mMonthSpinner.getSelectedItem().toString());
            }
        }
    }

    @Override
    public void openPinDialog(ActivityInvToMbData activityInvToMbData) {
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

                mActivityInvToMbPresenter.cancelTransaction(activityInvToMbData, pin, getContext(), mYearSpinner.getSelectedItem().toString(), mMonthSpinner.getSelectedItem().toString());
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