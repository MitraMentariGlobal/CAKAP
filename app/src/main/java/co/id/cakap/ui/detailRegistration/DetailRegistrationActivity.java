package co.id.cakap.ui.detailRegistration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.cashbill.cashbillSuccess.CashbillSuccessActivity;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.dialog.PinDialog;

public class DetailRegistrationActivity extends AppCompatActivity implements DetailRegistrationContract.View{
    @Inject
    DetailRegistrationPresenter mDetailRegistrationPresenter;

    @BindView(R.id.main_progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.title_toolbar)
    TextView mTitle;

    @BindView(R.id.et_rec_id)
    EditText mEtRecId;
    @BindView(R.id.et_rec_name)
    EditText mEtRecName;

    @BindView(R.id.et_sponsor_id)
    EditText mEtSponsorId;
    @BindView(R.id.et_sponsor_name)
    EditText mEtSponsorName;

    @BindView(R.id.et_member_id)
    EditText mEtMemberId;
    @BindView(R.id.et_full_name)
    EditText mEtFulllName;
    @BindView(R.id.et_id_card)
    EditText mEtIdCard;
    @BindView(R.id.spinner_sex)
    Spinner mSpinnerSex;
    @BindView(R.id.et_place_of_birth)
    EditText mEtPob;
    @BindView(R.id.et_date_of_birth)
    EditText mEtDob;
    @BindView(R.id.spinner_religion)
    Spinner mSpinnerReligion;
    @BindView(R.id.et_email)
    EditText mEtEmail;
    @BindView(R.id.et_phone_number)
    EditText mEtPhoneNumber;
    @BindView(R.id.et_mobile_number)
    EditText mEtMobileNumber;
    @BindView(R.id.et_address)
    EditText mEtAddress;
    @BindView(R.id.spinner_province)
    Spinner mSpinnerProvince;
    @BindView(R.id.spinner_city)
    Spinner mSpinnerCity;
    @BindView(R.id.et_postal_code)
    EditText mEtPostalCode;
    @BindView(R.id.et_activation_code)
    EditText mEtActivationCode;

    @BindView(R.id.et_couple_name)
    EditText mEtCoupleName;
    @BindView(R.id.et_heir_name)
    EditText mEtHeirName;
    @BindView(R.id.et_relationship)
    EditText mEtRelationship;

    @BindView(R.id.spinner_bank)
    Spinner mSpinnerBank;
    @BindView(R.id.et_branch_name)
    EditText mEtBranchName;
    @BindView(R.id.et_account_holder)
    EditText mEtAccountHolder;
    @BindView(R.id.et_aaccount_number)
    EditText mEtAccountNumber;

    private String mActivationCode = "";
    private String mMemberId = "";
    private DetailRegistrationContract.UserActionListener mUserActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_registration);
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
        mUserActionListener = mDetailRegistrationPresenter;
        mDetailRegistrationPresenter.setView(this);

        Intent intent = getIntent();
        mActivationCode = intent.getStringExtra(Constant.ACTIVATION_CODE);
        mMemberId = intent.getStringExtra(Constant.MEMBER_ID);

        mTitle.setText(getString(R.string.registration).toUpperCase());
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
    public void setErrorResponse(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.arrow_back)
    public void arrowBack(View view) {
        super.onBackPressed();
    }

    @OnClick(R.id.text_process)
    public void actionProcess(View view) {
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

//                Intent intent = new Intent(getApplicationContext(), CashbillSuccessActivity.class);
//                intent.putExtra(Constant.TITLE_DETAIL, getResources().getString(R.string.cashbill).toUpperCase());
//                startActivity(intent);
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
