package com.hiriganaapp.monster.hiriganaapp.Model;

import android.util.SparseBooleanArray;

/**
 * Created by Monster on 12-Mar-15.
 */
public class KanaSingleton {
    private static KanaSingleton singleton = null;
    private SparseBooleanArray selection;
    private int NumOfHiraganaItems = 75;

    private KanaSingleton() {
        selection = new SparseBooleanArray(NumOfHiraganaItems);
    }

    public static KanaSingleton getKanaSingleton() {
        if (singleton == null) {
            singleton = new KanaSingleton();
        }
        return singleton;
    }

    public boolean get(int key){
        return selection.get(key);
    }

    public void put(int key, boolean value) {
        selection.put(key,value);
    }

    public int length() {
        return NumOfHiraganaItems;
    }


}
