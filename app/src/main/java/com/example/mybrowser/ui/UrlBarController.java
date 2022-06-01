package com.example.mybrowser.ui;

import android.webkit.WebView;
import android.widget.EditText;

import com.example.mybrowser.webview.WebViewExt;

public class UrlBarController implements WebViewExt.Callback {

    private EditText mEdit;

    public UrlBarController(EditText editText) {
        mEdit = editText;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        mEdit.setText(title);
    }
}
