package com.example.maihuy.leaf_color.framework.gl;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by maihuy on 4/27/2015.
 */
public class AmbientLight {
    float[] color={0.2f, 0.2f, 0.2f, 1};

    public void setColor(float r, float g, float b, float a){
        color[0]=r;
        color[1]=g;
        color[2]=b;
        color[3]=a;
    }

    public void enable(GL10 gl){
        gl.glLightModelfv(GL10.GL_LIGHT_MODEL_AMBIENT, color, 0);
    }
}
