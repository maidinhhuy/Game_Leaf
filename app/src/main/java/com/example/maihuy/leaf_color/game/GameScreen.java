package com.example.maihuy.leaf_color.game;


import com.example.maihuy.leaf_color.framework.Game;
import com.example.maihuy.leaf_color.framework.Input;
import com.example.maihuy.leaf_color.framework.gl.Camera2D;
import com.example.maihuy.leaf_color.framework.gl.GLScreen;
import com.example.maihuy.leaf_color.framework.gl.SpriteBatcher;
import com.example.maihuy.leaf_color.framework.gl.TextureRegion;
import com.example.maihuy.leaf_color.framework.math.OverlapTester;
import com.example.maihuy.leaf_color.framework.math.Rectangle;
import com.example.maihuy.leaf_color.framework.math.Vector2;

import java.util.List;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by maihuy on 7/7/2015.
 */
public class GameScreen extends GLScreen {

    Camera2D guiCam;
    SpriteBatcher batcher;
    Rectangle playBounds;
    Rectangle shareBounds;
    Rectangle yesBounds,noBounds;
    Rectangle quitBounds,redBounds,greenBounds,blueBounds,volumeBounds;

    Rectangle binRec[],leafRec[];
    Vector2 touchPoint;
    boolean gameOver=true,buttonPlayClick,buttonShareClick,buttonPlay,play,start,checkFrame,buttonColorClick[],leafChooses[],quitChange;
    public int scores;
    Vector2 sizeButtonPlay,sizeButtonShare,coorButtonPlay,coorButtonShare,coorScoreFrame,coorHighscoreFrame;
    Vector2 coorBin[],coorLeaf[];
    Vector2 sizeButtonColor[];
    Vector2 sizeHighscoreFrame, sizeScoreFrame,sizeButtonQuit;
    final int RED=0,GREEN=1,BLUE=2;
    boolean binChoose[],leafChoose[],binMove=true,changeBin=false,touch=false,falling=false,buttonQuitClick,quit,yesClick, noClick;
    int binRow[],speed=0,leafNow;
    int colorChoose=0,v=0,leafFail;
    Vector2 coorQuitFrame,coorButtonY,coorButtonN;
    Random rand=new Random();


    public GameScreen(Game game) {
        super(game);
        scores=0;
        init();
    }

