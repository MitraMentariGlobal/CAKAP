package co.id.cakap.ui.myProfile;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.data.JenisKelaminData;
import co.id.cakap.data.ProfileData;
import co.id.cakap.data.ReligionData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.dialog.BottomDialogActivity;
import co.id.cakap.utils.dialog.PinDialog;
import co.id.cakap.utils.dialog.UserConfirmationDialog;

public class MyProfileActivity extends BottomDialogActivity implements MyProfileActivityContract.View, AdapterView.OnItemSelectedListener {
    @Inject
    MyProfileActivityPresenter mMyProfileActivityPresenter;

    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;
    @BindView(R.id.title_toolbar)
    TextView mTitle;
    @BindView(R.id.bottom_sheet)
    View bottomSheet;

    @BindView(R.id.linear_data_pendaftaran)
    LinearLayout mLinearDataPendaftaran;
    @BindView(R.id.linear_tanggal_expired)
    LinearLayout mLinearTanggalExpired;
    @BindView(R.id.et_tanggal_daftar)
    EditText mEtTanggalDaftar;
    @BindView(R.id.et_tanggal_expired)
    EditText mEtTanggalExpired;
    @BindView(R.id.et_stockist_id_nama)
    EditText mEtStockistIdNama;

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
    @BindView(R.id.linear_place_of_birth)
    LinearLayout mLinearPlaceOfBirth;
    @BindView(R.id.et_place_of_birth)
    EditText mEtPob;
    @BindView(R.id.txt_error_place_of_birth)
    TextView mTxtErrorPlaceOfBirth;
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
    @BindView(R.id.txt_error_religion)
    TextView mTxtErrorReligion;

    @BindView(R.id.linear_email)
    LinearLayout mLinearEmail;
    @BindView(R.id.et_email)
    EditText mEtEmail;
    @BindView(R.id.txt_error_email)
    TextView mTxtErrorEmail;

    @BindView(R.id.linear_phone_number)
    LinearLayout mLinearPhoneNumber;
    @BindView(R.id.et_phone_number)
    EditText mEtPhoneNumber;
    @BindView(R.id.txt_error_phone_number)
    TextView mTxtErrorPhoneNumber;

    @BindView(R.id.linear_mobile_number)
    LinearLayout mLinearMobileNumber;
    @BindView(R.id.et_mobile_number)
    EditText mEtMobileNumber;
    @BindView(R.id.txt_error_mobile_number)
    TextView mTxtErrorMobileNumber;

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

    @BindView(R.id.linear_postal_code)
    LinearLayout mLinearPostalCode;
    @BindView(R.id.et_postal_code)
    EditText mEtPostalCode;
    @BindView(R.id.txt_error_postal_code)
    TextView mTxtErrorPostalCode;

    @BindView(R.id.linear_activation_code)
    LinearLayout mLinearActivationCode;
    @BindView(R.id.et_activation_code)
    EditText mEtActivationCode;

    @BindView(R.id.et_couple_name)
    EditText mEtCoupleName;

    @BindView(R.id.linear_heir_name)
    LinearLayout mLinearHeirName;
    @BindView(R.id.et_heir_name)
    EditText mEtHeirName;
    @BindView(R.id.txt_error_heir_name)
    TextView mTxtErrorHeirName;

    @BindView(R.id.linear_relatoinship)
    LinearLayout mLinearRelationship;
    @BindView(R.id.et_relationship)
    EditText mEtRelationship;
    @BindView(R.id.txt_error_relationship)
    TextView mTxtErrorRelationship;

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

    @BindView(R.id.linear_spinner_status_pernikahan)
    LinearLayout mLinearSpinnerStatusPernikahan;
    @BindView(R.id.spinner_status_pernikahan)
    Spinner mSpinnerStatusPernikahan;
    @BindView(R.id.linear_status_pernikahan)
    LinearLayout mLinearStatusPernikahan;
    @BindView(R.id.et_status_pernikahan)
    EditText mEtStatusPernikahan;

    @BindView(R.id.linear_spinner_jumlah_anak)
    LinearLayout mLinearSpinnerJumlahAnak;
    @BindView(R.id.spinner_jumlah_anak)
    Spinner mSpinnerJumlahAnak;
    @BindView(R.id.linear_jumlah_anak)
    LinearLayout mLinearJumlahAnak;
    @BindView(R.id.et_jumlah_anak)
    EditText mEtJumlahAnak;

    private ProfileData mProfileData;
    private List<JenisKelaminData> mJenisKelaminDataList;
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
        mUserActionListener.getJenisKelamin();
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

