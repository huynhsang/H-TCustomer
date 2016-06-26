package com.vnpt.hddtcustomer.views.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vnpt.hddtcustomer.R;

public class TabRecentBillFrag extends Fragment{

    private View tabRecentBill;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        tabRecentBill = inflater.inflate(R.layout.tab_recent_bill_frag, container, false);
        return tabRecentBill;
    }
}
