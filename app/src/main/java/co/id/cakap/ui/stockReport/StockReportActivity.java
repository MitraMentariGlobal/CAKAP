package co.id.cakap.ui.stockReport;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.SectionsActivityPagerAdapter;
import co.id.cakap.adapter.SectionsStockPagerAdapter;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.ui.cashbill.CashbillActivityContract;
import co.id.cakap.ui.cashbill.CashbillActivityPresenter;

public class StockReportActivity extends AppCompatActivity implements StockReportActivityContract.View{
    @Inject
    StockReportActivityPresenter mStockReportActivityPresenter;

    @BindView(R.id.title_toolbar)
    TextView mTitle;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    private StockReportActivityContract.UserActionListener mUserActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_report);
        ButterKnife.bind(this);

        setupActivityComponent();
        initializeData();
    }

    private void setupActivityComponent() {
        CoreApp.get()
                .getAppComponent()
                .plus(new MainActivityModule(this))
                .inject(this);
    }

    @Override
    public void initializeData() {
        mUserActionListener = mStockReportActivityPresenter;
        mStockReportActivityPresenter.setView(this);

        mTitle.setText(getString(R.string.stock_report).toUpperCase());


        SectionsStockPagerAdapter sectionsStockPagerAdapter = new SectionsStockPagerAdapter(this, getSupportFragmentManager());
        mViewPager.setAdapter(sectionsStockPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.arrow_back)
    public void arrowBack(View view) {
        super.onBackPressed();
    }
}
