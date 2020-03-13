package com.m2dl.shotngun;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class Sprite {

    private Bitmap bitmap = null; // image
    protected int x,y; // Position
    protected boolean direction;
    protected double vx, vy;
    final static double interval = 0.9; // délai
    protected double g; // gravité

    public Sprite(Context context, int src) {
        this.bitmap = makeBitmap(context, src);
    }

    private Bitmap makeBitmap(Context context, int src) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        options.inSampleSize = 2;
        try {
            bitmap = BitmapFactory.decodeResource(context.getResources(), src, options);
        } catch (Exception e) {
            Log.e(TAG, "sprite.setBitmap() failed.");
            bitmap = BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher , options);
        }
        return bitmap;
    }

    public void drawBitmap(Canvas canvas, Paint paint) {
        try {
            canvas.drawBitmap(bitmap, x, y, paint);
        } catch (Exception e) {
            Log.e(TAG, "sprite.drawBitmap() failed.");
        }
    }

    public void refresh(){

    }

}
