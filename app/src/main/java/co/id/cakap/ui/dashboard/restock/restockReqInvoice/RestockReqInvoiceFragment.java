package co.id.cakap.ui.dashboard.restock.restockReqInvoice;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.RestockReqInvoiceAdapter;
import co.id.cakap.data.RestockReqInvoiceData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillContract;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillPresenter;
import co.id.cakap.ui.detailTransaction.DetailTransactionActivity;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class RestockReqInvoiceFragment extends Fragment implements RestockReqInvoiceContract.View {
    @Inject
    RestockReqInvoicePresenter mRestockReqInvoicePresenter;

    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;

    private View mView;
    private Unbinder mUnbinder;
    private RestockReqInvoiceAdapter mListAdapter;
    private RestockReqInvoiceContract.UserActionListener mUserActionListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_restock_req_invoice, container, false);
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
        mUserActionListener = mRestockReqInvoicePresenter;
        mRestockReqInvoicePresenter.setView(this);
        mUserActionListener.getData();
    }

    @Override
    public void setAdapter(List<RestockReqInvoiceData> resultData) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mListAdapter = new RestockReqInvoiceAdapter(resultData, getContext());
        mRecyclerView.setAdapter(mListAdapter);
        OverScrollDecoratorHelper.setUpOverScroll(mRecyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
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
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openDetailTransaction(RestockReqInvoiceData restockReqInvoiceData) {
        Intent intent = new Intent(getContext(), DetailTransactionActivity.class);
        intent.putExtra(Constant.TITLE_DETAIL, getContext().getResources().getString(R.string.req_invoice));
        intent.putExtra(Constant.URL_LINK_DETAIL, Constant.END_URL_DETAIL_REQ_INVOICE);
        intent.putExtra(Constant.ITEM_ID_DETAIL, restockReqInvoiceData.getItem_id());
        intent.putExtra(Constant.TRANSACTION_ID_DETAIL, "");
        intent.putExtra(Constant.MEMBER_ID_DETAIL, restockReqInvoiceData.getNo_stc());
        intent.putExtra(Constant.NAME_DETAIL, restockReqInvoiceData.getNama());
        intent.putExtra(Constant.DATE_DETAIL, restockReqInvoiceData.getDate());
        intent.putExtra(Constant.TOTAL_DETAIL, restockReqInvoiceData.getTotal_amount());
        intent.putExtra(Constant.TOTAL_PV_DETAIL, restockReqInvoiceData.getTotal_pv());
        intent.putExtra(Constant.REMARK_DETAIL, restockReqInvoiceData.getRemarkapp());
        startActivity(intent);
    }
}