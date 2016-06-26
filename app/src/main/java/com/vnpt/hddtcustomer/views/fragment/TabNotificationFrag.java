package com.vnpt.hddtcustomer.views.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vnpt.hddtcustomer.R;


public class TabNotificationFrag extends Fragment {

    private View tabNotify;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        tabNotify = inflater.inflate(R.layout.tab_notify_frag, container, false);
        return tabNotify;
    }
}
