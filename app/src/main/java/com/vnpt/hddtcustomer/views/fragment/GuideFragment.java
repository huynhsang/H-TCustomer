package com.vnpt.hddtcustomer.views.fragment;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vnpt.hddtcustomer.R;

public class GuideFragment extends Fragment{
    private View guideView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        guideView = inflater.inflate(R.layout.frag_guide, container, false);
        return guideView;
    }
}
