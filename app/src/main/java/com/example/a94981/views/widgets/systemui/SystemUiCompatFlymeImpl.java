package com.example.a94981.views.widgets.systemui;

import android.content.Context;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * flyme系统
 * Created by chen_bin on 2018/4/18 0018.
 */
class SystemUiCompatFlymeImpl extends SystemUiCompatAndroidImpl {

    @Override
    protected void setLightStatusBarApi21(Context context, Window window, boolean lightStatusBar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field f = lp.getClass()
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                f.setAccessible(true);
                int bits = f.getInt(lp);
                Field f2 = lp.getClass()
                        .getDeclaredField("meizuFlags");
                f2.setAccessible(true);
                int meizuFlags = f2.getInt(lp);
                int oldFlags = meizuFlags;
                if (lightStatusBar) {
                    meizuFlags |= bits;
                } else {
                    meizuFlags &= ~bits;
                }
                if (oldFlags != meizuFlags) {
                    f2.setInt(lp, meizuFlags);
                }
            } catch (Exception ignored) {

            }
        }
    }
}