    private void init(){
        guiCam=new Camera2D(768,1280,glGraphics);
        batcher=new SpriteBatcher(glGraphics,400);
        playBounds=new Rectangle(253,852,278,95);
        shareBounds=new Rectangle(183,590,300,100);
        quitBounds=new Rectangle(33,1153,94,94);
        redBounds=new Rectangle(131-90, 158-35,180,70);
        greenBounds=new Rectangle(384-90, 158-35,180,70);
        blueBounds=new Rectangle(621-90, 158-35,180,70);
        yesBounds=new Rectangle(253-94/2, 710-94/2,94,94);
        noBounds=new Rectangle(515-94/2,710-94/2,94,94);
        touchPoint=new Vector2();
        yesClick=false;
        noClick=false;
        gameOver=true;
        quit=false;
        leafFail=3;
        leafNow=3;
        quitChange=false;

        coorQuitFrame=new Vector2(525,215);
        coorButtonY=new Vector2(94,94);
        coorButtonN=new Vector2(94,94);

        coorBin=new Vector2[3];
        coorLeaf=new Vector2[4];
        coorLeaf[RED]=new Vector2(rand.nextInt(768),1400);
        coorLeaf[GREEN]=new Vector2(rand.nextInt(768),1400);
        coorLeaf[BLUE]=new Vector2(rand.nextInt(768),1400);
        volumeBounds=new Rectangle(688-94/2,1200-94/2,94,94);


        binRow=new int[3];
        sizeButtonPlay=new Vector2(290,140);
        sizeButtonShare=new Vector2(320,120);
        buttonPlayClick=true;
        buttonShareClick=true;
        checkFrame=true;
        buttonPlay=false;
        play=false;
        start=false;

        binRow=new int[3];
        binChoose=new boolean[3];

        binRec = new Rectangle[3];
        binRec[RED]=new Rectangle(0,0,165,200);
        binRec[GREEN]=new Rectangle(0,0,165,200);
        binRec[BLUE]=new Rectangle(0,0,165,200);

        leafRec=new Rectangle[3];
        leafRec[RED]=new Rectangle(0,0,150,100);
        leafRec[GREEN]=new Rectangle(0,0,150,100);
        leafRec[BLUE]=new Rectangle(0,0,150,100);

        speed=0;
        leafChoose=new boolean[3];
        for (int i=0;i<3;i++){
            binChoose[i]=false;
            leafChoose[i]=false;
            binRow[i]=i;
        }

        sizeButtonColor=new Vector2[3];
        sizeButtonColor[RED]=new Vector2(180,70);
        sizeButtonColor[GREEN]=new Vector2(180,70);
        sizeButtonColor[BLUE]=new Vector2(180,70);

        buttonColorClick=new boolean[3];
        buttonColorClick[RED]=false;
        buttonColorClick[GREEN]=false;
        buttonColorClick[BLUE]=false;

        buttonQuitClick=false;

        sizeButtonQuit=new Vector2(94,94);

        leafChooses=new boolean[3];
        leafChooses[RED]=true;
        leafChooses[GREEN]=true;
        leafChooses[BLUE]=true;

        coorButtonPlay=new Vector2(382, 900);
        coorButtonShare=new Vector2(333, 640);
        coorScoreFrame=new Vector2(203, 763);
        coorHighscoreFrame=new Vector2(600, 764);
        coorBin[RED]=new Vector2(173+768,286);
        coorBin[GREEN]=new Vector2(173+768+245,286);
        coorBin[BLUE]=new Vector2(173+768+245*2,286);
        sizeHighscoreFrame=new Vector2(258,178);
        sizeScoreFrame=new Vector2(183,138);

        colorChoose=0;
        v=0;
        binMove=true;
        changeBin=false;
        touch=false;
        falling=false;
    }


    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents=game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        int len=touchEvents.size();
        for (int i=0;i<len;i++){
            Input.TouchEvent event=touchEvents.get(i);
            touchPoint.set(event.x, event.y);
            guiCam.touchToWorld(touchPoint);
            float x=0;
            if (event.type!=Input.TouchEvent.TOUCH_UP){

                if (OverlapTester.pointInRectangle(playBounds,touchPoint)){
                    buttonPlayClick=true;
                    buttonPlay=true;
                    return;
                }

                if (OverlapTester.pointInRectangle(quitBounds,touchPoint)){
                    buttonQuitClick=true;
                    return;
                }

                if (OverlapTester.pointInRectangle(yesBounds,touchPoint)&&quit){
                    yesClick=true;
                    return;
                }

                if (OverlapTester.pointInRectangle(noBounds,touchPoint)&&quit){
                    noClick=true;
                    return;
                }

                if (OverlapTester.pointInRectangle(shareBounds,touchPoint)){
                    buttonShareClick=true;
                    return;
                }

                if (OverlapTester.pointInRectangle(volumeBounds,touchPoint)){
                    Settings.soundEnabled=!Settings.soundEnabled;
                    Assets.reload();
                    Settings.save(game.getFileIO());
                    Settings.load(game.getFileIO());
                    return;
                }

                if (OverlapTester.pointInRectangle(redBounds,touchPoint)&&!changeBin&&!buttonColorClick[RED]){
                    buttonColorClick[GREEN]=false;
                    buttonColorClick[BLUE]=false;
                    buttonColorClick[RED]=true;
                    colorChoose=RED;
                    return;
                }

                if (OverlapTester.pointInRectangle(greenBounds,touchPoint)&&!changeBin&&!buttonColorClick[GREEN]){
                    buttonColorClick[GREEN]=true;
                    buttonColorClick[BLUE]=false;
                    buttonColorClick[RED]=false;
                    colorChoose=GREEN;
                    return;
                }

                if (OverlapTester.pointInRectangle(blueBounds,touchPoint)&&!changeBin&&!buttonColorClick[BLUE]){
                    buttonColorClick[GREEN]=false;
                    buttonColorClick[BLUE]=true;
                    buttonColorClick[RED]=false;
                    colorChoose=BLUE;
                    return;
                }
            }
            if (event.type!=Input.TouchEvent.TOUCH_UP&&(buttonColorClick[RED]||buttonColorClick[GREEN]||buttonColorClick[BLUE])){
                touch=true;
                x=touchPoint.getX();
                if (touchPoint.getX()>coorBin[colorChoose].getX()){
                    v=1;
                }
                else {
                    v=0;
                }
            }

            if (event.type==Input.TouchEvent.TOUCH_UP||(x-coorBin[colorChoose].getX()<20&&x-coorBin[colorChoose].getX()>-20)){
                touch=false;
            }
        }



