package com.m2dl.shotngun;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Platform extends Sprite {

    public Platform(int screenWidth, int screenHeight) {
        this.x = (int) (Math.random() * (screenWidth -50));
        this.y = (int) (Math.random() * (screenHeight - 50));
        this.width = 80;
        this.height = 30;
        this.vy = 2.5;
        this.g = 0;
    }

    public void refresh(){
        y += (int) (vy * interval);
    }

    public boolean checkImpact(int x, int y){
        if(y < this.y + this.height && y > this.y - this.height ){
            return true;
        }
        return false;
    }
}
