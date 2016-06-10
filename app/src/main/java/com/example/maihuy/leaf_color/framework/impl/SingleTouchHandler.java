package com.example.maihuy.leaf_color.framework.impl;

/**
 * Created by maihuy on 4/12/2015.
 */

import android.view.MotionEvent;
import android.view.View;

import com.example.maihuy.leaf_color.framework.Input;
import com.example.maihuy.leaf_color.framework.Pool;
import com.example.maihuy.leaf_color.framework.Pool.PoolObjecFactory;

import java.util.ArrayList;
import java.util.List;

public class SingleTouchHandler implements TouchHandler {
    boolean isTouched;
    int touchX;
    int touchY;
    Pool<Input.TouchEvent> touchEventPool;
    List<Input.TouchEvent> touchEvents=new ArrayList<Input.TouchEvent>();
    List<Input.TouchEvent> touchEventsBuffer=new ArrayList<Input.TouchEvent>();
    float scaleX;
    float scaleY;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        synchronized (this){
            Input.TouchEvent touchEvent= touchEventPool.newObject();
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    touchEvent.type= Input.TouchEvent.TOUCH_DOWN;
                    isTouched=true;
                    break;
                case MotionEvent.ACTION_MOVE:
                    touchEvent.type= Input.TouchEvent.TOUCH_DRAGGED;
                    isTouched=true;
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    touchEvent.type= Input.TouchEvent.TOUCH_UP;
                    isTouched=false;
                    break;
            }

            touchEvent.x=touchX=(int)(event.getX()*scaleX);
            touchEvent.y=touchX=(int)(event.getY()*scaleY);
            touchEventsBuffer.add(touchEvent);

            return true;
        }
    }

    public SingleTouchHandler(View view,float scaleX, float scaleY) {
        PoolObjecFactory<Input.TouchEvent> factory=new PoolObjecFactory<Input.TouchEvent>() {
            @Override
            public Input.TouchEvent createObject() {
                return new Input.TouchEvent();
            }
        };
        touchEventPool=new Pool<Input.TouchEvent>(factory,100);
        view.setOnTouchListener(this);
        this.scaleX=scaleX;
        this.scaleY=scaleY;
    }

    @Override
    public boolean isTouchDown(int pointer) {
        synchronized (this){
            if (pointer==0) return isTouched;
            else return false;
        }
    }

    @Override
    public int getTouchX(int pointer) {
        synchronized (this){
            return touchX;
        }
    }

    @Override
    public int getTouchY(int pointer) {
        synchronized (this){
            return touchY;
        }
    }

    @Override
    public List<Input.TouchEvent> getTouchEvents() {
        synchronized (this){
            int len=touchEvents.size();
            for (int i=0;i<len;i++)
                touchEventPool.free(touchEvents.get(i));
            touchEvents.clear();
            touchEvents.addAll(touchEventsBuffer);
            touchEventsBuffer.clear();
            return touchEvents;
        }
    }
}
