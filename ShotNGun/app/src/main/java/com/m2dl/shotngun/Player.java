package com.m2dl.shotngun;

import android.content.Context;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class Player extends Sprite {

    protected boolean gameOver;
    protected boolean jumping;
    protected boolean direction;

    public Player(int screenWidth, int screenHeight, Context context) {
        this.makeBitmap(context, "/storage/emulated/0/shotncut/cut/HERO.png");
        this.x = screenWidth / 2;
        this.y = screenHeight;
        this.width = 80;
        this.height = 80;
        this.direction = true;
        this.vx = 0;
        this.vy = 0.7;
        this.g = 0.00322;
        this.jumping = true;
        this.gameOver = false;
    }

    public void refresh(int screenWidth, int screenHeight, boolean jumping){
        x += (int) (vx * interval);
        /*if (x > screenWidth / 2)
            x = screenWidth - width / 2 - 1;
        else if (x > screenWidth - width / 2)
            x = -width / 2 + 1;
        */
        if(jumping) {
            y -= (int) (vy * interval);
            vy += + g * interval;
        }
        else{
            y += (int) (vy * interval);
            vy -= + g * interval;
        }
        /*if (y <= screenHeight / 2 && vy < 0) still = true;
        else still = false;*/

        if (y > screenHeight)
            gameOver = true;

        //Log.e(TAG, "x = " + x);
        //Log.e(TAG, "vy = " + vy);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }
}
