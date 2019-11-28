package co.id.cakap.ui.myProfile;

import android.app.Dialog;
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

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.data.ProfileData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.dialog.BottomDialogActivity;
import co.id.cakap.utils.dialog.PinDialog;
import co.id.cakap.utils.dialog.UserConfirmationDialog;

public class MyProfileActivity extends BottomDialogActivity implements MyProfileActivityContract.View{
    @Inject
    MyProfileActivityPresenter mMyProfileActivityPresenter;

    @BindView(R.id.main_progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.title_toolbar)
    TextView mTitle;
    @BindView(R.id.bottom_sheet)
    View bottomSheet;

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
    @BindView(R.id.et_bcmb_id_nama)
    EditText mEtBCMBId;

    @BindView(R.id.txt_save_top)
    TextView mTxtSaveTop;
    @BindView(R.id.txt_save_bottom)
    TextView mTxtSaveBottom;

    @BindView(R.id.linear_for_profile)
    LinearLayout mLinearForProfile;
    @BindView(R.id.linear_for_profile2)
    LinearLayout mLinearForProfile2;
    @BindView(R.id.et_npwp)
    EditText mEtNpwp;
    @BindView(R.id.et_pekerjaaan)
    EditText mEtPekerjaan;
    @BindView(R.id.et_whatsapp_number)
    EditText mEtWhatsappNumber;
    @BindView(R.id.spinner_status_pernikahan)
    Spinner mSpinnerStatusPernikahan;
    @BindView(R.id.spinner_jumlah_anak)
    Spinner mSpinnerJumlahAnak;

    private ProfileData mSuccessData;
    private MyProfileActivityContract.UserActionListener mUserActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
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
        mUserActionListener = mMyProfileActivityPresenter;
        mMyProfileActivityPresenter.setView(this);
        mBehavior = BottomSheetBehavior.from(bottomSheet);

        mTitle.setText(getString(R.string.my_profile).toUpperCase());
        setSuccessInputData();
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

    @OnClick(R.id.txt_save_top)
    public void saveTop(View view) {
        actionSave();
    }

    @OnClick(R.id.txt_save_bottom)
    public void saveBottom(View view) {
        actionSave();
    }

    public void actionSave() {
        UserConfirmationDialog utils = new UserConfirmationDialog();
        Dialog dialog = utils.showDialog(this);
        utils.setTitleChangeProfileDialog();
        utils.setNegativeActionGreen();

        TextView txtNo = (TextView) dialog.findViewById(R.id.no_act_btn);
        txtNo.setText("Periksa Kembali");
        dialog.findViewById(R.id.no_act_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(MyProfileActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
            }
        });

