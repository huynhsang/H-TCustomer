package com.vnpt.hddtcustomer.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.vnpt.hddtcustomer.models.User.Customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TbCustomer extends DatabaseHelper{

    private static final String TB_NAME = "CUSTOMER";

    public TbCustomer(Context context) {
        super(context);
    }

    public Customer getCustomerById(int id){
        Customer customer = null;
        Cursor cursor = this.selectQuery(TB_NAME, id);
        customer = new Customer(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getDouble(4),
                cursor.getDouble(5), cursor.getString(6), cursor.getString(7), cursor.getString(8));
        cursor.close();
        return customer;
    }

    public List<Customer> getAllCustomer(){
        Customer customer = null;
        List<Customer> customerList = new ArrayList<>();
        Cursor cursor = this.QUERY_SELECT_ALL(TB_NAME);
        while (!cursor.isAfterLast()){
            customer = new Customer(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getDouble(4),
                    cursor.getDouble(5), cursor.getString(6), cursor.getString(7), cursor.getString(8));
            customerList.add(customer);
            cursor.moveToNext();
        }
        cursor.close();
        return customerList;
    }

    public long updateCustomer(Customer customer){
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", customer.get_name());
        contentValues.put("Phone", customer.get_phone());
        contentValues.put("Address", customer.get_address());
        contentValues.put("Longitude", customer.get_longtitude());
        contentValues.put("Latitude", customer.get_latitude());
        contentValues.put("CMND", customer.get_cmnd());
        contentValues.put("Email", customer.get_email());
        contentValues.put("Password", customer.get_password());
        String[] whereArgs = {Integer.toString(customer.get_id())};
        String whereClause = "ID=?";
        long returnValue = this.UPDATE_TABLE(TB_NAME, contentValues, whereClause, whereArgs);
        return returnValue;
    }

    public long addCustomer(Customer customer){
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", customer.get_id());
        contentValues.put("Name", customer.get_name());
        contentValues.put("Phone", customer.get_phone());
        contentValues.put("Address", customer.get_address());
        contentValues.put("Longitude", customer.get_longtitude());
        contentValues.put("Latitude", customer.get_latitude());
        contentValues.put("CMND", customer.get_cmnd());
        contentValues.put("Email", customer.get_email());
        contentValues.put("Password", customer.get_password());
        long returnValue = this.INSERT_TABLE(TB_NAME, contentValues);
        return returnValue;
    }


    public boolean deleteCustomerById(int id){
        String whereClause = "ID=?";
        String[] whereArgs = {String.valueOf(id)};
        return this.DELETE_TABLE(TB_NAME, whereClause, whereArgs);
    }
}
