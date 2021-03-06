package co.id.cakap.ui.dashboard.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.CustomViewPagerAdapter;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.dashboard.DashboardPresenter;
import co.id.cakap.ui.networkGenealogy.NetworkGenealogyActivity;
import co.id.cakap.ui.reqInvoiceToBc.ReqInvoiceToBcActivity;
import co.id.cakap.ui.cashbill.CashbillActivity;
import co.id.cakap.ui.invoiceToMb.InvoiceToMbActivity;
import co.id.cakap.ui.registration.RegistrationActivity;
import co.id.cakap.ui.pickUpDelivery.PickUpDeliveryActivity;
import co.id.cakap.utils.DateHelper;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import co.id.cakap.utils.widget.CustomRecyclerViewPager;

public class HomeFragment extends Fragment implements HomeContract.View {
    @Inject
    HomePresenter mHomePresenter;

    @BindView(R.id.viewPager)
    CustomRecyclerViewPager mRecyclerView;
    @BindView(R.id.running_text)
    TextView mRunningText;
    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;
    @BindView(R.id.linear_ewallet)
    LinearLayout mLinearEwallet;
    @BindView(R.id.linear_member_info)
    LinearLayout mLinearMemberInfo;

    @BindView(R.id.txt_transaction)
    TextView mTxtTransaction;
    @BindView(R.id.card_view_registration)
    CardView mCardViewRegistration;
    @BindView(R.id.relative_registration)
    RelativeLayout mRelativeRegistration;
    @BindView(R.id.relative_padding_transaction1)
    RelativeLayout mRelativePaddingTransaction1;
    @BindView(R.id.relative_padding_transaction2)
    RelativeLayout mRelativePaddingTransaction2;
    @BindView(R.id.relative_cashbill)
    RelativeLayout mRelativeCashbill;
    @BindView(R.id.card_view_bonus_statement)
    CardView mCardViewBonusStatement;
    @BindView(R.id.card_view_inv_to_mb)
    CardView mCardViewInvToMb;
    @BindView(R.id.relative_invoice_to_mb)
    RelativeLayout mRelativeInvToMb;
    @BindView(R.id.linear_network_genealogy)
    LinearLayout mlinearNetworkGenealogy;

    @BindView(R.id.card_view_req_inv_to_company)
    CardView mCardViewReqInvToCompany;
    @BindView(R.id.relative_padding_restock1)
    RelativeLayout mRelativePaddingRestock1;
    @BindView(R.id.relative_padding_restock2)
    RelativeLayout mRelativePaddingRestock2;
    @BindView(R.id.relative_req_inv_to_company)
    RelativeLayout mRelativeReqInvToCompany;
    @BindView(R.id.relative_req_inv_to_bc)
    RelativeLayout mRelativeReqInvToBc;
    @BindView(R.id.card_view_restock_not_visible)
    CardView mCardViewRestockNotVisible;
    @BindView(R.id.linear_restock)
    LinearLayout mLinearRestock;

    @BindView(R.id.txt_member_name)
    TextView mTxtMemberName;
    @BindView(R.id.txt_member_id)
    TextView mTxtMemberId;
    @BindView(R.id.txt_posisi)
    TextView mTxtPosisi;
    @BindView(R.id.txt_bonus)
    TextView mTxtBonus;

    @BindView(R.id.txt_sisa_pv)
    TextView mTxtSisaPv;
    @BindView(R.id.txt_pv)
    TextView mTxtPv;
    @BindView(R.id.pv_progress_bar)
    ProgressBar mPvProgressBar;

    @BindView(R.id.txt_ewallet)
    TextView mTxtEwallet;
    @BindView(R.id.txt_fee)
    TextView mTxtFee;
    @BindView(R.id.txt_omset)
    TextView mTxtOmset;
    @BindView(R.id.txt_total_omset)
    TextView mTxtTotalOmset;

    @BindView(R.id.txt_last_bonus)
    TextView mTxtLastBonus;

