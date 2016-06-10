package com.example.maihuy.leaf_color.framework.impl;

import android.graphics.Bitmap;

import com.example.maihuy.leaf_color.framework.Graphics;
import com.example.maihuy.leaf_color.framework.Pixmap;

/**
 * Created by maihuy on 4/12/2015.
 */
public class AndroidPixmap implements Pixmap {
    Bitmap bitmap;
    Graphics.PixmapFormat format;

    public AndroidPixmap(Bitmap bitmap, Graphics.PixmapFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }

    @Override
    public Graphics.PixmapFormat getFormat() {
        return format;
    }


    @Override
    public void dispose() {
        bitmap.recycle();
    }
}
