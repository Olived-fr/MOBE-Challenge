package com.m2dl.shotngun;

import android.content.Context;

public class Enemy extends Sprite {

    public Enemy(int x, int y, Context context) {
        this.makeBitmap(context, R.mipmap.ic_launcher);
        this.width = 40;
        this.height = 60;
        this.x = x;
        this.y = y;
        this.vy = 2.5;
        this.g = 0;

    }

    public void refresh(){
        y += (int) (vy * interval);
    }
}
