package co.id.cakap.ui.dashboard.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.SectionsActivityMBPagerAdapter;
import co.id.cakap.adapter.SectionsActivityMemberPagerAdapter;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.adapter.SectionsActivityBCPagerAdapter;
import co.id.cakap.helper.Constant;

public class ActivityFragment extends Fragment implements ActivityContract.View {
    @Inject
    ActivityPresenter mActivityPresenter;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.title)
    TextView mTitle;

    private View mView;
    private Unbinder mUnbinder;
    private ActivityContract.UserActionListener mUserActionListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_activity, container, false);
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
        mUserActionListener = mActivityPresenter;
        mActivityPresenter.setView(this);

        mTitle.setText(getContext().getResources().getString(R.string.activity).toUpperCase());

        if (Constant.LOGIN_DATA.equals(getResources().getString(R.string.bc_login))) {
            SectionsActivityBCPagerAdapter sectionsActivityBCPagerAdapter = new SectionsActivityBCPagerAdapter(getContext(), getChildFragmentManager());
            mViewPager.setAdapter(sectionsActivityBCPagerAdapter);
        } else if (Constant.LOGIN_DATA.equals(getResources().getString(R.string.mb_login))) {
            SectionsActivityMBPagerAdapter sectionsActivityMBPagerAdapter = new SectionsActivityMBPagerAdapter(getContext(), getChildFragmentManager());
            mViewPager.setAdapter(sectionsActivityMBPagerAdapter);
        } else if (Constant.LOGIN_DATA.equals(getResources().getString(R.string.member_login))) {
            SectionsActivityMemberPagerAdapter sectionsActivityMemberPagerAdapter = new SectionsActivityMemberPagerAdapter(getContext(), getChildFragmentManager());
            mViewPager.setAdapter(sectionsActivityMemberPagerAdapter);
        }
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
