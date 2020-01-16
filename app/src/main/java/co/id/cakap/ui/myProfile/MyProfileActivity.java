package co.id.cakap.ui.myProfile;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
import co.id.cakap.utils.DateHelper;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.dialog.BottomDialogActivity;
import co.id.cakap.utils.dialog.PinDialog;
import co.id.cakap.utils.dialog.UserConfirmationDialog;

public class MyProfileActivity extends BottomDialogActivity implements MyProfileActivityContract.View, AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener  {
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
    @BindView(R.id.linear_tanggal_daftar)
    LinearLayout mLinearTanggalDaftar;
    @BindView(R.id.linear_tanggal_expired)
    LinearLayout mLinearTanggalExpired;
    @BindView(R.id.linear_tanggal_expired2)
    LinearLayout mLinearTanggalExpired2;
    @BindView(R.id.et_tanggal_daftar)
    EditText mEtTanggalDaftar;
    @BindView(R.id.et_tanggal_expired)
    EditText mEtTanggalExpired;

    @BindView(R.id.linear_stockist_id_nama)
    LinearLayout mLinearStockistIdNama;
    @BindView(R.id.et_stockist_id_nama)
    EditText mEtStockistIdNama;

    @BindView(R.id.et_rec_id)
    EditText mEtRecId;
    @BindView(R.id.linear_rec_name)
    LinearLayout mLinearRecName;
    @BindView(R.id.et_rec_name)
    EditText mEtRecName;

    @BindView(R.id.et_sponsor_id)
    EditText mEtSponsorId;
    @BindView(R.id.linear_sponsor_name)
    LinearLayout mLinearSponsorName;
    @BindView(R.id.et_sponsor_name)
    EditText mEtSponsorName;

    @BindView(R.id.linear_member_id)
    LinearLayout mLinearMemberId;
    @BindView(R.id.et_member_id)
    EditText mEtMemberId;
    @BindView(R.id.linear_full_name)
    LinearLayout mLinearFullName;
    @BindView(R.id.et_full_name)
    EditText mEtFulllName;
    @BindView(R.id.linear_id_card)
    LinearLayout mLinearIdCard;
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
    @BindView(R.id.linear_date_of_birth)
    LinearLayout mLinearDateOfBirth;
    @BindView(R.id.txt_date_of_birth)
    TextView mTxtDob;
    @BindView(R.id.txt_error_date_of_birth)
    TextView mTxtErrorDateOfBirth;

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

    @BindView(R.id.linear_address)
    LinearLayout mLinearAddress;
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

    @BindView(R.id.linear_branch_name)
    LinearLayout mLinearBranchName;
    @BindView(R.id.et_branch_name)
    EditText mEtBranchName;
    @BindView(R.id.txt_error_branch_name)
    TextView mTxtErrorBranchName;

    @BindView(R.id.linear_account_holder)
    LinearLayout mLinearAAccountHolder;
    @BindView(R.id.et_account_holder)
    EditText mEtAccountHolder;

    @BindView(R.id.linear_account_number)
    LinearLayout mLinearAccountNumber;
    @BindView(R.id.et_account_number)
    EditText mEtAccountNumber;
    @BindView(R.id.txt_error_account_number)
    TextView mTxtErrorAccountNumber;

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
    @BindView(R.id.linear_npwp)
    LinearLayout mLinearNpwp;
    @BindView(R.id.linear_pekerjaan)
    LinearLayout mLinearPekerjaan;
    @BindView(R.id.et_pekerjaaan)
    EditText mEtPekerjaan;
    @BindView(R.id.et_whatsapp_number)
    EditText mEtWhatsappNumber;

    @BindView(R.id.txt_status_pernikahan)
    TextView mTxtStatusPernikahan;
    @BindView(R.id.linear_spinner_status_pernikahan)
    LinearLayout mLinearSpinnerStatusPernikahan;
    @BindView(R.id.spinner_status_pernikahan)
    Spinner mSpinnerStatusPernikahan;
    @BindView(R.id.linear_status_pernikahan)
    LinearLayout mLinearStatusPernikahan;
    @BindView(R.id.et_status_pernikahan)
    EditText mEtStatusPernikahan;

    @BindView(R.id.txt_jumlah_anak)
    TextView mTxtJumlahAnak;
    @BindView(R.id.linear_spinner_jumlah_anak)
    LinearLayout mLinearSpinnerJumlahAnak;
    @BindView(R.id.spinner_jumlah_anak)
    Spinner mSpinnerJumlahAnak;
    @BindView(R.id.linear_jumlah_anak)
    LinearLayout mLinearJumlahAnak;
    @BindView(R.id.et_jumlah_anak)
    EditText mEtJumlahAnak;

