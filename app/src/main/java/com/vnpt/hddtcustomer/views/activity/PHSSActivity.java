package com.vnpt.hddtcustomer.views.activity;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vnpt.hddtcustomer.R;

public class PHSSActivity extends AppCompatActivity {
    private TextView btnToLeftMenu, btnToPhanhoi, titleMainView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phss);

        Typeface fontAwesome = Typeface.createFromAsset(getAssets(),"fonts/fontawesome-webfont.ttf");
        btnToLeftMenu=(TextView) findViewById(R.id.phss_btntoLeftMenu);
        btnToLeftMenu.setTypeface(fontAwesome);
        btnToLeftMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnToPhanhoi=(TextView) findViewById(R.id.phss_btntoPhanHoi);
        btnToPhanhoi.setTypeface(fontAwesome);
        btnToPhanhoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        titleMainView=(TextView) findViewById(R.id.phss_titleMainView);
        titleMainView.setText("Phản hồi sai sót");
    }
}
