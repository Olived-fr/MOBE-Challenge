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


import com.m2dl.shotngun.CameraActivity;
import com.m2dl.shotngun.FullscreenActivity;
import com.m2dl.shotngun.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class EditorActivity extends AppCompatActivity {

    private EditorView viewtest;
    static String pimpedPhoto, namePhoto;
    private Button buttonValid;
    private String elementType;
    private ArrayList<String> pathList;
    private int idPnj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        idPnj = getIntent().getIntExtra("id",0);
        elementType = getIntent().getStringExtra("ELEMENT_TYPE");
        pathList = (ArrayList<String>) getIntent().getSerializableExtra("PATH_LIST");
        pimpedPhoto = intent.getStringExtra("pathPhoto");
        namePhoto = intent.getStringExtra("namePhoto");

        System.out.println();
        setContentView(R.layout.activity_editor);
        viewtest = (EditorView) findViewById(R.id.editorView);
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

                if (elementType != null
                        && f.getAbsolutePath() != null) {
                    addPath(elementType, f.getAbsolutePath(), namePhoto);
                }

                Intent homePage = new Intent(getBaseContext(), FullscreenActivity.class);
                homePage.putExtra("ELEMENT_TYPE", elementType);
                homePage.putExtra("PICTURE_PATH", f.getAbsolutePath());
                homePage.putExtra("PICTURE_NAME", namePhoto+".png");
                homePage.putExtra("PATH_LIST", pathList);
                homePage.putExtra("id", idPnj);
System.out.println("/////////////////////////////////////////////////////"+f.getAbsolutePath());
                startActivity(homePage);

            }
        });
    }

    public void addPath(String elementType, String picturePath, String namePhoto) {
        if ("ENNEMIE".equals(elementType)) {
            if (pathList == null) {
                pathList = new ArrayList<>();
            }
            pathList.add(picturePath);
        }

    }
}
