package com.example.maihuy.leaf_color.framework.gl;

import com.example.maihuy.leaf_color.framework.gamedev2d.GameObject;
import com.example.maihuy.leaf_color.framework.math.Vector3;

/**
 * Created by maihuy on 4/27/2015.
 */
public class DynamicGameObject3D extends GameObject{
    public final Vector3 velocity;
    public final Vector3 accel;

    public DynamicGameObject3D(float x, float y, float z, float radius) {
        super(x,y,z,radius);
        velocity=new Vector3();
        accel=new Vector3();
    }
}
