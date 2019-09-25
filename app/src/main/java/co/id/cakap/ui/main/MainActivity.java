package co.id.cakap.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.ListAdapter;
import co.id.cakap.data.ResultData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity implements MainContract.View{
    @Inject
    MainPresenter mMainPresenter;

    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.main_progress_bar)
    ProgressBar mProgressBar;

    private MainContract.UserActionListener mUserActionListener;
    private GridLayoutManager mGridLayoutManager;
    private ListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    public void setAdapter(List<ResultData> resultData) {
        mGridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mListAdapter = new ListAdapter(resultData, this);
        mRecyclerView.setAdapter(mListAdapter);
    }

    @Override
    public void initializeData() {
        mUserActionListener = mMainPresenter;
        mMainPresenter.setView(this);
        mUserActionListener.getData();
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}
