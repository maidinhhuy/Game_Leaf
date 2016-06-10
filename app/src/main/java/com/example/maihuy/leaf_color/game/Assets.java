package com.example.maihuy.leaf_color.game;

import com.example.maihuy.leaf_color.framework.Music;
import com.example.maihuy.leaf_color.framework.Sound;
import com.example.maihuy.leaf_color.framework.gl.Font;
import com.example.maihuy.leaf_color.framework.gl.GLGame;
import com.example.maihuy.leaf_color.framework.gl.Texture;
import com.example.maihuy.leaf_color.framework.gl.TextureRegion;

/**
 * Created by maihuy on 7/6/2015.
 */
public class Assets {
    public static Texture background;
    public static Texture backgroundWait;
    public static Texture bin_red_c;
    public static Texture bin_green_c;
    public static Texture bin_blue_c;
    public static Texture bin_red_o;
    public static Texture bin_green_o;
    public static Texture bin_blue_o;

    public static Texture button_red_w;
    public static Texture button_green_w;
    public static Texture button_blue_w;

    public static Texture button_red_p;
    public static Texture button_green_p;
    public static Texture button_blue_p;

    public static Texture button_play;
    public static Texture button_share;
    public static Texture score_frame;
    public static Texture highscore_frame;
    public static Texture button_quit;
    public static Texture leaf_red;
    public static Texture leaf_green;
    public static Texture leaf_blue;

    public static Texture quitFrame;
    public static TextureRegion quitFrameRegion;

    public static Texture buttonYN;

    public static TextureRegion button_yes;
    public static TextureRegion button_no;

    public static Texture fontTexture;


    public static TextureRegion backgroundRegion;
    public static TextureRegion backgroundWaitRegion;
    public static TextureRegion bin_redRegion_o;
    public static TextureRegion bin_greenRegion_o;
    public static TextureRegion bin_blueRegion_o;
    public static TextureRegion bin_redRegion_c;
    public static TextureRegion bin_greenRegion_c;
    public static TextureRegion bin_blueRegion_c;
    public static TextureRegion button_redRegion_w;
    public static TextureRegion button_greenRegion_w;
    public static TextureRegion button_blueRegion_w;

    public static TextureRegion button_redRegion_p;
    public static TextureRegion button_greenRegion_p;
    public static TextureRegion button_blueRegion_p;

    public static TextureRegion button_playRegion;
    public static TextureRegion button_shareRegion;
    public static TextureRegion score_frameRegion;
    public static TextureRegion highscore_frameRegion;
    public static TextureRegion button_quitRegion;
    public static TextureRegion leaf_redRegion;
    public static TextureRegion leaf_greenRegion;
    public static TextureRegion leaf_blueRegion;

    public static Texture volume;
    public static Texture mute;

    public static TextureRegion volumeR;
    public static TextureRegion muteR;


    public static Font font;

    public static Music music;

    public static Sound score;
    public static Sound gameOver;

