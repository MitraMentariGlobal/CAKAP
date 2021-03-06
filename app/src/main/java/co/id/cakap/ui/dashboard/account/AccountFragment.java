package co.id.cakap.ui.dashboard.account;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.id.cakap.BuildConfig;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.changePassword.ChangePasswordActivity;
import co.id.cakap.ui.changePin.ChangePinActivity;
import co.id.cakap.ui.dashboard.DashboardActivity;
import co.id.cakap.ui.downlineListing.DownlineListingActivity;
import co.id.cakap.ui.ebonus.EbonusActivity;
import co.id.cakap.ui.feeBCMB.FeeBcmbActivity;
import co.id.cakap.ui.login.LoginActivity;
import co.id.cakap.ui.monthlyPointReport.MonthlyPointReportActivity;
import co.id.cakap.ui.myProfile.MyProfileActivity;
import co.id.cakap.ui.networkGenealogy.NetworkGenealogyActivity;
import co.id.cakap.ui.networkTable.NetworkTableActivity;
import co.id.cakap.ui.omset.OmsetActivity;
import co.id.cakap.ui.searchMember.SearchMemberActivity;
import co.id.cakap.ui.stockReport.StockReportActivity;
import co.id.cakap.utils.PDFTools;
import co.id.cakap.utils.Utils;
import co.id.cakap.utils.dialog.UserConfirmationDialog;

public class AccountFragment extends Fragment implements AccountContract.View {
    @Inject
    AccountPresenter mAccountPresenter;

    @BindView(R.id.card_settings)
    CardView mCardSettings;
    @BindView(R.id.txt_version)
    TextView mTxtVersion;
    @BindView(R.id.user_name)
    TextView mTxtUserName;
    @BindView(R.id.account_name)
    TextView mTxtAccountName;
    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;

    @BindView(R.id.linear_member)
    LinearLayout mLinearMember;
    @BindView(R.id.linear_bcmb)
    LinearLayout mLinearBcmb;

    private boolean mIsExpand = false;
    private View mView;
    private Unbinder mUnbinder;
    private String mVersion = "";
    private AccountContract.UserActionListener mUserActionListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_account, container, false);
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
        mUserActionListener = mAccountPresenter;
        mAccountPresenter.setView(this);

        mUserActionListener.getLoginData();
        mVersion = "v" + BuildConfig.VERSION_CODE + "." + BuildConfig.VERSION_NAME;
        mTxtVersion.setText(mVersion);

        if (Constant.LOGIN_DATA.equals(getResources().getString(R.string.member_login))) {
            mLinearMember.setVisibility(View.VISIBLE);
            mLinearBcmb.setVisibility(View.GONE);
        }
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
        try {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setSuccessResponse() {
        ((DashboardActivity) getActivity()).finish();
        Intent i = new Intent(getContext(), LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @Override
    public void setLoginData(ResultDataLogin resultDataLogin) {
        mTxtUserName.setText(resultDataLogin.getUsername());
        mTxtAccountName.setText(resultDataLogin.getNama());
    }

    @OnClick(R.id.account_my_profile)
    public void accountMyProfile(View view) {
        startActivity(new Intent(getContext(), MyProfileActivity.class));
    }

    @OnClick(R.id.account_network_genealogy)
    public void accountNetworkGenealogy(View view) {
        startActivity(new Intent(getContext(), NetworkGenealogyActivity.class));
    }

    @OnClick(R.id.account_network_table)
    public void accountNetworkTable(View view) {
        startActivity(new Intent(getContext(), NetworkTableActivity.class));
    }

    @OnClick(R.id.account_ebonus)
    public void accountEbonus(View view) {
        startActivity(new Intent(getContext(), EbonusActivity.class));
    }

    @OnClick(R.id.account_downline_listing)
    public void accountDownlineListing(View view) {
        startActivity(new Intent(getContext(), DownlineListingActivity.class));
    }

    @OnClick(R.id.account_monthly_point_report)
    public void accountMonthlyPointReport(View view) {
        startActivity(new Intent(getContext(), MonthlyPointReportActivity.class));
    }

    @OnClick(R.id.account_fee_bcmb)
    public void accountFeeBcmb(View view) {
        startActivity(new Intent(getContext(), FeeBcmbActivity.class));
    }

    @OnClick(R.id.account_stock_report)
    public void accountStockReport(View view) {
        startActivity(new Intent(getContext(), StockReportActivity.class));
    }

    @OnClick(R.id.account_omset)
    public void accountOmset(View view) {
        startActivity(new Intent(getContext(), OmsetActivity.class));
    }

    @OnClick(R.id.account_search_member)
    public void accountSearchMember(View view) {
        startActivity(new Intent(getContext(), SearchMemberActivity.class));
    }

    @OnClick(R.id.arrow_settings)
    public void arrowSettings(View view) {
        Utils.expand(mCardSettings);
        mIsExpand = true;
    }

    @OnClick(R.id.img_close)
    public void arrowClose(View view) {
        if (mIsExpand) {
            Utils.collapse(mCardSettings);
            mIsExpand = false;
        }
    }

    @OnClick(R.id.txt_change_password)
    public void goToChangePassword(View view) {
        if (mIsExpand) {
            Utils.collapse(mCardSettings);
            mIsExpand = false;
        }
        startActivity(new Intent(getContext(), ChangePasswordActivity.class));
    }

    @OnClick(R.id.txt_change_pin)
    public void goToChangePin(View view) {
        if (mIsExpand) {
            Utils.collapse(mCardSettings);
            mIsExpand = false;
        }
        startActivity(new Intent(getContext(), ChangePinActivity.class));
    }

    @OnClick(R.id.txt_logout)
    public void actionLogout(View view) {
        openDialogLogout();
    }

    private void openDialogLogout() {
        UserConfirmationDialog utils = new UserConfirmationDialog();
        Dialog dialog = utils.showDialog(getContext());
        utils.setTitleLogout();
        utils.setNegativeAction();

        dialog.findViewById(R.id.no_act_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.yes_act_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                showProgressBar();
                mUserActionListener.getData();
            }
        });
    }

    @Override
    public void onPause() {
        if (mIsExpand) {
            Utils.collapse(mCardSettings);
            mIsExpand = false;
        }
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}