package com.he.ztto.perf;

public class Method {
    private static final String TAG = "Trace";

    public static void i() {
        android.util.Log.e(TAG, "time " + System.currentTimeMillis());
    }
}
