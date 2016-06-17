package com.vnpt.hddtcustomer.views.activity;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vnpt.hddtcustomer.R;

public class CuocActivity extends AppCompatActivity {
    private TextView btnToLeftMenu, btnToNext, titleMainView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuoc);

        Typeface fontAwesome = Typeface.createFromAsset(getAssets(),"fonts/fontawesome-webfont.ttf");

        titleMainView=(TextView) findViewById(R.id.cuoc_titleMainView);
        titleMainView.setText("Thông báo cước");

        btnToLeftMenu=(TextView) findViewById(R.id.cuoc_btntoLeftMenu);
        btnToLeftMenu.setTypeface(fontAwesome);
        btnToLeftMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnToNext=(TextView)findViewById(R.id.cuoc_btntoNext);
        btnToNext.setTypeface(fontAwesome);
        btnToNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
