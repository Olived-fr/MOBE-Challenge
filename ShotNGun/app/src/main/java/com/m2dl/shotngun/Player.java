package com.m2dl.shotngun;

import android.content.Context;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class Player extends Sprite {

    private boolean gameOver;
    protected boolean jumping;

    public Player(int screenWidth, int screenHeight, Context context) {
        super(context, R.mipmap.ic_launcher_round);
        this.x = screenWidth / 2;
        this.y = screenHeight;
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
}
