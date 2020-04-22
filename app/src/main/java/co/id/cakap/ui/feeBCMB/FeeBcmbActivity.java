package co.id.cakap.ui.feeBCMB;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import co.id.cakap.adapter.InfoAdapter;
import co.id.cakap.data.FeeBCMBData;
import co.id.cakap.di.module.MainActivityModule;

public class FeeBcmbActivity extends AppCompatActivity implements FeeBcmbActivityContract.View, AdapterView.OnItemSelectedListener {
    @Inject
    FeeBcmbActivityPresenter mFeeBcmbActivityPresenter;

    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;
    @BindView(R.id.title_toolbar)
    TextView mTitle;
    @BindView(R.id.month_spinner)
    Spinner mMonthSpinner;
    @BindView(R.id.year_spinner)
    Spinner mYearSpinner;
    @BindView(R.id.nested_scroll)
    NestedScrollView mNestedScroll;

    @BindView(R.id.main_list_omset)
    RecyclerView mListOmset;
    @BindView(R.id.main_list_bonus)
    RecyclerView mListBonus;
    @BindView(R.id.main_list_bonus_footer)
    RecyclerView mListBonusFooter;

//    @BindView(R.id.txt_oms_aft_ppn)
//    TextView txt_oms_aft_ppn;
//    @BindView(R.id.txt_oms_prd_aft_ppn)
//    TextView txt_oms_prd_aft_ppn;
//    @BindView(R.id.txt_stat_kit_sp01)
//    TextView txt_stat_kit_sp01;
//    @BindView(R.id.txt_stat_kit_sp03)
//    TextView txt_stat_kit_sp03;
//    @BindView(R.id.txt_stat_kit_sp04)
//    TextView txt_stat_kit_sp04;
//
//    @BindView(R.id.txt_tot_oms_prd)
//    TextView txt_tot_oms_prd;
////    @BindView(R.id.txt_tmbh_bns_tot_oms)
////    TextView txt_tmbh_bns_tot_oms;
//    @BindView(R.id.txt_stat_kit_bsc)
//    TextView txt_stat_kit_bsc;
//    @BindView(R.id.txt_pkt_lngkp)
//    TextView txt_pkt_lngkp;
//    @BindView(R.id.txt_bns_reff_mb)
//    TextView txt_bns_reff_mb;
//    @BindView(R.id.txt_bns_kit_v_bless)
//    TextView txt_bns_kit_v_bless;
//
//    @BindView(R.id.txt_total_fee)
//    TextView txt_total_fee;
//    @BindView(R.id.txt_pajak)
//    TextView txt_pajak;
//    @BindView(R.id.txt_netto_fee)
//    TextView txt_netto_fee;

    private FeeBcmbActivityContract.UserActionListener mUserActionListener;
    private List<String> mYearData = new ArrayList<>();
    private InfoAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_bcmb2);
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

        mTitle.setText(getString(R.string.fee_bcmb).toUpperCase());
        initSpinner();
        mUserActionListener.getData(mYearSpinner.getSelectedItem().toString(), mMonthSpinner.getSelectedItem().toString());
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
    public void setData(FeeBCMBData feeBCMBData) {
//        txt_oms_aft_ppn.setText(feeBCMBData.getTotal_omset_setelah_ppn());
//        txt_oms_prd_aft_ppn.setText(feeBCMBData.getTotal_omset_product_setelah_ppn());
//        txt_stat_kit_sp01.setText(feeBCMBData.getTotal_starter_kit_sp01());
//        txt_stat_kit_sp03.setText(feeBCMBData.getTotal_starter_kit_sp03());
//        txt_stat_kit_sp04.setText(feeBCMBData.getTotal_starter_kit_sp04());
//        txt_tot_oms_prd.setText(Utils.priceFromString(feeBCMBData.getBonus_total_omset_product()));
////        txt_tmbh_bns_tot_oms.setText(feeBCMBData.getTambahan_bonus_total_omset());
//        txt_stat_kit_bsc.setText(Utils.priceFromString(feeBCMBData.getBonus_stater_kit_basic()));
//        txt_pkt_lngkp.setText(Utils.priceFromString(feeBCMBData.getBonus_paket_kombinasi_lengkap()));
//        txt_bns_reff_mb.setText(Utils.priceFromString(feeBCMBData.getBonus_reff_mb()));
//        txt_bns_kit_v_bless.setText(Utils.priceFromString(feeBCMBData.getBonus_kit_v_bless()));
//
//        txt_total_fee.setText(Utils.priceWithoutDecimal(feeBCMBData.getTotal_fee()));
//        txt_pajak.setText(Utils.priceWithoutDecimal(feeBCMBData.getPajak()));
//        txt_netto_fee.setText(Utils.priceWithoutDecimal(feeBCMBData.getNetto_fee()));


        mNestedScroll.getParent().requestChildFocus(mNestedScroll, mNestedScroll);

        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this);
        mListOmset.setLayoutManager(layoutManager1);
        mListOmset.setNestedScrollingEnabled(false);
        mListAdapter = new InfoAdapter(feeBCMBData.getTxt_bonus(), this, false);
        mListOmset.setAdapter(mListAdapter);

        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this);
        mListBonus.setLayoutManager(layoutManager2);
        mListBonus.setNestedScrollingEnabled(false);
        mListAdapter = new InfoAdapter(feeBCMBData.getTxt_bonus(), this, false);
        mListBonus.setAdapter(mListAdapter);

        RecyclerView.LayoutManager layoutManager3 = new LinearLayoutManager(this);
        mListBonusFooter.setLayoutManager(layoutManager3);
        mListBonusFooter.setNestedScrollingEnabled(false);
        mListAdapter = new InfoAdapter(feeBCMBData.getTxt_bonus_footer(), this, true);
        mListBonusFooter.setAdapter(mListAdapter);

        hideProgressBar();
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
        mUserActionListener.getData(mYearSpinner.getSelectedItem().toString(), mMonthSpinner.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
