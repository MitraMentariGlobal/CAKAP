package co.id.cakap.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.ui.home.HomeActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    @Inject
    LoginPresenter mLoginPresenter;

    private LoginContract.UserActionListener mUserActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemUI();
        setContentView(R.layout.activity_login);
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

    @OnClick(R.id.login_btn)
    public void userLoginAction(View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void initializeData() {
        mUserActionListener = mLoginPresenter;
        mLoginPresenter.setView(this);
    }
}
