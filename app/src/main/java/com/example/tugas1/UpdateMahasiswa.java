package com.example.tugas1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateMahasiswa extends AppCompatActivity {
    String data1, data2, data3, id;
    EditText no_Mahasiswa,nama_Mahasiswa,phone_Mahasiswa;
    Button upload;

    private FirebaseFirestore firebaseFirestoreDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_mahasiswa);

        firebaseFirestoreDb = FirebaseFirestore.getInstance();

        no_Mahasiswa = findViewById(R.id.editTextUploadno_Mahasiswa);
        nama_Mahasiswa = findViewById(R.id.editTextUploadnama_Mahasiswa);
        phone_Mahasiswa = findViewById(R.id.editTextUploadPhone);
        upload = findViewById(R.id.buttonUploadMhs);

        getData();
        setData();

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!no_Mahasiswa.getText().toString().isEmpty() && !nama_Mahasiswa.getText().toString().isEmpty()) {
                    updateMahasiswa();
                } else {
                    Toast.makeText(getApplicationContext(), "No dan Nama Mahasiswa tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getData() {
        if (getIntent().hasExtra("no_Mahasiswa") && getIntent().hasExtra("nama_Mahasiswa") && getIntent().hasExtra("phone_Mahasiswa") && getIntent().hasExtra("KUNCI")) {
            data1 = getIntent().getStringExtra("no_Mahasiswa");
            data2 = getIntent().getStringExtra("nama_Mahasiswa");
            data3 = getIntent().getStringExtra("phone_Mahasiswa");
            id = getIntent().getStringExtra("KUNCI");
        } else {
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {
        no_Mahasiswa.setText(data1);
        nama_Mahasiswa.setText(data2);
        phone_Mahasiswa.setText(data3);
    }

    private void updateMahasiswa() {
        Mahasiswa mhs = new Mahasiswa(no_Mahasiswa.getText().toString(), nama_Mahasiswa.getText().toString(),phone_Mahasiswa.getText().toString());
        firebaseFirestoreDb.collection("Daftar_Mahasiswa").document(id).set(mhs)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Mahasiswa berhasil diupdate", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "ERROR" + e.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());
                    }
                });
    }
}