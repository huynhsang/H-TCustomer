package com.vnpt.hddtcustomer.presenter.db;


import com.vnpt.hddtcustomer.models.bill.Bill;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonToBillList {

    private JSONArray jsonArray;

    public JsonToBillList(JSONArray jsonArray){
        this.jsonArray = jsonArray;
    }

    public List<Bill> convertToBillList(){
        List<Bill> billList = new ArrayList<>();
        if(jsonArray == null) return null;
        for (int i = 0; i < jsonArray.length(); i++){
            try {
                JSONObject obj = jsonArray.getJSONObject(i);
                Bill bill = new Bill(obj.getInt("id"), obj.getString("Number"), obj.getString("Datetime"), obj.getInt("OwnerId"),
                        obj.getString("Price"), obj.getBoolean("Status"), obj.getString("Duedate"), obj.getInt("Csdk"), obj.getInt("Csck"), obj.getString("Type"));
                billList.add(bill);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return billList;
    }
}
