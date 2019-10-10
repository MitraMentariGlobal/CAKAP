package co.id.cakap.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.login.LoginActivity;
import co.id.cakap.utils.Logger;

public class HomeActivity extends AppCompatActivity implements HomeContract.View{

    @Inject
    HomePresenter mHomePresenter;

    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;

    String mUrl = "";
    private HomeContract.UserActionListener mUserActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemUI();
        setContentView(R.layout.activity_home);
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
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void initializeData() {
        mUserActionListener = mHomePresenter;
        mHomePresenter.setView(this);
        hideProgressBar();

        Intent intent = getIntent();
        mUrl = intent.getStringExtra(Constant.URL_LINK);
        Logger.d("home url : " + mUrl);

        WebSettings webSetting = mWebView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setDisplayZoomControls(true);
        if (Build.VERSION.SDK_INT >= 26) {
            webSetting.setSafeBrowsingEnabled(false);
        }
        mWebView.setWebViewClient(new CustomWebViewClient());
        mWebView.loadUrl(mUrl);
    }

    @OnClick(R.id.fab)
    public void userLogout(View view) {
        showProgressBar();
        mUserActionListener.getData();
    }

    @Override
    public void showProgressBar() {
        mRelativeProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mRelativeProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(HomeActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSuccessResponse() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
