package com.m2dl.shotngun;

import android.content.Context;

public class Enemy extends Sprite {

    public Enemy(int x, int y, Context context) {
        this.makeBitmap(context, "/storage/emulated/0/shotncut/cut/pnj0.png");
        this.width = 80;
        this.height = 80;
        this.x = x;
        this.y = y;
        this.vy = 2.5;
        this.g = 0;

    }

    public void refresh(){
        y += (int) (vy * interval);
    }
}
