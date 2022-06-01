package com.example.mybrowser.webview;

import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;

public class WebClient extends WebViewClient {

    /**
     * shouldOverrideUrlLoading调用时机：
     * 1、浏览器内核内部检测到需要load另一个url时，如WebView(浏览器)加载的网页内部存在一个超链接，如<a href=“...”>时。
     * 2、重定向时
     * 注：webview.loadUrl(...)是无法触发shouldOverrideUrlLoading的。
     *
     * 关于shouldOverrideUrlLoading返回值的问题讲解：
     * 1、若webview没有设置WebViewClient，即没有将该类WebClient(继承自WebViewClient)类进行实例化并设置给webview的话，WebView则
     * 连该类的实例对象都没有，则webview去loadUrl时，加载的url会丢给外部的Android系统的浏览器进行处理。所以不会涉及shouldOverrideUrlLoading被触发的问题
     * 2、若webview设置了WebViewClient，则在点击webview页面内的超链接时，会触发shouldOverrideUrlLoading，该方法的返回值有两种取值:true,false：
     *   (1)返回true时，则说明由应用的代码处理该url，webview不会再去处理，一般形式如下
     *     @Override
     *     public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
     *         （应用的代码，去处理该url）
     *         return true;
     *     }
     *   (2)返回false时，则说明由webview处理该url，即webview加载该url，一般形式如下
     *     @Override
     *     public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
     *         （可以写代码，但最终该方法返回false，表示这里的代码只是初步处理，最终还是要交给webview去加载）
     *         return false;
     *     }
     * @param view
     * @param request
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        Uri url = request.getUrl();
        Log.e("browser","[new]url: " + url);
        String scheme = url.getScheme();
        return scheme != null && !scheme.startsWith("http"); //http或https请求都交给WebView进行处理(加载)
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.e("browser","[old]url: " + url);
        return super.shouldOverrideUrlLoading(view, url);
    }
}
