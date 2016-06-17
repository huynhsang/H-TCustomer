package com.vnpt.hddtcustomer.views.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.vnpt.hddtcustomer.R;


public class ForgotPassActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView btnForgotPassToLeft;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpass_activity);

        Typeface fontAwesome = Typeface.createFromAsset(getAssets(),"fonts/fontawesome-webfont.ttf");

        btnForgotPassToLeft = (TextView) findViewById(R.id.btnForgotPassToLeft);
        btnForgotPassToLeft.setTypeface(fontAwesome);
        btnForgotPassToLeft.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnForgotPassToLeft)
        {
            Intent toLogin = new Intent(ForgotPassActivity.this, LoginActivity.class);
            startActivity(toLogin);
            finish();
        }
    }
}
