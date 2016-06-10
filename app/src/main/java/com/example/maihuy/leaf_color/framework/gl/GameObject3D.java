package com.example.maihuy.leaf_color.framework.gl;

import com.example.maihuy.leaf_color.framework.math.Sphere;
import com.example.maihuy.leaf_color.framework.math.Vector3;

/**
 * Created by maihuy on 4/27/2015.
 */
public class GameObject3D
{
    public final Vector3 position;
    public final Sphere bounds;

    public GameObject3D(float x, float y, float z, float radius){
        this.position=new Vector3(x, y, z);
        this.bounds=new Sphere(x, y, z, radius);
    }
}
