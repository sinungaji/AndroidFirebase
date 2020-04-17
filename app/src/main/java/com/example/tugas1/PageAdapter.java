package com.example.tugas1;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.example.tugas1.FragmentHome;
import com.example.tugas1.FragmentCall;
import com.example.tugas1.FragmentStatus;


public class PageAdapter extends FragmentPagerAdapter {


    private Fragment[] activities;

    public PageAdapter(FragmentManager fn){
        super(fn);
        activities = new Fragment[]{
                new FragmentHome(),
                new FragmentStatus(),
                new FragmentCall(),
        };
    }

    @Override
    public Fragment getItem(int position) {
        return activities[position];
    }

    @Override
    public int getCount(){
        return activities.length;
    }

    @Override
    public CharSequence getPageTitle(int position){
        String title = getItem(position).getClass().getName();
        return title.subSequence(title.lastIndexOf(".") + 1, title.length());
    }
}
