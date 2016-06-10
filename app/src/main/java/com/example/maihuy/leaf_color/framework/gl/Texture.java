package com.example.maihuy.leaf_color.framework.gl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.opengl.GLUtils;

import com.example.maihuy.leaf_color.framework.FileIO;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;


/**
 * Created by maihuy on 4/27/2015.
 */
public class Texture {
    GLGraphics glGraphics;
    FileIO fileIO;
    String fileName;
    int textureId;
    int minFilter;
    int magFilter;
    private float width, height;
    boolean mipmapped;

    public Texture(GLGame glGame, String fileName) {
        this(glGame, fileName, false);
    }

    public Texture(GLGame glGame, String fileName, boolean mipmapped){
        this.glGraphics=glGame.glGraphics;
        this.fileIO=glGame.getFileIO();
        this.fileName = fileName;
        this.mipmapped=mipmapped;
        load();
    }

    private void load() {
        GL10 gl=glGraphics.getGl();
        int[] textureIds=new int[1];
        gl.glGenTextures(1, textureIds, 0);
        textureId=textureIds[0];

        InputStream in=null;
        try {
            in=fileIO.readAsset(fileName);
            Bitmap bitmap= BitmapFactory.decodeStream(in);
            if (mipmapped){
                createMipmaps(gl,bitmap);
            }else {
                width = bitmap.getWidth();
                height = bitmap.getHeight();
                gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
                GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
                setFilters(GL10.GL_NEAREST, GL10.GL_NEAREST);
                gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
                bitmap.recycle();
            }
        }catch (IOException e){
            throw  new RuntimeException("Couldn't load texture '"+ fileName+"'",e);
        }finally {
            if (in!=null)
                try {
                    in.close();
                }catch (IOException e){}
        }
    }

    private void createMipmaps(GL10 gl, Bitmap bitmap) {
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
        this.width=bitmap.getWidth();
        this.height=bitmap.getHeight();
        setFilters(GL10.GL_LINEAR_MIPMAP_NEAREST, GL10.GL_NEAREST);

        int level=0;
        float newWidth=width;
        float newHeight=height;
        while (true){
            GLUtils.texImage2D(GL10.GL_TEXTURE_2D, level, bitmap, 0);
            newWidth=newWidth/2;
            newHeight=newHeight/2;
            if (newWidth<=0)
                break;
            Bitmap newBitmap = Bitmap.createBitmap((int) newWidth, (int) newHeight, bitmap.getConfig());
            Canvas canvas=new Canvas(newBitmap);
            canvas.drawBitmap(bitmap, new Rect(0,0,bitmap.getWidth(), bitmap.getHeight()),
                    new Rect(0,0,(int)newWidth, (int)newHeight), null);
            bitmap.recycle();
            bitmap=newBitmap;
            level++;
        }

        gl.glBindTexture(GL10.GL_TEXTURE_2D,0);
        bitmap.recycle();
    }

    public void reload(){
        load();
        bind();
        setFilters(minFilter, magFilter);
        glGraphics.getGl().glBindTexture(GL10.GL_TEXTURE_2D,0);
    }

    public void bind() {
        GL10 gl=glGraphics.getGl();
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
    }

    public void setFilters(int minFilter, int magFilter) {
        this.minFilter=minFilter;
        this.magFilter=magFilter;
        GL10 gl=glGraphics.getGl();
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, minFilter);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, magFilter);
    }

    public void dispose(){
        GL10 gl=glGraphics.getGl();
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
        int[] textureIds={textureId};
        gl.glDeleteTextures(1, textureIds, 0);
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
