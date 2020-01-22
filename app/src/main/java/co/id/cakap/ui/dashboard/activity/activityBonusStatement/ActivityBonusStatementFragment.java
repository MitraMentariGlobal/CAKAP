package co.id.cakap.ui.dashboard.activity.activityBonusStatement;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.data.ActivityBonusStatementData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.ui.homeWebView.HomeWebViewActivity;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.PDFTools;

public class ActivityBonusStatementFragment extends Fragment implements ActivityBonusStatementContract.View, AdapterView.OnItemSelectedListener {
    @Inject
    ActivityBonusStatementPresenter mActivityBonusStatementPresenter;

    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;
    @BindView(R.id.month_spinner)
    Spinner mMonthSpinner;
    @BindView(R.id.year_spinner)
    Spinner mYearSpinner;

    @BindView(R.id.txt_name)
    TextView mTxtName;
    @BindView(R.id.txt_id)
    TextView mTxtId;
    @BindView(R.id.txt_alamat)
    TextView mTxtAlamat;
    @BindView(R.id.txt_nomor_hp)
    TextView mTxtNomorHp;
    @BindView(R.id.txt_kode_bcmb)
    TextView mTxtKodeBcmb;
    @BindView(R.id.txt_tgl_gabung)
    TextView mTxtTglGabung;
    @BindView(R.id.txt_posisi)
    TextView mTxtPosisi;
    @BindView(R.id.txt_sponsor)
    TextView mTxtSponsor;
    @BindView(R.id.txt_sponsor_id)
    TextView mTxtSponsorId;
    @BindView(R.id.txt_posisi_sponsor)
    TextView mTxtPosisiSponsor;
    @BindView(R.id.download_btn)
    Button mDownloadBtn;

    private String mUrl = "http://aplikasicakap.co.id";
    private View mView;
    private Unbinder mUnbinder;
    private ActivityBonusStatementContract.UserActionListener mUserActionListener;
    private List<String> mYearData = new ArrayList<>();
    private static ActivityBonusStatementData mActivityBonusStatementData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_activity_bonus_statement, container, false);
            mUnbinder = ButterKnife.bind(this, mView);

            setupActivityComponent();
            initializeData();
        }

        ButterKnife.bind(this, mView);
        return mView;
    }

    private void setupActivityComponent() {
        CoreApp.get()
                .getAppComponent()
                .plus(new MainActivityModule(this))
                .inject(this);
    }

    @Override
    public void initializeData() {
        mUserActionListener = mActivityBonusStatementPresenter;
        mActivityBonusStatementPresenter.setView(this);

        WebSettings webSetting = mWebView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setDisplayZoomControls(true);
        if (Build.VERSION.SDK_INT >= 26) {
            webSetting.setSafeBrowsingEnabled(false);
        }
        mWebView.setWebViewClient(new ActivityBonusStatementWebViewClient());
        mWebView.loadUrl(mUrl);

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
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void initSpinner() {
        mMonthSpinner.setOnItemSelectedListener(this);
        mYearSpinner.setOnItemSelectedListener(this);
        Calendar calendar = Calendar.getInstance();
        String month = new SimpleDateFormat("MMMM").format(calendar.getTime());

        ArrayAdapter<CharSequence> monthAdapter = ArrayAdapter.createFromResource(getActivity(),
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

        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(getActivity(),
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

    @OnClick(R.id.download_btn)
    public void downloadAction(View view) {
        mUserActionListener.getData(mYearSpinner.getSelectedItem().toString(), mMonthSpinner.getSelectedItem().toString());
//        checkPermissionStorage();
    }

    @Override
    public void checkPermissionStorage(ActivityBonusStatementData activityBonusStatementData) {
        mActivityBonusStatementData = activityBonusStatementData;
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
            Logger.i("Permission has already been granted");
            PDFTools.showPDFUrl(getContext(), mActivityBonusStatementData.getLink(), mActivityBonusStatementData.getFile_name());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Logger.i("Permission was granted");
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    PDFTools.showPDFUrl(getContext(), mActivityBonusStatementData.getLink(), mActivityBonusStatementData.getFile_name());
                } else {
                    Logger.e("Permission denied");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }
}
