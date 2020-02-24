package co.id.cakap.ui.pickUpDelivery;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.AddressAdapter;
import co.id.cakap.data.AddressData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.ui.reqInvoiceToCompany.ReqInvoiceToCompanyActivity;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.dialog.NewAddressDialog;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class PickUpDeliveryActivity extends AppCompatActivity implements PickUpDeliveryActivityContract.View, AdapterView.OnItemSelectedListener {
    @Inject
    PickUpDeliveryActivityPresenter mPickUpDeliveryActivityPresenter;

    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;
    @BindView(R.id.title_toolbar)
    TextView mTitle;
    @BindView(R.id.pick_up_delivery_spinner)
    Spinner mPickUpDeliverySpinner;
    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.fab_plus)
    FloatingActionButton mFabPlus;
//
    private boolean mIsChangeAddress = false;
    private Spinner mCitySpinner;
    private Spinner mProvinceSpinner;
    private EditText mAlamat;
    private TextView mSubmit;
    private AddressAdapter mListAdapter;
    private String mProvinceId = "";
    private String mKotaId = "";
    private String mKotaIdChange = "";
    private List<String> mCityIdList;
    private List<String> mProvinsiIdList;
    private List<String> mCityData = new ArrayList<>();
    private List<String> mProvinceData = new ArrayList<>();
    private PickUpDeliveryActivityContract.UserActionListener mUserActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_up_delivery);
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
        mUserActionListener = mPickUpDeliveryActivityPresenter;
        mPickUpDeliveryActivityPresenter.setView(this);
//        mRecyclerView.setNestedScrollingEnabled(false);
        mUserActionListener.getData();
        mUserActionListener.getProvinsi();

        mFab.hide();
        mTitle.setText(getString(R.string.req_invoice_to_com).toUpperCase());
        initSpinner();
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
    public void setAdapter(List<AddressData> resultData) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mListAdapter = new AddressAdapter(mRecyclerView, resultData, this);
        mRecyclerView.setAdapter(mListAdapter);
        OverScrollDecoratorHelper.setUpOverScroll(mRecyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                if (dy<0 && !mFabPlus.isShown()) {
                    mFabPlus.show();
                } else if(dy>0 && mFabPlus.isShown()) {
                    mFabPlus.hide();
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        hideProgressBar();
    }

    @Override
    public void changeAddress(String province, String kota, String address) {
        NewAddressDialog utils = new NewAddressDialog();
        Dialog dialog = utils.showDialog(this);

        mCitySpinner = dialog.findViewById(R.id.city_spinner);
        mProvinceSpinner = dialog.findViewById(R.id.province_spinner);
        mAlamat = dialog.findViewById(R.id.et_alamat);
        mSubmit = dialog.findViewById(R.id.submit_btn);

        initAddressSpinner();

        for (int i = 0; i < mProvinsiIdList.size(); i++) {
            if (mProvinsiIdList.get(i).equals(province)) {
                mProvinceSpinner.setSelection(i);
            }
        }

        showProgressBar();
        mKotaIdChange = kota;
        mIsChangeAddress = true;
        mPickUpDeliveryActivityPresenter.getKota(mProvinceId);

        mAlamat.setText(address);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d("province : " + mProvinceSpinner.getSelectedItem().toString());
                Logger.d("kota : " + mCitySpinner.getSelectedItem().toString());
                Logger.d("alamat : " + mAlamat.getText());
                dialog.hide();
            }
        });
    }

    @Override
    public void setProvinsiData(List<String> provinsiDataList, List<String> provinsiIdList) {
        mProvinceData = provinsiDataList;
        mProvinsiIdList = provinsiIdList;
    }

    @Override
    public void setKotaData(List<String> kotaDataList, List<String> kotaIdList) {
        mCityData = kotaDataList;
        mCityIdList = kotaIdList;

        mCitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                mKotaId = mCityIdList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this,
                R.layout.item_spinner, android.R.id.text1, mCityData);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCitySpinner.setAdapter(cityAdapter);

        if (mIsChangeAddress) {
            for (int i = 0; i < mCityIdList.size(); i++) {
                if (mCityIdList.get(i).equals(mKotaIdChange)) {
                    mCitySpinner.setSelection(i);
                    mIsChangeAddress = false;
                    hideProgressBar();
                }
            }
        } else {
            hideProgressBar();
        }
    }

    @OnClick(R.id.arrow_back)
    public void arrowBack(View view) {
        super.onBackPressed();
    }

    @OnClick(R.id.fab_plus)
    public void addAddress(View view) {
        NewAddressDialog utils = new NewAddressDialog();
        Dialog dialog = utils.showDialog(this);

        mCitySpinner = dialog.findViewById(R.id.city_spinner);
        mProvinceSpinner = dialog.findViewById(R.id.province_spinner);
        mAlamat = dialog.findViewById(R.id.et_alamat);
        mSubmit = dialog.findViewById(R.id.submit_btn);

        initAddressSpinner();

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d("province : " + mProvinceSpinner.getSelectedItem().toString());
                Logger.d("kota : " + mCitySpinner.getSelectedItem().toString());
                Logger.d("alamat : " + mAlamat.getText());
                dialog.hide();
            }
        });
    }

    public void initAddressSpinner() {
        mProvinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                mProvinceId = mProvinsiIdList.get(position);

                showProgressBar();
                mPickUpDeliveryActivityPresenter.getKota(mProvinceId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        ArrayAdapter<String> provinceeAdapter = new ArrayAdapter<String>(this,
                R.layout.item_spinner, android.R.id.text1, mProvinceData);
        provinceeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mProvinceSpinner.setAdapter(provinceeAdapter);
    }

    @OnClick(R.id.text_submit)
    public void actionSubmit(View view) {
        startActivity(new Intent(this, ReqInvoiceToCompanyActivity.class));
    }

    public void initSpinner() {
        mPickUpDeliverySpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> monthAdapter = ArrayAdapter.createFromResource(this,
                R.array.pick_up_delivery, R.layout.item_spinner);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPickUpDeliverySpinner.setAdapter(monthAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
