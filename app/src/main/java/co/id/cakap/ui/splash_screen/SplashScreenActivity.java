package co.id.cakap.ui.splash_screen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.home.HomeActivity;
import co.id.cakap.ui.login.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity implements SplashScreenContract.View{
    private static final String TAG = "LoginActivity";

    @Inject
    SplashScreenPresenter mSplashScreenPresenter;

    private SplashScreenContract.UserActionListener mUserActionListener;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemUI();
        setContentView(R.layout.activity_splash);

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

    public void initializeData() {
        mAuth = FirebaseAuth.getInstance();
        mUserActionListener = mSplashScreenPresenter;
        mSplashScreenPresenter.setView(this);

        mUserActionListener.getData();
    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(SplashScreenActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToHome(String url) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(Constant.URL_LINK, url);
        startActivity(intent);
        finishActivity();
    }

    @Override
    public void goToLogin() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                finishActivity();
            }
        },2000);
    }

    @Override
    public void finishActivity() {
        finish();
    }
}
