package co.id.cakap.ui.changePin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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

    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;
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
        mChangePinPresenter.setView(this, this);

        mTitle.setText(getString(R.string.change_pin).toUpperCase());
        hideProgressBar();
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
    public void setSuccessResponse() {
        startActivity(new Intent(this, ChangePinSuccessActivity.class));
    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setErrorOldPin(boolean isError) {
        if (isError) {
            mLinearOldPin.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
            mTxtErrorOldPin.setVisibility(View.VISIBLE);
        } else {
            mLinearOldPin.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
            mTxtErrorOldPin.setVisibility(View.GONE);
        }
    }

    @Override
    public void setErrorNewPin(boolean isError, boolean isFilled) {
        if (isError) {
            mLinearNewPin.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
            mTxtErrorNewPin.setVisibility(View.VISIBLE);

            if (isFilled)
                mTxtErrorNewPin.setText(getString(R.string.field_not_match));
            else
                mTxtErrorNewPin.setText(getString(R.string.field_required));

        } else {
            mLinearNewPin.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
            mTxtErrorNewPin.setVisibility(View.GONE);
        }
    }

    @Override
    public void setErrorRetypeNewPin(boolean isError, boolean isFilled) {
        if (isError) {
            mLinearRetypeNewPin.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
            mTxtErrorRetypeNewPin.setVisibility(View.VISIBLE);

            if (isFilled)
                mTxtErrorRetypeNewPin.setText(getString(R.string.field_not_match));
            else
                mTxtErrorRetypeNewPin.setText(getString(R.string.field_required));

        } else {
            mLinearRetypeNewPin.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
            mTxtErrorRetypeNewPin.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.arrow_back)
    public void arrowBack(View view) {
        super.onBackPressed();
    }

    @OnClick(R.id.text_submit)
    public void actionSubmit(View view) {
        mUserActionListener.checkData(mEtOldPin.getText().toString(), mEtNewPin.getText().toString(), mEtRetypeNewPin.getText().toString());
    }
}
