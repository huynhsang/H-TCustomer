package com.vnpt.hddtcustomer.presenter;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.vnpt.hddtcustomer.R;
import com.vnpt.hddtcustomer.models.bill.Bill;
import com.vnpt.hddtcustomer.views.adapter.ListBillAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchBillPresenter {

    private Context context;
    private View view;
    private List<Bill> billList;
    private EditText etSearch;
    private ListView lvBill ;
    private ListBillAdapter listBillAdapter;
    private Bill []arrayBill;

    public SearchBillPresenter(View view, EditText editText, List<Bill> billList){
        this.view = view;
        this.etSearch = editText;
        this.billList = billList;
        this.arrayBill = new Bill[billList.size()];
        billList.toArray(this.arrayBill);
        initList();
    }

    public void initializeSearch(){
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                initList();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    billList = new ArrayList<Bill>(Arrays.asList(arrayBill));
                    initList();
                }else {
                    searchItem(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void intializeClickToSearch(String text){
        if(text.equals("")){
            billList = new ArrayList<Bill>(Arrays.asList(arrayBill));
            initList();
        }else{
            searchItem(text.toString());
        }
    }

    private void initList(){
        listBillAdapter = new ListBillAdapter(view.getContext() , R.layout.detail_bill_item, billList);
        lvBill = (ListView) view.findViewById(R.id.lvBill);
        lvBill.setAdapter(listBillAdapter);
    }

    private void searchItem(String text){

        for(Bill item : arrayBill){
            if(!item.get_datetime().contains(text)){
                billList.remove(item);
            }
        }
        listBillAdapter.notifyDataSetChanged();
    }
}
