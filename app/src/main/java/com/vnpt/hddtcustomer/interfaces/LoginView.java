package com.vnpt.hddtcustomer.interfaces;


public interface LoginView {

    public void showProgress();

    public void hideProgress();

    public void setPhoneNumberError();

    public void setPasswordError();

    public void navigateToApp();
}
