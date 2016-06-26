package com.vnpt.hddtcustomer.views.fragment;



import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vnpt.hddtcustomer.R;
import com.vnpt.hddtcustomer.views.adapter.ViewPagerAdapter;

public class HomeFragment extends Fragment {
    private View homeView;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeView = inflater.inflate(R.layout.frag_home, container, false);
        viewPager = (ViewPager) homeView.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) homeView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.WHITE));

        return homeView;
    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new TabNotificationFrag(),"Thông báo ");
        adapter.addFragment(new TabRecentBillFrag(), "Hóa đơn gần đây");
        viewPager.setAdapter(adapter);
    }

}


