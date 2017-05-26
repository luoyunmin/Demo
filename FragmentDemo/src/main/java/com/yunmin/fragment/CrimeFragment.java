package com.yunmin.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;


/**
 * Created by luoyu on 2016/3/6.
 */
public class CrimeFragment extends android.support.v4.app.Fragment {
    private Button crime1;
    private static final int REQUEST_CODE = 0;
    private final String[] mTitles = {
            "热门", "iOS", "Android", "前端"
            , "后端", "设计", "工具资源"
    };
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);
//        SlidingTabLayout tabLayout=(SlidingTabLayout)v.findViewById(R.id.tab_layout);
//        ViewPager vp = (ViewPager) v.findViewById(R.id.vp);
//        vp.setAdapter(new SamplePagerAdapter());
//        tabLayout.setViewPager(vp);
//        crime1 = (Button) v.findViewById(R.id.crime1);
//        crime1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                CrimeFragment2 crime2 = CrimeFragment2.newInstance();
//                crime2.setTargetFragment(CrimeFragment.this, REQUEST_CODE);
//                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.fragment_pop_left_enter,R.animator.fragment_pop_left_exit).replace(R.id.fragmentContainer, crime2).addToBackStack("crime2Fragment").commit();
//            }
//        });
        for (String title : mTitles) {
            mFragments.add(SimpleFragment.getInstance(title));
        }
        ViewPager pager = (ViewPager) v.findViewById(R.id.vp);
        pager.setAdapter(new MyPagerAdapter(getFragmentManager()));

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) v.findViewById(R.id.tabs);
        tabs.setViewPager(pager);
        return v;
    }

    public static CrimeFragment newInstance() {

        Bundle args = new Bundle();
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == REQUEST_CODE) {
            Log.e("lym------", data.getStringExtra("str"));
        }
    }

    class SamplePagerAdapter extends FragmentPagerAdapter {

        public SamplePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return new Fragment();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return null;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
