package co.id.cakap.ui.changePassword;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.ui.changePassword.changePasswordSuccess.ChangePasswordSuccessActivity;
import co.id.cakap.ui.myProfile.MyProfileActivityContract;
import co.id.cakap.ui.myProfile.MyProfileActivityPresenter;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.dialog.PinDialog;

public class ChangePasswordActivity extends AppCompatActivity implements ChangePasswordContract.View{
    @Inject
    ChangePasswordPresenter mChangePasswordPresenter;

    @BindView(R.id.main_progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.title_toolbar)
    TextView mTitle;

    @BindView(R.id.et_old_password)
    EditText mEtOldPassword;
    @BindView(R.id.et_new_password)
    EditText mEtNewPassword;
    @BindView(R.id.et_retype_new_password)
    EditText mEtRetypeNewPassword;
    @BindView(R.id.text_submit)
    TextView mTxtSubmit;

    @BindView(R.id.txt_error_old_password)
    TextView mTxtErrorOldPassword;
    @BindView(R.id.txt_error_new_password)
    TextView mTxtErrorNewPassword;
    @BindView(R.id.txt_error_retype_new_password)
    TextView mTxtErrorRetypeNewPassword;

    @BindView(R.id.linear_old_password)
    LinearLayout mLinearOldPassword;
    @BindView(R.id.linear_new_password)
    LinearLayout mLinearNewPassword;
    @BindView(R.id.linear_retype_new_password)
    LinearLayout mLinearRetypeNewPassword;

    private ChangePasswordContract.UserActionListener mUserActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
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
        mUserActionListener = mChangePasswordPresenter;
        mChangePasswordPresenter.setView(this);

        mTitle.setText(getString(R.string.change_password).toUpperCase());
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
        startActivity(new Intent(this, ChangePasswordSuccessActivity.class));
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
        if (mEtNewPassword.getText().toString().equals(mEtRetypeNewPassword.getText().toString())) {
            mLinearNewPassword.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
            mTxtErrorNewPassword.setVisibility(View.GONE);

            mLinearRetypeNewPassword.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
            mTxtErrorRetypeNewPassword.setVisibility(View.GONE);

            openDialogPin();
        } else {
            mLinearNewPassword.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
            mTxtErrorNewPassword.setVisibility(View.VISIBLE);

            mLinearRetypeNewPassword.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
            mTxtErrorRetypeNewPassword.setVisibility(View.VISIBLE);
        }
    }

    public void openDialogPin() {
        PinDialog utils = new PinDialog();
        Dialog dialog = utils.showDialog(this);

        PinLockView pinLockView = dialog.findViewById(R.id.pin_lock_view);
        IndicatorDots indicatorDots = dialog.findViewById(R.id.indicator_dots);
        PinLockListener pinLockListener = new PinLockListener() {
            @Override
            public void onComplete(String pin) {
                Logger.d("Pin complete: " + pin);
                dialog.hide();
                dialog.dismiss();

                mUserActionListener.changeData(
                        mEtOldPassword.getText().toString(),
                        mEtNewPassword.getText().toString(),
                        mEtRetypeNewPassword.getText().toString(),
                        pin
                );

            }

            @Override
            public void onEmpty() {
                Logger.d("Pin empty");
            }

            @Override
            public void onPinChange(int pinLength, String intermediatePin) {
                Logger.d("Pin changed, new length " + pinLength + " with intermediate pin " + intermediatePin);
            }
        };

        pinLockView.attachIndicatorDots(indicatorDots);
        pinLockView.setPinLockListener(pinLockListener);

        pinLockView.setPinLength(6);
        pinLockView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        indicatorDots.setIndicatorType(IndicatorDots.IndicatorType.FILL_WITH_ANIMATION);
    }
}
