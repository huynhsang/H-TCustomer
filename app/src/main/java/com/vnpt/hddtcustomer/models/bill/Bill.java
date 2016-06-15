package com.vnpt.hddtcustomer.models.bill;


public class Bill {
    private String _period, _csdk, _csck, _status, _type;
    private int _money, _sl;

    public Bill(String period, String csdk, String csck, int sl, int money, String status, String type){
        this._period = period;
        this._csdk = csdk;
        this._csck = csck;
        this._sl = sl;
        this._money = money;
        this._status = status;
        this._type = type;
    }

    public void set_period(String period){
        this._period = period;
    }
    public void set_csdk(String csdk){
        this._csdk = csdk;
    }
    public void set_csck(String csck){
        this._csck = csck;
    }
    public void set_status(String status){
        this._status = status;
    }
    public void set_type(String _type) {
        this._type = _type;
    }
    public void set_sl(int sl){
        this._sl = sl;
    }
    public void set_money(int money){
        this._money = money;
    }

    public String get_period(){
        return this._period;
    }
    public String get_csdk(){
        return this._csdk;
    }
    public String get_csck(){
        return this._csck;
    }
    public String get_status(){
        return this._status;
    }
    public String get_type() {
        return _type;
    }
    public int get_sl(){
        return this._sl;
    }
    public int get_money(){
        return this._money;
    }

}
