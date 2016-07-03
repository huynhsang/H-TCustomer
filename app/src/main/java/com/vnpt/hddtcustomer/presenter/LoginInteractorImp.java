package com.vnpt.hddtcustomer.presenter;

import android.os.Handler;
import android.text.TextUtils;

import com.vnpt.hddtcustomer.constants.CusRestURIConstants;
import com.vnpt.hddtcustomer.interfaces.LoginInteractor;
import com.vnpt.hddtcustomer.interfaces.OnLoginFinishedListener;
import com.vnpt.hddtcustomer.presenter.db.GetDataArray;
import com.vnpt.hddtcustomer.presenter.db.GetDataObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class LoginInteractorImp implements LoginInteractor {

    private static final int LOGIN_TIMEOUT=1000;
    private int currentUserId;

    @Override
    public void checkLogin(final String username, final String password, final OnLoginFinishedListener listener) {
        String path = CusRestURIConstants.DO_LOGIN + "username="+ username + "&password="+ password;
        GetDataObject requestData = new GetDataObject(path);
        final JSONObject jsonObject = requestData.getJsonObject();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean error = false;
                if((TextUtils.isEmpty(username)) || (username.length() > 100) || (jsonObject == null) ){
                    listener.onUsernameError();
                    error = true;
                }
                if((TextUtils.isEmpty(password)) || (password.length() > 15) || (jsonObject == null)){
                    listener.onPasswordError();
                    error = true;
                }
                if(!error){
                    try {
                        currentUserId = jsonObject.getInt("id");
                        listener.onSuccess(currentUserId);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, LOGIN_TIMEOUT);
    }

}