        binRec[RED]=new Rectangle(coorBin[RED].getX()-380/2, coorBin[RED].getY()-320/2,380,320);
        binRec[GREEN]=new Rectangle(coorBin[GREEN].getX()-380/2, coorBin[GREEN].getY()-320/2,380,320);
        binRec[BLUE]=new Rectangle(coorBin[BLUE].getX()-380/2, coorBin[BLUE].getY()-320/2,380,320);


        leafRec[RED]=new Rectangle(coorLeaf[RED].getX()-150/2,coorLeaf[RED].getY()-150/2,150,100);
        leafRec[GREEN]=new Rectangle(coorLeaf[GREEN].getX()-150/2,coorLeaf[GREEN].getY()-150/2,150,100);
        leafRec[BLUE]=new Rectangle(coorLeaf[BLUE].getX()-150/2,coorLeaf[BLUE].getY()-150/2,150,100);

        if (touch) moveBin(coorBin[colorChoose],v);

        changeBin=false;

        quitChange();

        frameChange();

        buttonBlueClick();

        buttonGreenClick();

        buttonRedClick();

        buttonQuitClick();

        yesClick();

        noClick();

        if (sizeButtonPlay.getX()<300+30&&sizeButtonPlay.getY()<150+30&&gameOver&&buttonPlayClick){
            sizeButtonPlay.setX(sizeButtonPlay.getX()+2.9f);
            sizeButtonPlay.setY(sizeButtonPlay.getY()+1.4f);
        }

        if (sizeButtonShare.getX()<330+30&&sizeButtonShare.getY()<130+30&&gameOver&&buttonShareClick){
            sizeButtonShare.setX(sizeButtonShare.getX()+3.2f);
            sizeButtonShare.setY(sizeButtonShare.getY()+1.2f);
        }

        if (sizeScoreFrame.getX()>=183+30){
            checkFrame=false;
        }

        if (sizeButtonShare.getX()>=330+30||sizeButtonShare.getY()>=130+30||sizeButtonPlay.getX()>=300+30||sizeButtonPlay.getY()>=150+30){
            buttonPlayClick=false;
            buttonShareClick=false;
            if (buttonPlay) play=true;
        }

        if ((sizeButtonShare.getX()<290||sizeButtonShare.getY()<140)&&play){
            start=true;
        }

        if (start){
            if (coorButtonPlay.getX()<768+500){
                scores=0;
                speed=(int) scores/3;
                coorButtonPlay.setX(coorButtonPlay.getX()+9);
                if (coorHighscoreFrame.getX()<768+390){
                    coorHighscoreFrame.setX(coorHighscoreFrame.getX()+9);
                }
                if (coorButtonShare.getX()>-320){
                    coorButtonShare.setX(coorButtonShare.getX()-10);
                }
                if (coorScoreFrame.getX()>-183)
                coorScoreFrame.setX(coorScoreFrame.getX()-10);
            }else {
                gameOver=false;
            }
        }

        if (!gameOver&&binMove){
            if (coorBin[RED].getX()>134){
                coorBin[RED].setX(coorBin[RED].getX() -10);
            }
            if (coorBin[GREEN].getX()>384){
                coorBin[GREEN].setX(coorBin[GREEN].getX() -10);
            }
            if (coorBin[BLUE].getX()>624){
                coorBin[BLUE].setX(coorBin[BLUE].getX() -10);
            }

            if (coorBin[RED].getX()<=131&&coorBin[GREEN].getX()<=384&&coorBin[BLUE].getX()<=621){
                binMove=false;
            }

        }

