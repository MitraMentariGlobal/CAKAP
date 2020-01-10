package co.id.cakap.ui.registration.registrationSuccess;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.data.RegistrationSuccessData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.dashboard.DashboardActivity;
import co.id.cakap.ui.myProfile.MyProfileActivityContract;
import co.id.cakap.ui.myProfile.MyProfileActivityPresenter;
import co.id.cakap.utils.DateHelper;

public class RegistrationSuccessActivity extends AppCompatActivity implements RegistrationSuccessContract.View{
    @Inject
    RegistrationSuccessPresenter mRegistrationSuccessPresenter;

    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;
    @BindView(R.id.title_toolbar)
    TextView mTitleToolbar;

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
    @BindView(R.id.et_gender)
    EditText mEtGender;
    @BindView(R.id.radioGroupGender)
    RadioGroup mRadioGroupGender;
    @BindView(R.id.radio_male)
    RadioButton mRadioMale;
    @BindView(R.id.radio_female)
    RadioButton mRadioFemale;
    @BindView(R.id.et_place_of_birth)
    EditText mEtPob;
    @BindView(R.id.et_date_of_birth)
    TextView mEtDob;

    @BindView(R.id.linear_spinner_religion)
    LinearLayout mLinearSpinnerReligion;
    @BindView(R.id.spinner_religion)
    Spinner mSpinnerReligion;
    @BindView(R.id.linear_et_religion)
    LinearLayout mLinearEtReligion;
    @BindView(R.id.et_religion)
    EditText mEtReligion;

    @BindView(R.id.et_email)
    EditText mEtEmail;
    @BindView(R.id.et_phone_number)
    EditText mEtPhoneNumber;
    @BindView(R.id.et_mobile_number)
    EditText mEtMobileNumber;
    @BindView(R.id.et_address)
    EditText mEtAddress;

    @BindView(R.id.linear_spinner_province)
    LinearLayout mLinearSpinnerProvince;
    @BindView(R.id.spinner_province)
    Spinner mSpinnerProvince;
    @BindView(R.id.linear_et_province)
    LinearLayout mLinearEtProvince;
    @BindView(R.id.et_province)
    EditText mEtProvince;

    @BindView(R.id.linear_spinner_city)
    LinearLayout mLinearSpinnerCity;
    @BindView(R.id.spinner_city)
    Spinner mSpinnerCity;
    @BindView(R.id.linear_et_city)
    LinearLayout mLinearEtCity;
    @BindView(R.id.et_city)
    EditText mEtCity;

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

    @BindView(R.id.linear_spinner_bank)
    LinearLayout mLinearSpinnerBank;
    @BindView(R.id.spinner_bank)
    Spinner mSpinnerBank;
    @BindView(R.id.linear_et_bank)
    LinearLayout mLinearEtBank;
    @BindView(R.id.et_bank)
    EditText mEtBank;

    @BindView(R.id.et_branch_name)
    EditText mEtBranchName;
    @BindView(R.id.et_account_holder)
    EditText mEtAccountHolder;
    @BindView(R.id.et_account_number)
    EditText mEtAccountNumber;

    @BindView(R.id.linear_search_rec_id)
    LinearLayout mSearchRecId;
    @BindView(R.id.linear_search_sponsor_id)
    LinearLayout mSearchSponsorId;

    @BindView(R.id.relative_rec_id)
    RelativeLayout mRelativeRecId;
    @BindView(R.id.relative_sponsor_id)
    RelativeLayout mRelativeSponsorId;

    @BindView(R.id.linear_data_pendaftaran)
    LinearLayout mLinearDataPendaftaran;
    @BindView(R.id.linear_tanggal_expired)
    LinearLayout mLinearTanggalExpired;
    @BindView(R.id.et_tanggal_daftar)
    EditText mEtTanggalDaftar;
    @BindView(R.id.et_stockist_id_nama)
    EditText mEtStockistIdName;

