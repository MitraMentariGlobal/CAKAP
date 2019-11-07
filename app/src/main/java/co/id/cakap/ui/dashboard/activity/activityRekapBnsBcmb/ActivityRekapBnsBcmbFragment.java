package co.id.cakap.ui.dashboard.activity.activityRekapBnsBcmb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

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
import co.id.cakap.adapter.ActivityRekapBnsBcmbAdapter;
import co.id.cakap.data.ActivityRekapBnsBcmbData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.ui.dashboard.activity.activityInvToMb.ActivityInvToMbContract;
import co.id.cakap.ui.dashboard.activity.activityInvToMb.ActivityInvToMbPresenter;

public class ActivityRekapBnsBcmbFragment extends Fragment implements ActivityRekapBnsBcmbContract.View {
    @Inject
    ActivityRekapBnsBcmbPresenter mActivityRekapBnsBcmbPresenter;

    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.main_progress_bar)
    ProgressBar mProgressBar;

    private View mView;
    private Unbinder mUnbinder;
    private ActivityRekapBnsBcmbAdapter mListAdapter;
    private ActivityRekapBnsBcmbContract.UserActionListener mUserActionListener;

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
        mUserActionListener.getData();
    }

    @Override
    public void setAdapter(List<ActivityRekapBnsBcmbData> resultData) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mListAdapter = new ActivityRekapBnsBcmbAdapter(resultData, getContext());
        mRecyclerView.setAdapter(mListAdapter);
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
}