        if (!gameOver){

            if (coorLeaf[0].getY()>=286){
                if (speed*0.002f<=2)
                    coorLeaf[0].setY(coorLeaf[0].getY()-4-speed*0.002f);
                else{
                    coorLeaf[0].setY(coorLeaf[0].getY()-4-1000*0.002f);
                }
                leafChooses[RED]=false;
            }else {
                if (OverlapTester.overlapRectangles(leafRec[RED],binRec[RED])){
                    scores++;
                    Assets.playSound(Assets.score);
                }
                coorLeaf[0].setY(1380);
                if (coorLeaf[RED].getY()==1380&&!leafChooses[RED]) {
                    leafFail++;
                }
                coorLeaf[0].setX(rand.nextInt(768));
            }


            if (leafFail-3>scores){
                if (Settings.highscores<scores)
                    Settings.highscores=scores;
                Settings.save(game.getFileIO());
                Assets.playSound(Assets.gameOver);
                init();
            }

            if (coorLeaf[GREEN].getY()>=286&&(coorLeaf[RED].getY()-coorLeaf[GREEN].getY()>332||coorLeaf[GREEN].getY()-coorLeaf[RED].getY()>332)){
                if (speed*0.002f<=2) {
                    coorLeaf[GREEN].setY(coorLeaf[GREEN].getY() - 4-speed*0.002f);
                }else{
                    coorLeaf[GREEN].setY(coorLeaf[GREEN].getY() - 4-1000*0.002f);
                }
                leafChooses[GREEN]=false;
            }else {
                coorLeaf[GREEN].setY(1380);
                if (OverlapTester.overlapRectangles(leafRec[GREEN],binRec[GREEN])){
                    scores++;
                    Assets.playSound(Assets.score);
                }
                if (coorLeaf[GREEN].getY()==1380&&!leafChooses[GREEN]) {
                    leafFail++;
                }
                coorLeaf[1].setX(rand.nextInt(768));

            }

            if (leafFail-3>scores){
                if (Settings.highscores<scores)
                    Settings.highscores=scores;
                Settings.save(game.getFileIO());
                Assets.playSound(Assets.gameOver);
                init();
            }


            if (coorLeaf[BLUE].getY()>=286&&(coorLeaf[GREEN].getY()-coorLeaf[BLUE].getY()>332||coorLeaf[BLUE].getY()-coorLeaf[GREEN].getY()>332)){
                if (speed*0.002f<=2) {
                    coorLeaf[BLUE].setY(coorLeaf[BLUE].getY() - 4-speed*0.002f);
                }else{
                    coorLeaf[BLUE].setY(coorLeaf[BLUE].getY() - 4-1000*0.002f);
                }
                leafChooses[BLUE]=false;
            } else {
                coorLeaf[BLUE].setY(1380);
                if (OverlapTester.overlapRectangles(leafRec[BLUE],binRec[BLUE])){
                    scores++;
                    speed=(int) scores/3;
                    Assets.playSound(Assets.score);
                }
                if (coorLeaf[BLUE].getY()==1380&&!leafChooses[BLUE]) {
                    leafFail++;
                }
                coorLeaf[BLUE].setX(rand.nextInt(768));
            }

            if (leafFail-3>scores){
                if (Settings.highscores<scores)
                    Settings.highscores=scores;
                Settings.save(game.getFileIO());
                Assets.playSound(Assets.gameOver);
                init();
            }


        }

        if (sizeButtonPlay.getX()>290&&sizeButtonPlay.getY()>140&&gameOver&&!buttonPlayClick){
            sizeButtonPlay.setX(sizeButtonPlay.getX()-2.9f);
            sizeButtonPlay.setY(sizeButtonPlay.getY()-1.4f);
        }

