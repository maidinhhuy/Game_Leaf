package com.example.maihuy.leaf_color.game;


import com.example.maihuy.leaf_color.framework.FileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by maihuy on 7/6/2015.
 */
public class Settings {
    public static boolean soundEnabled=true;
    public static int highscores=0;
    public final static String file=".leaf_color";

    public static void load(FileIO files){
        BufferedReader in=null;
        try {
            in=new BufferedReader(new InputStreamReader(files.readFile(file)));
            soundEnabled = Boolean.parseBoolean(in.readLine());
            highscores=Integer.parseInt(in.readLine());
        }catch (IOException e){

        }catch (NumberFormatException e){

        }finally {
            try {
                if (in!=null) in.close();
            }catch (IOException e){

            }
        }
    }

    public static void save(FileIO files){
        BufferedWriter out=null;
        try{
            out=new BufferedWriter(new OutputStreamWriter(files.writeFile(file)));
            out.write(Boolean.toString(soundEnabled)+"\n");
            out.write(highscores+"\n");
        }catch (IOException e){

        }finally {
            try {
                if (out!=null) out.close();
            }catch (IOException e){

            }
        }
    }

    public static void addScore(int score){
        highscores=score;
    }
}
