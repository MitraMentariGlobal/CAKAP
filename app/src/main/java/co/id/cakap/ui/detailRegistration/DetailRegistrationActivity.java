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
import java.text.SimpleDateFormat;
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
import co.id.cakap.adapter.ActivityCashbillAdapter;
import co.id.cakap.adapter.ItemSearchRegistrationAdapter;
import co.id.cakap.data.ItemSearchRegistrationData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.cashbill.cashbillSuccess.CashbillSuccessActivity;
import co.id.cakap.utils.DateHelper;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.dialog.PinDialog;
import co.id.cakap.utils.dialog.SearchRegistrationDialog;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class DetailRegistrationActivity extends AppCompatActivity implements DetailRegistrationContract.View, AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {
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
    @BindView(R.id.radio_male)
    RadioButton mRadioMale;
    @BindView(R.id.radio_female)
    RadioButton mRadioFemale;

    @BindView(R.id.et_place_of_birth)
    EditText mEtPob;
    @BindView(R.id.et_date_of_birth)
    TextView mEtDob;
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
    @BindView(R.id.relative_sponsor_id)
    RelativeLayout mRelativeSponsorId;

    private int mDay;
    private int mMonth;
    private int mYear;
    private EditText mSearchEditText;
    private RecyclerView mRecyclerView;
    private Dialog mDialog;
    private ItemSearchRegistrationAdapter mListAdapter;
    private String mActivationCode = "";
    private String mMemberId = "";
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

    @OnClick(R.id.linear_search_rec_id)
    public void searchRecId(View view) {
        if (mEtRecId.getText().length() == 0) {
            mRelativeRecId.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
        } else {
            mRelativeRecId.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
            mUserActionListener.getDataRecId(mEtRecId.getText().toString());
            mEtRecId.setInputType(0);
        }
    }

    @OnClick(R.id.linear_search_sponsor_id)
    public void searchSponsorId(View view) {
        if (mEtSponsorId.getText().length() == 0) {
            mRelativeSponsorId.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
        } else {
            mRelativeSponsorId.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
            mUserActionListener.getDataSponsorId(mEtSponsorId.getText().toString());
            mEtSponsorId.setInputType(0);
        }
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

    @Override
    public void openDialogSearchData(List<ItemSearchRegistrationData> resultData, int idFrom) {
        SearchRegistrationDialog utils = new SearchRegistrationDialog();
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
        mSpinnerSex.setOnItemSelectedListener(this);
        mSpinnerReligion.setOnItemSelectedListener(this);
        mSpinnerProvince.setOnItemSelectedListener(this);
        mSpinnerCity.setOnItemSelectedListener(this);
        mSpinnerBank.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> sexAdapter = ArrayAdapter.createFromResource(this,
                R.array.sex_list, R.layout.item_spinner);
        sexAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerSex.setAdapter(sexAdapter);

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

        ArrayAdapter<String> provinceeAdapter = new ArrayAdapter<String>(this,
                R.layout.item_spinner, android.R.id.text1, mProvinceData);
        provinceeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerProvince.setAdapter(provinceeAdapter);

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

        String dob = mDay + "/" + mMonth + "/" + mYear;
        mEtDob.setText(dob);
    }

    @OnClick(R.id.et_date_of_birth)
    public void openDatePicker(View view) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        Date date = null;
        String dob = mEtDob.getText().toString();

        if (!dob.isEmpty()) {
            try {
                date = DateHelper.dateFormatSlash.parse(dob);
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
