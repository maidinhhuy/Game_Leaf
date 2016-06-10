package com.example.maihuy.leaf_color.framework.gl;

/**
 * Created by maihuy on 4/27/2015.
 */
public class Animation {
    public static final int ANIMATION_LOOPING=0;
    public static final int ANIMATION_NONLOOPING=1;

    final TextureRegion[] keyFrames;
    final float frameDuration;

    public Animation(float frameDuration, TextureRegion ... keyFrames){
        this.frameDuration=frameDuration;
        this.keyFrames=keyFrames;
    }

    public TextureRegion getKeyFrame(float stateTime, int mode){
        int frameNumber=(int)(stateTime/frameDuration);

        if (mode==ANIMATION_NONLOOPING){
            frameNumber= Math.min(keyFrames.length - 1, frameNumber);
        }else {
            frameNumber=frameNumber%keyFrames.length;
        }
        return keyFrames[frameNumber];
    }
}
