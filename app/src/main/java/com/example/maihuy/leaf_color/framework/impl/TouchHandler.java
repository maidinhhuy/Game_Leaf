package com.example.maihuy.leaf_color.framework.impl;

import android.view.View;

import java.util.List;

/**
 * Created by maihuy on 4/12/2015.
 */

public interface TouchHandler extends View.OnTouchListener {
    public boolean isTouchDown(int pointer);

    public int getTouchX(int pointer);

    public int getTouchY(int pointer);

    public List<com.example.maihuy.leaf_color.framework.Input.TouchEvent> getTouchEvents();
    public static class TouchEvent {
        public static final int TOUCH_DOWN=0;

        public static final int TOUCH_UP=1;

        public static final int TOUCH_DRAGGED=2;

        public int type;
        public int x,y;
        public int pointer;

    }
}
