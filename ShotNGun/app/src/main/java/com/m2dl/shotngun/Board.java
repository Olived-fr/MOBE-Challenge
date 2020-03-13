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
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.SENSOR_SERVICE;


public class Board extends View {

    private Player player;
    private List<Platform> platforms = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private int screenWidth, screenHeight;
    private Paint paint;
    SensorManager sensorManager = (SensorManager) getContext().getSystemService(SENSOR_SERVICE);
    Sensor orientationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    Timer timer = new Timer();

    public Board(Context context,  @Nullable AttributeSet attrs) {
        super(context, attrs);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        sensorManager.registerListener(sensorListener, orientationSensor, 2 * 1000 * 1000);
        player = new Player(screenWidth, screenHeight, context);
        for(int i = 0; i < 1; i++) {
            platforms.add(new Platform(screenWidth, screenHeight));
        }
        for(Platform platform: platforms) {
            enemies.add(new Enemy(platform.x, platform.y-platform.height, context));
        }
    }

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            player.jumping = false;
        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        player.drawBitmap(canvas, paint);
        player.refresh(screenWidth, screenHeight, player.jumping);
        for(Platform platform: platforms) {
            canvas.drawRect(platform.x, platform.y, platform.x+platform.width, platform.y+platform.height, paint);
            if(platform.checkImpact(player.x, player.y)){
                player.jumping = true;
            }
            platform.refresh();
        }
        for(Enemy enemy: enemies){
            enemy.drawBitmap(canvas, paint);
            enemy.refresh();

        }
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
