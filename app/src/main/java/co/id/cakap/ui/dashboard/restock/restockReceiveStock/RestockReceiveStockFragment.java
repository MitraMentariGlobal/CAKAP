package co.id.cakap.ui.dashboard.restock.restockReceiveStock;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.RestockReceiveStockAdapter;
import co.id.cakap.data.RestockReceiveStockData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillContract;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillPresenter;
import co.id.cakap.ui.detailTransaction.DetailTransactionActivity;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.dialog.PinDialog;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class RestockReceiveStockFragment extends Fragment implements RestockReceiveStockContract.View {
    @Inject
    RestockReceiveStockPresenter mRestockReceiveStockPresenter;

    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;
    @BindView(R.id.linear_empty_data)
    LinearLayout mLinearEmptyData;
    @BindView(R.id.swiperefresh_items)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private View mView;
    private Unbinder mUnbinder;
    private RestockReceiveStockAdapter mListAdapter;
    private RestockReceiveStockContract.UserActionListener mUserActionListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_restock_reveive_stock, container, false);
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
        mUserActionListener = mRestockReceiveStockPresenter;
        mRestockReceiveStockPresenter.setView(this);
        mUserActionListener.getData();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                mUserActionListener.getData();
            }
        });
    }

    @Override
    public void setAdapter(List<RestockReceiveStockData> resultData) {
        mLinearEmptyData.setVisibility(View.GONE);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mListAdapter = new RestockReceiveStockAdapter(resultData, getContext());
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
        mSwipeRefreshLayout.setRefreshing(false);
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
    public void openDetailTransaction(RestockReceiveStockData restockReceiveStockData) {
        Intent intent = new Intent(getContext(), DetailTransactionActivity.class);
        intent.putExtra(Constant.TITLE_DETAIL, getContext().getResources().getString(R.string.receive_stock));
        intent.putExtra(Constant.URL_LINK_DETAIL, Constant.END_URL_DETAIL_RECEIVE_STOCK);
        intent.putExtra(Constant.ITEM_ID_DETAIL, restockReceiveStockData.getItem_id());
        intent.putExtra(Constant.TRANSACTION_ID_DETAIL, restockReceiveStockData.getTransaction_id());
        intent.putExtra(Constant.MEMBER_ID_DETAIL, restockReceiveStockData.getNo_stc());
        intent.putExtra(Constant.NAME_DETAIL, restockReceiveStockData.getNama());
        intent.putExtra(Constant.DATE_DETAIL, restockReceiveStockData.getDate());
        intent.putExtra(Constant.TOTAL_DETAIL, restockReceiveStockData.getTotal_amount());
        intent.putExtra(Constant.TOTAL_PV_DETAIL, restockReceiveStockData.getTotal_pv());
        intent.putExtra(Constant.REMARK_DETAIL, restockReceiveStockData.getRemarkapp());
        startActivity(intent);
    }

    @Override
    public void openPinDialog(RestockReceiveStockData restockReceiveStockData) {
        PinDialog utils = new PinDialog();
        Dialog dialog = utils.showDialog(getContext());

        PinLockView pinLockView = dialog.findViewById(R.id.pin_lock_view);
        IndicatorDots indicatorDots = dialog.findViewById(R.id.indicator_dots);
        PinLockListener pinLockListener = new PinLockListener() {
            @Override
            public void onComplete(String pin) {
                Logger.d("Pin complete: " + pin);
                dialog.hide();
                dialog.dismiss();

                mRestockReceiveStockPresenter.actionTransaction(restockReceiveStockData, pin);
            }

            @Override
            public void onEmpty() {
                Logger.d("Pin empty");
            }

            @Override
            public void onPinChange(int pinLength, String intermediatePin) {
                Logger.d("Pin changed, new length " + pinLength + " with intermediate pin " + intermediatePin);
            }
        };

        pinLockView.attachIndicatorDots(indicatorDots);
        pinLockView.setPinLockListener(pinLockListener);

        pinLockView.setPinLength(6);
        pinLockView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        indicatorDots.setIndicatorType(IndicatorDots.IndicatorType.FILL_WITH_ANIMATION);
    }
}