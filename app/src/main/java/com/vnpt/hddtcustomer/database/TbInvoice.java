package com.vnpt.hddtcustomer.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.vnpt.hddtcustomer.models.bill.Bill;

import java.util.ArrayList;
import java.util.List;

public class TbInvoice extends DatabaseHelper{

    private static final String TB_NAME = "INVOICE";

    public TbInvoice(Context context) {
        super(context);
    }

    public Bill getBillById(int id){
        Bill bill = null;
        Cursor cursor = this.selectQuery(TB_NAME, id);
        bill = new Bill(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5),
                cursor.getString(6), cursor.getInt(7), cursor.getInt(8), cursor.getString(9));
        cursor.close();
        return bill;
    }

    public List<Bill> getBillFollowOwner(int ownerID){
        Bill bill = null;
        List<Bill> billListOwner = new ArrayList<>();
        String whereOwner = "IdCustomer=" + ownerID;
        Cursor cursor = this.QUERY_SELECT_OWNER(TB_NAME, whereOwner);
        while (!cursor.isAfterLast()){
            bill = new Bill(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5),
                    cursor.getString(6), cursor.getInt(7), cursor.getInt(8), cursor.getString(9));
            billListOwner.add(bill);
            cursor.moveToNext();
        }
        cursor.close();
        return billListOwner;
    }

    public List<Bill> getAllBill(){
        Bill bill = null;
        List<Bill> billList = new ArrayList<>();
        Cursor cursor = this.QUERY_SELECT_ALL(TB_NAME);
        while (!cursor.isAfterLast()){
            bill = new Bill(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5),
                    cursor.getString(6), cursor.getInt(7), cursor.getInt(8), cursor.getString(9));
            billList.add(bill);
            cursor.moveToNext();
        }
        cursor.close();
        return billList;
    }

    public long UpdateBill(Bill bill){
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", bill.get_id());
        contentValues.put("NUMBER", bill.get_number());
        contentValues.put("Datetime", bill.get_datetime());
        contentValues.put("IdCustomer", bill.get_ownerID());
        contentValues.put("Price", bill.get_price());
        contentValues.put("Status", bill.get_status());
        contentValues.put("DueDate", bill.get_dueDate());
        contentValues.put("Csdk", bill.get_csdk());
        contentValues.put("Csck", bill.get_csck());
        contentValues.put("Type", bill.get_type());
        String[] whereArgs = {Integer.toString(bill.get_id())};
        String whereClause = "ID=?";
        long returnValue = this.UPDATE_TABLE(TB_NAME, contentValues, whereClause, whereArgs);
        return returnValue;
    }

    public long addBill(Bill bill){
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", bill.get_id());
        contentValues.put("NUMBER", bill.get_number());
        contentValues.put("Datetime", bill.get_datetime());
        contentValues.put("IdCustomer", bill.get_ownerID());
        contentValues.put("Price", bill.get_price());
        contentValues.put("Status", bill.get_status());
        contentValues.put("DueDate", bill.get_dueDate());
        contentValues.put("Csdk", bill.get_csdk());
        contentValues.put("Csck", bill.get_csck());
        contentValues.put("Type", bill.get_type());
        long returnValue = this.INSERT_TABLE(TB_NAME, contentValues);
        return returnValue;
    }

    public boolean deleteBillById(int id){
        String whereClause = "ID=?";
        String[] whereArgs = {Integer.toString(id)};
        return this.DELETE_TABLE(TB_NAME, whereClause, whereArgs);
    }

}
