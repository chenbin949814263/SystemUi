package com.systemui.views.widgets;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * oppo系统
 * Created by chen_bin on 2018/4/18 0018.
 */
class SystemUiCompatOppoImpl extends SystemUiCompatAndroidImpl {

    private static final int SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT = 0x00000010;

    @Override
    protected void setLightStatusBarApi21(Context context, Window window, boolean lightStatusBar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.TRANSPARENT);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            View view = window.getDecorView();
            int oldVis = view.getSystemUiVisibility();
            int newVis = oldVis;
            if (lightStatusBar) {
                newVis |= SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT;
            } else {
                newVis &= ~SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT;
            }
            if (oldVis != newVis) {
                view.setSystemUiVisibility(newVis);
            }
        }
    }
}
