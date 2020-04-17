package com.example.tugas1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.SnapshotParser;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddMahasiswa extends AppCompatActivity {

    EditText no_Mahasiswa,nama_Mahasiswa,phone_Mahasiswa;
    Button tambah;

    private RecyclerView data_tampil;
    private FirebaseFirestore firebaseFirestoreDb;
    private FirestoreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_mahasiswa);

        no_Mahasiswa = findViewById(R.id.txt_noMahasiswa);
        nama_Mahasiswa = findViewById(R.id.txt_namaMahasiswa);
        phone_Mahasiswa = findViewById(R.id.txt_phoneMahasiswa);
        tambah = findViewById(R.id.addMahasiswaButton);
        firebaseFirestoreDb = FirebaseFirestore.getInstance();

        data_tampil = findViewById(R.id.recycleData1);

        FirestoreRecyclerOptions<Mahasiswa> options = new FirestoreRecyclerOptions.Builder<Mahasiswa>()
                .setLifecycleOwner(this)
                .setQuery(firebaseFirestoreDb.collection("Daftar_Mahasiswa"), new SnapshotParser<Mahasiswa>() {
                    @NonNull
                    @Override
                    public Mahasiswa parseSnapshot(@NonNull DocumentSnapshot snapshot) {
                        Mahasiswa mahasiswa = snapshot.toObject(Mahasiswa.class);
                        String idItem = snapshot.getId();
                        mahasiswa.setId_item(idItem);
                        return mahasiswa;
                    }
                })
                .build();

        adapter = new FirestoreAdapter(options,getApplicationContext());
        data_tampil.setHasFixedSize(true);
        data_tampil.setLayoutManager(new LinearLayoutManager(this));
        data_tampil.setAdapter(adapter);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!no_Mahasiswa.getText().toString().isEmpty() && !nama_Mahasiswa.getText().toString().isEmpty()) {
                    addMahasiswa();
                } else {
                    Toast.makeText(getApplicationContext(), "No dan Nama mahasiswa tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void addMahasiswa() {
        Mahasiswa mahasiswa = new Mahasiswa(no_Mahasiswa.getText().toString(), nama_Mahasiswa.getText().toString(),phone_Mahasiswa.getText().toString());
        firebaseFirestoreDb.collection("Daftar_Mahasiswa").document().set(mahasiswa)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AddMahasiswa.this, "Mahasiswa didaftarkan", Toast.LENGTH_SHORT).show();
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