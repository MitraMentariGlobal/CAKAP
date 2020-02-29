package co.id.cakap.ui.login;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.network.ApiResponseLogin;
import co.id.cakap.ui.changePin.ChangePinActivity;
import co.id.cakap.ui.dashboard.DashboardActivity;
import co.id.cakap.ui.homeWebView.HomeWebViewActivity;
import co.id.cakap.ui.myProfile.MyProfileActivity;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.dialog.BottomDialogActivity;
import co.id.cakap.utils.dialog.ForgotPasswordDialog;

public class LoginActivity extends BottomDialogActivity implements LoginContract.View {
    private static final String TAG = "LoginActivity";

    @Inject
    LoginPresenter mLoginPresenter;

    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;
    @BindView(R.id.user_id_et)
    EditText mUserId;
    @BindView(R.id.password_et)
    EditText mPassword;
    @BindView(R.id.login_btn)
    Button mLoginButton;
    @BindView(R.id.bottom_sheet)
    View bottomSheet;

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
        getAuthData(mUserId.getText().toString(), mPassword.getText().toString());
    }

    @OnClick(R.id.txt_forgot_password)
    public void forgotPassword(View view) {
        ForgotPasswordDialog utils = new ForgotPasswordDialog();
        Dialog dialog = utils.showDialog(this);

        EditText userId = dialog.findViewById(R.id.user_id_et);

        dialog.findViewById(R.id.txt_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                bottomSheetAlert(
                        getResources().getDrawable(R.drawable.ic_success_forgot_password),
                        getResources().getString(R.string.your_default_password)
                );
            }
        });
    }

    public void getAuthData(String userId, String password) {
        mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
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
        mBehavior = BottomSheetBehavior.from(bottomSheet);
        hideProgressBar();
    }

    @Override
    public void showProgressBar() {
        mRelativeProgressBar.setVisibility(View.VISIBLE);
        mUserId.setEnabled(false);
        mPassword.setEnabled(false);
        mLoginButton.setEnabled(false);
    }

    @Override
    public void hideProgressBar() {
        mRelativeProgressBar.setVisibility(View.GONE);
        mUserId.setEnabled(true);
        mPassword.setEnabled(true);
        mLoginButton.setEnabled(true);
    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSuccessResponse(ApiResponseLogin apiResponseLogin) {
//        Intent intent = new Intent(this, HomeWebViewActivity.class);
//        intent.putExtra(Constant.URL_LINK, apiResponseLogin.getResult().getUrl());
//        startActivity(intent);

        Intent intent = new Intent(this, DashboardActivity.class);
        if (Constant.LOGIN_DATA.equals(getResources().getString(R.string.bc_login))) {
            intent = new Intent(this, DashboardActivity.class);
//            intent = new Intent(this, ChangePinActivity.class);
        } else if (Constant.LOGIN_DATA.equals(getResources().getString(R.string.mb_login))) {
            if (apiResponseLogin.getResult().getLeader_ids().equals("0")) {
                Constant.IS_HAVE_PARENT = false;
            } else {
                Constant.IS_HAVE_PARENT = true;
            }
            intent = new Intent(this, DashboardActivity.class);
//            intent = new Intent(this, ChangePinActivity.class);
        } else if (Constant.LOGIN_DATA.equals(getResources().getString(R.string.member_login))) {
            if (Boolean.parseBoolean(apiResponseLogin.getResult().getUpdate_profile())) {
                intent = new Intent(this, MyProfileActivity.class);
            }
        }

        startActivity(intent);
        finish();
    }
}
