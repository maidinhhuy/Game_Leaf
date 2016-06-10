package com.example.maihuy.leaf_color.framework.math;

/**
 * Created by maihuy on 4/27/2015.
 */
public class Sphere {
    public final Vector3 center=new Vector3();
    public float radius;

    public Sphere(float x, float y, float z, float radius){
        this.center.set(x, y, z);
        this.radius=radius;
    }

    public static boolean overlapSpheres(Sphere s1, Sphere s2){
        float distance=s1.center.distSquared(s2.center);
        float radiusSum=s1.radius+s2.radius;
        return distance<=radiusSum*radiusSum;
    }

    public static boolean pointInSphere(Sphere c, Vector3 p){
        return c.center.distSquared(p)<c.radius*c.radius;
    }

    public static boolean pointInSphere(Sphere c, float x, float y, float z){
        return c.center.distSquared(x, y, z)<c.radius*c.radius;
    }


}
