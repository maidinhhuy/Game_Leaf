package com.example.maihuy.leaf_color.framework.gl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maihuy on 4/27/2015.
 */
public class ObjLoader {
    /*public static Vectices3 load(GLGame game, String file){
        InputStream in=null;
        try {
            in=game.getFileIO().readAsset(file);
            List<String> lines=readLines(in);

            float[] vertices =new float[lines.size()*3];
            float[] normals=new float[lines.size()*3];
            float[] uv=new float[lines.size()*2];

            int numVertices=0,vertexIndex=0;
            int numNormals=0,normalIndex=0;
            int numUV=0, uvIndex=0;
            int numFaces=0, faceIndex=0;

            for (int i=0; i<lines.size();i++){
                String line=lines.get(i);
                if (line.startsWith("v")){
                    String[] tokens=line.split("[ ]+");
                    vertices[vertexIndex]=Float.parseFloat(tokens[1]);
                    vertices[vertexIndex+1]=Float.parseFloat(tokens[2]);
                    vertices[vertexIndex+2]=Float.parseFloat(tokens[3]);
                    vertexIndex+=3;
                    numVertices++;
                    continue;;
                }
                if (line.startsWith("vn ")){
                    String[] tokens=line.split("[ ]+");
                    normals[normalIndex]=Float.parseFloat(tokens[1]);
                    normals[normalIndex+1]=Float.parseFloat(tokens[2]);
                    normals[normalIndex+2]=Float.parseFloat(tokens[3]);
                    normalIndex+=3;
                    numNormals++;
                    continue;
                }

                if (line.startsWith("vt")){
                    String[] tokens=line.split("[ ]+");
                    uv[uvIndex]=Float.parseFloat(tokens[1]);
                    uv[uvIndex+1]=Float.parseFloat(tokens[2]);
                    uvIndex+=2;
                    numUV++;
                    continue;
                }

                if (line.startsWith("f ")){
                    String[] tokens=line.split("[ ]+");

                    String[] parts=tokens[1].split("/");
                    faceVerts[faceIndex]=getIndex(parts[0], numVertices);
                    if (parts.length>2){
                        faceMo
                    }


                }
            }

        }catch (Exception e){

        }

    }*/

    static int getIndex(String index, int size){
        int idx= Integer.parseInt(index);
        if (idx<0) return size+idx;
        else return idx-1;
    }

    static List<String> readLines(InputStream in) throws IOException {
        List<String> lines=new ArrayList<String>();

        BufferedReader reader=new BufferedReader(new InputStreamReader(in));
        String line=null;
        while ((line=reader.readLine())!=null)
            lines.add(line);
        return lines;
    }
}
