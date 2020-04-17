package com.example.tugas1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ComponentName;

public class MainActivity extends AppCompatActivity {
    Database tugasDatabase;
    Button button;
    EditText emailLogin, passwordLogin;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences preferences = getSharedPreferences("Enter",MODE_PRIVATE);
        String cek = preferences.getString("Don't Forget","");

        if(cek.equals("true")){
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }

        tugasDatabase = new Database(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn_Login);
        emailLogin = findViewById(R.id.txt_emailLogin);
        passwordLogin = findViewById(R.id.txt_passwordLogin);
        textView = findViewById(R.id.daftar);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisActivity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailLogin.getText().toString();
                String password = passwordLogin.getText().toString();

                Boolean emailCheck = tugasDatabase.checkLogin(email,password);
                if(emailCheck==true){
                    SharedPreferences preferen = getSharedPreferences("Enter",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferen.edit();
                    editor.putString("Remember","true");
                    editor.apply();

                    Bundle extras = new Bundle();
                    extras.putString("KEY",email);
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtras(extras);
                    startActivity(intent);
                    emailLogin.getText().clear();
                    passwordLogin.getText().clear();

                }
                else{
                    Toast.makeText(getApplicationContext(),"Email or password is Wrong, can you try again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}