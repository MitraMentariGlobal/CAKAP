package co.id.cakap.ui.detailTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.DetailTransaksiAdapter;
import co.id.cakap.data.DetailTransactionData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class DetailTransactionActivity extends AppCompatActivity implements DetailTransactionContract.View {
    private static final String TAG = "DetailTransactionActivity";

    @Inject
    DetailTransactionPresenter mDetailTransactionPresenter;

    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;
    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.title_toolbar)
    TextView mTitleToolbar;
    @BindView(R.id.title)
    TextView mTitleText;
    @BindView(R.id.txt_transaction_id)
    TextView mTransactionIdText;
    @BindView(R.id.nested_scroll)
    NestedScrollView mNestedScroll;

    private String mTitle = "";
    private String mTransactionId = "";
    private DetailTransaksiAdapter mListAdapter;
    private DetailTransactionContract.UserActionListener mUserActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaksi);
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
        mUserActionListener = mDetailTransactionPresenter;
        mDetailTransactionPresenter.setView(this);
        mUserActionListener.getData();

        Intent intent = getIntent();
        mTitle = intent.getStringExtra(Constant.TITLE_DETAIL);
        mTransactionId = intent.getStringExtra(Constant.TRANSACTION_ID_DETAIL);
        mTitleText.setText(mTitle);
        mTransactionIdText.setText(mTransactionId);
        mTitleToolbar.setText(getString(R.string.detail_transaksi).toUpperCase());
    }

    @Override
    public void setAdapter(List<DetailTransactionData> resultData) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
        mNestedScroll.getParent().requestChildFocus(mNestedScroll, mNestedScroll);

        mListAdapter = new DetailTransaksiAdapter(resultData, this);
        mRecyclerView.setAdapter(mListAdapter);
        OverScrollDecoratorHelper.setUpOverScroll(mRecyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

        hideProgressBar();
    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        mRelativeProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mRelativeProgressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.arrow_back)
    public void arrowBack(View view) {
        super.onBackPressed();
    }
}
