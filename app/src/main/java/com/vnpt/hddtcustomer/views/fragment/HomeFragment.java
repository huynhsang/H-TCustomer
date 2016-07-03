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

import java.util.Calendar;

public class HomeFragment extends Fragment {
    private View homeView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String dateFrom, dateTo;

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
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        if(month-3<0)   dateFrom = (year-1) +"-"+(12 + month-2)+"-01";
        else dateFrom = year + "-" + (month-2) + "-01";

        dateTo = year + "-" + (month+1) + "-" + day;

        System.out.println(dateFrom + " and " + dateTo);


        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new TabNotificationFrag(),"Thông báo ");
        adapter.addFragment(new BillFragment(dateFrom, dateTo), "Hóa đơn gần đây");
        viewPager.setAdapter(adapter);
    }

}


