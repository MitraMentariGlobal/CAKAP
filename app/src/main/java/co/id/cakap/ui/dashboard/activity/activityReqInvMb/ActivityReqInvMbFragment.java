package co.id.cakap.ui.dashboard.activity.activityReqInvMb;

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

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.ActivityReqInvMbAdapter;
import co.id.cakap.data.ActivityReqInvMbData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.dashboard.activity.activityRekapBnsBcmb.ActivityRekapBnsBcmbContract;
import co.id.cakap.ui.dashboard.activity.activityRekapBnsBcmb.ActivityRekapBnsBcmbPresenter;
import co.id.cakap.ui.detailTransaction.DetailTransactionActivity;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.dialog.PinDialog;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class ActivityReqInvMbFragment extends Fragment implements ActivityReqInvMbContract.View {
    @Inject
    ActivityReqInvMbPresenter mActivityReqInvMbPresenter;

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
    private ActivityReqInvMbAdapter mListAdapter;
    private ActivityReqInvMbContract.UserActionListener mUserActionListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_activity_req_inv_mb, container, false);
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
        mUserActionListener = mActivityReqInvMbPresenter;
        mActivityReqInvMbPresenter.setView(this);
        mUserActionListener.getData(getContext());

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mUserActionListener.getData(getContext());
            }
        });
    }

    @Override
    public void setAdapter(List<ActivityReqInvMbData> resultData) {
        if (resultData.isEmpty()) {
            mLinearEmptyData.setVisibility(View.VISIBLE);
        } else {
            mLinearEmptyData.setVisibility(View.GONE);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mListAdapter = new ActivityReqInvMbAdapter(resultData, getContext());
        mRecyclerView.setAdapter(mListAdapter);
        hideProgressBar();
    }

    @Override
    public void showProgressBar() {
        mRelativeProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        try {
            mSwipeRefreshLayout.setRefreshing(false);
            mRelativeProgressBar.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public void openDetailTransaction(ActivityReqInvMbData activityReqInvMbData) {
        Intent intent = new Intent(getContext(), DetailTransactionActivity.class);

        intent.putExtra(Constant.TITLE_DETAIL, getContext().getResources().getString(R.string.req_invoice_mb));
        intent.putExtra(Constant.URL_LINK_DETAIL, Constant.END_URL_DETAIL_REQ_INVOICE_MB);
        intent.putExtra(Constant.ITEM_ID_DETAIL, activityReqInvMbData.getItem_id());
        intent.putExtra(Constant.TRANSACTION_ID_DETAIL, "");
        intent.putExtra(Constant.MEMBER_ID_DETAIL, activityReqInvMbData.getMember_id());
        intent.putExtra(Constant.NAME_DETAIL, activityReqInvMbData.getName());
        intent.putExtra(Constant.DATE_DETAIL, activityReqInvMbData.getDate());
        intent.putExtra(Constant.TOTAL_DETAIL, activityReqInvMbData.getTotal_amount());
        intent.putExtra(Constant.TOTAL_PV_DETAIL, activityReqInvMbData.getTotal_pv());
        intent.putExtra(Constant.REMARK_DETAIL, activityReqInvMbData.getRemark());

        startActivity(intent);
    }

    @Override
    public void openPinDialog(ActivityReqInvMbData activityReqInvMbData, String action) {
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

                mActivityReqInvMbPresenter.actionTransaction(activityReqInvMbData, pin, getContext(), action);
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