package com.example.maihuy.leaf_color.framework.math;

/**
 * Created by maihuy on 4/27/2015.
 */
public class Rectangle {
    public final Vector2 lowerLeft;
    public float width, height;

    public Rectangle(float x, float y, float width, float height){
        this.lowerLeft=new Vector2(x, y);
        this.width=width;
        this.height=height;
    }

    public boolean overlapRectangles(Rectangle r1, Rectangle r2){
        if (r1.lowerLeft.getX()<r2.lowerLeft.getX()+r2.width&&
                r1.lowerLeft.getX()+r1.width>r2.lowerLeft.getX()&&
                r1.lowerLeft.getY()<r2.lowerLeft.getY()+r2.height&&
                r1.lowerLeft.getY()+r1.height>r2.lowerLeft.getY())
            return true;
        else return false;
    }

    public boolean overlapCircleRectangle(Circle c, Rectangle r){
        float closestX=c.center.getX();
        float closestY=c.center.getY();

        if (c.center.getX()<r.lowerLeft.getX()){
            closestX=r.lowerLeft.getX();
        }
        else if (c.center.getX()>r.lowerLeft.getX()+r.width){
            closestX=r.lowerLeft.getX()+r.width;
        }

        if (c.center.getY()<r.lowerLeft.getY()){
            closestY=r.lowerLeft.getY();
        }
        else  if (c.center.getY()>r.lowerLeft.getY()+r.height){
            closestY=r.lowerLeft.getY()+r.height;
        }

        return c.center.distSquared(closestX, closestY)<c.radius*c.radius;
    }
}
