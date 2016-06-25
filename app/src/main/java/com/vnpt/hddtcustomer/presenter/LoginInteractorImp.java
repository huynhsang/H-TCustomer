package com.vnpt.hddtcustomer.presenter;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.vnpt.hddtcustomer.database.TbCustomer;
import com.vnpt.hddtcustomer.interfaces.LoginInteractor;
import com.vnpt.hddtcustomer.interfaces.OnLoginFinishedListener;
import com.vnpt.hddtcustomer.models.User.Customer;

import java.util.List;


public class LoginInteractorImp implements LoginInteractor {

    private static final int LOGIN_TIMEOUT=1000;
    private List<Customer> _customerList;
    private int currentUserId;


    @Override
    public boolean checkUsernameError(String username) {

        if((TextUtils.isEmpty(username)) || (username.length() > 100)){
            return true;
        }
        for(int i=0; i<_customerList.size(); i++){
            if(username.equals(_customerList.get(i).get_email())){
                currentUserId = _customerList.get(i).get_id();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkPasswordError(String password) {
        if(TextUtils.isEmpty(password) || (password.length() > 15)){
            return true;
        }
        for(int i=0; i<_customerList.size(); i++){
            if(password.equals(_customerList.get(i).get_password())){
                return false;
            }
        }
        return true;
    }

    @Override
    public void checkLogin(final String username, final String password, final List<Customer> customerList, final OnLoginFinishedListener listener) {
        this._customerList = customerList;
        System.out.println(_customerList.size());
        System.out.println(_customerList.get(14).get_email());
        System.out.println(username);
        System.out.println(password);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean error = false;
                if(checkUsernameError(username)){
                    listener.onUsernameError();
                    error = true;
                }
                if(checkPasswordError(password)){
                    listener.onPasswordError();
                    error = true;
                }
                if(!error){
                    listener.onSuccess(currentUserId);
                }
            }
        }, LOGIN_TIMEOUT);
    }
}
