package com.example.mybrowser;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.mybrowser.ui.UrlBarController;
import com.example.mybrowser.util.UiUtils;
import com.example.mybrowser.webview.WebViewExt;

public class MainActivity extends AppCompatActivity {

    private FrameLayout webViewContainer;
    private EditText urlEdit;
    private WebViewExt webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webViewContainer = findViewById(R.id.webViewContainer);
        urlEdit = findViewById(R.id.urlEdit);

        webView = new WebViewExt(this);
        webViewContainer.addView(webView);

        urlEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO ||
                event.getKeyCode() == KeyEvent.KEYCODE_ENTER &&
                event.getAction() == KeyEvent.ACTION_DOWN) {
                    webView.loadUrl(v.getText().toString());
                    UiUtils.hideKeyBoard(v);
                    return true;
                }
                return false;
            }
        });

        webView.init(new UrlBarController(urlEdit));
        webView.loadUrl("https://te3.ztemall.com/mtest");
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}