    private View mView;
    private Unbinder mUnbinder;
    private Calendar mCalendar;
    private HomeContract.UserActionListener mUserActionListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_home, container, false);
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
        mUserActionListener = mHomePresenter;
        mHomePresenter.setView(this);
        mUserActionListener.getData();
        mCalendar = Calendar.getInstance();
        int days = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        Logger.d("days : " + days);

        if (Constant.LOGIN_DATA.equals(getContext().getResources().getString(R.string.bc_login))) {
            mRelativeReqInvToBc.setVisibility(View.GONE);
        } else if (Constant.LOGIN_DATA.equals(getContext().getResources().getString(R.string.mb_login))) {
            mCardViewInvToMb.setVisibility(View.GONE);

            Logger.d("have parent : " + Constant.IS_HAVE_PARENT);
            if (Constant.IS_HAVE_PARENT) {
//                mLinearRestock.setVisibility(View.GONE); // sementara

                int dayBetween = DateHelper.getDaysBetween(DateHelper.getTimeNow(), DateHelper.getLastDayOfTheMonth());
//                int dayBetween = DateHelper.getDaysBetween(DateHelper.getTimeNow(), "05-03-2020");
                if (dayBetween <= 5) {
                    mCardViewReqInvToCompany.setVisibility(View.VISIBLE);
                    mRelativePaddingRestock1.setVisibility(View.VISIBLE);
                    mRelativePaddingRestock2.setVisibility(View.GONE);
                    mCardViewRestockNotVisible.setVisibility(View.GONE);
                } else {
                    mCardViewReqInvToCompany.setVisibility(View.GONE);
                    mRelativePaddingRestock1.setVisibility(View.GONE);
                    mRelativePaddingRestock2.setVisibility(View.VISIBLE);
                    mCardViewRestockNotVisible.setVisibility(View.VISIBLE);
                }
            } else {
                mRelativeReqInvToBc.setVisibility(View.GONE);
            }
        } else if (Constant.LOGIN_DATA.equals(getContext().getResources().getString(R.string.member_login))) {
            mTxtTransaction.setText(getContext().getResources().getString(R.string.title_information));
            mLinearEwallet.setVisibility(View.GONE);
            mLinearRestock.setVisibility(View.GONE);
            mCardViewRegistration.setVisibility(View.GONE);
            mCardViewInvToMb.setVisibility(View.GONE);
            mRelativePaddingTransaction1.setVisibility(View.GONE);
            mRelativePaddingTransaction2.setVisibility(View.VISIBLE);
            mCardViewBonusStatement.setVisibility(View.VISIBLE);
            mlinearNetworkGenealogy.setVisibility(View.VISIBLE);
            mLinearMemberInfo.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setAdapter(ResultDataLogin resultDataLogin) {
        mRunningText.setText(resultDataLogin.getRunning_text());
        mRunningText.setSelected(true);

        DisplayMetrics metrics = getDisplayMetrics();
        List<String> metalList = Arrays.asList(
                resultDataLogin.getGambar()
        );
        CustomViewPagerAdapter customViewPagerAdapter = new CustomViewPagerAdapter(metrics, metalList, getContext());
        mRecyclerView.setAdapter(customViewPagerAdapter);
//        OverScrollDecoratorHelper.setUpOverScroll(mRecyclerView, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);

        if (Constant.LOGIN_DATA.equals(getResources().getString(R.string.member_login))) {
            mTxtMemberName.setText(resultDataLogin.getNama());
            mTxtMemberId.setText(resultDataLogin.getMember_id());
            mTxtPosisi.setText(resultDataLogin.getPosisi());
            mTxtBonus.setText("Rp. " + Utils.priceFromString(resultDataLogin.getBonus()));

            int pvMax = Integer.parseInt(resultDataLogin.getPv_max());
            int pvTupo = Integer.parseInt(resultDataLogin.getPv_tupo());
            String sisaPv = String.valueOf(pvMax - pvTupo);

            mPvProgressBar.setMax(pvMax);
            mPvProgressBar.setProgress(pvTupo);
            mTxtSisaPv.setText(sisaPv + " PV left to complete your tupo");
            mTxtPv.setText(pvTupo + " PV");
            mTxtLastBonus.setText(resultDataLogin.getBulan_bonus());
        } else {
            mTxtEwallet.setText(resultDataLogin.getEwallet());
            mTxtFee.setText(resultDataLogin.getFee());
            mTxtOmset.setText(resultDataLogin.getOmset());
            mTxtTotalOmset.setText(resultDataLogin.getTotal_omset());
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

    private DisplayMetrics getDisplayMetrics() {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        return metrics;
    }

    @OnClick(R.id.relative_registration)
    public void homeRegistration(View view) {
        startActivity(new Intent(getContext(), RegistrationActivity.class));
    }

    @OnClick(R.id.relative_cashbill)
    public void homeCashbill(View view) {
        if (Constant.LOGIN_DATA.equals(getContext().getResources().getString(R.string.member_login))) {
            new DashboardPresenter().getView().moveToActivity(0);
        } else {
            startActivity(new Intent(getContext(), CashbillActivity.class));
        }
    }

    @OnClick(R.id.relative_invoice_to_mb)
    public void homeInvoiceToMb(View view) {
        startActivity(new Intent(getContext(), InvoiceToMbActivity.class));
    }

    @OnClick(R.id.relative_req_inv_to_company)
    public void homeReqInvToCompany(View view) {
        startActivity(new Intent(getContext(), PickUpDeliveryActivity.class));
    }

    @OnClick(R.id.relative_req_inv_to_bc)
    public void homeReqInvToBc(View view) {
        startActivity(new Intent(getContext(), ReqInvoiceToBcActivity.class));
    }

    @OnClick(R.id.relative_network_genealogy)
    public void networkGenealogy(View view) {
        startActivity(new Intent(getContext(), NetworkGenealogyActivity.class));
    }

    @OnClick(R.id.relative_bonus_statement)
    public void bonusStatement(View view) {
        new DashboardPresenter().getView().moveToActivity(1);
    }
}
