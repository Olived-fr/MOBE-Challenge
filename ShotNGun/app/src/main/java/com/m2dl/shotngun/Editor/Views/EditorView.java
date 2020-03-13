package com.m2dl.shotngun.Editor.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.m2dl.shotngun.R;

import java.io.File;

import static android.content.Context.SENSOR_SERVICE;
import static com.m2dl.shotngun.Editor.Views.EditorActivity.pimpedPhoto;

public class EditorView extends View {

    Bitmap bitmap, bitmap2, bitmap3;
    private Integer poutineClick1 = 0;
    private Integer poutineClick2 = 0;
    private float coordPoutine2x, coordPoutine2y, coordPoutine1x, coordPoutine1y;
    int hexa, hexa1, hexa2, hexa3 ;
    private String poutine1="";
    private String poutine2="";
    private Integer isPoutine = 0;
    private Integer isPoutinetest =0;
    private String priority = "";
    private String priorityg = "";
    private Boolean firstTime = true;
    private Paint myPaint;
    int color;
    int colorMagnet = (0xff) << 24 | (0xff) << 16 | (0xff) << 8 | (0xff) ;
    float x = -1;
    float y = -1;
    float x4 = -1;
    float y4 = -1;
    float x2 = -1;
    float y2 = -1;
    float x3 = -1;
    float y3 = -1;

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
System.out.println("x "+ x + "y "+ y + "x2 "+ x2 + "y2 "+ y2 + "x3 "+ x3 + "y3 "+ y3 + "x4 "+ x4 + "y4 " + y4);
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

                //   x = event.getX();
            //    y = event.getY();
             //   invalidate();
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

    public void getPoutine (String poutine) {

        if(poutine.equals("fromage"))  {
            if(this.poutine1.equals("fromage")) {
                this.poutine1 = "";
                if(this.poutine2.equals("effiloché")) {
                    priorityg =this.poutine2;
                   poutineClick2 = 2;
                }
            }

            else {
                this.poutine1 = poutine;
                    priorityg = "fromage";
                }
            if (poutineClick1 == 1) poutineClick1 = 10;
            else poutineClick1=1;
            if (isPoutinetest == 2 || isPoutinetest == 0) isPoutine = 1;
            priority = this.poutine1 ;
        }
        if(poutine.equals("effiloché")) {

            if(this.poutine2.equals("effiloché")) {
                this.poutine2 = "";
                if (this.poutine1.equals("fromage")) {
                    priorityg ="fromage";
                    poutineClick1 = 1;
                }
            }

            else {
                this.poutine2 = poutine;
                priorityg = "effiloché";
            }

            if (poutineClick2 == 2) poutineClick2 = 20;
            else poutineClick2=2;
            if (isPoutinetest == 1 || isPoutinetest == 0) isPoutine = 2;
            priority = this.poutine2;

        }
        invalidate();

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
