package co.id.cakap.ui.dashboard;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.cashbill.CashbillActivityPresenter;
import co.id.cakap.ui.dashboard.account.AccountFragment;
import co.id.cakap.ui.dashboard.activity.activityReqInvMb.ActivityReqInvMbPresenter;
import co.id.cakap.ui.dashboard.notification.NotificationFragment;
import co.id.cakap.ui.dashboard.restock.RestockFragment;
import co.id.cakap.ui.dashboard.home.HomeFragment;
import co.id.cakap.ui.dashboard.activity.ActivityFragment;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.dialog.BottomDialogActivity;
import co.id.cakap.utils.dialog.UserConfirmationDialog;

public class DashboardActivity extends BottomDialogActivity implements DashboardContract.View, BottomNavigationView.OnNavigationItemSelectedListener {
    @Inject
    DashboardPresenter mDashboardPresenter;

    @BindView(R.id.bn_main)
    BottomNavigationView mBottomNavigationView;
    @BindView(R.id.bn_main_member)
    BottomNavigationView mBottomNavigationViewMember;
    @BindView(R.id.bottom_sheet)
    View bottomSheet;

    private static final int TIME_DELAY = 1500;
    private static long back_pressed;
    private int mMoveNotification = 0;
    private SharedPreferences mSharedPreferences;
    private DashboardContract.UserActionListener mUserActionListener;

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
        mUserActionListener = mDashboardPresenter;
        mDashboardPresenter.setView(this);
        mUserActionListener.checkVersionUpdate();
        mBehavior = BottomSheetBehavior.from(bottomSheet);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        try {
            mMoveNotification = mSharedPreferences.getInt(Constant.FIREBASE_NOTIFICATION_MOVE, 0);
        } catch (Exception e) {
            Logger.e("error SharedPreferences : " + e.getMessage());
            mMoveNotification = 0;
        }

        if (mMoveNotification == 1) {
            moveToNotification();
        } else {
            loadFragment(new HomeFragment());
        }

        if (Constant.LOGIN_DATA.equals(getResources().getString(R.string.member_login))) {
            mBottomNavigationView.setVisibility(View.GONE);
            mBottomNavigationViewMember.setVisibility(View.VISIBLE);
            mBottomNavigationViewMember.setOnNavigationItemSelectedListener(this);
            mBottomNavigationViewMember.setItemIconTintList(null);
        } else {
            mBottomNavigationView.setOnNavigationItemSelectedListener(this);
            mBottomNavigationView.setItemIconTintList(null);
        }
    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void moveToActivity(int index) {
        loadFragment(ActivityFragment.newInstance(index));
        mBottomNavigationViewMember.getMenu().findItem(R.id.activity_menu).setChecked(true);
    }

    @Override
    public void openDialogUpdate(String url, boolean isCancelDialog) {
        UserConfirmationDialog utils = new UserConfirmationDialog();
        Dialog dialog = utils.showDialog(this);
        utils.setTitleDialogSingle("Update Info");
        utils.setDescDialog("Cakap versi terbaru telah tersedia. Silakan memperbarui aplikasi Cakap.");
        utils.setUpdateAction(isCancelDialog);

        dialog.findViewById(R.id.yes_act_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                redirectStore(url);
            }
        });
    }

    public void redirectStore(String url) {
        Uri uri = Uri.parse(url);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(url)));
        }
    }

    public void moveToNotification() {
        loadFragment(new NotificationFragment());
        mBottomNavigationViewMember.getMenu().findItem(R.id.notification_menu).setChecked(true);
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
                fragment = ActivityFragment.newInstance(0);
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

    @Override
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            System.exit(0);
        } else {
            setErrorResponse("Tekan sekali lagi untuk keluar");
        }
        back_pressed = System.currentTimeMillis();
    }
}
