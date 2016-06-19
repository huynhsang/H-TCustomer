package com.vnpt.hddtcustomer.views.activity;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.vnpt.hddtcustomer.R;

public class BillContentActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView btnToBack, btnToShare;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_content);

        Typeface fontAwesome = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");

        btnToBack = (TextView) findViewById(R.id.btnToBack);
        btnToBack.setTypeface(fontAwesome);
        btnToBack.setOnClickListener(this);
        btnToShare =(TextView) findViewById(R.id.btnToShare);
        btnToShare.setTypeface(fontAwesome);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnToBack:
                onBackPressed();
                break;
        }
    }
}
