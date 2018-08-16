package com.example.a94981.views.widgets.systemui;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.hunliji.hljcommonlibrary.utils.CommonUtil;

/**
 * 通用android系统
 * Created by chen_bin on 2018/4/18 0018.
 */
class SystemUiCompatAndroidImpl implements SystemUiCompatImpl {

    @Override
    public void setLightStatusBar(Context context, Window window, boolean lightStatusBar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setLightStatusBarApi23(context, window, lightStatusBar);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setLightStatusBarApi21(context, window, lightStatusBar);
        }
    }

    protected void setLightStatusBarApi23(Context context, Window window, boolean lightStatusBar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.setStatusBarColor(Color.TRANSPARENT);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            View view = window.getDecorView();
            int oldVis = view.getSystemUiVisibility();
            int newVis = oldVis;
            if (lightStatusBar) {
                newVis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                newVis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            if (oldVis != newVis) {
                view.setSystemUiVisibility(newVis);
            }
        }
    }

    protected void setLightStatusBarApi21(Context context, Window window, boolean lightStatusBar) {

    }

    @Override
    public void setNavigationBarColor(Context context, Window window, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            window.setNavigationBarColor(color);
            View view = window.getDecorView();
            int oldVis = view.getSystemUiVisibility();
            int newVis = oldVis;
            if (CommonUtil.isLightColor(color)) {
                newVis |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
            } else {
                newVis &= ~View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
            }
            if (oldVis != newVis) {
                view.setSystemUiVisibility(newVis);
            }
        }
    }
}
