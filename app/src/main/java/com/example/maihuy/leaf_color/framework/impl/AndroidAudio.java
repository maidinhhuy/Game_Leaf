package com.example.maihuy.leaf_color.framework.impl;

/**
 * Created by maihuy on 4/12/2015.
 */

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.example.maihuy.leaf_color.framework.Audio;
import com.example.maihuy.leaf_color.framework.Music;
import com.example.maihuy.leaf_color.framework.Sound;

import java.io.IOException;

public class AndroidAudio implements Audio{
    AssetManager assets;
    SoundPool soundPool;

    public AndroidAudio(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets=activity.getAssets();
        this.soundPool=new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
    }

    @Override
    public Music newMusic(String filename) {
        try{
            AssetFileDescriptor assetDescriptor=assets.openFd(filename);
            return new AndroidMusic(assetDescriptor);
        }catch (IOException e){
            throw new RuntimeException("Could not load music '"+filename);
        }
    }

    @Override
    public Sound newSound(String filename) {
        try{
            AssetFileDescriptor assetDescriptor=assets.openFd(filename);
            int soundId=soundPool.load(assetDescriptor, 0);
            return new AndroidSound(soundPool, soundId);
        }catch (IOException e){
            throw  new RuntimeException("Could not load sound '"+filename+"'");
        }
    }
}
