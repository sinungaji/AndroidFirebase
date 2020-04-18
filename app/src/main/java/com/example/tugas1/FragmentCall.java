package com.example.tugas1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.tugas1.R;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;
public class FragmentCall extends Fragment {
    private Button logout,mulai,berhenti;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState){
        View view = inflater.inflate(R.layout.calling_layout,container,false);
        logout = view.findViewById(R.id.button);
        mulai = view.findViewById(R.id.mulai);
        berhenti = view.findViewById(R.id.berhenti);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferen = Objects.requireNonNull(getActivity()).getSharedPreferences("masuk", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferen.edit();
                editor.putString("ingat","false");
                editor.apply();
                getActivity().finish();
            }
        });
        return view;
    }
}
