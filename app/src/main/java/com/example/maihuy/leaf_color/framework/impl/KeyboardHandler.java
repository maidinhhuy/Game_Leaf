package com.example.maihuy.leaf_color.framework.impl;


import android.view.View;

import com.example.maihuy.leaf_color.framework.Input;
import com.example.maihuy.leaf_color.framework.Input.KeyEvent;
import com.example.maihuy.leaf_color.framework.Pool;
import com.example.maihuy.leaf_color.framework.Pool.PoolObjecFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maihuy on 4/12/2015.
 */
public class KeyboardHandler implements View.OnKeyListener {
    boolean[] pressedKeys=new boolean[128];
    Pool<Input.KeyEvent> keyEventPool;
    List<KeyEvent> keyEventsBuffer=new ArrayList<KeyEvent>();
    List<KeyEvent> keyEvents =new ArrayList<KeyEvent>();

    public KeyboardHandler(View view) {
        PoolObjecFactory<KeyEvent> factory=new PoolObjecFactory<KeyEvent>() {
            @Override
            public KeyEvent createObject() {
                return new KeyEvent();
            }
        };
        keyEventPool=new Pool<KeyEvent>(factory,100);
        view.setOnKeyListener(this);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }

    @Override
    public boolean onKey(View v, int keyCode, android.view.KeyEvent event) {
        if (event.getAction()==android.view.KeyEvent.ACTION_MULTIPLE)
            return false;

        synchronized (this){
            KeyEvent keyEven=keyEventPool.newObject();
            keyEven.keyCode=keyCode;
            keyEven.keyChar=(char) event.getUnicodeChar();
            if (event.getAction()==android.view.KeyEvent.ACTION_DOWN){
                keyEven.type= KeyEvent.KEY_DOWN;
                if (keyCode>0&&keyCode<127) pressedKeys[keyCode]=true;
            }
            if (event.getAction()==android.view.KeyEvent.ACTION_UP){
                keyEven.type=KeyEvent.KEY_UP;
                if (keyCode>0&&keyCode<127)
                    pressedKeys[keyCode]=false;
            }
            keyEventsBuffer.add(keyEven);
        }
        return false;
    }

    public boolean isKeyPressed(int keyCode){
        if (keyCode<0|| keyCode>127) return false;
        return pressedKeys[keyCode];
    }

    public List<KeyEvent> getKeyEvents(){
        synchronized (this){
            int len=keyEvents.size();
            for (int i=0;i<len;i++) keyEventPool.free(keyEvents.get(i));

            keyEvents.clear();
            keyEvents.addAll(keyEventsBuffer);
            keyEventsBuffer.clear();
            return keyEvents;
        }
    }
}
