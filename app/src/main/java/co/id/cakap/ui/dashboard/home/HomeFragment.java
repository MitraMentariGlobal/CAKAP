package co.id.cakap.ui.dashboard.home;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.CustomViewPagerAdapter;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.utils.widget.CustomRecyclerViewPager;

public class HomeFragment extends Fragment implements HomeContract.View {
    @Inject
    HomePresenter mHomePresenter;

    @BindView(R.id.viewPager)
    CustomRecyclerViewPager mViewPager;

    private View mView;
    private Unbinder mUnbinder;
    private HomeContract.UserActionListener mUserActionListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_home, container, false);
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
    public void setAdapter() {
        DisplayMetrics metrics = getDisplayMetrics();
        List<String> metalList = Arrays.asList(
                "https://asset-apac.unileversolutions.com/content/dam/unilever/lipton_international/global/general_image/worldtea_teatype_black_tea_img1_1460x593-1382286.jpg.ulenscale.1024x415.jpg",
                "https://asset-apac.unileversolutions.com/content/dam/unilever/lipton_international/global/general_image/worldtea_teatype_black_tea_img1_1460x593-1382286.jpg.ulenscale.1024x415.jpg",
                "https://asset-apac.unileversolutions.com/content/dam/unilever/lipton_international/global/general_image/worldtea_teatype_black_tea_img1_1460x593-1382286.jpg.ulenscale.1024x415.jpg",
                "https://asset-apac.unileversolutions.com/content/dam/unilever/lipton_international/global/general_image/worldtea_teatype_black_tea_img1_1460x593-1382286.jpg.ulenscale.1024x415.jpg",
                "https://asset-apac.unileversolutions.com/content/dam/unilever/lipton_international/global/general_image/worldtea_teatype_black_tea_img1_1460x593-1382286.jpg.ulenscale.1024x415.jpg",
                "https://asset-apac.unileversolutions.com/content/dam/unilever/lipton_international/global/general_image/worldtea_teatype_black_tea_img1_1460x593-1382286.jpg.ulenscale.1024x415.jpg"
        );
        CustomViewPagerAdapter customViewPagerAdapter = new CustomViewPagerAdapter(metrics, metalList, getContext());
        mViewPager.setAdapter(customViewPagerAdapter);
    }

    @Override
    public void initializeData() {
        mUserActionListener = mHomePresenter;
        mHomePresenter.setView(this);
        mUserActionListener.getData();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    private DisplayMetrics getDisplayMetrics() {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        return metrics;
    }
}
