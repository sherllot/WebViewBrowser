package com.example.mybrowser.util;

import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.RequiresApi;

public class UiUtils {
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void hideKeyBoard(View view) {
        InputMethodManager imm = view.getContext().getSystemService(InputMethodManager.class);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
