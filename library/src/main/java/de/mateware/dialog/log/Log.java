package de.mateware.dialog.log;

/**
 * Created by mate on 10.02.2017.
 */

public class Log {

    private static final String TAG = "DIALOG";

    public static void v(String msg) {
        android.util.Log.v(TAG, msg);
    }

    public static void v(String msg, Throwable tr) {
        android.util.Log.v(TAG, msg, tr);
    }
    public static void d(String msg) {
        android.util.Log.d(TAG, msg);
    }
    public static void d(String msg, Throwable tr) {
        android.util.Log.d(TAG, msg, tr);
    }
    public static void i(String msg) {
        android.util.Log.i(TAG, msg);
    }
    public static void i(String msg, Throwable tr) {
        android.util.Log.i(TAG, msg, tr);
    }
    public static void w(String msg) {
        android.util.Log.w(TAG, msg);
    }
    public static void w(String msg, Throwable tr) {
        android.util.Log.w(TAG, msg, tr);
    }
    public static void w(Throwable tr) {
        android.util.Log.w(TAG, tr);
    }
    public static void e(String msg) {
        android.util.Log.e(TAG, msg);
    }
    public static void e(String msg, Throwable tr) {
        android.util.Log.e(TAG, msg, tr);
    }
    public static void wtf(String msg) {
        android.util.Log.wtf(TAG, msg);
    }
    public static void wtf(String msg, Throwable tr) {
        android.util.Log.wtf(TAG, msg, tr);
    }
    public static void wtf(Throwable tr) {
        android.util.Log.wtf(TAG, tr);
    }

}