        TextView txtYes = (TextView) dialog.findViewById(R.id.yes_act_btn);
        txtYes.setText("Bersedia");
        dialog.findViewById(R.id.yes_act_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                openDialogPin();
            }
        });
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

                bottomSheetAlert(
                        getResources().getDrawable(R.drawable.ic_success_forgot_password),
                        getResources().getString(R.string.success_update_profile)
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

    public void setSuccessInputData() {
        mLinearDataPendaftaran.setVisibility(View.VISIBLE);
        mLinearForProfile.setVisibility(View.VISIBLE);
        mLinearForProfile2.setVisibility(View.VISIBLE);

        mEtRecId.setEnabled(false);
//        mEtRecId.setText(mSuccessData.getRecruiting_id());
        mEtRecId.setTextColor(getResources().getColor(R.color.curated_light));
        mSearchRecId.setVisibility(View.GONE);

        mEtRecName.setEnabled(false);
//        mEtRecName.setText(mSuccessData.getRecruiting_name());
        mEtRecName.setTextColor(getResources().getColor(R.color.curated_light));

        mEtSponsorId.setEnabled(false);
//        mEtSponsorId.setText(mSuccessData.getSponsor_id());
        mEtSponsorId.setTextColor(getResources().getColor(R.color.curated_light));
        mSearchSponsorId.setVisibility(View.GONE);

        mEtSponsorName.setEnabled(false);
//        mEtSponsorName.setText(mSuccessData.getSponsor_name());
        mEtSponsorName.setTextColor(getResources().getColor(R.color.curated_light));

        mEtMemberId.setEnabled(false);
//        mEtMemberId.setText(mSuccessData.getMember_id());
        mEtMemberId.setTextColor(getResources().getColor(R.color.curated_light));

        mEtFulllName.setEnabled(false);
//        mEtFulllName.setText(mSuccessData.getFull_name());
        mEtFulllName.setTextColor(getResources().getColor(R.color.curated_light));

        mEtIdCard.setEnabled(false);
//        mEtIdCard.setText(mSuccessData.getId_card());
        mEtIdCard.setTextColor(getResources().getColor(R.color.curated_light));

//        mRadioGroupGender.setVisibility(View.GONE);
//        mEtGender.setVisibility(View.VISIBLE);
//        mEtGender.setEnabled(false);
//        mEtGender.setText(mSuccessData.getGender());
//        mEtGender.setTextColor(getResources().getColor(R.color.curated_light));

//        mEtPob.setEnabled(false);
//        mEtPob.setText(mSuccessData.getPob());
//        mEtPob.setTextColor(getResources().getColor(R.color.curated_light));

        mEtDob.setEnabled(false);
//        mEtDob.setText(mSuccessData.getDob());
        mEtDob.setTextColor(getResources().getColor(R.color.curated_light));

//        mLinearSpinnerReligion.setVisibility(View.GONE);
//        mLinearEtReligion.setVisibility(View.VISIBLE);
//        mEtReligion.setEnabled(false);
//        mEtReligion.setText(mSuccessData.getReligion());
//        mEtReligion.setTextColor(getResources().getColor(R.color.curated_light));

//        mEtEmail.setEnabled(false);
//        mEtEmail.setText(mSuccessData.getEmail());
//        mEtEmail.setTextColor(getResources().getColor(R.color.curated_light));

//        mEtPhoneNumber.setEnabled(false);
//        mEtPhoneNumber.setText(mSuccessData.getPhone_number());
//        mEtPhoneNumber.setTextColor(getResources().getColor(R.color.curated_light));

//        mEtMobileNumber.setEnabled(false);
//        mEtMobileNumber.setText(mSuccessData.getMobile_number());
//        mEtMobileNumber.setTextColor(getResources().getColor(R.color.curated_light));

        mEtAddress.setEnabled(false);
//        mEtAddress.setText(mSuccessData.getAddress());
        mEtAddress.setTextColor(getResources().getColor(R.color.curated_light));

//        mLinearSpinnerProvince.setVisibility(View.GONE);
//        mLinearEtProvince.setVisibility(View.VISIBLE);
//        mEtProvince.setEnabled(false);
//        mEtProvince.setText(mSuccessData.getProvince());
//        mEtProvince.setTextColor(getResources().getColor(R.color.curated_light));

//        mLinearSpinnerCity.setVisibility(View.GONE);
//        mLinearEtCity.setVisibility(View.VISIBLE);
//        mEtCity.setEnabled(false);
//        mEtCity.setText(mSuccessData.getCity());
//        mEtCity.setTextColor(getResources().getColor(R.color.curated_light));

//        mEtPostalCode.setEnabled(false);
//        mEtPostalCode.setText(mSuccessData.getPostal_code());
//        mEtPostalCode.setTextColor(getResources().getColor(R.color.curated_light));

        mEtActivationCode.setEnabled(false);
//        mEtActivationCode.setText(mSuccessData.getActivation_code());
        mEtActivationCode.setTextColor(getResources().getColor(R.color.curated_light));

//        mEtHeirName.setEnabled(false);
//        mEtHeirName.setText(mSuccessData.getNama_pewaris());
//        mEtHeirName.setTextColor(getResources().getColor(R.color.curated_light));

//        mEtRelationship.setEnabled(false);
//        mEtRelationship.setText(mSuccessData.getRelationship());
//        mEtRelationship.setTextColor(getResources().getColor(R.color.curated_light));

        mLinearSpinnerBank.setVisibility(View.GONE);
        mLinearEtBank.setVisibility(View.VISIBLE);
        mEtBank.setEnabled(false);
//        mEtBank.setText(mSuccessData.getBank_name());
        mEtBank.setTextColor(getResources().getColor(R.color.curated_light));

        mEtBranchName.setEnabled(false);
//        mEtBranchName.setText(mSuccessData.getBranch());
        mEtBranchName.setTextColor(getResources().getColor(R.color.curated_light));

        mEtAccountHolder.setEnabled(false);
//        mEtAccountHolder.setText(mSuccessData.getAccount_holder_name());
        mEtAccountHolder.setTextColor(getResources().getColor(R.color.curated_light));

        mEtAccountNumber.setEnabled(false);
//        mEtAccountNumber.setText(mSuccessData.getAccount_number());
        mEtAccountNumber.setTextColor(getResources().getColor(R.color.curated_light));
    }
}
