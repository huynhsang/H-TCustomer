package com.vnpt.hddtcustomer.presenter;

import android.os.Handler;
import android.text.TextUtils;

import com.vnpt.hddtcustomer.interfaces.LoginInteractor;
import com.vnpt.hddtcustomer.interfaces.OnLoginFinishedListener;


public class LoginInteractorImp implements LoginInteractor {

    private static final int LOGIN_TIMEOUT=3000;

    @Override
    public void checkLogin(final String phoneNumber, final String password, final OnLoginFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean error = false;
                if((TextUtils.isEmpty(phoneNumber)) || (phoneNumber.length() > 11)){
                    listener.onPhoneNumberError();
                    error = true;
                }
                if(TextUtils.isEmpty(password) || (password.length() > 10)){
                    listener.onPasswordError();
                    error = true;
                }
                if(!error){
                    listener.onSuccess();
                }
            }
        }, LOGIN_TIMEOUT);
    }
}
