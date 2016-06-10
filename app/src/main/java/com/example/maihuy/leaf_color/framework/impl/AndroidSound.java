package com.example.maihuy.leaf_color.framework.impl;

/**
 * Created by maihuy on 4/12/2015.
 */
import android.media.SoundPool;

import com.example.maihuy.leaf_color.framework.Sound;

class AndroidSound implements Sound {
    int soundId;
    SoundPool soundPool;

    public AndroidSound(SoundPool soundPool, int soundId ) {
        this.soundId = soundId;
        this.soundPool = soundPool;
    }

    @Override
    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0, 0, 1);
    }

    @Override
    public void dispose() {
        soundPool.unload(soundId);
    }
}
