package com.vnpt.hddtcustomer.presenter;


import com.vnpt.hddtcustomer.interfaces.LoginInteractor;
import com.vnpt.hddtcustomer.interfaces.LoginPresenter;
import com.vnpt.hddtcustomer.interfaces.LoginView;
import com.vnpt.hddtcustomer.interfaces.OnLoginFinishedListener;

public class LoginPresenterImp implements LoginPresenter, OnLoginFinishedListener {

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImp(LoginView loginView){
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImp();
    }

    @Override
    public void validateCredentials(String phoneNumber, String password) {
        loginView.showProgress();
        loginInteractor.checkLogin(phoneNumber, password, this);
    }

    @Override
    public void onPhoneNumberError() {
        loginView.setPhoneNumberError();
        loginView.hideProgress();
    }

    @Override
    public void onPasswordError() {
        loginView.setPasswordError();
        loginView.hideProgress();
    }

    @Override
    public void onSuccess() {
        loginView.navigateToApp();
    }
}
