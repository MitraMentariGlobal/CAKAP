package co.id.cakap.ui.downlineListing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.DownlineListingAdapter;
import co.id.cakap.adapter.DownlineListingDropdownAdapter;
import co.id.cakap.adapter.NetworkTableAdapter;
import co.id.cakap.data.DownlineListingData;
import co.id.cakap.data.LevelData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.ui.networkGenealogy.NetworkGenealogyActivity;
import co.id.cakap.utils.Logger;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class DownlineListingActivity extends AppCompatActivity implements DownlineListingContract.View {
    @Inject
    DownlineListingPresenter mDownlineListingPresenter;

    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;
    @BindView(R.id.title_toolbar)
    TextView mTitle;
    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.nested_scroll)
    NestedScrollView mNestedScroll;
    @BindView(R.id.et_level)
    EditText mEtLevel;
    @BindView(R.id.relative_level)
    RelativeLayout mRelativeLevel;

    private DownlineListingContract.UserActionListener mUserActionListener;
    private DownlineListingAdapter mListAdapter;
    private DownlineListingDropdownAdapter mDropdownAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downline_listing);
        ButterKnife.bind(this);

        setupActivityComponent();
        initializeData();
    }

    private void setupActivityComponent() {
        CoreApp.get()
                .getAppComponent()
                .plus(new MainActivityModule(this))
                .inject(this);
    }

    @Override
    public void initializeData() {
        mUserActionListener = mDownlineListingPresenter;
        mDownlineListingPresenter.setView(this);

        mTitle.setText(getString(R.string.downline_listing).toUpperCase());
        mUserActionListener.getDataDropdown();
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
        Toast.makeText(DownlineListingActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setAdapter(LevelData levelData, List<DownlineListingData> resultData, RecyclerView recyclerView, TextView txtTitle) {
        txtTitle.setText("Level " + levelData.getLevel() + " [" + resultData.size() + " data]");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        mListAdapter = new DownlineListingAdapter(resultData, this);
        recyclerView.setAdapter(mListAdapter);
        hideProgressBar();
    }

    @Override
    public void setAdapterDropdown(List<LevelData> levelDataList) {
        levelDataList.remove(0);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
        mNestedScroll.getParent().requestChildFocus(mNestedScroll, mNestedScroll);
        mDropdownAdapter = new DownlineListingDropdownAdapter(levelDataList, this);
        mRecyclerView.setAdapter(mDropdownAdapter);
        hideProgressBar();
    }

    @OnClick(R.id.arrow_back)
    public void arrowBack(View view) {
        super.onBackPressed();
    }

    @OnClick(R.id.linear_submit)
    public void submitLevel(View view) {
        if (mEtLevel.getText().length() == 0) {
            mRelativeLevel.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
        } else {
            mRelativeLevel.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
        }
    }
}
