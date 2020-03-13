package com.m2dl.shotngun.Editor.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import java.io.File;

import static com.m2dl.shotngun.Editor.Views.EditorActivity.pimpedPhoto;

public class EditorView extends View {

    Bitmap  bitmap2;
    private Paint myPaint;
    float x = -1;
    float y = -1;
    float x2 = -1;
    float y2 = -1;


    public EditorView(Context context, AttributeSet attrs ) {
        super(context, attrs);
        File file = new File(pimpedPhoto);

        bitmap2 = BitmapFactory.decodeFile(file.getAbsolutePath());
        myPaint = new Paint();
        myPaint.setColor(Color.rgb(0, 0, 0));
        myPaint.setStrokeWidth(10);
        myPaint.setStyle(Paint.Style.STROKE);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            if (x == - 1 && y == -1) {
                x = event.getX();
                y = event.getY();
            }  else {
                x2 = event.getX();
                y2 = event.getY();
            }
                break;
            case MotionEvent.ACTION_MOVE:
                invalidate();

                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
            int w = canvas.getClipBounds().width();
            int h = canvas.getClipBounds().height();
            canvas.drawBitmap(bitmap2, null, new RectF(0, 0, w, h),  null);


        if (x != -1 && y != -1 && x2 !=-1 && y2 != -1) {
            canvas.drawRect(x, y, x2, y2, myPaint);
            bitmap2 = Bitmap.createBitmap (bitmap2,(int)((bitmap2.getWidth()*x)/canvas.getWidth()),(int)((bitmap2.getHeight()*y)/canvas.getHeight()),(int)  500, (int) (500));
            x =-1;
            y = -1;
            x2 = -1;
            y2 = -1;
            invalidate();

        }
    }
    public Bitmap validImage() {
        this.layout(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
        this.setDrawingCacheEnabled(true);
        this.buildDrawingCache(true);
        Bitmap bitmap5 = Bitmap.createBitmap(this.getDrawingCache());
        this.setDrawingCacheEnabled(false);
        return bitmap5;
    }
}
