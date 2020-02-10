package co.id.cakap.ui.dashboard.activity.activityReqInvMb;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.ActivityReqInvMbAdapter;
import co.id.cakap.data.ActivityReqInvMbData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.dashboard.activity.activityRekapBnsBcmb.ActivityRekapBnsBcmbContract;
import co.id.cakap.ui.dashboard.activity.activityRekapBnsBcmb.ActivityRekapBnsBcmbPresenter;
import co.id.cakap.ui.detailTransaction.DetailTransactionActivity;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class ActivityReqInvMbFragment extends Fragment implements ActivityReqInvMbContract.View {
    @Inject
    ActivityReqInvMbPresenter mActivityReqInvMbPresenter;

    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;

    private View mView;
    private Unbinder mUnbinder;
    private ActivityReqInvMbAdapter mListAdapter;
    private ActivityReqInvMbContract.UserActionListener mUserActionListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_activity_req_inv_mb, container, false);
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
        mUserActionListener = mActivityReqInvMbPresenter;
        mActivityReqInvMbPresenter.setView(this);
        mUserActionListener.getData();
    }

    @Override
    public void setAdapter(List<ActivityReqInvMbData> resultData) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mListAdapter = new ActivityReqInvMbAdapter(resultData, getContext());
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
        mRelativeProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openDetailTransaction(String transactionId) {
        Intent intent = new Intent(getContext(), DetailTransactionActivity.class);
        intent.putExtra(Constant.TITLE_DETAIL, getContext().getResources().getString(R.string.req_invoice_mb));
        intent.putExtra(Constant.TRANSACTION_ID_DETAIL, transactionId);
        startActivity(intent);
    }
}