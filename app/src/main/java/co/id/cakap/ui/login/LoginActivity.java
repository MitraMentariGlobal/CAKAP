package co.id.cakap.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.ui.home.HomeActivity;
import co.id.cakap.utils.Logger;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private static final String TAG = "LoginActivity";

    @Inject
    LoginPresenter mLoginPresenter;

    @BindView(R.id.main_progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.user_id_et)
    EditText mUserId;
    @BindView(R.id.password_et)
    EditText mPassword;

    private LoginContract.UserActionListener mUserActionListener;
    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }

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
//        startActivity(new Intent(this, HomeActivity.class));
        getAuthData(mUserId.getText().toString(), mPassword.getText().toString());
    }

    public void getAuthData(String userId, String password) {
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Logger.d("signInAnonymously:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            mUserActionListener.getNotificationToken(userId, password);
                        } else {
                            // If sign in fails, display a message to the user.
                            Logger.w("signInAnonymously:failure", task.getException());
                            setErrorResponse("Authentication failed.");
                        }
                    }
                });
    }

    @Override
    public void initializeData() {
        mAuth = FirebaseAuth.getInstance();
        mUserActionListener = mLoginPresenter;
        mLoginPresenter.setView(this);
        hideProgressBar();

        mUserId.setText("BC013");
        mPassword.setText("12345678");
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSuccessResponse() {

    }
}
