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
import com.vnpt.hddtcustomer.database.TbInvoice;
import com.vnpt.hddtcustomer.models.bill.Bill;
import com.vnpt.hddtcustomer.presenter.HDDTSharedPreference;
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
    private int currentUserId;
    private static final String KEY_PREFS = "CurrentUserID";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listBill = new ArrayList<Bill>();
        TbInvoice tbInvoice = new TbInvoice(this.getActivity());
        currentUserId = Integer.parseInt(new HDDTSharedPreference(this.getActivity()).getValue(KEY_PREFS));
        listBill = tbInvoice.getBillFollowOwner(currentUserId);
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
