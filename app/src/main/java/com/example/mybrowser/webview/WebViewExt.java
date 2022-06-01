package com.example.mybrowser.webview;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class WebViewExt extends WebView {
    public WebViewExt(@NonNull Context context) {
        super(context);
    }

    public WebViewExt(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WebViewExt(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public WebViewExt(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void init(Callback callback) {
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        setWebContentsDebuggingEnabled(true);

        //只要设置了WebViewClient,则【webview加载的页面内部的超链接(如<a hreh="超链接">)】被触发时，该超链接就不会交给外部系统浏览器进行处理。若webview没有设置WebViewClient，则webview加载的页面内部的超链接被触发时会交给外部浏览器进行处理。可以注释setWebViewClient(...)查看效果
        setWebViewClient(new WebClient());

        setWebChromeClient(new ChromeClient(callback));
    }

    public interface Callback {
        void onReceivedTitle(WebView view, String title);
    }
}
