package com.vnpt.hddtcustomer.views.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vnpt.hddtcustomer.R;

public class ConfigFragment extends Fragment {
    private View configView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        configView = inflater.inflate(R.layout.frag_config, container, false);
        return configView;
    }
}