    private String mTitle = "";
    private RegistrationSuccessData mSuccessData;
    private RegistrationSuccessContract.UserActionListener mUserActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_success);
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
        mUserActionListener = mRegistrationSuccessPresenter;
        mRegistrationSuccessPresenter.setView(this);

        Intent intent = getIntent();
        mTitle = intent.getStringExtra(Constant.TITLE_DETAIL);

        Bundle b = intent.getBundleExtra(Constant.REGISTRATION_DATA);
        mSuccessData = b.getParcelable(Constant.REGISTRATION_DATA);

        mTitleToolbar.setText(mTitle);
        setSuccessInputData();
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
    public void setErrorResponse(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_close)
    public void arrowBack(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(this, DashboardActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public void setSuccessInputData() {
        mLinearDataPendaftaran.setVisibility(View.VISIBLE);
        mLinearTanggalExpired.setVisibility(View.GONE);
        mEtTanggalDaftar.setText(DateHelper.getTimeNow());
//        mEtBCMBId.setText();

        mEtRecId.setEnabled(false);
        mEtRecId.setText(mSuccessData.getRecruiting_id());
        mEtRecId.setTextColor(getResources().getColor(R.color.curated_light));
        mSearchRecId.setVisibility(View.GONE);

        mEtRecName.setEnabled(false);
        mEtRecName.setText(mSuccessData.getRecruiting_name());
        mEtRecName.setTextColor(getResources().getColor(R.color.curated_light));

        mEtSponsorId.setEnabled(false);
        mEtSponsorId.setText(mSuccessData.getSponsor_id());
        mEtSponsorId.setTextColor(getResources().getColor(R.color.curated_light));
        mSearchSponsorId.setVisibility(View.GONE);

        mEtSponsorName.setEnabled(false);
        mEtSponsorName.setText(mSuccessData.getSponsor_name());
        mEtSponsorName.setTextColor(getResources().getColor(R.color.curated_light));

        mEtMemberId.setEnabled(false);
        mEtMemberId.setText(mSuccessData.getMember_id());
        mEtMemberId.setTextColor(getResources().getColor(R.color.curated_light));

        mEtFulllName.setEnabled(false);
        mEtFulllName.setText(mSuccessData.getFull_name());
        mEtFulllName.setTextColor(getResources().getColor(R.color.curated_light));

        mEtIdCard.setEnabled(false);
        mEtIdCard.setText(mSuccessData.getId_card());
        mEtIdCard.setTextColor(getResources().getColor(R.color.curated_light));

        mRadioGroupGender.setVisibility(View.GONE);
        mEtGender.setVisibility(View.VISIBLE);
        mEtGender.setEnabled(false);
        mEtGender.setText(mSuccessData.getGender());
        mEtGender.setTextColor(getResources().getColor(R.color.curated_light));

        mEtPob.setEnabled(false);
        mEtPob.setText(mSuccessData.getPob());
        mEtPob.setTextColor(getResources().getColor(R.color.curated_light));

        mEtDob.setEnabled(false);
        mEtDob.setText(mSuccessData.getDob());
        mEtDob.setTextColor(getResources().getColor(R.color.curated_light));

        mLinearSpinnerReligion.setVisibility(View.GONE);
        mLinearEtReligion.setVisibility(View.VISIBLE);
        mEtReligion.setEnabled(false);
        mEtReligion.setText(mSuccessData.getReligion());
        mEtReligion.setTextColor(getResources().getColor(R.color.curated_light));

        mEtEmail.setEnabled(false);
        mEtEmail.setText(mSuccessData.getEmail());
        mEtEmail.setTextColor(getResources().getColor(R.color.curated_light));

        mEtPhoneNumber.setEnabled(false);
        mEtPhoneNumber.setText(mSuccessData.getPhone_number());
        mEtPhoneNumber.setTextColor(getResources().getColor(R.color.curated_light));

        mEtMobileNumber.setEnabled(false);
        mEtMobileNumber.setText(mSuccessData.getMobile_number());
        mEtMobileNumber.setTextColor(getResources().getColor(R.color.curated_light));

        mEtAddress.setEnabled(false);
        mEtAddress.setText(mSuccessData.getAddress());
        mEtAddress.setTextColor(getResources().getColor(R.color.curated_light));

        mLinearSpinnerProvince.setVisibility(View.GONE);
        mLinearEtProvince.setVisibility(View.VISIBLE);
        mEtProvince.setEnabled(false);
        mEtProvince.setText(mSuccessData.getProvince());
        mEtProvince.setTextColor(getResources().getColor(R.color.curated_light));

        mLinearSpinnerCity.setVisibility(View.GONE);
        mLinearEtCity.setVisibility(View.VISIBLE);
        mEtCity.setEnabled(false);
        mEtCity.setText(mSuccessData.getCity());
        mEtCity.setTextColor(getResources().getColor(R.color.curated_light));

        mEtPostalCode.setEnabled(false);
        mEtPostalCode.setText(mSuccessData.getPostal_code());
        mEtPostalCode.setTextColor(getResources().getColor(R.color.curated_light));

        mEtActivationCode.setEnabled(false);
        mEtActivationCode.setText(mSuccessData.getActivation_code());
        mEtActivationCode.setTextColor(getResources().getColor(R.color.curated_light));

        mEtHeirName.setEnabled(false);
        mEtHeirName.setText(mSuccessData.getNama_pewaris());
        mEtHeirName.setTextColor(getResources().getColor(R.color.curated_light));

        mEtRelationship.setEnabled(false);
        mEtRelationship.setText(mSuccessData.getRelationship());
        mEtRelationship.setTextColor(getResources().getColor(R.color.curated_light));

        mLinearSpinnerBank.setVisibility(View.GONE);
        mLinearEtBank.setVisibility(View.VISIBLE);
        mEtBank.setEnabled(false);
        mEtBank.setText(mSuccessData.getBank_name());
        mEtBank.setTextColor(getResources().getColor(R.color.curated_light));

        mEtBranchName.setEnabled(false);
        mEtBranchName.setText(mSuccessData.getBranch());
        mEtBranchName.setTextColor(getResources().getColor(R.color.curated_light));

        mEtAccountHolder.setEnabled(false);
        mEtAccountHolder.setText(mSuccessData.getAccount_holder_name());
        mEtAccountHolder.setTextColor(getResources().getColor(R.color.curated_light));

        mEtAccountNumber.setEnabled(false);
        mEtAccountNumber.setText(mSuccessData.getAccount_number());
        mEtAccountNumber.setTextColor(getResources().getColor(R.color.curated_light));
    }
}
