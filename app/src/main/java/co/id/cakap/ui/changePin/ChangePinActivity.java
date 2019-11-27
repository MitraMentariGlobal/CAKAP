package co.id.cakap.ui.changePin;

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
import co.id.cakap.ui.changePin.changePinSuccess.ChangePinSuccessActivity;
import co.id.cakap.ui.myProfile.MyProfileActivityContract;
import co.id.cakap.ui.myProfile.MyProfileActivityPresenter;

public class ChangePinActivity extends AppCompatActivity implements ChangePinContract.View{
    @Inject
    ChangePinPresenter mChangePinPresenter;

    @BindView(R.id.main_progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.title_toolbar)
    TextView mTitle;

    @BindView(R.id.et_old_pin)
    EditText mEtOldPin;
    @BindView(R.id.et_new_pin)
    EditText mEtNewPin;
    @BindView(R.id.et_retype_new_pin)
    EditText mEtRetypeNewPin;
    @BindView(R.id.text_submit)
    TextView mTxtSubmit;

    @BindView(R.id.txt_error_old_pin)
    TextView mTxtErrorOldPin;
    @BindView(R.id.txt_error_new_pin)
    TextView mTxtErrorNewPin;
    @BindView(R.id.txt_error_retype_new_pin)
    TextView mTxtErrorRetypeNewPin;

    @BindView(R.id.linear_old_pin)
    LinearLayout mLinearOldPin;
    @BindView(R.id.linear_new_pin)
    LinearLayout mLinearNewPin;
    @BindView(R.id.linear_retype_new_pin)
    LinearLayout mLinearRetypeNewPin;

    private ChangePinContract.UserActionListener mUserActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pin);
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
        mUserActionListener = mChangePinPresenter;
        mChangePinPresenter.setView(this);

        mTitle.setText(getString(R.string.change_pin).toUpperCase());
        hideProgressBar();
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
    public void setSuccessResponse() {
        startActivity(new Intent(this, ChangePinSuccessActivity.class));
    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.arrow_back)
    public void arrowBack(View view) {
        super.onBackPressed();
    }

    @OnClick(R.id.text_submit)
    public void actionSubmit(View view) {
        if (mEtNewPin.getText().toString().equals(mEtRetypeNewPin.getText().toString())) {
            mLinearNewPin.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
            mTxtErrorNewPin.setVisibility(View.GONE);

            mLinearRetypeNewPin.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
            mTxtErrorRetypeNewPin.setVisibility(View.GONE);

            mUserActionListener.changeData(
                    mEtOldPin.getText().toString(),
                    mEtNewPin.getText().toString(),
                    mEtRetypeNewPin.getText().toString()
            );
        } else {
            mLinearNewPin.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
            mTxtErrorNewPin.setVisibility(View.VISIBLE);

            mLinearRetypeNewPin.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
            mTxtErrorRetypeNewPin.setVisibility(View.VISIBLE);
        }
    }
}
