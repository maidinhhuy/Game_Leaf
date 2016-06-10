package com.example.maihuy.leaf_color.framework.math;

/**
 * Created by maihuy on 4/27/2015.
 */
public class Circle {
    public final Vector2 center=new Vector2();
    public float radius;

    public Circle(float x, float y, float radius){
        this.center.set(x, y);
        this.radius=radius;
    }

    public boolean overlapCircles(Circle c1, Circle c2){
        float distance=c1.center.distSquared(c2.center);
        float radiusSum=c1.radius+c2.radius;
        return distance<=radiusSum*radiusSum;
    }

}
