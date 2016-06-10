package com.example.maihuy.leaf_color.framework.gl;

/**
 * Created by maihuy on 4/28/2015.
 */
public class Font {
    public final Texture texture;
    public final int glyphWidth;
    public final int glyphHeight;
    public final TextureRegion[] glyphs=new TextureRegion[10];

    public Font(Texture texture, int offsetX, int offsetY, int glyphWidth, int glyphHeight) {
         this.texture = texture;
         this.glyphWidth = glyphWidth;
         this.glyphHeight = glyphHeight;
         int x = offsetX;
         int y = offsetY;
         for(int i = 0; i < 10; i++) {
             glyphs[i] = new TextureRegion(texture, x, y, glyphWidth, glyphHeight);
             x += glyphWidth;
         }
     }
    public void drawText(SpriteBatcher batcher, String text, float x, float y){
        int len=text.length();
        for (int i=0;i<len;i++){
            int c = text.charAt(i) - ' ';
            if (c<16||c>16+glyphs.length-1)
                continue;
            TextureRegion glyph=glyphs[c-16];
            batcher.drawSprite(x,y,glyphWidth, glyphHeight, glyph);
            x+=(glyphWidth);
        }
    }

    public void drawTextSize(SpriteBatcher batcher, String text, float x, float y,float size){
        int len=text.length();
        for (int i=0;i<len;i++){
            int c = text.charAt(i) - ' ';
            if (c<16||c>16+glyphs.length-1)
                continue;
            TextureRegion glyph=glyphs[c-16];
            batcher.drawSprite(x,y,glyphWidth*size, glyphHeight*size, glyph);
            x+=glyphWidth*size;
        }
    }


    public void drawText(SpriteBatcher batcher, String text, float x, float y,float ratio){
        int len=text.length();
        for (int i=0;i<len;i++){
            int c = text.charAt(i) - ' ';
            if (c<16||c>16+glyphs.length-1)
                continue;
            TextureRegion glyph=glyphs[c];
            batcher.drawSprite(x,y,glyphWidth*ratio, glyphHeight*ratio, glyph);
            x+=(glyphWidth-40);
        }
    }

    public void drawTextNumber(SpriteBatcher batcher, String text, float x, float y){
        int len=text.length();
        for (int i=0;i<len;i++){
            int c = text.charAt(i) - ' ';
            if (c<16||c>16+glyphs.length-1)
                continue;
            TextureRegion glyph=glyphs[c];
            batcher.drawSprite(x,y,glyphWidth, glyphHeight, glyph);
            x+=(glyphWidth-30);
        }
    }
}