    @Override
    public void setProfileData(ProfileData profileData) {
        mProfileData = profileData;

        setSuccessInputData();
    }

    @Override
    public void setJenisKelaminData(List<JenisKelaminData> jenisKelaminDataList) {
        mJenisKelaminDataList = jenisKelaminDataList;
        mRadioMale.setText(jenisKelaminDataList.get(0).getId());
        mRadioFemale.setText(jenisKelaminDataList.get(1).getId());
    }

    @Override
    public void setReligionData(List<String> religionDataList) {
        mSpinnerReligion.setOnItemSelectedListener(this);
        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(this,
                R.layout.item_spinner, android.R.id.text1, religionDataList);
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerReligion.setAdapter(provinceAdapter);

    }

    @Override
    public void setErrorPob(boolean isError) {
        if (isError) {
            mLinearPlaceOfBirth.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
            mTxtErrorPlaceOfBirth.setVisibility(View.VISIBLE);
        } else {
            mLinearPlaceOfBirth.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
            mTxtErrorPlaceOfBirth.setVisibility(View.GONE);
        }
    }

    @Override
    public void setErrorReligion(boolean isError) {
        if (isError) {
            mLinearSpinnerReligion.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
            mTxtErrorReligion.setVisibility(View.VISIBLE);
        } else {
            mLinearSpinnerReligion.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
            mTxtErrorReligion.setVisibility(View.GONE);
        }
    }

    @Override
    public void setErrorEmail(boolean isError, boolean isFilled) {
        if (isError) {
            mLinearEmail.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
            mTxtErrorEmail.setVisibility(View.VISIBLE);

            if (isFilled)
                mTxtErrorEmail.setText(getString(R.string.field_not_match));
            else
                mTxtErrorEmail.setText(getString(R.string.field_required));

        } else {
            mLinearEmail.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
            mTxtErrorEmail.setVisibility(View.GONE);
        }
    }

    @Override
    public void setErrorHp(boolean isError) {
        if (isError) {
            mLinearMobileNumber.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
            mTxtErrorMobileNumber.setVisibility(View.VISIBLE);
        } else {
            mLinearMobileNumber.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
            mTxtErrorMobileNumber.setVisibility(View.GONE);
        }
    }

    @Override
    public void setErrorTelp(boolean isError) {
        if (isError) {
            mLinearPhoneNumber.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
            mTxtErrorPhoneNumber.setVisibility(View.VISIBLE);
        } else {
            mLinearPhoneNumber.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
            mTxtErrorPhoneNumber.setVisibility(View.GONE);
        }
    }

    @Override
    public void setErrorKodePos(boolean isError) {
        if (isError) {
            mLinearPostalCode.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
            mTxtErrorPostalCode.setVisibility(View.VISIBLE);
        } else {
            mLinearPostalCode.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
            mTxtErrorPostalCode.setVisibility(View.GONE);
        }
    }

    @Override
    public void setErrorNamaPewaris(boolean isError) {
        if (isError) {
            mLinearHeirName.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
            mTxtErrorHeirName.setVisibility(View.VISIBLE);
        } else {
            mLinearHeirName.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
            mTxtErrorHeirName.setVisibility(View.GONE);
        }
    }

