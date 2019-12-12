package co.id.cakap.ui.networkGenealogy;

import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.data.NetworkGenealogyData;
import co.id.cakap.di.module.MainActivityModule;

public class NetworkGenealogyActivity extends AppCompatActivity implements NetworkGenealogyContract.View {
    @Inject
    NetworkGenealogyPresenter mNetworkGenealogyPresenter;

    @BindView(R.id.relative_member_id)
    RelativeLayout mRelativeMemberId;
    @BindView(R.id.et_mb_id)
    EditText mMbId;
    @BindView(R.id.et_name)
    EditText mName;
    @BindView(R.id.linear_submit)
    LinearLayout mLinearSubmit;
    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;

    private String mUrl = "http://aplikasicakap.co.id";
    private NetworkGenealogyContract.UserActionListener mUserActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_genealogy);

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
        mUserActionListener = mNetworkGenealogyPresenter;
        mNetworkGenealogyPresenter.setView(this);

        WebSettings webSetting = mWebView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setDisplayZoomControls(true);
        if (Build.VERSION.SDK_INT >= 26) {
            webSetting.setSafeBrowsingEnabled(false);
        }
        mWebView.setWebViewClient(new NetworkGenealogyWebViewClient());
        mWebView.loadUrl(mUrl);
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
        Toast.makeText(NetworkGenealogyActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setData(NetworkGenealogyData networkGenealogyData) {
        mName.setText(networkGenealogyData.getUser_name());
    }

    @OnClick(R.id.linear_submit)
    public void submitMemberId(View view) {
        if (mMbId.getText().length() == 0) {
            mRelativeMemberId.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
        } else {
            mRelativeMemberId.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
            mUserActionListener.getData(mMbId.getText().toString());
            mMbId.setInputType(0);
//            showProgressBar();
        }
    }

}
