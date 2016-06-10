package com.example.maihuy.leaf_color.framework.gl;

import com.example.maihuy.leaf_color.framework.Game;
import com.example.maihuy.leaf_color.framework.Screen;

/**
 * Created by maihuy on 4/27/2015.
 */
public abstract class GLScreen  extends Screen{
    protected final GLGraphics glGraphics;
    protected final GLGame glGame;
    public GLScreen(Game game) {
        super(game);
        glGame= (GLGame) game;
        glGraphics=((GLGame)game).getGlGraphics();

    }
}
