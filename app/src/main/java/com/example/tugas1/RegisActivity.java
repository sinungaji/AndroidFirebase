package com.example.tugas1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisActivity extends AppCompatActivity {
    Database db;
    private Button buttonRegis, buttonCancel;
    private EditText txtEmail,txtNama,txtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regis_layout);
        db = new Database(this);
        txtEmail = findViewById(R.id.txt_email);
        txtNama = findViewById(R.id.txt_nama);
        txtPass = findViewById(R.id.txt_pass);


        buttonRegis = findViewById(R.id.btn_Regis);
        buttonRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();
                String nama = txtNama.getText().toString();
                String pass = txtPass.getText().toString();
                if(email.equals("") || nama.equals("") || pass.equals("")){
                    Toast.makeText(getApplicationContext(),"Field Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkMail = db.checkData(email);
                    if(checkMail==true){
                        Boolean insertData = db.masukkanData(email,nama,pass);
                        if(insertData==true){
                            Toast.makeText(getApplicationContext(),"Registrasi Berhasil",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Email Telah Terdaftar",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        buttonCancel = findViewById(R.id.btn_Batal);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}