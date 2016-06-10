package com.example.maihuy.leaf_color.framework.math;

/**
 * Created by maihuy on 4/27/2015.
 */
public class OverlapTester {
    public static boolean overlapCircles(Circle c1, Circle c2){
        float distance=c1.center.distSquared(c2.center);
        float radiusSum=c1.radius+c2.radius;
        return distance<=radiusSum*radiusSum;
    }

    public static boolean overlapRectangles(Rectangle r1, Rectangle r2){
        if(r1.lowerLeft.getX()+ r1.width  < r2.lowerLeft.getX() + r2.width &&
                r1.lowerLeft.getX() > r2.lowerLeft.getX() &&
                r1.lowerLeft.getY() + r1.height< r2.lowerLeft.getY() + r2.height &&
                r1.lowerLeft.getY()  > r2.lowerLeft.getY())
            return true;
        else
            return false;
    }

    public static boolean overlapCircleRectangle(Circle c, Rectangle r) {
        float closestX = c.center.getX();
        float closestY = c.center.getY();

        if(c.center.getX() < r.lowerLeft.getX()) {
            closestX = r.lowerLeft.getX();
        }
        else if(c.center.getX() > r.lowerLeft.getX() + r.width) {
            closestX = r.lowerLeft.getX() + r.width;
        }

        if(c.center.getY() < r.lowerLeft.getY()) {
            closestY = r.lowerLeft.getY();
        }
        else if(c.center.getY() > r.lowerLeft.getY() + r.height) {
            closestY = r.lowerLeft.getY() + r.height;
        }

        return c.center.distSquared(closestX, closestY) < c.radius * c.radius;
    }

    public static boolean pointInCircle(Circle c, Vector2 p) {
        return c.center.distSquared(p) < c.radius * c.radius;
    }

    public static boolean pointInCircle(Circle c, float x, float y) {
        return c.center.distSquared(x, y) < c.radius * c.radius;
    }

    public static boolean pointInRectangle(Rectangle r, Vector2 p) {
//        Log.d(r.lowerLeft.getX()+"-"+r.lowerLeft.getY()+"-"+r.width+"-"+r.height+":"+p.getX()+"-"+p.getY(),"");
//        Log.d((r.lowerLeft.getX() <= p.getX())+"+"+( r.lowerLeft.getX() + r.width >= p.getX())+"+"+
//                (r.lowerLeft.getY() <= p.getY())+"+"+ (r.lowerLeft.getY() + r.height >= p.getY()),"");
//        Log.d((r.lowerLeft.getX() <= p.getX())+"&&&&"+r.lowerLeft.getX()+"<="+p.getX(),"");
        return r.lowerLeft.getX() <= p.getX() && r.lowerLeft.getX() + r.width >= p.getX() &&
                r.lowerLeft.getY() <= p.getY() && r.lowerLeft.getY() + r.height >= p.getY();
    }

    public static boolean pointInRectangle(Rectangle r, float x, float y) {
        return r.lowerLeft.getX() <= x && r.lowerLeft.getX() + r.width >= x &&
                r.lowerLeft.getY() <= y && r.lowerLeft.getY() + r.height >= y;
    }
}
