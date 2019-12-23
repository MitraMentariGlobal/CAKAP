package co.id.cakap.ui.networkTable;

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

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.EbonusAdapter;
import co.id.cakap.adapter.NetworkTableAdapter;
import co.id.cakap.data.NetworkTableData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.ui.networkGenealogy.NetworkGenealogyActivity;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class NetworkTableActivity extends AppCompatActivity implements NetworkTableContract.View {
    @Inject
    NetworkTablePresenter mNetworkTablePresenter;

    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;
    @BindView(R.id.title_toolbar)
    TextView mTitle;
    @BindView(R.id.et_level)
    EditText mLevel;
    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.nested_scroll)
    NestedScrollView mNestedScroll;

    private NetworkTableContract.UserActionListener mUserActionListener;
    private NetworkTableAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_table);
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
        mUserActionListener = mNetworkTablePresenter;
        mNetworkTablePresenter.setView(this);

        mUserActionListener.getData();
        mTitle.setText(getString(R.string.network_table).toUpperCase());
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
        Toast.makeText(NetworkTableActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setAdapter(List<NetworkTableData> resultData) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
        mNestedScroll.getParent().requestChildFocus(mNestedScroll, mNestedScroll);
        mListAdapter = new NetworkTableAdapter(resultData, this);
        mRecyclerView.setAdapter(mListAdapter);
        hideProgressBar();
    }

    @OnClick(R.id.arrow_back)
    public void arrowBack(View view) {
        super.onBackPressed();
    }
}
