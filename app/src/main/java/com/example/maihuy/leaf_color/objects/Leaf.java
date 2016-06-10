package com.example.maihuy.leaf_color.objects;

import com.example.maihuy.leaf_color.framework.gamedev2d.DynamicGameObject;

import java.net.PortUnreachableException;

/**
 * Created by maihuy on 7/11/2015.
 */
public class Leaf extends DynamicGameObject {

    public static final float LEAF_WIDTH=150.0F;
    public static final float LEAF_HEIGHT=100.0F;
    public static final int LEAF_WAIT_STATE=0;
    public static final int LEAF_FALLING_STATE=1;
    public static final int LEAF_FELL_STATE=2;

    int state;
    float stateTime;

    public Leaf(float x, float y, float width, float height) {
        super(x, y, LEAF_WIDTH, LEAF_HEIGHT);
        state=LEAF_WAIT_STATE;
        stateTime=0;
    }

    public void update(float deltaTime){

    }
}
