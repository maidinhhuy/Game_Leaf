package com.example.maihuy.leaf_color.game;

import android.os.Bundle;
import android.util.Log;

import com.example.maihuy.leaf_color.R;
import com.example.maihuy.leaf_color.framework.Screen;
import com.example.maihuy.leaf_color.framework.gl.GLGame;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


/**
 * Created by maihuy on 7/7/2015.
 */
public class GameColor extends GLGame{
    boolean firstTimeCreate=true;


    @Override
    public Screen getStartScreen() {
        return new GameScreen(this);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        super.onSurfaceCreated(gl, config);

        if (firstTimeCreate){
            Settings.load(getFileIO());
            Assets.load(this);
            firstTimeCreate=false;
        }else {
            Assets.reload();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
