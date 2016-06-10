package com.example.maihuy.leaf_color.framework.gl;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by maihuy on 4/26/2015.
 */
public class GLGraphics {
    GLSurfaceView glView;
    GL10 gl;

    GLGraphics(GLSurfaceView glView){
        this.glView=glView;
    }

    public GL10 getGl(){
        return gl;
    }

    public void setGL(GL10 gl){
        this.gl=gl;
    }

    public int getWidth(){
        return glView.getWidth();
    }

    public int getHeight(){
        return glView.getHeight();
    }
}
