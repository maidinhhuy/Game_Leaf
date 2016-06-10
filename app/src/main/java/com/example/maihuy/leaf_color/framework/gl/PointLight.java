package com.example.maihuy.leaf_color.framework.gl;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by maihuy on 4/27/2015.
 */
public class PointLight {
    float[] ambitent={0.2f, 0.2f, 0.2f, 1.0f};
    float[] diffuse={1.0f, 1.0f, 1.0f, 1.0f};
    float[] specular={0.0f, 0.0f, 0.0f, 1.0f};
    float[] position={0, 0 ,0, 1};
    int lastLightId=0;

    public void setAmbitent(float r, float g, float b, float a){
        ambitent[0]=r;
        ambitent[1]=g;
        ambitent[2]=b;
        ambitent[3]=a;
    }

    public void setDiffuse(float r, float g, float b, float a){
        diffuse[0] =r;
        diffuse[1]=g;
        diffuse[2]=b;
        diffuse[3]=a;
    }

    public void setSpecular(float r, float g, float b, float a){
        specular[0] =r;
        specular[1]=g;
        specular[2]=b;
        specular[3]=a;
    }

    public void setPosition(float x, float y, float z){
        position[0]=x;
        position[1]=y;
        position[2]=z;
    }

    public void enable(GL10 gl, int lightId){
        gl.glEnable(lightId);
        gl.glLightfv(lightId, GL10.GL_AMBIENT, ambitent, 0);
        gl.glLightfv(lightId, GL10.GL_DIFFUSE, diffuse, 0);
        gl.glLightfv(lightId, GL10.GL_SPECULAR, specular, 0);
        gl.glLightfv(lightId, GL10.GL_POSITION, position, 0);
    }

    public void disable(GL10 gl){
        gl.glDisable(lastLightId);
    }
}
