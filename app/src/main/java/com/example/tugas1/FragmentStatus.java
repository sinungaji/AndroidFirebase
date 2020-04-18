package com.example.tugas1;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.tugas1.AddMahasiswa;
import com.example.tugas1.R;


public class FragmentStatus extends Fragment {
    Button tambah;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntancesState){
        View view = inflater.inflate(R.layout.status_layout,container,false);
        tambah = view.findViewById(R.id.tambahMahasiswaAwal);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddMahasiswa.class);
                startActivity(intent);

            }
        });
        return view;
    }
}
