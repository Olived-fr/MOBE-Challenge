package com.m2dl.shotngun.Editor.Views;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

import android.media.ExifInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import com.m2dl.shotngun.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;

public class EditorActivity extends AppCompatActivity {

    private EditorView viewtest;
    static String pimpedPhoto, namePhoto;
    private ImageButton buttonFiltre1, buttonFiltre2;
    private Button buttonValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        pimpedPhoto = intent.getStringExtra("pathPhoto");
        namePhoto = intent.getStringExtra("namePhoto");
        setContentView(R.layout.activity_editor);
        viewtest =(EditorView) findViewById(R.id.editorView);
        buttonFiltre1 = findViewById(R.id.buttonFiltre1);
        buttonFiltre1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewtest.getPoutine("fromage");
            }
        });

        buttonFiltre2 = findViewById(R.id.buttonFiltre2);
        buttonFiltre2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewtest.getPoutine("effiloché");
            }
        });

        buttonValid = findViewById(R.id.buttonValid);
        buttonValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File myDir = new File(Environment.getExternalStorageDirectory() +
                        File.separator + "shotncut/cut/"); //pour créer le repertoire dans lequel on va mettre notre fichier
                Boolean success=true;
                if (!myDir.exists()) {
                    success = myDir.mkdir(); //On crée le répertoire (s'il n'existe pas!!)
                }
                File f = new File(Environment.getExternalStorageDirectory()
                        .toString() + "/shotncut/cut/" + namePhoto + ".png");

                try {
                    f.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                FileOutputStream fOut = null;

                try {
                    fOut = new FileOutputStream(f);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                viewtest.validImage().compress(Bitmap.CompressFormat.JPEG, 100, fOut);

                ExifInterface exif = null;

                try {
                    exif = new ExifInterface(f.getPath());
                    exif.saveAttributes();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
