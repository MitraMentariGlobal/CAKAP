package co.id.cakap.ui.dashboard;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.ui.cashbill.CashbillActivityPresenter;
import co.id.cakap.ui.dashboard.account.AccountFragment;
import co.id.cakap.ui.dashboard.notification.NotificationFragment;
import co.id.cakap.ui.dashboard.restock.RestockFragment;
import co.id.cakap.ui.dashboard.home.HomeFragment;
import co.id.cakap.ui.dashboard.activity.ActivityFragment;

public class DashboardActivity extends AppCompatActivity implements DashboardContract.View, BottomNavigationView.OnNavigationItemSelectedListener {
    @Inject
    DashboardPresenter mDashboardPresenter;

    @BindView(R.id.bn_main)
    BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
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
        loadFragment(new HomeFragment());
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        mBottomNavigationView.setItemIconTintList(null);
    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.home_menu:
                fragment = new HomeFragment();
                break;
            case R.id.activity_menu:
                fragment = new ActivityFragment();
                break;
            case R.id.restock_menu:
                fragment = new RestockFragment();
                break;
            case R.id.notification_menu:
                fragment = new NotificationFragment();
                break;
            case R.id.account_menu:
                fragment = new AccountFragment();
                break;
        }
        return loadFragment(fragment);

    }
}
