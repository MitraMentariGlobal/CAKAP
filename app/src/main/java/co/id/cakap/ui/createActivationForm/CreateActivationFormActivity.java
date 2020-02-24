package co.id.cakap.ui.createActivationForm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.helper.Constant;

public class CreateActivationFormActivity extends AppCompatActivity implements CreateActivationFormContract.View{
    @Inject
    CreateActivationFormPresenter mCreateActivationFormPresenter;

    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.title_toolbar)
    TextView mTitleToolbar;
    @BindView(R.id.txt_transaction_id)
    TextView mTxtTransactionId;
    @BindView(R.id.txt_mb_id)
    TextView mTxtMbId;
    @BindView(R.id.txt_name)
    TextView mTxtName;

    private String mTitle = "";
    private String mTransactionId = "";
    private String mMemberId = "";
    private String mName = "";
    private CreateActivationFormContract.UserActionListener mUserActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_activation_form);
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
        mUserActionListener = mCreateActivationFormPresenter;
        mCreateActivationFormPresenter.setView(this);

        Intent intent = getIntent();
        mTitle = intent.getStringExtra(Constant.TITLE_DETAIL);
        mTransactionId = intent.getStringExtra(Constant.TRANSACTION_ID_DETAIL);
        mMemberId = intent.getStringExtra(Constant.MEMBER_ID_DETAIL);
        mName = intent.getStringExtra(Constant.NAME_DETAIL);

        mTitleToolbar.setText(mTitle.toUpperCase());
        mTxtTransactionId.setText(mTransactionId);
        mTxtMbId.setText(mMemberId);
        mTxtName.setText(mName);

        hideProgressBar();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void setErrorResponse(String message) {

    }
}
