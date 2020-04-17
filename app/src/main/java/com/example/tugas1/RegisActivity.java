package com.example.tugas1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisActivity extends AppCompatActivity {
    Database tugasDatabase;
    private Button buttonRegis, buttonCancel;
    private EditText emailText,nameText,passwordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regis_layout);
        tugasDatabase = new Database(this);
        emailText = findViewById(R.id.txt_email);
        nameText = findViewById(R.id.txt_name);
        passwordText = findViewById(R.id.txt_password);


        buttonRegis = findViewById(R.id.btn_Regis);
        buttonRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString();
                String name = nameText.getText().toString();
                String password = passwordText.getText().toString();
                if(email.equals("") || name.equals("") || password.equals("")){
                    Toast.makeText(getApplicationContext(),"Field Empyty",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean emailCheck = tugasDatabase.checkData(email);
                    if(emailCheck==true){
                        Boolean insertData = tugasDatabase.masukkanData(email,name,password);
                        if(insertData==true){
                            Toast.makeText(getApplicationContext(),"Successful Registration",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Email Registered",Toast.LENGTH_SHORT).show();
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