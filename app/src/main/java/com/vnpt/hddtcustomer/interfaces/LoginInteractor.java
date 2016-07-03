package com.vnpt.hddtcustomer.interfaces;


import com.vnpt.hddtcustomer.models.User.Customer;

import java.util.List;

public interface LoginInteractor {

    public void checkLogin(String username, String password, OnLoginFinishedListener listener);

}
