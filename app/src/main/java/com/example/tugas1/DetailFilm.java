package com.example.tugas1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugas1.R;

public class DetailFilm extends AppCompatActivity {
    String data1, data2;
    int images;
    ImageView mainImageView;
    TextView judul,deskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_film);

        mainImageView = findViewById(R.id.gambarDetail);
        judul = findViewById(R.id.judulFilmDetail);
        deskripsi = findViewById(R.id.deskripsiFilmDetail);

        getData();
        setData();
    }

    private void getData(){
        if(getIntent().hasExtra("images") && getIntent().hasExtra("data1") && getIntent().hasExtra("data2")){
            data1 = getIntent().getStringExtra("data1");
            data2 = getIntent().getStringExtra("data2");
            images = getIntent().getIntExtra("images",1);
        }else{
            Toast.makeText(getApplicationContext(),"No Data",Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(){
        judul.setText(data1);
        deskripsi.setText(data2);
        mainImageView.setImageResource(images);
    }
}