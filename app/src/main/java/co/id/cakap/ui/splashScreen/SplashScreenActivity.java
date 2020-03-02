package co.id.cakap.ui.splashScreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.network.ApiResponseLogin;
import co.id.cakap.ui.changePassword.ChangePasswordActivity;
import co.id.cakap.ui.changePin.ChangePinActivity;
import co.id.cakap.ui.dashboard.DashboardActivity;
import co.id.cakap.ui.homeWebView.HomeWebViewActivity;
import co.id.cakap.ui.login.LoginActivity;
import co.id.cakap.ui.myProfile.MyProfileActivity;
import co.id.cakap.utils.Logger;

public class SplashScreenActivity extends AppCompatActivity implements SplashScreenContract.View{
    private static final String TAG = "SplashScreenActivity";

    @Inject
    SplashScreenPresenter mSplashScreenPresenter;

    @BindView(R.id.main_progress_bar)
    ProgressBar mProgressBar;

    private SplashScreenContract.UserActionListener mUserActionListener;
    private FirebaseAuth mAuth;
    private ThreeBounce mThreeBounce;
    private SharedPreferences mSharedPreferences;
    private String mUrlNotification = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemUI();
        setContentView(R.layout.activity_splash);
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

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    @Override
    public void initializeData() {
        mAuth = FirebaseAuth.getInstance();
        mUserActionListener = mSplashScreenPresenter;
        mSplashScreenPresenter.setView(this);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        mThreeBounce = new ThreeBounce();
        mThreeBounce.setColor(getResources().getColor(R.color.colorPrimaryDark));
        mProgressBar.setIndeterminateDrawable(mThreeBounce);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showProgressBar();
                try {
                    mUrlNotification = mSharedPreferences.getString(Constant.FIREBASE_NOTIFICATION_URL, "");
                } catch (Exception e) {
                    Logger.e("error SharedPreferences : " + e.getMessage());
                    mUrlNotification = "";
                }
                mUserActionListener.getData();
//                startActivity(new Intent(SplashScreenActivity.this, DashboardActivity.class));
//                finishActivity();

            }
        },2000);
    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(SplashScreenActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToHome(ApiResponseLogin apiResponseLogin) {
//        Intent intent = new Intent(this, HomeWebViewActivity.class);
//
//        if (mUrlNotification != null && mUrlNotification.length() > 0) {
//            intent.putExtra(Constant.URL_LINK, mUrlNotification);
//            SharedPreferences.Editor sharedPrefEd = mSharedPreferences.edit();
//            sharedPrefEd.putString(Constant.FIREBASE_NOTIFICATION_URL, apiResponseLogin.getResult().getUrl());
//            sharedPrefEd.apply();
//        } else {
//            intent.putExtra(Constant.URL_LINK, apiResponseLogin.getResult().getUrl());
//        }
//        startActivity(intent);

        Intent intent = new Intent(this, DashboardActivity.class);
        if (Constant.LOGIN_DATA.equals(getResources().getString(R.string.bc_login))) {
            intent = getIntentParse(apiResponseLogin);
        } else if (Constant.LOGIN_DATA.equals(getResources().getString(R.string.mb_login))) {
            if (apiResponseLogin.getResult().getLeader_ids().equals("0")) {
                Constant.IS_HAVE_PARENT = false;
            } else {
                Constant.IS_HAVE_PARENT = true;
            }

            intent = getIntentParse(apiResponseLogin);
        } else if (Constant.LOGIN_DATA.equals(getResources().getString(R.string.member_login))) {
            if (Boolean.parseBoolean(apiResponseLogin.getResult().getUpdate_profile())) {
                intent = new Intent(this, MyProfileActivity.class);
            }
        }

        startActivity(intent);
        finishActivity();
    }

    private Intent getIntentParse(ApiResponseLogin apiResponseLogin) {
        if (apiResponseLogin.getResult().getFlag_login().equals("0")) {
            Constant.IS_FLAG_UPDATE = true;
            return new Intent(this, ChangePasswordActivity.class);
        } else if (apiResponseLogin.getResult().getFlag_login().equals("1")) {
            Constant.IS_FLAG_UPDATE = true;
            return new Intent(this, ChangePinActivity.class);
        } else {
            Constant.IS_FLAG_UPDATE = false;
            return new Intent(this, DashboardActivity.class);
        }
    }

    @Override
    public void goToLogin() {
        startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
        finishActivity();
    }

    @Override
    public void finishActivity() {
        finish();
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
