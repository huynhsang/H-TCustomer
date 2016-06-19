package com.vnpt.hddtcustomer.views.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vnpt.hddtcustomer.R;
import com.vnpt.hddtcustomer.views.activity.ChargesDetailActivity;


public class NotifyFragment extends Fragment {

    private View notifyView;
    private TextView btnToChargesDetail;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        notifyView = inflater.inflate(R.layout.frag_notify, container, false);
        btnToChargesDetail = (TextView) notifyView.findViewById(R.id.btnToChargesDetail);
        btnToChargesDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toDetail = new Intent(getActivity(), ChargesDetailActivity.class);
                startActivity(toDetail);
                getActivity().finish();
            }
        });
        return notifyView;
    }
}
