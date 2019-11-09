package co.id.cakap.ui.dashboard.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.ui.feeBCMB.FeeBcmbActivity;
import co.id.cakap.ui.myProfile.MyProfileActivity;
import co.id.cakap.ui.omset.OmsetActivity;
import co.id.cakap.ui.searchMember.SearchMemberActivity;
import co.id.cakap.ui.stockReport.StockReportActivity;

public class AccountFragment extends Fragment implements AccountContract.View {
    @Inject
    AccountPresenter mAccountPresenter;

    private View mView;
    private Unbinder mUnbinder;
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
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.account_my_profile)
    public void aaccountMyProfile(View view) {
        startActivity(new Intent(getContext(), MyProfileActivity.class));
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
}