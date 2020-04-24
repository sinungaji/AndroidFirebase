package com.example.tugas1;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
public class CameraActivity extends AppCompatActivity {
    private static final int PERMISSION_CODE = 1000;
    private static final int CAPTURE_CODE = 1001;
    Button camera;
    ImageView hasil;
    private Uri file;



    public void onCreate(Bundle savedInstanceStare) {

        super.onCreate(savedInstanceStare);
        setContentView(R.layout.camera);

        camera= findViewById(R.id.btnCamera);
        hasil= findViewById(R.id.picture);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED ){
                        String [] permision = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permision, PERMISSION_CODE);
                    }
                    else {
                        ambilFoto();
                    }
                }
                else {
                    ambilFoto();
                }


            }
        });
    }

    public  void ambilFoto(){
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Fotp Baru");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Dari Camera");
        file = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
        startActivityForResult(intent,CAPTURE_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    ambilFoto();
                }
                else {
                    Toast.makeText(this, "Permision denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            hasil.setImageURI(file);
        }

    }

    public static File getOutputMedia(){
        File mediaStorage = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Camera");

        if(mediaStorage.exists()){
            if(mediaStorage.mkdirs()){
                return null;
            }
        }

        String timeStap = new SimpleDateFormat("yyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorage.getPath() + File.separator + "IMG_" + timeStap + ".JPG");
    }
}


