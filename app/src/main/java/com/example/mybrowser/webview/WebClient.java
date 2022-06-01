package com.example.mybrowser.webview;

import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;

public class WebClient extends WebViewClient {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        Uri url = request.getUrl();
        Log.e("browser","url: " + url);
        String scheme = url.getScheme();
        return scheme != null && !scheme.startsWith("http");
    }
}
