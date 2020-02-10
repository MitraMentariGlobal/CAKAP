package co.id.cakap.ui.detailRegistration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.ItemSearchRegistrationAdapter;
import co.id.cakap.data.ItemSearchRegistrationData;
import co.id.cakap.data.RegistrationSuccessData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.registration.registrationSuccess.RegistrationSuccessActivity;
import co.id.cakap.utils.DateHelper;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.dialog.PinDialog;
import co.id.cakap.utils.dialog.SearchDataDialog;
import co.id.cakap.utils.dialog.UserConfirmationDialog;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class DetailRegistrationActivity extends AppCompatActivity implements DetailRegistrationContract.View, AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {
    @Inject
    DetailRegistrationPresenter mDetailRegistrationPresenter;

    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;
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
    @BindView(R.id.radio_male)
    RadioButton mRadioMale;
    @BindView(R.id.radio_female)
    RadioButton mRadioFemale;

    @BindView(R.id.et_place_of_birth)
    EditText mEtPob;
    @BindView(R.id.txt_date_of_birth)
    TextView mTxtDob;
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
    @BindView(R.id.et_account_number)
    EditText mEtAccountNumber;

    @BindView(R.id.linear_search_rec_id)
    LinearLayout mSearchRecId;
    @BindView(R.id.linear_search_sponsor_id)
    LinearLayout mSearchSponsorId;

    @BindView(R.id.relative_rec_id)
    RelativeLayout mRelativeRecId;
    @BindView(R.id.txt_error_rec_id)
    TextView mTxtErrorRecId;
    @BindView(R.id.relative_sponsor_id)
    RelativeLayout mRelativeSponsorId;
    @BindView(R.id.txt_error_sponsor_id)
    TextView mTxtErrorSponsorId;

    private int mDay;
    private int mMonth;
    private int mYear;
    private EditText mSearchEditText;
    private RecyclerView mRecyclerView;
    private Dialog mDialog;
    private ItemSearchRegistrationAdapter mListAdapter;
    private String mActivationCode = "";
    private String mMemberId = "";
    private RegistrationSuccessData mSuccessData;
    private List<String> mCityData = new ArrayList<>();
    private List<String> mProvinceData = new ArrayList<>();
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

        mEtActivationCode.setText(mActivationCode);
        mEtMemberId.setText(mMemberId);

        mTitle.setText(getString(R.string.registration).toUpperCase());
        mRadioMale.setChecked(true);
        setupOnFocusListenerName();
        initSpinner();
        hideProgressBar();




        mEtFulllName.setText("test full name");
        mEtIdCard.setText("3475639200129943");
        mEtPob.setText("test tempat lahir");
        mEtEmail.setText("test email");
        mEtPhoneNumber.setText("021123456789");
        mEtMobileNumber.setText("081987654321");
        mEtAddress.setText("test address");
        mEtPostalCode.setText("34321");
        mEtHeirName.setText("test nama pewaris");
        mEtRelationship.setText("test hubungan pewaris");
        mEtBranchName.setText("test cabang");
        mEtAccountHolder.setText("test full name");
        mEtAccountNumber.setText("633764383847");
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

    @OnClick(R.id.arrow_back)
    public void arrowBack(View view) {
        super.onBackPressed();
    }

    @OnClick(R.id.linear_search_rec_id)
    public void searchRecId(View view) {
        if (mEtRecId.getText().length() < 3) {
            mRelativeRecId.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
            mTxtErrorRecId.setVisibility(View.VISIBLE);
        } else {
            mRelativeRecId.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
            mTxtErrorRecId.setVisibility(View.GONE);
            mUserActionListener.getDataRecId(mEtRecId.getText().toString());
            mEtRecId.setInputType(0);
        }
    }

    @OnClick(R.id.linear_search_sponsor_id)
    public void searchSponsorId(View view) {
        if (mEtSponsorId.getText().length() < 3) {
            mRelativeSponsorId.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
            mTxtErrorSponsorId.setVisibility(View.VISIBLE);
        } else {
            mRelativeSponsorId.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
            mTxtErrorSponsorId.setVisibility(View.GONE);
            mUserActionListener.getDataSponsorId(mEtSponsorId.getText().toString());
            mEtSponsorId.setInputType(0);
        }
    }

    @OnClick(R.id.text_process)
    public void actionProcess(View view) {
        UserConfirmationDialog utils = new UserConfirmationDialog();
        Dialog dialog = utils.showDialog(this);
        utils.setTitleDialog();
        utils.setNegativeActionGreen();

        TextView txtNo = (TextView) dialog.findViewById(R.id.no_act_btn);
        txtNo.setText("Periksa Kembali");
        dialog.findViewById(R.id.no_act_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(DetailRegistrationActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
            }
        });

        TextView txtYes = (TextView) dialog.findViewById(R.id.yes_act_btn);
        txtYes.setText("Bersedia");
        dialog.findViewById(R.id.yes_act_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                String gender = "";
                if (mRadioMale.isChecked())
                    gender = "Laki - Laki";
                else
                    gender = "Perempuan";

                mSuccessData = new RegistrationSuccessData(
                        mEtRecId.getText().toString(),
                        mEtRecName.getText().toString(),
                        mEtSponsorId.getText().toString(),
                        mEtSponsorName.getText().toString(),
                        mEtMemberId.getText().toString(),
                        mEtFulllName.getText().toString(),
                        mEtIdCard.getText().toString(),
                        gender,
                        mEtPob.getText().toString(),
                        mTxtDob.getText().toString(),
                        mSpinnerReligion.getSelectedItem().toString(),
                        mEtEmail.getText().toString(),
                        mEtPhoneNumber.getText().toString(),
                        mEtMobileNumber.getText().toString(),
                        mEtAddress.getText().toString(),
                        mSpinnerProvince.getSelectedItem().toString(),
                        mSpinnerCity.getSelectedItem().toString(),
                        mEtPostalCode.getText().toString(),
                        mEtActivationCode.getText().toString(),
                        mEtHeirName.getText().toString(),
                        mEtRelationship.getText().toString(),
                        mSpinnerBank.getSelectedItem().toString(),
                        mEtBranchName.getText().toString(),
                        mEtAccountHolder.getText().toString(),
                        mEtAccountNumber.getText().toString()
                );

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

                Bundle b = new Bundle();
                b.putParcelable(Constant.REGISTRATION_DATA, mSuccessData);

                Intent intent = new Intent(getApplicationContext(), RegistrationSuccessActivity.class);
                intent.putExtra(Constant.TITLE_DETAIL, getResources().getString(R.string.registration).toUpperCase());
                intent.putExtra(Constant.REGISTRATION_DATA, b);
                startActivity(intent);
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

    @Override
    public void openDialogSearchData(List<ItemSearchRegistrationData> resultData, int idFrom) {
        SearchDataDialog utils = new SearchDataDialog();
        mDialog = utils.showDialog(this);

        mSearchEditText = mDialog.findViewById(R.id.et_search);
        mRecyclerView = mDialog.findViewById(R.id.main_list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mListAdapter = new ItemSearchRegistrationAdapter(resultData, this, idFrom);
        mRecyclerView.setAdapter(mListAdapter);
        OverScrollDecoratorHelper.setUpOverScroll(mRecyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
        setupOnFocusListener(mSearchEditText);
    }

    @Override
    public void hideDialogSearchData(ItemSearchRegistrationData itemSearchRegistrationData, int idFrom) {
        mDialog.dismiss();
        if (idFrom == 1) {
            mEtRecId.setText(itemSearchRegistrationData.getMember_id());
            mEtRecName.setText(itemSearchRegistrationData.getName());
        } else if (idFrom == 2) {
            mEtSponsorId.setText(itemSearchRegistrationData.getMember_id());
            mEtSponsorName.setText(itemSearchRegistrationData.getName());
        }
    }

    private void setupOnFocusListener(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                Logger.d("arg0 : " + arg0);
                String text = editText.getText().toString().toLowerCase(Locale.getDefault());
                if (mListAdapter != null) mListAdapter.getFilter().filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void setupOnFocusListenerName() {
        mEtFulllName.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                Logger.d("arg0 : " + arg0);
                String text = arg0.toString();
                mEtAccountHolder.setText(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });
    }

    public void initSpinner() {
        mSpinnerReligion.setOnItemSelectedListener(this);
        mSpinnerProvince.setOnItemSelectedListener(this);
        mSpinnerCity.setOnItemSelectedListener(this);
        mSpinnerBank.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> religionAdapter = ArrayAdapter.createFromResource(this,
                R.array.religion_list, R.layout.item_spinner);
        religionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerReligion.setAdapter(religionAdapter);

        mCityData.add(" - ");
        mCityData.add("Bekasi");
        mCityData.add("Bandung");
        mCityData.add("Solo");
        mCityData.add("Malang");
        mCityData.add("Bali");
        mCityData.add("Lombok");
        mCityData.add("Aceh");
        mCityData.add("Pontianak");

        mProvinceData.add(" - ");
        mProvinceData.add("Jawa Barat");
        mProvinceData.add("Jawa Timur");
        mProvinceData.add("Jawa Tengah");
        mProvinceData.add("Jambi");
        mProvinceData.add("Kalimantan Timur");
        mProvinceData.add("Sulawesi Utara");

        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(this,
                R.layout.item_spinner, android.R.id.text1, mProvinceData);
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerProvince.setAdapter(provinceAdapter);

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this,
                R.layout.item_spinner, android.R.id.text1, mCityData);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerCity.setAdapter(cityAdapter);

        ArrayAdapter<CharSequence> bankAdapter = ArrayAdapter.createFromResource(this,
                R.array.bank_list, R.layout.item_spinner);
        bankAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerBank.setAdapter(bankAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

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
