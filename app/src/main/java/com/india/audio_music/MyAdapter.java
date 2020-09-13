package com.india.audio_music;
import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class MyAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;
    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                footbal homeFragment = new footbal();
                return homeFragment;
            case 1:
                cricket sportFragment = new cricket();
                return sportFragment;
            case 2:
                nba adil = new nba();
                return adil;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}

