package com.items.code.ui.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by lihongxin on 2016/11/18.
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<String> title;
     public  MainFragmentPagerAdapter(FragmentManager fragmentManager,List<String> title,List<Fragment> fragments){

         super(fragmentManager);
         this.title=title;
         this.fragments=fragments;

     }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    public CharSequence getPageTitle(int position) {
         return title.get(position);
    }
}
