package com.vnpt.hddtcustomer.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.vnpt.hddtcustomer.R;
import com.vnpt.hddtcustomer.models.bill.Bill;

public class CTHDActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView btnToback, btnToRefresh, btnToMoreDetail, titleMainView, cthd_status, cthd_interval, cthd_csdk, cthd_csck, cthd_money;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cthd);

        Typeface fontAwesome = Typeface.createFromAsset(getAssets(),"fonts/fontawesome-webfont.ttf");

        btnToback = (TextView) findViewById(R.id.cthd_btntoBack);
        btnToback.setTypeface(fontAwesome);
        btnToback.setOnClickListener(this);
        btnToRefresh = (TextView) findViewById(R.id.cthd_btntoRefresh);
        btnToRefresh.setTypeface(fontAwesome);
        btnToMoreDetail = (TextView) findViewById(R.id.cthd_btntoMoreDetail);
        btnToMoreDetail.setTypeface(fontAwesome);
        btnToMoreDetail.setOnClickListener(this);
        btnToback.setOnClickListener(this);
        titleMainView=(TextView) findViewById(R.id.cthd_titleMainView);
        titleMainView.setText(R.string.lblBillDetail);

        cthd_status = (TextView) findViewById(R.id.cthd_status);
        cthd_interval = (TextView) findViewById(R.id.cthd_interval);
        cthd_csdk = (TextView) findViewById(R.id.cthd_csdk);
        cthd_csck = (TextView) findViewById(R.id.cthd_csck);
        cthd_money = (TextView) findViewById(R.id.cthd_money);

        setDataDetail();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.cthd_btntoBack:
                onBackPressed();
                break;
            case R.id.cthd_btntoMoreDetail:
                Intent toMoreDetail = new Intent(CTHDActivity.this, BillContentActivity.class);
                startActivity(toMoreDetail);
                finish();
                break;
        }
    }

    private void setDataDetail(){
        Bill bill = (Bill)getIntent().getSerializableExtra("billDetail");
        /*DataDetail dataDetail = new DataDetail(bill);*/
        if(bill.get_status().equals("0")){
            cthd_status.setText(R.string.lblStatus0);
        }else{
            cthd_status.setText(R.string.lblStatus1);
        }
        //cthd_status.setText(bill.get_status());
        cthd_interval.setText(new StringBuilder().append("01/").append(bill.get_month()).append("/")
                .append(bill.get_year()).append(" - 30/").append(bill.get_month()).append("/").append(bill.get_year()));
        cthd_csdk.setText(bill.get_csdk() + "");
        cthd_csck.setText(bill.get_csck() + "");
        cthd_money.setText(bill.get_price() + "");
    }

}
