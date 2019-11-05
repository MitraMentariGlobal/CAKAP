package co.id.cakap.ui.dashboard.activity.activityRekapBnsBcmb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.ui.dashboard.activity.activityInvToMb.ActivityInvToMbContract;
import co.id.cakap.ui.dashboard.activity.activityInvToMb.ActivityInvToMbPresenter;

public class ActivityRekapBnsBcmbFragment extends Fragment implements ActivityRekapBnsBcmbContract.View {
    @Inject
    ActivityRekapBnsBcmbPresenter mActivityRekapBnsBcmbPresenter;

    private View mView;
    private Unbinder mUnbinder;
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
    }
}