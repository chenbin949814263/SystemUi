package com.systemui.views.widgets;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.Window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by chen_bin on 2018/4/18 0018.
 */
public class SystemUiCompat {

    private static final String ROM_OPPO = "OPPO";
    private static final String ROM_MIUI = "MIUI";
    private static final String ROM_FLYME = "FLYME";
    private static final String ROM_UNKNOW = "UNKNOW";

    private static final String VERSION_OPPO = "ro.build.version.opporom";
    private static final String VERSION_MIUI = "ro.miui.ui.version.name";
    private static final String VERSION_DISPLAY = "ro.build.display.id";

    private static final SystemUiCompatAndroidImpl IMPL;

    static {
        switch (getSystemRom()) {
            case ROM_OPPO:
                IMPL = new SystemUiCompatOppoImpl();
                break;
            case ROM_MIUI:
                IMPL = new SystemUiCompatMiuiImpl();
                break;
            case ROM_FLYME:
                IMPL = new SystemUiCompatFlymeImpl();
                break;
            default:
                IMPL = new SystemUiCompatAndroidImpl();
                break;
        }
    }

    public static void setLightStatusBar(Context context, boolean lightStatusBar) {
        if (context instanceof Activity) {
            setLightStatusBar(context, ((Activity) context).getWindow(), lightStatusBar);
        }
    }

    public static void setLightStatusBar(Context context, Window window, boolean lightStatusBar) {
        IMPL.setLightStatusBar(context, window, lightStatusBar);
    }

    public static void setNavigationBarColor(Context context, int navigationBarColor) {
        if (context instanceof Activity) {
            setNavigationBarColor(context, ((Activity) context).getWindow(), navigationBarColor);
        }
    }

    public static void setNavigationBarColor(
            Context context, Window window, int navigationBarColor) {
        IMPL.setNavigationBarColor(context, window, navigationBarColor);
    }

    /**
     * 获取系统ROM信息
     *
     * @return
     */
    private static String getSystemRom() {
        if (!TextUtils.isEmpty(getSystemProperty(VERSION_OPPO))) {
            return ROM_OPPO;
        } else if (!TextUtils.isEmpty(getSystemProperty(VERSION_MIUI))) {
            return ROM_MIUI;
        } else {
            String property = getSystemProperty(VERSION_DISPLAY);
            if (!TextUtils.isEmpty(property) && property.toUpperCase()
                    .contains(ROM_FLYME)) {
                return ROM_FLYME;
            } else {
                return ROM_UNKNOW;
            }
        }
    }

    /**
     * 获取系统信息
     *
     * @param propName
     * @return
     */
    private static String getSystemProperty(String propName) {
        String line = "";
        BufferedReader reader = null;
        Process process = null;
        try {
            process = Runtime.getRuntime()
                    .exec("getprop " + propName);
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()), 1024);
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (process != null) {
                process.destroy();
            }
        }
        return line;
    }

}
