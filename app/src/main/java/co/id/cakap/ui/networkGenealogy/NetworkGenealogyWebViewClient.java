package co.id.cakap.ui.networkGenealogy;

import android.webkit.WebView;
import android.webkit.WebViewClient;

class NetworkGenealogyWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
