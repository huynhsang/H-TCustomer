package com.vnpt.hddtcustomer.views.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.vnpt.hddtcustomer.R;
import com.vnpt.hddtcustomer.models.bill.Bill;
import com.vnpt.hddtcustomer.presenter.SearchBillPresenter;
import com.vnpt.hddtcustomer.views.activity.CTHDActivity;
import com.vnpt.hddtcustomer.views.activity.MainActivity;
import com.vnpt.hddtcustomer.views.adapter.ListBillAdapter;

public class BillFragment extends Fragment implements AdapterView.OnItemClickListener {
    private View billView;
    private List<Bill> listBill;
    private ListView lvBill;
    private EditText etSearchBill;
    private ListBillAdapter listBillAdapter;
    private SearchBillPresenter searchBillPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listBill = new ArrayList<Bill>();
        listBill.add(new Bill("1-10/2014", "11054", "11619", 455, 123456, "Da thanh toan", "Dien"));
        listBill.add(new Bill("1-9/2014", "10600", "11054", 454, 123456, "Da thanh toan", "Dien"));
        listBill.add(new Bill("1-08/2014", "10189", "10600", 411, 123456, "Da thanh toan", "Dien"));
        listBill.add(new Bill("1-07/2014", "9721", "10189", 468, 123456, "Da thanh toan", "Dien"));
        listBill.add(new Bill("1-06/2014", "9368", "10189", 468, 123456, "Da thanh toan", "Dien"));
        listBill.add(new Bill("1-05/2014", "9368", "10189", 413, 123456, "Da thanh toan", "Dien"));
        listBill.add(new Bill("1-04/2014", "9368", "10189", 413, 123456, "Da thanh toan", "Dien"));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        billView = inflater.inflate(R.layout.frag_bill, container, false);
        lvBill = (ListView) billView.findViewById(R.id.lvBill);
        etSearchBill = ((MainActivity)getActivity()).getEtSearchBill();

        return billView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        searchBillPresenter = new SearchBillPresenter(billView, etSearchBill, listBill);
        searchBillPresenter.initializeSearch();
        setListViewAdapter();
        lvBill.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bill bill = (Bill) lvBill.getAdapter().getItem(position);

        Intent toDetail = new Intent(getActivity(), CTHDActivity.class);
        toDetail.putExtra("billDetail", bill);
        startActivity(toDetail);
    }

    public List<Bill> getListBill(){
        return listBill;
    }

    private void setListViewAdapter() {
        listBillAdapter = new ListBillAdapter(this.getActivity() , R.layout.detail_bill_item, listBill);
        lvBill.setAdapter(listBillAdapter);
    }


}
