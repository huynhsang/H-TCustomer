package com.vnpt.hddtcustomer.views.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.vnpt.hddtcustomer.R;
import com.vnpt.hddtcustomer.interfaces.LoginPresenter;
import com.vnpt.hddtcustomer.interfaces.LoginView;
import com.vnpt.hddtcustomer.presenter.LoginPresenterImp;


public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener{

    private ProgressDialog progressDialog;
    private EditText ETUsername, ETPassword;
    private Button btnToApp;
    private TextView TVForgetPW;
    private LoginPresenter loginPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Login...");
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        btnToApp = (Button) findViewById(R.id.btnToApp);
        btnToApp.setOnClickListener(this);

        ETUsername = (EditText) findViewById(R.id.ETUsername);
        ETPassword = (EditText) findViewById(R.id.ETPassword);

        TVForgetPW = (TextView) findViewById(R.id.TVForgetPW);
        TVForgetPW.setOnClickListener(this);

        loginPresenter = new LoginPresenterImp(this);
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void setPhoneNumberError() {
        ETUsername.setError(getString(R.string.phoneNumber_error));
    }

    @Override
    public void setPasswordError() {
        ETPassword.setError(getString(R.string.password_error));
    }

    @Override
    public void navigateToApp() {
        Intent toApp = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(toApp);
        hideProgress();
        finish();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id){
            case R.id.btnToApp:
                loginPresenter.validateCredentials(ETUsername.getText().toString(), ETPassword.getText().toString());
                break;
            case R.id.TVForgetPW:

                break;
        }
    }
}
