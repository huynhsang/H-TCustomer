package com.vnpt.hddtcustomer.interfaces;


public interface OnLoginFinishedListener {

    public void onUsernameError();

    public void onPasswordError();

    public void onSuccess(int id);
}
