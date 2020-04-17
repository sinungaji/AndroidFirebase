package com.example.tugas1;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreAdapter extends FirestoreRecyclerAdapter<Mahasiswa, FirestoreAdapter.MahasiswaViewHolder> {


    private FirebaseFirestore firebaseFirestore;
    Context context;

    public FirestoreAdapter(@NonNull FirestoreRecyclerOptions<Mahasiswa> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull MahasiswaViewHolder holder, int position, @NonNull final Mahasiswa model) {
        holder.nim.setText(model.getNim());
        holder.namaMahasiswa.setText(model.getNama());
        holder.phone_Mahasiswa.setText((model.getPhone()));
        holder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = model.getId_item();
                firebaseFirestore.collection("Daftar_Mahasiswa").document(id)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(context, "Mahasiswa berhasil dihapus",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, "Error deleting document: " + e.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        holder.upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateMahasiswa.class);
                intent.putExtra("no_Mahasiswa",model.getNim());
                intent.putExtra("nama_Mahasiswa",model.getNama());
                intent.putExtra("phone_Mahasiswa",model.getPhone());
                intent.putExtra("KUNCI",model.getId_item());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_firestore, parent, false);
        return new MahasiswaViewHolder(view);
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder {
        TextView nim, namaMahasiswa, phone_Mahasiswa;
        Button hapus, upload;

        public MahasiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            firebaseFirestore = FirebaseFirestore.getInstance();
            hapus = itemView.findViewById(R.id.hapusMahasiswaButton);
            upload = itemView.findViewById(R.id.updateMahasiswaButton);
            nim = itemView.findViewById(R.id.list1);
            namaMahasiswa = itemView.findViewById(R.id.list2);
            phone_Mahasiswa = itemView.findViewById(R.id.list3);
        }
    }
}