    private int mDay;
    private int mMonth;
    private int mYear;
    private boolean mIsFilledBank = false;
    private boolean mIsFilledDob = false;
    private boolean mIsFilledPostalCode = false;
    private String mBankId = "";
    private ProfileData mProfileData;
    private List<String> mBankIdList;
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

        try {
            if (Integer.parseInt(mProfileData.getBank_id()) == 0) {
                mIsFilledBank = false;
                mUserActionListener.getBank();
            } else {
                mIsFilledBank = true;
                setSuccessInputData();
            }
        } catch (Exception e) {
            mIsFilledBank = true;
            setSuccessInputData();
        }
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
    public void setBankData(List<String> bankDataList, List<String> bankIdList) {
        mBankIdList = bankIdList;
        mSpinnerBank.setOnItemSelectedListener(this);
        ArrayAdapter<String> bankAdapter = new ArrayAdapter<String>(this,
                R.layout.item_spinner, android.R.id.text1, bankDataList);
        bankAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerBank.setAdapter(bankAdapter);

        setSuccessInputData();
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
    public void setErrorDob(boolean isError) {
        if (isError) {
            mLinearDateOfBirth.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
            mTxtErrorDateOfBirth.setVisibility(View.VISIBLE);
        } else {
            mLinearDateOfBirth.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
            mTxtErrorDateOfBirth.setVisibility(View.GONE);
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
    public void setErrorKodePos(boolean isError, boolean isFilled) {
        if (isError) {
            mLinearPostalCode.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
            mTxtErrorPostalCode.setVisibility(View.VISIBLE);

            if (isFilled)
                mTxtErrorEmail.setText(getString(R.string.minimum_5));
            else
                mTxtErrorEmail.setText(getString(R.string.field_required));
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

    @Override
    public void setErrorCabang(boolean isError) {
        if (isError) {
            mLinearBranchName.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
            mTxtErrorBranchName.setVisibility(View.VISIBLE);
        } else {
            mLinearBranchName.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
            mTxtErrorBranchName.setVisibility(View.GONE);
        }
    }

    @Override
    public void setErrorNorek(boolean isError) {
        if (isError) {
            mLinearAccountNumber.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
            mTxtErrorAccountNumber.setVisibility(View.VISIBLE);
        } else {
            mLinearAccountNumber.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
            mTxtErrorAccountNumber.setVisibility(View.GONE);
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
        mUserActionListener.checkData(mEtPob.getText().toString(), mTxtDob.getText().toString(), mEtEmail.getText().toString(),
                mEtMobileNumber.getText().toString(), mEtPhoneNumber.getText().toString(), mEtPostalCode.getText().toString(),
                mEtHeirName.getText().toString(), mEtRelationship.getText().toString(), mEtBranchName.getText().toString(),
                mEtAccountNumber.getText().toString(), mIsFilledBank, mIsFilledDob, mIsFilledPostalCode);
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

                mUserActionListener.sendProfileData(mEtIdCard.getText().toString(), mEtAddress.getText().toString(),
                        mEtPostalCode.getText().toString(), mEtNpwp.getText().toString(), mProfileData.getStatus_mnkh(),
                        mProfileData.getSuami(), mSpinnerReligion.getSelectedItem().toString(), mEtJumlahAnak.getText().toString(),
                        mEtPekerjaan.getText().toString(), mEtRelationship.getText().toString(), mEtHeirName.getText().toString(),
                        mEtCity.getText().toString(), mEtEmail.getText().toString(), mEtPob.getText().toString(), gender,
                        DateHelper.changeToFormatBackend(mTxtDob.getText().toString()), mEtMobileNumber.getText().toString(),
                        mEtPhoneNumber.getText().toString(), mProfileData.getFax(), mProfileData.getCity_id(),
                        mEtProvince.getText().toString(), mProfileData.getBank_id(), mBankId, mEtAccountNumber.getText().toString(),
                        mEtBranchName.getText().toString(), mEtBranchName.getText().toString(), mEtFulllName.getText().toString(), pin);
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

        mLinearTanggalDaftar.setBackgroundColor(getResources().getColor(R.color.transparent));
        mEtTanggalDaftar.setPadding(0,0,0,0);
        mEtTanggalDaftar.setEnabled(false);
        mEtTanggalDaftar.setText(mProfileData.getTanggal_daftar());
        mEtTanggalDaftar.setTextColor(getResources().getColor(R.color.curated_light));

        mLinearTanggalExpired2.setBackgroundColor(getResources().getColor(R.color.transparent));
        mEtTanggalExpired.setPadding(0,0,0,0);
        mEtTanggalExpired.setEnabled(false);
        mEtTanggalExpired.setText(mProfileData.getTanggal_expired());
        mEtTanggalExpired.setTextColor(getResources().getColor(R.color.curated_light));

        mLinearStockistIdNama.setBackgroundColor(getResources().getColor(R.color.transparent));
        mEtStockistIdNama.setPadding(0,0,0,0);
        mEtStockistIdNama.setEnabled(false);
        mEtStockistIdNama.setText(mProfileData.getStockist_id() + " / " + mProfileData.getStockist_name());
        mEtStockistIdNama.setTextColor(getResources().getColor(R.color.curated_light));

        mRelativeRecId.setBackgroundColor(getResources().getColor(R.color.transparent));
        mEtRecId.setPadding(0,0,0,0);
        mEtRecId.setEnabled(false);
        mEtRecId.setText(mProfileData.getRecruiting_id());
        mEtRecId.setTextColor(getResources().getColor(R.color.curated_light));
        mSearchRecId.setVisibility(View.GONE);

        mLinearRecName.setBackgroundColor(getResources().getColor(R.color.transparent));
        mEtRecName.setPadding(0,0,0,0);
        mEtRecName.setEnabled(false);
        mEtRecName.setText(mProfileData.getRecruiting_name());
        mEtRecName.setTextColor(getResources().getColor(R.color.curated_light));

        mRelativeSponsorId.setBackgroundColor(getResources().getColor(R.color.transparent));
        mEtSponsorId.setPadding(0,0,0,0);
        mEtSponsorId.setEnabled(false);
        mEtSponsorId.setText(mProfileData.getSponsor_id());
        mEtSponsorId.setTextColor(getResources().getColor(R.color.curated_light));
        mSearchSponsorId.setVisibility(View.GONE);

        mLinearSponsorName.setBackgroundColor(getResources().getColor(R.color.transparent));
        mEtSponsorName.setPadding(0,0,0,0);
        mEtSponsorName.setEnabled(false);
        mEtSponsorName.setText(mProfileData.getSponsor_name());
        mEtSponsorName.setTextColor(getResources().getColor(R.color.curated_light));

        mLinearMemberId.setBackgroundColor(getResources().getColor(R.color.transparent));
        mEtMemberId.setPadding(0,0,0,0);
        mEtMemberId.setEnabled(false);
        mEtMemberId.setText(mProfileData.getMember_id());
        mEtMemberId.setTextColor(getResources().getColor(R.color.curated_light));

        mLinearFullName.setBackgroundColor(getResources().getColor(R.color.transparent));
        mEtFulllName.setPadding(0,0,0,0);
        mEtFulllName.setEnabled(false);
        mEtFulllName.setText(mProfileData.getFull_name());
        mEtFulllName.setTextColor(getResources().getColor(R.color.curated_light));

        mLinearIdCard.setBackgroundColor(getResources().getColor(R.color.transparent));
        mEtIdCard.setPadding(0,0,0,0);
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

        if (!mProfileData.getDob().equals("0000-00-00")) {
            mLinearDateOfBirth.setBackgroundColor(getResources().getColor(R.color.transparent));
            mTxtDob.setPadding(0,0,0,0);
            mTxtDob.setEnabled(false);
            mTxtDob.setText(mProfileData.getFdob());
            mIsFilledDob = true;
        } else {
            mIsFilledDob = false;
        }
//        mTxtDob.setTextColor(getResources().getColor(R.color.curated_light));

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

        mLinearNpwp.setBackgroundColor(getResources().getColor(R.color.transparent));
        mEtNpwp.setPadding(0,0,0,0);
        mEtNpwp.setEnabled(false);
        mEtNpwp.setText(mProfileData.getNonpwp());
        mEtNpwp.setTextColor(getResources().getColor(R.color.curated_light));

        mLinearPekerjaan.setVisibility(View.GONE);
//        mEtPekerjaan.setEnabled(false);
//        mEtPekerjaan.setText(mProfileData.getPekerjaan());
//        mEtPekerjaan.setTextColor(getResources().getColor(R.color.curated_light));

        mTxtStatusPernikahan.setVisibility(View.GONE);
        mLinearSpinnerStatusPernikahan.setVisibility(View.GONE);
        mLinearStatusPernikahan.setVisibility(View.GONE);
//        mEtStatusPernikahan.setText(mProfileData.getStatus_pernikahan());
//        mEtStatusPernikahan.setTextColor(getResources().getColor(R.color.curated_light));

        mTxtJumlahAnak.setVisibility(View.GONE);
        mLinearSpinnerJumlahAnak.setVisibility(View.GONE);
        mLinearJumlahAnak.setVisibility(View.GONE);
//        mEtJumlahAnak.setText(mProfileData.getJumlah_anak());
//        mEtJumlahAnak.setTextColor(getResources().getColor(R.color.curated_light));

//        mEtPhoneNumber.setEnabled(false);
        mEtPhoneNumber.setText(mProfileData.getPhone_number());
//        mEtPhoneNumber.setTextColor(getResources().getColor(R.color.curated_light));

//        mEtMobileNumber.setEnabled(false);
        mEtMobileNumber.setText(mProfileData.getMobile_number());
//        mEtMobileNumber.setTextColor(getResources().getColor(R.color.curated_light));

        mLinearAddress.setBackgroundColor(getResources().getColor(R.color.transparent));
        mEtAddress.setPadding(0,0,0,0);
        mEtAddress.setEnabled(false);
        mEtAddress.setText(mProfileData.getAddress());
        mEtAddress.setTextColor(getResources().getColor(R.color.curated_light));

        mLinearSpinnerProvince.setVisibility(View.GONE);
        mLinearEtProvince.setVisibility(View.VISIBLE);
        mLinearEtProvince.setBackgroundColor(getResources().getColor(R.color.transparent));
        mEtProvince.setPadding(0,0,0,0);
        mEtProvince.setEnabled(false);
        mEtProvince.setText(mProfileData.getProvince());
        mEtProvince.setTextColor(getResources().getColor(R.color.curated_light));

        mLinearSpinnerCity.setVisibility(View.GONE);
        mLinearEtCity.setVisibility(View.VISIBLE);
        mLinearEtCity.setBackgroundColor(getResources().getColor(R.color.transparent));
        mEtCity.setPadding(0,0,0,0);
        mEtCity.setEnabled(false);
        mEtCity.setText(mProfileData.getCity());
        mEtCity.setTextColor(getResources().getColor(R.color.curated_light));

        if (mProfileData.getPostal_code().length() >= 5) {
            mLinearPostalCode.setBackgroundColor(getResources().getColor(R.color.transparent));
            mEtPostalCode.setPadding(0,0,0,0);
            mEtPostalCode.setEnabled(false);
            mEtPostalCode.setText(mProfileData.getPostal_code());
            mEtPostalCode.setTextColor(getResources().getColor(R.color.curated_light));
            mIsFilledPostalCode = true;
        } else {
            mIsFilledPostalCode = false;
        }

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

        if (!mIsFilledBank) {
            mLinearSpinnerBank.setVisibility(View.VISIBLE);
            mLinearEtBank.setVisibility(View.GONE);

            mEtBranchName.setEnabled(true);
            mEtAccountHolder.setEnabled(false);
            mEtAccountHolder.setText(mProfileData.getFull_name());
            mEtAccountHolder.setTextColor(getResources().getColor(R.color.curated_light));
            mEtAccountNumber.setEnabled(true);
        } else {
            mLinearSpinnerBank.setVisibility(View.GONE);
            mLinearEtBank.setVisibility(View.VISIBLE);
            mLinearEtBank.setBackgroundColor(getResources().getColor(R.color.transparent));
            mEtBank.setPadding(0, 0, 0, 0);
            mEtBank.setEnabled(false);
            mEtBank.setText(mProfileData.getBank_name());
            mEtBank.setTextColor(getResources().getColor(R.color.curated_light));

            mLinearBranchName.setBackgroundColor(getResources().getColor(R.color.transparent));
            mEtBranchName.setPadding(0,0,0,0);
            mEtBranchName.setEnabled(false);
            mEtBranchName.setText(mProfileData.getBranch());
            mEtBranchName.setTextColor(getResources().getColor(R.color.curated_light));

            mLinearAAccountHolder.setBackgroundColor(getResources().getColor(R.color.transparent));
            mEtAccountHolder.setPadding(0,0,0,0);
            mEtAccountHolder.setEnabled(false);
            mEtAccountHolder.setText(mProfileData.getAccount_holder_name());
            mEtAccountHolder.setTextColor(getResources().getColor(R.color.curated_light));

            mLinearAccountNumber.setBackgroundColor(getResources().getColor(R.color.transparent));
            mEtAccountNumber.setPadding(0,0,0,0);
            mEtAccountNumber.setEnabled(false);
            mEtAccountNumber.setText(mProfileData.getAccount_number());
            mEtAccountNumber.setTextColor(getResources().getColor(R.color.curated_light));
        }

        hideProgressBar();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner_bank:
                mBankId = mBankIdList.get(position);
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        mDay = dayOfMonth;
        mMonth = month + 1;
        mYear = year;

        String dob = mDay + "-" + mMonth + "-" + mYear;
        mTxtDob.setText(dob);
    }

    @OnClick(R.id.txt_date_of_birth)
    public void openDatePicker(View view) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        Date date = null;
        String dob = mTxtDob.getText().toString();

        if (!dob.isEmpty()) {
            try {
                date = DateHelper.dateFormatFrontEnd.parse(dob);
                calendar.setTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        DatePickerDialog dialog = new DatePickerDialog(this, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
}
