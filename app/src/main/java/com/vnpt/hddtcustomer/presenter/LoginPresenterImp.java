package com.vnpt.hddtcustomer.presenter;


import android.content.Context;

import com.vnpt.hddtcustomer.interfaces.LoginInteractor;
import com.vnpt.hddtcustomer.interfaces.LoginPresenter;
import com.vnpt.hddtcustomer.interfaces.LoginView;
import com.vnpt.hddtcustomer.interfaces.OnLoginFinishedListener;

public class LoginPresenterImp implements LoginPresenter, OnLoginFinishedListener {

    private LoginView loginView;
    private LoginInteractor loginInteractor;
    private Context _context;

    public LoginPresenterImp(Context context){
        this.loginView = (LoginView) context;
        this._context = context;
        this.loginInteractor = new LoginInteractorImp();
    }

    @Override
    public void validateCredentials(String username, String password) {
        loginView.showProgress();
        loginInteractor.checkLogin(username, password, this);
    }

    @Override
    public void onUsernameError() {
        loginView.setPhoneNumberError();
        loginView.hideProgress();
    }

    @Override
    public void onPasswordError() {
        loginView.setPasswordError();
        loginView.hideProgress();
    }

    @Override
    public void onSuccess(int id) {
        saveUserId(id);
        loginView.navigateToApp();
    }

    private void saveUserId(int  id){
        new HDDTSharedPreference(_context).save("CurrentUserID", Integer.toString(id));
    }
}
