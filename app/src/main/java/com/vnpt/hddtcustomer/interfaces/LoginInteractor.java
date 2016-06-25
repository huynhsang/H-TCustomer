package com.vnpt.hddtcustomer.interfaces;


import com.vnpt.hddtcustomer.models.User.Customer;

import java.util.List;

public interface LoginInteractor {

    public boolean checkUsernameError(String username);

    public boolean checkPasswordError(String password);

    public void checkLogin(String username, String password, List<Customer> customerList, OnLoginFinishedListener listener);

}
