package com.vnpt.hddtcustomer.interfaces;


public interface LoginInteractor {

    public void checkLogin(String phoneNumber, String password, OnLoginFinishedListener listener);

}
