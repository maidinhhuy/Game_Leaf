package com.example.maihuy.leaf_color.framework.gamedev2d;

import com.example.maihuy.leaf_color.framework.math.Rectangle;
import com.example.maihuy.leaf_color.framework.math.Vector2;

/**
 * Created by maihuy on 4/27/2015.
 */
public class GameObject {
    public final Vector2 position;
    public final Rectangle bounds;

    public GameObject(float x, float y, float width, float height){
        this.position=new Vector2(x, y);
        this.bounds=new Rectangle(x-width/2, y-height/2, width, height);
    }


}