    @Override
    public void setErrorHubungan(boolean isError) {
        if (isError) {
            mLinearRelationship.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
            mTxtErrorRelationship.setVisibility(View.VISIBLE);
        } else {
            mLinearRelationship.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
            mTxtErrorRelationship.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.arrow_back)
    public void arrowBack(View view) {
        super.onBackPressed();
    }

    @OnClick(R.id.txt_save_top)
    public void saveTop(View view) {
        checkData();
    }

    @OnClick(R.id.txt_save_bottom)
    public void saveBottom(View view) {
        checkData();
    }

    public void checkData() {
        mUserActionListener.checkData(mEtPob.getText().toString(), mSpinnerReligion.getSelectedItem().toString(), mEtEmail.getText().toString(),
                mEtMobileNumber.getText().toString(), mEtPhoneNumber.getText().toString(), mEtPostalCode.getText().toString(),
                mEtHeirName.getText().toString(), mEtRelationship.getText().toString());
    }

    @Override
    public void openDialogCheck() {
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

    @Override
    public void openSuccessBottomSheet() {
        bottomSheetAlert(
                getResources().getDrawable(R.drawable.ic_success_forgot_password),
                getResources().getString(R.string.success_update_profile)
        );
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

                showProgressBar();

                String gender = "";
                if (mRadioMale.isChecked())
                    gender = mRadioMale.getText().toString();
                else
                    gender = mRadioFemale.getText().toString();

                mUserActionListener.sendProfileData(mEtIdCard.getText().toString(), mEtAddress.getText().toString(), mEtPostalCode.getText().toString(),
                        mEtNpwp.getText().toString(), mEtStatusPernikahan.getText().toString(), mProfileData.getSuami(), mSpinnerReligion.getSelectedItem().toString(),
                        mEtJumlahAnak.getText().toString(), mEtPekerjaan.getText().toString(), mEtRelationship.getText().toString(), mEtHeirName.getText().toString(),
                        mEtCity.getText().toString(), mEtEmail.getText().toString(), mEtPob.getText().toString(), gender, "", mEtMobileNumber.getText().toString(),
                        mEtPhoneNumber.getText().toString(), mProfileData.getFax(), mProfileData.getCity_id(), mEtProvince.getText().toString(), mProfileData.getBank_id(),
                        mEtAccountNumber.getText().toString(), "", mEtBranchName.getText().toString(), pin);
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
//        mLinearForProfile2.setVisibility(View.VISIBLE);

        mEtTanggalDaftar.setEnabled(false);
        mEtTanggalDaftar.setText(mProfileData.getTanggal_daftar());
        mEtTanggalDaftar.setTextColor(getResources().getColor(R.color.curated_light));

        mEtTanggalExpired.setEnabled(false);
        mEtTanggalExpired.setText(mProfileData.getTanggal_expired());
        mEtTanggalExpired.setTextColor(getResources().getColor(R.color.curated_light));

        mEtStockistIdNama.setEnabled(false);
        mEtStockistIdNama.setText(mProfileData.getStockist_id() + " / " + mProfileData.getStockist_name());
        mEtStockistIdNama.setTextColor(getResources().getColor(R.color.curated_light));

        mEtRecId.setEnabled(false);
        mEtRecId.setText(mProfileData.getRecruiting_id());
        mEtRecId.setTextColor(getResources().getColor(R.color.curated_light));
        mSearchRecId.setVisibility(View.GONE);

        mEtRecName.setEnabled(false);
        mEtRecName.setText(mProfileData.getRecruiting_name());
        mEtRecName.setTextColor(getResources().getColor(R.color.curated_light));

        mEtSponsorId.setEnabled(false);
        mEtSponsorId.setText(mProfileData.getSponsor_id());
        mEtSponsorId.setTextColor(getResources().getColor(R.color.curated_light));
        mSearchSponsorId.setVisibility(View.GONE);

        mEtSponsorName.setEnabled(false);
        mEtSponsorName.setText(mProfileData.getSponsor_name());
        mEtSponsorName.setTextColor(getResources().getColor(R.color.curated_light));

        mEtMemberId.setEnabled(false);
        mEtMemberId.setText(mProfileData.getMember_id());
        mEtMemberId.setTextColor(getResources().getColor(R.color.curated_light));

        mEtFulllName.setEnabled(false);
        mEtFulllName.setText(mProfileData.getFull_name());
        mEtFulllName.setTextColor(getResources().getColor(R.color.curated_light));

        mEtIdCard.setEnabled(false);
        mEtIdCard.setText(mProfileData.getId_card());
        mEtIdCard.setTextColor(getResources().getColor(R.color.curated_light));

//        mRadioGroupGender.setVisibility(View.GONE);
//        mEtGender.setVisibility(View.VISIBLE);
//        mEtGender.setEnabled(false);
//        mEtGender.setText(mProfileData.getGender());
//        mEtGender.setTextColor(getResources().getColor(R.color.curated_light));

        if (mProfileData.getGender().equals(mJenisKelaminDataList.get(0).getId())) {
            mRadioMale.setChecked(true);
        } else {
            mRadioFemale.setChecked(true);
        }

//        mEtPob.setEnabled(false);
        mEtPob.setText(mProfileData.getPob());
//        mEtPob.setTextColor(getResources().getColor(R.color.curated_light));

        mEtDob.setEnabled(false);
        mEtDob.setText(mProfileData.getDob());
        mEtDob.setTextColor(getResources().getColor(R.color.curated_light));

//        mLinearSpinnerReligion.setVisibility(View.GONE);
//        mLinearEtReligion.setVisibility(View.VISIBLE);
//        mEtReligion.setEnabled(false);
//        mEtReligion.setText(mProfileData.getReligion());
//        mEtReligion.setTextColor(getResources().getColor(R.color.curated_light));

        for (int i = 0; i < mSpinnerReligion.getAdapter().getCount(); i++) {
            if(mSpinnerReligion.getAdapter().getItem(i).toString().contains(String.valueOf(mProfileData.getReligion()))) {
                mSpinnerReligion.setSelection(i);
            }
        }

//        mEtEmail.setEnabled(false);
        mEtEmail.setText(mProfileData.getEmail());
//        mEtEmail.setTextColor(getResources().getColor(R.color.curated_light));

        mEtNpwp.setEnabled(false);
        mEtNpwp.setText(mProfileData.getNpwp());
        mEtNpwp.setTextColor(getResources().getColor(R.color.curated_light));

        mEtPekerjaan.setEnabled(false);
        mEtPekerjaan.setText(mProfileData.getPekerjaan());
        mEtPekerjaan.setTextColor(getResources().getColor(R.color.curated_light));

        mLinearSpinnerStatusPernikahan.setVisibility(View.GONE);
        mLinearStatusPernikahan.setVisibility(View.VISIBLE);
        mEtStatusPernikahan.setText(mProfileData.getStatus_pernikahan());
        mEtStatusPernikahan.setTextColor(getResources().getColor(R.color.curated_light));

        mLinearSpinnerJumlahAnak.setVisibility(View.GONE);
        mLinearJumlahAnak.setVisibility(View.VISIBLE);
        mEtJumlahAnak.setText(mProfileData.getJumlah_anak());
        mEtJumlahAnak.setTextColor(getResources().getColor(R.color.curated_light));

//        mEtPhoneNumber.setEnabled(false);
        mEtPhoneNumber.setText(mProfileData.getPhone_number());
//        mEtPhoneNumber.setTextColor(getResources().getColor(R.color.curated_light));

//        mEtMobileNumber.setEnabled(false);
        mEtMobileNumber.setText(mProfileData.getMobile_number());
//        mEtMobileNumber.setTextColor(getResources().getColor(R.color.curated_light));

        mEtAddress.setEnabled(false);
        mEtAddress.setText(mProfileData.getAddress());
        mEtAddress.setTextColor(getResources().getColor(R.color.curated_light));

        mLinearSpinnerProvince.setVisibility(View.GONE);
        mLinearEtProvince.setVisibility(View.VISIBLE);
        mEtProvince.setEnabled(false);
        mEtProvince.setText(mProfileData.getProvince());
        mEtProvince.setTextColor(getResources().getColor(R.color.curated_light));

        mLinearSpinnerCity.setVisibility(View.GONE);
        mLinearEtCity.setVisibility(View.VISIBLE);
        mEtCity.setEnabled(false);
        mEtCity.setText(mProfileData.getCity());
        mEtCity.setTextColor(getResources().getColor(R.color.curated_light));

//        mEtPostalCode.setEnabled(false);
        mEtPostalCode.setText(mProfileData.getPostal_code());
//        mEtPostalCode.setTextColor(getResources().getColor(R.color.curated_light));

        mLinearActivationCode.setVisibility(View.GONE);
//        mEtActivationCode.setEnabled(false);
//        mEtActivationCode.setText(mProfileData.getActivation_code());
//        mEtActivationCode.setTextColor(getResources().getColor(R.color.curated_light));

        String[] ahliWaris = mProfileData.getNama_pewaris().split(";");
//        mEtHeirName.setEnabled(false);
        mEtHeirName.setText(ahliWaris[0]);
//        mEtHeirName.setTextColor(getResources().getColor(R.color.curated_light));

//        mEtRelationship.setEnabled(false);
//        mEtRelationship.setText(mProfileData.getRelationship());
        mEtRelationship.setText(ahliWaris[1]);
//        mEtRelationship.setTextColor(getResources().getColor(R.color.curated_light));

        mLinearSpinnerBank.setVisibility(View.GONE);
        mLinearEtBank.setVisibility(View.VISIBLE);
        mEtBank.setEnabled(false);
        mEtBank.setText(mProfileData.getBank_name());
        mEtBank.setTextColor(getResources().getColor(R.color.curated_light));

        mEtBranchName.setEnabled(false);
        mEtBranchName.setText(mProfileData.getBranch());
        mEtBranchName.setTextColor(getResources().getColor(R.color.curated_light));

        mEtAccountHolder.setEnabled(false);
        mEtAccountHolder.setText(mProfileData.getAccount_holder_name());
        mEtAccountHolder.setTextColor(getResources().getColor(R.color.curated_light));

        mEtAccountNumber.setEnabled(false);
        mEtAccountNumber.setText(mProfileData.getAccount_number());
        mEtAccountNumber.setTextColor(getResources().getColor(R.color.curated_light));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
