package com.lmx.common.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    private static Toast toast;

    private ToastUtils() {
    }

    public static void show(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }

}