    public static void load(GLGame game){
        background=new Texture(game,"background.png");
        backgroundRegion=new TextureRegion(background,0,0,768,1280);

        fontTexture=new Texture(game,"font.png");

        backgroundWait=new Texture(game,"background_wait.png");
        backgroundWaitRegion=new TextureRegion(backgroundWait,0,0,768,1280);

        bin_red_c=new Texture(game,"bin_red_c.png");
        bin_redRegion_c=new TextureRegion(bin_red_c,0,0,380,230);

        bin_green_c=new Texture(game,"bin_green_c.png");
        bin_greenRegion_c=new TextureRegion(bin_green_c,0,0,380,230);

        bin_blue_c=new Texture(game,"bin_blue_c.png");
        bin_blueRegion_c=new TextureRegion(bin_blue_c,0,0,380,230);


        bin_red_o=new Texture(game,"bin_red_o.png");
        bin_redRegion_o=new TextureRegion(bin_red_o,0,0,380,230);

        bin_green_o=new Texture(game,"bin_green_o.png");
        bin_greenRegion_o=new TextureRegion(bin_green_o,0,0,380,230);

        bin_blue_o=new Texture(game,"bin_blue_o.png");
        bin_blueRegion_o=new TextureRegion(bin_blue_c,0,0,380,230);

        button_red_p=new Texture(game,"button_red_p.png");
        button_redRegion_p=new TextureRegion(button_red_p,0,0,180,70);

        button_green_p=new Texture(game,"button_green_p.png");
        button_greenRegion_p=new TextureRegion(button_green_p,0,0,180,70);

        button_blue_p=new Texture(game,"button_blue_p.png");
        button_blueRegion_p=new TextureRegion(button_blue_p,0,0,180,70);


        button_red_w=new Texture(game,"button_red_w.png");
        button_redRegion_w=new TextureRegion(button_red_w,0,0,180,70);

        button_green_w=new Texture(game,"button_green_w.png");
        button_greenRegion_w=new TextureRegion(button_green_w,0,0,180,70);

        button_blue_w=new Texture(game,"button_blue_w.png");
        button_blueRegion_w=new TextureRegion(button_blue_w,0,0,180,70);

        button_play=new Texture(game,"button_play.png");
        button_playRegion=new TextureRegion(button_play,0,0,290,140);

        button_share=new Texture(game,"button_share.png");
        button_shareRegion=new TextureRegion(button_share,0,0,320,120);

        score_frame=new Texture(game,"score_frame.png");
        score_frameRegion=new TextureRegion(score_frame,0,0,183,138);

        highscore_frame=new Texture(game,"highscore_frame.png");
        highscore_frameRegion=new TextureRegion(highscore_frame,0,0,258,178);

        leaf_red=new Texture(game,"leaf_red.png");
        leaf_redRegion=new TextureRegion(leaf_red,0,0,150,100);

        leaf_green=new Texture(game,"leaf_green.png");
        leaf_greenRegion=new TextureRegion(leaf_green,0,0,150,100);

        leaf_blue=new Texture(game,"leaf_blue.png");
        leaf_blueRegion=new TextureRegion(leaf_blue,0,0,150,100);

        button_quit=new Texture(game,"button_quit.png");
        button_quitRegion=new TextureRegion(button_quit,0,0,94,94);

        volume=new Texture(game,"volume.png");
        mute=new Texture(game,"mute.png");

        volumeR=new TextureRegion(volume,0,0,94,94);
        muteR=new TextureRegion(mute,0,0,94,94);

        font=new Font(fontTexture,0,0,20,40);

        score=game.getAudio().newSound("score.mp3");
        gameOver=game.getAudio().newSound("gameover.mp3");

        music=game.getAudio().newMusic("leaffall.mp3");
        music.setLooping(true);
        music.setVolume(0.5f);
        if (Settings.soundEnabled) music.play();

        quitFrame=new Texture(game,"quit_frame.png");
        quitFrameRegion=new TextureRegion(quitFrame,0,0,525,215);

        buttonYN=new Texture(game,"button_yes_no.png");
        button_yes=new TextureRegion(buttonYN,0,0,94,94);
        button_no=new TextureRegion(buttonYN,94,0,94,94);

    }

    public static void reload(){
        background.reload();
        backgroundWait.reload();
        bin_red_c.reload();
        bin_green_c.reload();
        bin_blue_c.reload();
        button_red_w.reload();
        button_green_w.reload();
        button_blue_w.reload();
        button_red_p.reload();
        button_green_p.reload();
        button_blue_p.reload();
        button_play.reload();
        button_share.reload();
        score_frame.reload();
        highscore_frame.reload();
        button_quit.reload();
        leaf_red.reload();
        leaf_green.reload();
        leaf_blue.reload();
        button_quit.reload();
        volume.reload();
        mute.reload();
        quitFrame.reload();
        buttonYN.reload();
        fontTexture.reload();

        if (Settings.soundEnabled) music.play();
        else {
            music.pause();
        }
    }

    public static void playSound(Sound sound){
        if (Settings.soundEnabled) sound.play(1);
    }
}
