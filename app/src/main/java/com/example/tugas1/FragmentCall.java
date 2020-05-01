package com.example.tugas1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;
import androidx.fragment.app.Fragment;

import com.example.tugas1.R;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;
public class FragmentCall extends Fragment {
    private Button galeri;
    private Button logout;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState){
        View view = inflater.inflate(R.layout.calling_layout,container,false);
        galeri = view.findViewById(R.id.galeri);
        logout = view.findViewById(R.id.button);
        galeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),CameraActivity.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferen = Objects.requireNonNull(getActivity()).getSharedPreferences("Enter", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferen.edit();
                editor.putString("ingat","false");
                editor.apply();
                getActivity().finish();
            }
        });

        return view;
    }
}
