package com.systemui.views.widgets;

import android.content.Context;
import android.os.Build;
import android.view.Window;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * miui系统
 * Created by chen_bin on 2018/4/18 0018.
 */
class SystemUiCompatMiuiImpl extends SystemUiCompatAndroidImpl {

    @Override
    protected void setLightStatusBarApi23(Context context, Window window, boolean lightStatusBar) {
        setLightStatusBarApi21(context,window,lightStatusBar);
        super.setLightStatusBarApi23(context, window, lightStatusBar);
    }

    @Override
    protected void setLightStatusBarApi21(Context context, Window window, boolean lightStatusBar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Class<? extends Window> clazz = window.getClass();
            try {
                Class<?> lp = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field f = lp.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                int darkModeFlag = f.getInt(lp);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                extraFlagField.invoke(window, lightStatusBar ? darkModeFlag : 0, darkModeFlag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}