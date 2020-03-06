package com.example.tugas1;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        List<String> isTab = new ArrayList<>();
        isTab.add("Chat");
        isTab.add("Status");
        isTab.add("Panggilan");

        for (int i = 0;i < isTab.size(); i++){
            tabLayout.addTab(tabLayout.newTab().setText(isTab.get(i)));
        }

        PageAdapter adapter = new PageAdapter(getSupportFragmentManager(), isTab);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }
}
