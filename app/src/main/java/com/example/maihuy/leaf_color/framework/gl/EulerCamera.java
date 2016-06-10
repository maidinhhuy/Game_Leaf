package com.example.maihuy.leaf_color.framework.gl;

import android.opengl.GLU;
import android.opengl.Matrix;

import com.example.maihuy.leaf_color.framework.math.Vector3;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by maihuy on 4/27/2015.
 */
public class EulerCamera {
    final Vector3 position=new Vector3();
    float yaw;
    float pitch;
    float fieldOfView;
    float aspectRatiol;
    float near;
    float far;

    public EulerCamera(float fieldOfView, float aspectRatiol, float near, float far) {
        this.fieldOfView = fieldOfView;
        this.aspectRatiol = aspectRatiol;
        this.near = near;
        this.far = far;
    }

    public Vector3 getPosition(){
        return position;
    }
    public float getYaw(){
        return yaw;
    }
    public float getPitch(){
        return pitch;
    }

    public void setAndles(float yaw, float pitch){
        if (pitch<=-90) pitch=-90;
        if (pitch>90) pitch=90;
        this.yaw=yaw;
        this.pitch=pitch;
    }

    public void rotate(float yawInc, float pitchInc){
        this.yaw+=yawInc;
        this.pitch+=pitchInc;
        if (pitch<-90) pitch=-90;
        if (pitch>90) pitchInc=90;
    }

    public void setMatrices(GL10 gl){
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, fieldOfView, aspectRatiol, near, far);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glRotatef(-pitch, 1, 0,0);
        gl.glRotatef(-yaw, 0, 1, 0);
        gl.glTranslatef(-position.x, -position.y, -position.z);
    }

    final float[] matrix=new float[16];
    final float[] inVec={0,0,-1,1};
    final float[] outVec=new float[4];
    final Vector3 direction=new Vector3();

    public Vector3 getDirection(){
        Matrix.setIdentityM(matrix, 0);
        Matrix.rotateM(matrix, 0, yaw, 0, 1, 0);
        Matrix.rotateM(matrix, 0, pitch, 1, 0, 0);
        Matrix.multiplyMV(outVec, 0, matrix, 0, inVec, 0);
        direction.set(outVec[0], outVec[1], outVec[2]);
        return direction;
    }

}
