package com.vnpt.hddtcustomer.models.bill;


import java.io.Serializable;

public class Bill implements Serializable {
    private String _number, _datetime, _dueDate, _price, _type;
    private int _id , _ownerID,  _csdk, _csck;
    private boolean _status;

    public Bill(int id, String number, String datetime, int ownerID, String price, boolean status, String dueDate,
                int csdk, int csck, String type){
        this._id = id;
        this._number = number;
        this._datetime = datetime;
        this._ownerID = ownerID;
        this._price = price;
        this._status = status;
        this._dueDate = dueDate;
        this._csdk = csdk;
        this._csck = csck;
        this._type = type;
    }

    public String get_number() {
        return _number;
    }

    public void set_number(String _number) {
        this._number = _number;
    }

    public String get_datetime() {
        return _datetime;
    }

    public void set_datetime(String _datetime) {
        this._datetime = _datetime;
    }

    public String get_dueDate() {
        return _dueDate;
    }

    public void set_dueDate(String _dueDate) {
        this._dueDate = _dueDate;
    }

    public int get_csdk() {
        return _csdk;
    }

    public void set_csdk(int _csdk) {
        this._csdk = _csdk;
    }

    public int get_csck() {
        return _csck;
    }

    public void set_csck(int _csck) {
        this._csck = _csck;
    }

    public boolean is_status() {
        return _status;
    }

    public void set_status(boolean _status) {
        this._status = _status;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_price() {
        return _price;
    }

    public void set_price(String _price) {
        this._price = _price;
    }

    public int get_sl() {
        return this._csck - this._csdk;
    }


    public int get_ownerID() {
        return _ownerID;
    }

    public void set_ownerID(int _ownerID) {
        this._ownerID = _ownerID;
    }

    public int get_month(){
        int pos1 = this.get_datetime().indexOf("-");
        int pos2 = this.get_datetime().lastIndexOf("-");
        return Integer.parseInt(this.get_datetime().substring(pos1+1, pos2));
    }

    public int get_year(){
        int pos = this.get_datetime().indexOf("-");
        return Integer.parseInt(this.get_datetime().substring(0,pos));
    }

}
