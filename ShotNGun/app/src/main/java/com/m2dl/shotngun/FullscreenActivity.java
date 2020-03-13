package com.m2dl.shotngun;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    ScrollView scrollView;


    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
        }
    };
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
        }
    };
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        FrameLayout frameMenu = findViewById(R.id.frame_content_menu);
        FrameLayout framePersonnaliser = findViewById(R.id.frame_personnaliser_menu);

        ImageButton boutonJouer = findViewById(R.id.bouton_jouer);
        ImageButton boutonPersonnaliser = findViewById(R.id.bouton_personnaliser);

        boutonJouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Démarrer le jeu
            }
        });

        boutonPersonnaliser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // On change de layout
                frameMenu.setVisibility(View.INVISIBLE);
                framePersonnaliser.setVisibility(View.VISIBLE);
            }
        });

        scrollView = findViewById(R.id.ennemies_scroll_view);
        LinearLayout scrollLayout = findViewById(R.id.scroll_layout);

        // TODO à retirer
        List<ImageView> ennemies = new ArrayList<>();
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(R.drawable.imagetest);
        ennemies.add(imageView);

        ImageView imageView2 = new ImageView(this);
        imageView2.setBackgroundResource(R.drawable.poutine);
        ennemies.add(imageView2);
        //fin à retirer

        // TODO à remplacer par getEnnemiesFromStorage
        addEnnemiesOnScrollView(ennemies,scrollLayout);
    }

    public void getEnnemiesFromStorage(List<String> pathList, LinearLayout scrollLayout) {

        List<ImageView> imageViews = new ArrayList<>();

        for (String imagePath : pathList) {

            Bitmap bmp = BitmapFactory.decodeFile(imagePath);
            Drawable image = new BitmapDrawable(getResources(), bmp);
            ImageView imageView = new ImageView(this);
            imageView.setBackground(image);

            //imageView.setBackgroundResource(R.drawable.imagetest);
        }

        addEnnemiesOnScrollView(imageViews, scrollLayout);
    }

    public void addEnnemiesOnScrollView(List<ImageView> ennemies, LinearLayout scrollLayout) {
        for (ImageView image : ennemies) {

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    scrollLayout.removeView(image);
                }
            });

            scrollLayout.addView(image);
        }
    }
    
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }


    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}