package co.id.cakap.ui.stockReport.stockCard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.RestockReqInvoiceAdapter;
import co.id.cakap.data.RestockReqInvoiceData;
import co.id.cakap.data.StockCardData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.dashboard.restock.restockReqInvoice.RestockReqInvoiceContract;
import co.id.cakap.ui.dashboard.restock.restockReqInvoice.RestockReqInvoicePresenter;
import co.id.cakap.ui.detailTransaction.DetailTransactionActivity;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class StockCardFragment extends Fragment implements StockCardContract.View {
    @Inject
    StockCardPresenter mStockCardPresenter;

    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.main_progress_bar)
    ProgressBar mProgressBar;

    private View mView;
    private Unbinder mUnbinder;
//    private RestockReqInvoiceAdapter mListAdapter;
    private StockCardContract.UserActionListener mUserActionListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_stock_card, container, false);
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
        mUserActionListener = mStockCardPresenter;
        mStockCardPresenter.setView(this);
        mUserActionListener.getData();
    }

    @Override
    public void setAdapter(List<StockCardData> resultData) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
//        mListAdapter = new RestockReqInvoiceAdapter(resultData, getContext());
//        mRecyclerView.setAdapter(mListAdapter);
        OverScrollDecoratorHelper.setUpOverScroll(mRecyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
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
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}