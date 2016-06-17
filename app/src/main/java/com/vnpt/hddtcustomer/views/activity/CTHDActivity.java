package com.vnpt.hddtcustomer.views.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.vnpt.hddtcustomer.R;

public class CTHDActivity extends AppCompatActivity {
    private TextView btnToback, btnToRefresh, titleMainView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cthd);

        Typeface fontAwesome = Typeface.createFromAsset(getAssets(),"fonts/fontawesome-webfont.ttf");

        btnToback = (TextView) findViewById(R.id.cthd_btntoBack);
        btnToback.setTypeface(fontAwesome);
        btnToback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnToRefresh = (TextView) findViewById(R.id.cthd_btntoRefresh);
        btnToRefresh.setTypeface(fontAwesome);
        btnToback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        titleMainView=(TextView) findViewById(R.id.cthd_titleMainView);
        titleMainView.setText("Chi tiết");
    }
}
