package com.example.maihuy.leaf_color.framework;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maihuy on 4/12/2015.
 */
public class Pool<T> {
    public interface PoolObjecFactory<T>{
        public T createObject();
    }

    private final List<T> freeObject;
    private final PoolObjecFactory<T> factory;
    private final int maxSize;

    public Pool(PoolObjecFactory<T> factory, int maxSize) {
        this.factory = factory;
        this.maxSize = maxSize;
        this.freeObject=new ArrayList<T>(maxSize);
    }

    public T newObject(){
        T object=null;
        if (freeObject.size()==0) object=factory.createObject();
        else object=freeObject.remove(freeObject.size()-1);
        return object;
    }

    public void free(T object){
        if (freeObject.size()<maxSize){
            freeObject.add(object);
        }
    }
}
