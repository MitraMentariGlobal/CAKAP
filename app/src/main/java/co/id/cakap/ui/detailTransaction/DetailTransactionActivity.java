package co.id.cakap.ui.detailTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.di.module.MainActivityModule;

public class DetailTransactionActivity extends AppCompatActivity implements DetailTransactionContract.View {
    private static final String TAG = "DetailTransactionActivity";

    @Inject
    DetailTransactionPresenter mDetailTransactionPresenter;

    @BindView(R.id.main_progress_bar)
    ProgressBar mProgressBar;

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
    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(DetailTransactionActivity.this, message, Toast.LENGTH_SHORT).show();
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
