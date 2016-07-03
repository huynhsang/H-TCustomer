package com.vnpt.hddtcustomer.views.fragment;

import android.annotation.SuppressLint;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.vnpt.hddtcustomer.R;
import com.vnpt.hddtcustomer.constants.InvRestURIConstants;
import com.vnpt.hddtcustomer.models.bill.Bill;
import com.vnpt.hddtcustomer.presenter.HDDTSharedPreference;
import com.vnpt.hddtcustomer.presenter.SearchBillPresenter;
import com.vnpt.hddtcustomer.presenter.db.GetDataArray;
import com.vnpt.hddtcustomer.presenter.db.JsonToBillList;
import com.vnpt.hddtcustomer.views.activity.CTHDActivity;
import com.vnpt.hddtcustomer.views.activity.MainActivity;
import com.vnpt.hddtcustomer.views.adapter.ListBillAdapter;

public class BillFragment extends Fragment implements AdapterView.OnItemClickListener {
    private View billView;
    private List<Bill> listBill;
    private ListView lvBill;
    private EditText etSearchBill;
    private TextView btnSearch, btnChart;
    private ListBillAdapter listBillAdapter;
    private SearchBillPresenter searchBillPresenter;
    private LinearLayout linearNavigator;
    private int currentUserId;
    private String type = "", dateFrom, dateTo ;
    private static final String KEY_PREFS = "CurrentUserID";

    public BillFragment(){
        type = "all";
    }

    @SuppressLint("ValidFragment")
    public BillFragment(String type){
        this.type = type;
    }

    @SuppressLint("ValidFragment")
    public BillFragment(String from, String to){
        this.dateFrom = from;
        this.dateTo = to;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listBill = new ArrayList<Bill>();
        currentUserId = Integer.parseInt(new HDDTSharedPreference(this.getActivity()).getValue(KEY_PREFS));
        String url = "";

        if(type.equals("all")){
            url = InvRestURIConstants.GET_INV_FOLLOW_OWNER + currentUserId;
        }else if(type.equals("")){
            url = InvRestURIConstants.GET_INV_FOLLOW_PERIOD + currentUserId +"/from=" + dateFrom + "&to=" +dateTo;
        }else{
            url = InvRestURIConstants.GET_INV_FOLLOW_TYPE + currentUserId +"/type=" + type;
        }
        listBill = new JsonToBillList(new GetDataArray(url).getJsonArray()).convertToBillList();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        billView = inflater.inflate(R.layout.frag_bill, container, false);
        lvBill = (ListView) billView.findViewById(R.id.lvBill);
        btnSearch = (TextView) billView.findViewById(R.id.btnSearch);
        btnChart = (TextView) billView.findViewById(R.id.btnChart);
        linearNavigator = (LinearLayout) billView.findViewById(R.id.navigatorBill);

        etSearchBill = ((MainActivity)getActivity()).getEtSearchBill();
        hideBtnChart();

        return billView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        searchBillPresenter = new SearchBillPresenter(billView, etSearchBill, listBill);
        searchBillPresenter.initializeSearch();
        setListViewAdapter();
        lvBill.setOnItemClickListener(this);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnChart.getVisibility() != View.VISIBLE){
                    btnChart.setVisibility(View.VISIBLE);
                }else{
                    btnChart.setVisibility(View.GONE);
                }
                ((MainActivity)getActivity()).toggleSearch();
            }
        });

        btnChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).toChart();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bill bill = (Bill) lvBill.getAdapter().getItem(position);

        Intent toDetail = new Intent(getActivity(), CTHDActivity.class);
        System.out.println("to detail");
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

    private void hideBtnChart(){
        if(type.equals("all")){
            btnChart.setVisibility(View.GONE);
        }
    }

    public void hideNavigatorBill(){
        linearNavigator.setVisibility(View.GONE);
    }

}
