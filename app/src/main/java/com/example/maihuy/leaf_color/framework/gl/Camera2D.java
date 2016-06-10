package com.example.maihuy.leaf_color.framework.gl;

import com.example.maihuy.leaf_color.framework.math.Vector2;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by maihuy on 4/27/2015.
 */
public class Camera2D {
    public final Vector2 position;
    public float zoom;
    public final float frustumWidth;
    public final float frustumHeight;
    final GLGraphics glGraphics;

    public Camera2D(float frustumWidth, float frustumHeight, GLGraphics glGraphics) {
        this.frustumWidth = frustumWidth;
        this.frustumHeight = frustumHeight;
        this.glGraphics = glGraphics;
        this.position = new Vector2(frustumWidth/2, frustumHeight/2);
        this.zoom=1.0f;
    }

    public void setViewportAndMatrices(){
        GL10 gl=glGraphics.getGl();
        gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(position.getX()-frustumWidth*zoom/2,
                position.getX()+frustumWidth*zoom/2,
                position.getY()-frustumHeight*zoom/2,
                position.getY()+frustumHeight*zoom/2, 1, -1);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void touchToWorld(Vector2 touch){
        touch.setX(touch.getX()/(float)glGraphics.getWidth()*frustumWidth*zoom);
        touch.setY((1-touch.getY()/(float)glGraphics.getHeight())*frustumHeight*zoom);
        touch.add(position).sub(frustumWidth*zoom/2, frustumHeight*zoom/2);
    }
}
