package com.example.maihuy.leaf_color.framework;

/**
 * Created by maihuy on 4/12/2015.
 */
import com.example.maihuy.leaf_color.framework.Graphics.PixmapFormat;

public interface Pixmap {
    public int getWidth();

    public int getHeight();

    public Graphics.PixmapFormat getFormat();

    public void dispose();
}
