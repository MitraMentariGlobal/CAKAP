package co.id.cakap.ui.feeBCMB;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.data.FeeBCMBData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.ui.cashbill.CashbillActivityContract;
import co.id.cakap.ui.cashbill.CashbillActivityPresenter;

public class FeeBcmbActivity extends AppCompatActivity implements FeeBcmbActivityContract.View, AdapterView.OnItemSelectedListener {
    @Inject
    FeeBcmbActivityPresenter mFeeBcmbActivityPresenter;

    @BindView(R.id.main_progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.title_toolbar)
    TextView mTitle;
    @BindView(R.id.month_spinner)
    Spinner mMonthSpinner;
    @BindView(R.id.year_spinner)
    Spinner mYearSpinner;

    @BindView(R.id.txt_oms_aft_ppn)
    TextView txt_oms_aft_ppn;
    @BindView(R.id.txt_oms_prd_aft_ppn)
    TextView txt_oms_prd_aft_ppn;
    @BindView(R.id.txt_stat_kit_reg)
    TextView txt_stat_kit_reg;
    @BindView(R.id.txt_pkt_reg_leng)
    TextView txt_pkt_reg_leng;

    @BindView(R.id.txt_tot_oms_prd)
    TextView txt_tot_oms_prd;
    @BindView(R.id.txt_tmbh_bns_tot_oms)
    TextView txt_tmbh_bns_tot_oms;
    @BindView(R.id.txt_stat_kit_bsc)
    TextView txt_stat_kit_bsc;
    @BindView(R.id.txt_pkt_lngkp)
    TextView txt_pkt_lngkp;
    @BindView(R.id.txt_bns_reff_mb)
    TextView txt_bns_reff_mb;
    @BindView(R.id.txt_bns_kit_v_bless)
    TextView txt_bns_kit_v_bless;
    @BindView(R.id.txt_total_fee)
    TextView txt_total_fee;

    private FeeBcmbActivityContract.UserActionListener mUserActionListener;
    private List<String> mYearData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_bcmb);
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
        mUserActionListener = mFeeBcmbActivityPresenter;
        mFeeBcmbActivityPresenter.setView(this);
        mUserActionListener.getData();

        mTitle.setText(getString(R.string.fee_bcmb).toUpperCase());
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

    @Override
    public void setData(FeeBCMBData feeBCMBData) {
        txt_oms_aft_ppn.setText(feeBCMBData.getTotal_omset_setelah_ppn());
        txt_oms_prd_aft_ppn.setText(feeBCMBData.getTotal_omset_product_setelah_ppn());
        txt_stat_kit_reg.setText(feeBCMBData.getTotal_stater_kit_regular());
        txt_pkt_reg_leng.setText(feeBCMBData.getTotal_paket_register_kombinasi());
        txt_tot_oms_prd.setText(feeBCMBData.getBonus_total_omset_product());
        txt_tmbh_bns_tot_oms.setText(feeBCMBData.getTambahan_bonus_total_omset());
        txt_stat_kit_bsc.setText(feeBCMBData.getBonus_stater_kit_basic());
        txt_pkt_lngkp.setText(feeBCMBData.getBonus_paket_kombinasi());
        txt_bns_reff_mb.setText(feeBCMBData.getBonus_reff_mb());
        txt_bns_kit_v_bless.setText(feeBCMBData.getBonus_kit_v_bless());
        txt_total_fee.setText(feeBCMBData.getTotal_fee());
    }

    @OnClick(R.id.arrow_back)
    public void arrowBack(View view) {
        super.onBackPressed();
    }

    public void initSpinner() {
        mMonthSpinner.setOnItemSelectedListener(this);
        mYearSpinner.setOnItemSelectedListener(this);
        Calendar calendar = Calendar.getInstance();
        String month = new SimpleDateFormat("MMMM").format(calendar.getTime());

        ArrayAdapter<CharSequence> monthAdapter = ArrayAdapter.createFromResource(this,
                R.array.month_array, R.layout.item_spinner);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mMonthSpinner.setAdapter(monthAdapter);

        for (int i = 0; i < mMonthSpinner.getAdapter().getCount(); i++) {
            if(mMonthSpinner.getAdapter().getItem(i).toString().contains(month)) {
                mMonthSpinner.setSelection(i);
            }
        }

        mYearData.add(String.valueOf(getResources().getInteger(R.integer.minimum_year)));
        int now = calendar.get(Calendar.YEAR);
        int totalLoop = now - getResources().getInteger(R.integer.minimum_year);

        for (int i = 0; i < totalLoop; i++) {
            mYearData.add(String.valueOf(now));
        }

        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this,
                R.layout.item_spinner, android.R.id.text1, mYearData);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mYearSpinner.setAdapter(yearAdapter);

        for (int i = 0; i < mYearSpinner.getAdapter().getCount(); i++) {
            if(mYearSpinner.getAdapter().getItem(i).toString().contains(String.valueOf(now))) {
                mYearSpinner.setSelection(i);
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