        if (sizeButtonShare.getX()>320&&sizeButtonShare.getY()>120&&gameOver&&!buttonShareClick){
            sizeButtonShare.setX(sizeButtonShare.getX()-3.2f);
            sizeButtonShare.setY(sizeButtonShare.getY()-1.2f);
        }
    }

    private void frameChange(){
        if (sizeScoreFrame.getX()<183+30&&checkFrame){
            sizeScoreFrame.setX(sizeScoreFrame.getX()+1.83f);
            sizeScoreFrame.setY(sizeScoreFrame.getY()+1.38f);
            sizeHighscoreFrame.setX(sizeHighscoreFrame.getX() +2.58f);
            sizeHighscoreFrame.setY(sizeHighscoreFrame.getY() +1.78f);
        }

        if (sizeScoreFrame.getX()>183&&!checkFrame){
            sizeScoreFrame.setX(sizeScoreFrame.getX()-1.83f);
            sizeScoreFrame.setY(sizeScoreFrame.getY()-1.38f);
            sizeHighscoreFrame.setX(sizeHighscoreFrame.getX() -2.58f);
            sizeHighscoreFrame.setY(sizeHighscoreFrame.getY() -1.78f);
        }
    }

    private void buttonRedClick(){
        if (sizeButtonColor[RED].getX()<180+30&&sizeButtonColor[RED].getY()<70+10&&buttonColorClick[RED]){
            sizeButtonColor[RED].setX(sizeButtonColor[RED].getX() + 3);
            sizeButtonColor[RED].setY(sizeButtonColor[RED].getY() + 1);
        }

        if (sizeButtonColor[RED].getX()>180&&sizeButtonColor[RED].getY()>70&&!buttonColorClick[RED]){
            sizeButtonColor[RED].setX(sizeButtonColor[RED].getX() - 3);
            sizeButtonColor[RED].setY(sizeButtonColor[RED].getY() - 1);
        }
    }

    private void buttonGreenClick(){
        if (sizeButtonColor[GREEN].getX()<180+30&&sizeButtonColor[GREEN].getY()<70+10&&buttonColorClick[GREEN]){
            sizeButtonColor[GREEN].setX(sizeButtonColor[GREEN].getX()+3);
            sizeButtonColor[GREEN].setY(sizeButtonColor[GREEN].getY()+1);
        }

        if (sizeButtonColor[GREEN].getX()>180&&sizeButtonColor[GREEN].getY()>70&&!buttonColorClick[GREEN]){
            sizeButtonColor[GREEN].setX(sizeButtonColor[GREEN].getX()-3);
            sizeButtonColor[GREEN].setY(sizeButtonColor[GREEN].getY()-1);
        }
    }

    private void buttonBlueClick(){
        if (sizeButtonColor[BLUE].getX()<180+30&&sizeButtonColor[BLUE].getY()<70+10&&buttonColorClick[BLUE]){
            sizeButtonColor[BLUE].setX(sizeButtonColor[BLUE].getX()+3);
            sizeButtonColor[BLUE].setY(sizeButtonColor[BLUE].getY()+1);
        }

        if (sizeButtonColor[BLUE].getX()>180&&sizeButtonColor[BLUE].getY()>70&&!buttonColorClick[BLUE]){
            sizeButtonColor[BLUE].setX(sizeButtonColor[BLUE].getX()-3);
            sizeButtonColor[BLUE].setY(sizeButtonColor[BLUE].getY()-1);
        }
    }

    private void buttonQuitClick(){
        if (sizeButtonQuit.getX()<94+20&&buttonQuitClick){
            sizeButtonQuit.setX(sizeButtonQuit.getX()+3);
            sizeButtonQuit.setY(sizeButtonQuit.getY()+3);
        }

        if (sizeButtonQuit.getX()>=94+20){
            buttonQuitClick=false;
            quit=true;
            quitChange=true;
        }

        if (sizeButtonQuit.getX()>94&&!buttonQuitClick){
            sizeButtonQuit.setX(sizeButtonQuit.getX() - 3);
            sizeButtonQuit.setY(sizeButtonQuit.getY() - 3);
        }
    }

    private void quitChange(){
        if (coorQuitFrame.getX()<525+25&&quitChange){
            coorQuitFrame.setX(coorQuitFrame.getX() + 2.5f/2);
            coorQuitFrame.setY(coorQuitFrame.getY() + 2.5f/2);
            coorButtonY.setX(coorButtonY.getX() + 2.5f/2);
            coorButtonY.setY(coorButtonY.getY() + 2.5f/2);
            coorButtonN.setX(coorButtonN.getX() + 2.5f/2);
            coorButtonN.setY(coorButtonN.getY()+2.5f/2);
        }

        if (coorQuitFrame.getX()>=525+25){
            quitChange=false;
        }

        if (coorQuitFrame.getX()>525&&!quitChange){
            coorQuitFrame.setX(coorQuitFrame.getX() - 2.5f/2);
            coorQuitFrame.setY(coorQuitFrame.getY() - 2.5f/2);
            coorButtonY.setX(coorButtonY.getX() - 2.5f/2);
            coorButtonY.setY(coorButtonY.getY()-2.5f/2);
            coorButtonN.setX(coorButtonN.getX() - 2.5f/2);
            coorButtonN.setY(coorButtonN.getY()-2.5f/2);
        }
    }

    private void yesClick(){
        if (coorButtonY.getX()<94+25&&yesClick){
            coorButtonY.setX(coorButtonY.getX()+2.5f/2);
            coorButtonY.setY(coorButtonY.getY()+2.5f/2);
        }

        if (coorButtonY.getX()>=94+25){
            System.exit(0);
        }
    }

    private void noClick(){
        if (coorButtonN.getX()<94+25&&noClick){
            coorButtonN.setX(coorButtonN.getX()+2.5f/2);
            coorButtonN.setY(coorButtonN.getY() + 2.5f/2);
        }

        if (coorButtonN.getX()>=94+25){
            init();
        }
    }

    public void moveBin(Vector2 bin,int y){
        if (y==0){
            if (bin.getX() > 0) {
                bin.setX(bin.getX() - 9);
            }
        }else{
            if (bin.getX()<768){
                bin.setX(bin.getX()+9);
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        GL10 gl=glGraphics.getGl();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();

        if (gameOver) {
            gl.glEnable(GL10.GL_TEXTURE_2D);

            batcher.beginBatch(Assets.backgroundWait);
            batcher.drawSprite(768 / 2, 1280 / 2, 768, 1280, Assets.backgroundWaitRegion);
            batcher.endBatch();

            gl.glEnable(GL10.GL_BLEND);
            gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

            batcher.beginBatch(Assets.button_quit);
            batcher.drawSprite(80, 1200, sizeButtonQuit.getX(), sizeButtonQuit.getY(), Assets.button_quitRegion);
            batcher.endBatch();

            if (Settings.soundEnabled) {
                batcher.beginBatch(Assets.volume);
                batcher.drawSprite(688, 1200, 94, 94, Assets.volumeR);
                batcher.endBatch();
            }else {
                batcher.beginBatch(Assets.mute);
                batcher.drawSprite(688, 1200, 94, 94, Assets.muteR);
                batcher.endBatch();
            }
            batcher.beginBatch(Assets.score_frame);
            batcher.drawSprite(coorScoreFrame.getX(), 763, sizeScoreFrame.getX(), sizeScoreFrame.getY(), Assets.score_frameRegion);
            batcher.endBatch();

            batcher.beginBatch(Assets.button_play);
            batcher.drawSprite(coorButtonPlay.getX(), 900, sizeButtonPlay.getX(), sizeButtonPlay.getY(), Assets.button_playRegion);
            batcher.endBatch();

            batcher.beginBatch(Assets.highscore_frame);
            batcher.drawSprite(coorHighscoreFrame.getX(), 764, sizeHighscoreFrame.getX(), sizeHighscoreFrame.getY(), Assets.highscore_frameRegion);
            batcher.endBatch();

            batcher.beginBatch(Assets.fontTexture);
            Assets.font.drawTextSize(batcher, String.valueOf(scores), coorScoreFrame.getX() - (String.valueOf(scores).length() - 1) * 10, 763, 1.1f);
            batcher.endBatch();

            batcher.beginBatch(Assets.fontTexture);
            Assets.font.drawTextSize(batcher, String.valueOf(Settings.highscores), coorHighscoreFrame.getX() - (String.valueOf(Settings.highscores).length() - 1) * 12.5f, 764, 1.25f);
            batcher.endBatch();


            batcher.beginBatch(Assets.button_share);
            batcher.drawSprite(coorButtonShare.getX(), 640, sizeButtonShare.getX(), sizeButtonShare.getY(), Assets.button_shareRegion);
            batcher.endBatch();

            batcher.beginBatch(Assets.button_red_w);
            batcher.drawSprite(131, 158, 180, 70, Assets.button_redRegion_w);
            batcher.endBatch();

            batcher.beginBatch(Assets.button_green_w);
            batcher.drawSprite(384, 158, 180, 70, Assets.button_greenRegion_w);
            batcher.endBatch();

            batcher.beginBatch(Assets.button_blue_w);
            batcher.drawSprite(621, 158, 180, 70, Assets.button_blueRegion_w);
            batcher.endBatch();

            gl.glDisable(GL10.GL_BLEND);
        }else {
            gl.glEnable(GL10.GL_TEXTURE_2D);

            batcher.beginBatch(Assets.background);
            batcher.drawSprite(768 / 2, 1280 / 2, 768, 1280, Assets.backgroundRegion);
            batcher.endBatch();

            gl.glEnable(GL10.GL_BLEND);
            gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

            batcher.beginBatch(Assets.button_quit);
            batcher.drawSprite(80, 1200, sizeButtonQuit.getX(), sizeButtonQuit.getY(), Assets.button_quitRegion);
            batcher.endBatch();

            batcher.beginBatch(Assets.score_frame);
            batcher.drawSprite(715, 1200, 183, 138, Assets.score_frameRegion);
            batcher.endBatch();

            batcher.beginBatch(Assets.fontTexture);
            Assets.font.drawTextSize(batcher, String.valueOf(scores), 715 - (String.valueOf(scores).length() - 1) * 10, 1200, 1.0f);
            batcher.endBatch();

            if (!buttonColorClick[RED]) {
                batcher.beginBatch(Assets.bin_red_c);
                batcher.drawSprite(coorBin[RED].getX(), coorBin[RED].getY(), 225, 125, Assets.bin_redRegion_c);
                batcher.endBatch();
            }
            if (!buttonColorClick[GREEN]){
                batcher.beginBatch(Assets.bin_green_c);
                batcher.drawSprite(coorBin[GREEN].getX(), coorBin[GREEN].getY(), 225, 125, Assets.bin_greenRegion_c);
                batcher.endBatch();
            }

            if (!buttonColorClick[BLUE]) {
                batcher.beginBatch(Assets.bin_blue_c);
                batcher.drawSprite(coorBin[BLUE].getX(), coorBin[BLUE].getY(), 225, 125, Assets.bin_blueRegion_c);
                batcher.endBatch();
            }

            if (buttonColorClick[RED]) {
                batcher.beginBatch(Assets.bin_red_o);
                batcher.drawSprite(coorBin[RED].getX(), coorBin[RED].getY(), 225, 125, Assets.bin_redRegion_o);
                batcher.endBatch();
            }
            if (buttonColorClick[GREEN]){
                batcher.beginBatch(Assets.bin_green_o);
                batcher.drawSprite(coorBin[GREEN].getX(), coorBin[GREEN].getY(), 225, 125, Assets.bin_greenRegion_o);
                batcher.endBatch();
            }

            if (buttonColorClick[BLUE]) {
                batcher.beginBatch(Assets.bin_blue_o);
                batcher.drawSprite(coorBin[BLUE].getX(), coorBin[BLUE].getY(), 225, 125, Assets.bin_blueRegion_o);
                batcher.endBatch();
            }

            if (coorLeaf[RED].getY()>=376) {
                batcher.beginBatch(Assets.leaf_red);
                batcher.drawSprite(coorLeaf[RED].getX(), coorLeaf[RED].getY(), 150, 100, Assets.leaf_redRegion);
                batcher.endBatch();
            }else{
                batcher.beginBatch(Assets.leaf_red);
                batcher.drawSprite(coorLeaf[RED].getX(), 376-(376-coorLeaf[RED].getY())/2, 150, 100-(376-coorLeaf[RED].getY()), new TextureRegion(Assets.leaf_red,0,0,150, 100-(376-coorLeaf[RED].getY())));
                batcher.endBatch();
            }

            if (coorLeaf[GREEN].getY()>=376) {
                batcher.beginBatch(Assets.leaf_green);
                batcher.drawSprite(coorLeaf[GREEN].getX(), coorLeaf[GREEN].getY(), 150, 100, Assets.leaf_greenRegion);
                batcher.endBatch();
            }else{
                batcher.beginBatch(Assets.leaf_green);
                batcher.drawSprite(coorLeaf[GREEN].getX(), 376-(376-coorLeaf[GREEN].getY())/2, 150, 100-(376-coorLeaf[GREEN].getY()), new TextureRegion(Assets.leaf_green,0,0,150, 100-(376-coorLeaf[GREEN].getY())));
                batcher.endBatch();
            }

            if (coorLeaf[BLUE].getY()>=376) {
                batcher.beginBatch(Assets.leaf_blue);
                batcher.drawSprite(coorLeaf[BLUE].getX(), coorLeaf[BLUE].getY(), 150, 100, Assets.leaf_blueRegion);
                batcher.endBatch();
            }else{
                batcher.beginBatch(Assets.leaf_blue);
                batcher.drawSprite(coorLeaf[BLUE].getX(), 376-(376-coorLeaf[BLUE].getY())/2, 150, 100-(376-coorLeaf[BLUE].getY()), new TextureRegion(Assets.leaf_blue,0,0,150, 100-(376-coorLeaf[BLUE].getY())));
                batcher.endBatch();
            }
            batcher.beginBatch(Assets.button_red_p);
            batcher.drawSprite(131, 158, sizeButtonColor[RED].getX(), sizeButtonColor[RED].getY(), Assets.button_redRegion_p);
            batcher.endBatch();

            batcher.beginBatch(Assets.button_green_p);
            batcher.drawSprite(384, 158, sizeButtonColor[GREEN].getX(), sizeButtonColor[GREEN].getY(), Assets.button_greenRegion_p);
            batcher.endBatch();

            batcher.beginBatch(Assets.button_blue_p);
            batcher.drawSprite(621, 158, sizeButtonColor[BLUE].getX(), sizeButtonColor[BLUE].getY(), Assets.button_blueRegion_p);
            batcher.endBatch();

            gl.glDisable(GL10.GL_BLEND);
        }

        if (quit){
            gl.glEnable(GL10.GL_TEXTURE_2D);

            batcher.beginBatch(Assets.backgroundWait);
            batcher.drawSprite(768 / 2, 1280 / 2, 768, 1280, Assets.backgroundWaitRegion);
            batcher.endBatch();

            gl.glEnable(GL10.GL_BLEND);
            gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

            batcher.beginBatch(Assets.button_quit);
            batcher.drawSprite(80, 1200, sizeButtonQuit.getX(), sizeButtonQuit.getY(), Assets.button_quitRegion);
            batcher.endBatch();

            if (Settings.soundEnabled) {
                batcher.beginBatch(Assets.volume);
                batcher.drawSprite(688, 1200, 94, 94, Assets.volumeR);
                batcher.endBatch();
            }else {
                batcher.beginBatch(Assets.mute);
                batcher.drawSprite(688, 1200, 94, 94, Assets.muteR);
                batcher.endBatch();
            }

            batcher.beginBatch(Assets.quitFrame);
            batcher.drawSprite(384, 720, coorQuitFrame.getX(), coorQuitFrame.getY(), Assets.quitFrameRegion);
            batcher.endBatch();

            batcher.beginBatch(Assets.buttonYN);
            batcher.drawSprite(253, 710, coorButtonY.getX(), coorButtonY.getY(), Assets.button_yes);
            batcher.endBatch();

            batcher.beginBatch(Assets.buttonYN);
            batcher.drawSprite(515, 710, coorButtonN.getX(), coorButtonN.getY(), Assets.button_no);
            batcher.endBatch();


            batcher.beginBatch(Assets.button_red_w);
            batcher.drawSprite(131, 158, 180, 70, Assets.button_redRegion_w);
            batcher.endBatch();

            batcher.beginBatch(Assets.button_green_w);
            batcher.drawSprite(384, 158, 180, 70, Assets.button_greenRegion_w);
            batcher.endBatch();

            batcher.beginBatch(Assets.button_blue_w);
            batcher.drawSprite(621, 158, 180, 70, Assets.button_blueRegion_w);
            batcher.endBatch();

            gl.glDisable(GL10.GL_BLEND);
        }
    }

    @Override
    public void pause() {
        Settings.save(game.getFileIO());
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {

    }
}
