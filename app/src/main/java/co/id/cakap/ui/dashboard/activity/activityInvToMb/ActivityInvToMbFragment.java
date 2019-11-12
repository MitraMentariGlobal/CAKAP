package co.id.cakap.ui.dashboard.activity.activityInvToMb;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillContract;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillPresenter;
import co.id.cakap.ui.detailTransaction.DetailTransactionActivity;
import co.id.cakap.utils.Logger;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class ActivityInvToMbFragment extends Fragment implements ActivityInvToMbContract.View, DatePickerDialog.OnDateSetListener {
    @Inject
    ActivityInvToMbPresenter mActivityInvToMbPresenter;

    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.main_progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    private View mView;
    private Unbinder mUnbinder;
    private ActivityInvToMbAdapter mListAdapter;
    private ActivityInvToMbContract.UserActionListener mUserActionListener;

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
        mUserActionListener.getData();
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
    public void openDetailTransaction(String transactionId) {
        Intent intent = new Intent(getContext(), DetailTransactionActivity.class);
        intent.putExtra(Constant.TITLE_DETAIL, getContext().getResources().getString(R.string.invoice_to_mb));
        intent.putExtra(Constant.TRANSACTION_ID_DETAIL, transactionId);
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
}