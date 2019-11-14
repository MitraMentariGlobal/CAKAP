package co.id.cakap.ui.dashboard.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.CustomViewPagerAdapter;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.ui.reqInvoiceToBc.ReqInvoiceToBcActivity;
import co.id.cakap.ui.reqInvoiceToCompany.ReqInvoiceToCompanyActivity;
import co.id.cakap.ui.cashbill.CashbillActivity;
import co.id.cakap.ui.invoiceToMb.InvoiceToMbActivity;
import co.id.cakap.ui.registration.RegistrationActivity;
import co.id.cakap.ui.reqInvoiceToCompany.pick_up_delivery.PickUpDeliveryActivity;
import co.id.cakap.utils.widget.CustomRecyclerViewPager;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class HomeFragment extends Fragment implements HomeContract.View {
    @Inject
    HomePresenter mHomePresenter;

    @BindView(R.id.viewPager)
    CustomRecyclerViewPager mRecyclerView;
    @BindView(R.id.running_text)
    TextView mRunningText;
    @BindView(R.id.main_progress_bar)
    ProgressBar mProgressBar;

    private View mView;
    private Unbinder mUnbinder;
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
    public void setAdapter() {
        DisplayMetrics metrics = getDisplayMetrics();
        List<String> metalList = Arrays.asList(
                "https://asset-apac.unileversolutions.com/content/dam/unilever/lipton_international/global/general_image/worldtea_teatype_black_tea_img1_1460x593-1382286.jpg.ulenscale.1024x415.jpg"
        );
        CustomViewPagerAdapter customViewPagerAdapter = new CustomViewPagerAdapter(metrics, metalList, getContext());
        mRecyclerView.setAdapter(customViewPagerAdapter);
//        OverScrollDecoratorHelper.setUpOverScroll(mRecyclerView, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);

        hideProgressBar();
    }

    @Override
    public void initializeData() {
        mUserActionListener = mHomePresenter;
        mHomePresenter.setView(this);
        mUserActionListener.getData();
        mRunningText.setText("SEMANGAT PAGI MITRA BLESSTEA........ APA KABAR..... MANTAP. BLESSTEA .... PASTI. CAKAP.... YES..... ");
        mRunningText.setSelected(true);
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
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
        startActivity(new Intent(getContext(), CashbillActivity.class));
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
}
