package com.example.maihuy.leaf_color.framework.gl;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.RelativeLayout;

import com.example.maihuy.leaf_color.framework.Audio;
import com.example.maihuy.leaf_color.framework.FileIO;
import com.example.maihuy.leaf_color.framework.Game;
import com.example.maihuy.leaf_color.framework.Graphics;
import com.example.maihuy.leaf_color.framework.Input;
import com.example.maihuy.leaf_color.framework.Screen;
import com.example.maihuy.leaf_color.framework.impl.AndroidAudio;
import com.example.maihuy.leaf_color.framework.impl.AndroidFileIO;
import com.example.maihuy.leaf_color.framework.impl.AndroidInput;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by maihuy on 4/26/2015.
 */
public abstract class GLGame extends Activity implements Game, GLSurfaceView.Renderer{
    enum GLGameState{
        Initialized,
        Running,
        Paused,
        Finished,
        Idle
    }

    GLSurfaceView glView;
    GLGraphics glGraphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    Screen screen;
    GLGameState state= GLGameState.Initialized;
    Object stateChanged=new Object();
    long startTime= System.nanoTime();
    PowerManager.WakeLock wakeLock;
    private AdView adView;
    private AdRequest adRequest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        glView=new GLSurfaceView(this);
        glView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        glView.setRenderer(this);

        // Create and load the AdView.
        adView=new AdView(this);
        adView.setAdUnitId("ca-app-pub-3589731046622055/2101015327");
        adView.setAdSize(AdSize.BANNER);

        // Create a RelativeLayout as the main layout and add the gameView.
        RelativeLayout mainLayout = new RelativeLayout(this);
        mainLayout.addView(glView);
        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mainLayout.addView(adView, adParams);
        adRequest=new AdRequest.Builder()
                .build();
        adView.loadAd(adRequest);
        setContentView(mainLayout);

        glGraphics=new GLGraphics(glView);
        fileIO=new AndroidFileIO(this);
        audio=new AndroidAudio(this);
        input=new AndroidInput(this, glView, 1, 1);
        PowerManager powerManager=(PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock=powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK,"GLGAME");
    }
    @Override
    public void onResume() {
        super.onResume();
        glView.onResume();
        wakeLock.acquire();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        glGraphics.setGL(gl);

        synchronized (stateChanged){
            if (state== GLGameState.Initialized) screen=getStartScreen();
            state= GLGameState.Running;
            screen.resume();
            startTime= System.nanoTime();
        }
    }

    private void showBanner(){
    }
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLGameState state=null;

        synchronized (stateChanged){
            state=this.state;
        }

        if (state== GLGameState.Running){
            float deltaTime=(System.nanoTime()-startTime)/ 1000000000.0f;
            startTime= System.nanoTime();

            screen.update(deltaTime);
            screen.present(deltaTime);
        }

        if (state== GLGameState.Paused){
            screen.pause();
            synchronized (stateChanged){
                this.state= GLGameState.Idle;
                stateChanged.notifyAll();
            }
        }

        if (state== GLGameState.Finished){
            screen.pause();
            screen.dispose();
            synchronized (stateChanged){
                this.state= GLGameState.Idle;
                stateChanged.notifyAll();
            }
        }
    }

    @Override
    public void onPause() {
        synchronized (stateChanged){
            if (isFinishing()) state= GLGameState.Finished;
            else state= GLGameState.Paused;
            while (true){
                try{
                    stateChanged.wait();
                    break;
                }catch (InterruptedException e){

                }
            }
        }
        wakeLock.release();
        glView.onPause();
        super.onPause();
    }

    public GLGraphics getGlGraphics(){
        return glGraphics;
    }

    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public FileIO getFileIO() {
        return fileIO;
    }

    @Override
    public Graphics getGraphics() {
        throw  new IllegalStateException("We are using OpenGl");
    }

    @Override
    public Audio getAudio() {
        return audio;
    }

    @Override
    public void setScreen(Screen screen) {
        if (screen==null) throw  new IllegalArgumentException("Screen must not be null");
        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen=screen;
    }

    @Override
    public Screen getCurrentScreen() {
        return screen;
    }


}
