package co.id.cakap.ui.monthlyPointReport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import javax.inject.Inject;

import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.di.module.MainActivityModule;

public class MonthlyPointReportActivity extends AppCompatActivity implements MonthlyPointReportContract.View {
    @Inject
    MonthlyPointReportPresenter mMonthlyPointReportPresenter;

    private MonthlyPointReportContract.UserActionListener mUserActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_point_report);

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
        mUserActionListener = mMonthlyPointReportPresenter;
        mMonthlyPointReportPresenter.setView(this);
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void setErrorResponse(String message) {

    }
}
