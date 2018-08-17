package com.systemui.views.widgets;

import android.content.Context;
import android.view.Window;

/**
 * Created by chen_bin on 2018/4/18 0018.
 */
public interface SystemUiCompatImpl {

    /**
     * @param context
     * @param window
     * @param lightStatusBar
     */
    void setLightStatusBar(Context context, Window window, boolean lightStatusBar);


    /**
     * 设置底部状态栏颜色
     *
     * @param context
     * @param color
     */
    void setNavigationBarColor(Context context, Window window, int color);
}
