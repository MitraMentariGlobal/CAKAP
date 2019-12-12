package co.id.cakap.ui.dashboard.activity.activityBonusStatement;

import android.webkit.WebView;
import android.webkit.WebViewClient;

class ActivityBonusStatementWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
