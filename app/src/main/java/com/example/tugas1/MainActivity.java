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

public class MainActivity extends AppCompatActivity {
    Database db;
    Button button;
    EditText loginEmail,loginPassword;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences preferences = getSharedPreferences("enter",MODE_PRIVATE);
        String cek = preferences.getString("remember","");

        if(cek.equals("true")){
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }

        db = new Database(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.login);
        loginEmail = findViewById(R.id.emailUser);
        loginPassword = findViewById(R.id.passwordUser);
        textView = findViewById(R.id.signup);
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
                String email = loginEmail.getText().toString();
                String pass = loginPassword.getText().toString();
                Boolean checkMail = db.checkLogin(email,pass);
                if(checkMail==true){
                    Toast.makeText(getApplicationContext(),"\n" +
                            "Login Successful",Toast.LENGTH_SHORT).show();

                    SharedPreferences preferen = getSharedPreferences("enter",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferen.edit();
                    editor.putString("remember","true");
                    editor.apply();

                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Email or Password is Wrong",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
