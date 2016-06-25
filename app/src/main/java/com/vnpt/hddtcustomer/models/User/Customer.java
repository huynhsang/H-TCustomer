package com.vnpt.hddtcustomer.models.User;


public class Customer {
    private int _id;
    private String _name, _phone, _address, _email, _password, _cmnd;
    private Double _longtitude, _latitude;

    public Customer(int _id, String _name, String _phone, String _address, Double _longtitude, Double _latitude, String _cmnd, String _email, String _password) {
        this._id = _id;
        this._name = _name;
        this._phone = _phone;
        this._address = _address;
        this._longtitude = _longtitude;
        this._latitude = _latitude;
        this._cmnd = _cmnd;
        this._email = _email;
        this._password = _password;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_cmnd() {
        return _cmnd;
    }

    public void set_cmnd(String _cmnd) {
        this._cmnd = _cmnd;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_phone() {
        return _phone;
    }

    public void set_phone(String _phone) {
        this._phone = _phone;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public Double get_longtitude() {
        return _longtitude;
    }

    public void set_longtitude(Double _longtitude) {
        this._longtitude = _longtitude;
    }

    public Double get_latitude() {
        return _latitude;
    }

    public void set_latitude(Double _latitude) {
        this._latitude = _latitude;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }
}
