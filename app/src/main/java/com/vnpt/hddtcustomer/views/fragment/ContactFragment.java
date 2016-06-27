package com.vnpt.hddtcustomer.views.fragment;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vnpt.hddtcustomer.R;

public class ContactFragment extends Fragment {
    private View contactView;
    private TextView  btnToPhanhoi;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contactView = inflater.inflate(R.layout.frag_contact, container, false);

        ImageView img=(ImageView) contactView.findViewById(R.id.phss_bg);
        if(getResources().getDisplayMetrics().widthPixels>getResources().getDisplayMetrics().heightPixels){
        img.setVisibility(View.INVISIBLE);}
        else{img.setVisibility(View.VISIBLE);}

        btnToPhanhoi=(TextView) contactView.findViewById(R.id.phss_btntoPhanHoi);
        btnToPhanhoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_phss, (ViewGroup) contactView.findViewById(R.id.phss_toast_root));
                Toast myToast=new Toast(getContext().getApplicationContext());
                myToast.setDuration(Toast.LENGTH_LONG);
                myToast.setGravity(Gravity.CENTER,0,0);
                myToast.setView(layout);
                myToast.show();
            }
        });
        return contactView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
