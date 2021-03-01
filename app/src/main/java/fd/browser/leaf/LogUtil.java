package fd.browser.leaf;

import android.util.Log;

public class LogUtil {
    public static final int V = 5;
    public static final int D = 4;
    public static final int I = 3;
    public static final int W = 2;
    public static final int E = 1;
    private static final String TAG = "[leaf-browser]";
    private static int logLevel = 5;

    public LogUtil() {
    }

    public static void v(String msg) {
        if (logLevel >= 5) {
            Log.v(TAG, msg);
        }

    }

    public static void d(String msg) {
        if (logLevel >= 4) {
            Log.d(TAG, msg);
        }

    }

    public static void i(String msg) {
        if (logLevel >= 3) {
            Log.i(TAG, msg);
        }

    }

    public static void w(String msg) {
        if (logLevel >= 2) {
            Log.w(TAG, msg);
        }

    }

    public static void e(String msg) {
        if (logLevel >= 1) {
            Log.e(TAG, msg);
        }

    }

    public static void setLogLevel(int level) {
        logLevel = level;
    }
}
