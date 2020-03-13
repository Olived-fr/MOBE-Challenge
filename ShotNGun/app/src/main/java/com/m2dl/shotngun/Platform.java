package com.m2dl.shotngun;

import android.content.Context;

public class Platform extends Sprite {

    public Platform(int x, int y, Context context) {
        super(context, R.mipmap.ic_launcher);
        this.x = x;
        this.y = y;
        this.g = 0;
    }

    public void refresh(int screenWidth, int screenHeight){
        y += (int) (vy * interval);
    }
}
