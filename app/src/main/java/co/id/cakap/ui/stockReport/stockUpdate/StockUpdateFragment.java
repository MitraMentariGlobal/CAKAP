package co.id.cakap.ui.stockReport.stockUpdate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.InfoAdapter;
import co.id.cakap.adapter.StockUpdateAdapter;
import co.id.cakap.data.StockUpdate;
import co.id.cakap.di.module.MainActivityModule;

public class StockUpdateFragment extends Fragment implements StockUpdateContract.View {
    @Inject
    StockUpdatePresenter mStockUpdatePresenter;

    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.main_list_info)
    RecyclerView mRecyclerViewInfo;

    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;
    @BindView(R.id.linear_empty_data)
    LinearLayout mLinearEmptyData;
    @BindView(R.id.swiperefresh_items)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private View mView;
    private Unbinder mUnbinder;
    private StockUpdateAdapter mListAdapter;
    private InfoAdapter mListAdapterInfo;
    private StockUpdateContract.UserActionListener mUserActionListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_stock_update, container, false);
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
        mUserActionListener = mStockUpdatePresenter;
        mStockUpdatePresenter.setView(this);
        mUserActionListener.getData();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mUserActionListener.getData();
            }
        });
    }

    @Override
    public void setAdapter(StockUpdate resultData) {
        if (resultData.getData().isEmpty()) {
            mLinearEmptyData.setVisibility(View.VISIBLE);
        } else {
            mLinearEmptyData.setVisibility(View.GONE);
        }

        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        mRecyclerViewInfo.setLayoutManager(layoutManager1);
        mListAdapterInfo = new InfoAdapter(resultData.getNote(), getContext(), true);
        mRecyclerViewInfo.setAdapter(mListAdapterInfo);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mListAdapter = new StockUpdateAdapter(resultData.getData(), getContext());
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
}