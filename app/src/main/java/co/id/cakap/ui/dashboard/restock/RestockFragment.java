package co.id.cakap.ui.dashboard.restock;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.SectionsRestockBCPagerAdapter;
import co.id.cakap.adapter.SectionsRestockMBPagerAdapter;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.pickUpDelivery.PickUpDeliveryActivity;
import co.id.cakap.ui.reqInvoiceToBc.ReqInvoiceToBcActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class RestockFragment extends Fragment implements RestockContract.View {
    @Inject
    RestockPresenter mRestockPresenter;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.iv_add)
    CircleImageView mIvAdd;

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

        if (Constant.LOGIN_DATA.equals(getResources().getString(R.string.bc_login))) {
            SectionsRestockBCPagerAdapter sectionsRestockBCPagerAdapter = new SectionsRestockBCPagerAdapter(getContext(), getChildFragmentManager());
            mViewPager.setAdapter(sectionsRestockBCPagerAdapter);
        } else if (Constant.LOGIN_DATA.equals(getResources().getString(R.string.mb_login))) {
            if (Constant.IS_HAVE_PARENT) {
                SectionsRestockMBPagerAdapter sectionsRestockMBPagerAdapter = new SectionsRestockMBPagerAdapter(getContext(), getChildFragmentManager());
                mViewPager.setAdapter(sectionsRestockMBPagerAdapter);
            } else {
                SectionsRestockBCPagerAdapter sectionsRestockBCPagerAdapter = new SectionsRestockBCPagerAdapter(getContext(), getChildFragmentManager());
                mViewPager.setAdapter(sectionsRestockBCPagerAdapter);
            }
        }
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @OnClick(R.id.iv_add)
    public void actionAdd(View view) {
        if (Constant.LOGIN_DATA.equals(getResources().getString(R.string.bc_login))) {
            startActivity(new Intent(getContext(), PickUpDeliveryActivity.class));
        } else if (Constant.LOGIN_DATA.equals(getResources().getString(R.string.mb_login))) {
            if (Constant.IS_HAVE_PARENT) {
                startActivity(new Intent(getContext(), ReqInvoiceToBcActivity.class));
            } else {
                startActivity(new Intent(getContext(), PickUpDeliveryActivity.class));
            }
        }
    }
}