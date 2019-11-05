package co.id.cakap.ui.dashboard.restock;

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
import co.id.cakap.adapter.SectionsRestockPagerAdapter;
import co.id.cakap.di.module.MainActivityModule;

public class RestockFragment extends Fragment implements RestockContract.View {
    @Inject
    RestockPresenter mRestockPresenter;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.title)
    TextView mTitle;

    private View mView;
    private Unbinder mUnbinder;
    private RestockContract.UserActionListener mUserActionListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_restock, container, false);
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
        mUserActionListener = mRestockPresenter;
        mRestockPresenter.setView(this);

        mTitle.setText(getContext().getResources().getString(R.string.restock).toUpperCase());

        SectionsRestockPagerAdapter sectionsRestockPagerAdapter = new SectionsRestockPagerAdapter(getContext(), getChildFragmentManager());
        mViewPager.setAdapter(sectionsRestockPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}