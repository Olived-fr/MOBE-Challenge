package com.m2dl.shotngun;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import static android.content.Context.SENSOR_SERVICE;


public class Board extends View {

    private Player player;
    private Platform platform;
    private int screenWidth, screenHeight;
    private Paint paint;
    SensorManager sensorManager = (SensorManager) getContext().getSystemService(SENSOR_SERVICE);
    Sensor orientationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


    public Board(Context context,  @Nullable AttributeSet attrs) {
        super(context, attrs);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(50);
        sensorManager.registerListener(sensorListener, orientationSensor, 2 * 1000 * 1000);
        player = new Player(screenWidth, screenHeight, context);
        platform = new Platform(200, 200, context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        player.drawBitmap(canvas, paint);
        player.refresh(screenWidth, screenHeight, player.jumping);
        platform.refresh();
        invalidate();
    }

    SensorEventListener sensorListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            double x0 = (double) sensorEvent.values[0];
            player.vx = -x0 * 5;
            player.direction = !(x0 > 0);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
