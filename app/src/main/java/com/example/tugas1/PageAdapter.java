package com.example.tugas1;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PageAdapter extends FragmentPagerAdapter {

    private List<String> IsTab;
    private List<Fragment> IsFragment;

    public PageAdapter(FragmentManager fn, List<String> IsTab){
        super(fn);
        this.IsTab = IsTab;
        IsFragment = new ArrayList<>();
    }
    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        if (IsFragment.size() > position) frag =  IsFragment.get(position);

        if (IsTab.get(position).equals("Chat")) {
            frag =  new FragmentHome();
            IsFragment.add(frag);
        }else if (IsTab.get(position).equals("Status")){
            frag =  new FragmentStatus();
            IsFragment.add(frag);
        }else if (IsTab.get(position).equals("Panggilan")){
            frag =  new FragmentCall();
            IsFragment.add(frag);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
