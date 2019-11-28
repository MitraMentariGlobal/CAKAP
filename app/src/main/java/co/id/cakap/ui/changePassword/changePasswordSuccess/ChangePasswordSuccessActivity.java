package co.id.cakap.ui.changePassword.changePasswordSuccess;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.ui.changePassword.ChangePasswordContract;
import co.id.cakap.ui.changePassword.ChangePasswordPresenter;
import co.id.cakap.ui.dashboard.DashboardActivity;

public class ChangePasswordSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_success);
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

    public void initializeData() {

    }

    @OnClick(R.id.btn_close)
    public void closeScreen(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(this, DashboardActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
