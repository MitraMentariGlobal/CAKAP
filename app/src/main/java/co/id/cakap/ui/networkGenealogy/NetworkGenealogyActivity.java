package co.id.cakap.ui.networkGenealogy;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.data.NetworkGenealogyData;
import co.id.cakap.di.module.MainActivityModule;

public class NetworkGenealogyActivity extends AppCompatActivity implements NetworkGenealogyContract.View {
    @Inject
    NetworkGenealogyPresenter mNetworkGenealogyPresenter;

    @BindView(R.id.title_toolbar)
    TextView mTitle;
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

    private NetworkGenealogyContract.UserActionListener mUserActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_genealogy);
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
        mUserActionListener = mNetworkGenealogyPresenter;
        mNetworkGenealogyPresenter.setView(this);
        mTitle.setText(getString(R.string.network_genealogy).toUpperCase());
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
//        mName.setText(networkGenealogyData.getUser_name());

        WebSettings webSetting = mWebView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setDisplayZoomControls(true);
        if (Build.VERSION.SDK_INT >= 26) {
            webSetting.setSafeBrowsingEnabled(false);
        }

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                showProgressBar();
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                hideProgressBar();
                super.onPageFinished(view, url);
            }
        });
        mWebView.loadUrl(networkGenealogyData.getLink());

        hideProgressBar();
    }

    @OnClick(R.id.linear_submit)
    public void submitMemberId(View view) {
        if (mMbId.getText().length() == 0) {
            mRelativeMemberId.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
        } else {
            mRelativeMemberId.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
            mUserActionListener.getData(mMbId.getText().toString());
//            mMbId.setInputType(0);
//            showProgressBar();
        }
    }

    @OnClick(R.id.arrow_back)
    public void arrowBack(View view) {
        super.onBackPressed();
    }
}
