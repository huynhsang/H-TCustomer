package com.vnpt.hddtcustomer.views.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.vnpt.hddtcustomer.R;
import com.vnpt.hddtcustomer.models.bill.Bill;
import com.vnpt.hddtcustomer.views.adapter.ListBillAdapter;

public class BillFragment extends Fragment {
    private View billView;
    private List<Bill> listBill;
    private ListView lvBill;
    private ListBillAdapter listBillAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listBill = new ArrayList<Bill>();
        listBill.add(new Bill("1-10/2014", "11054", "11619", 455, 123456, "Da thanh toan", "Dien"));
        listBill.add(new Bill("1-9/2014", "11054", "11619", 455, 123456, "Da thanh toan", "Dien"));
        listBill.add(new Bill("1-08/2014", "11054", "11619", 455, 123456, "Da thanh toan", "Dien"));
        listBill.add(new Bill("1-07/2014", "11054", "11619", 455, 123456, "Da thanh toan", "Dien"));
        listBill.add(new Bill("1-06/2014", "11054", "11619", 455, 123456, "Da thanh toan", "Dien"));
        listBill.add(new Bill("1-05/2014", "11054", "11619", 455, 123456, "Da thanh toan", "Dien"));
        listBill.add(new Bill("1-04/2014", "11054", "11619", 455, 123456, "Da thanh toan", "Dien"));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        billView = inflater.inflate(R.layout.frag_bill, container, false);
        lvBill = (ListView) billView.findViewById(R.id.lvBill);

        return billView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListViewAdapter();
    }

    private void setListViewAdapter() {
        listBillAdapter = new ListBillAdapter(this.getActivity() , R.layout.detail_bill_item, listBill);
        lvBill.setAdapter(listBillAdapter);
    }
}
