package com.systemui.utils;

import android.graphics.Color;

public class CommonUtil {

    /**
     * 是否为浅色
     *
     * @param color
     * @return
     */
    public static boolean isLightColor(int color) {
        final double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) +
                0.114 * Color.blue(
                color)) / 255;
        return darkness < 0.4;
    }

}
