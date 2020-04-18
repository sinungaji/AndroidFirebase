package com.example.tugas1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class FragmentHome extends Fragment {

    RecyclerView recyclerView;
    String s1[], s2[];
    int images[] = {R.drawable.dua_garis_biru,R.drawable.endgame,R.drawable.bucin,R.drawable.garuda_didadaku};

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState){
        View view = inflater.inflate(R.layout.home,container,false);
        s1 = getResources().getStringArray(R.array.nama_film);
        s2 = getResources().getStringArray(R.array.Deskripsi);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        Recycle recyclerviewHomeAdapter = new Recycle(getContext(),s1,s2,images);
        recyclerView.setAdapter(recyclerviewHomeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

}
