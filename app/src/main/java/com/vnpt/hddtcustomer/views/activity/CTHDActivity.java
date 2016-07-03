package com.vnpt.hddtcustomer.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import com.vnpt.hddtcustomer.R;
import com.vnpt.hddtcustomer.models.bill.Bill;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class CTHDActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView btnToback, btnToRefresh, btnToMoreDetail, titleMainView, cthd_status, cthd_interval, cthd_csdk, cthd_csck, cthd_money, cthd_timepayV, cthd_hdtd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cthd);


        btnToback = (TextView) findViewById(R.id.cthd_btntoBack);
        btnToback.setOnClickListener(this);
        btnToRefresh = (TextView) findViewById(R.id.cthd_btntoRefresh);
        btnToMoreDetail = (TextView) findViewById(R.id.cthd_btntoMoreDetail);
        btnToMoreDetail.setOnClickListener(this);
        titleMainView=(TextView) findViewById(R.id.cthd_titleMainView);
        titleMainView.setText(R.string.lblBillDetail);

        cthd_hdtd = (TextView) findViewById(R.id.cthd_hdtd);
        cthd_timepayV = (TextView) findViewById(R.id.cthd_timepayV);
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
                break;
            case R.id.cthd_btntoRefresh:
                break;
        }
    }

    private void setDataDetail(){
        Bill bill = (Bill)getIntent().getSerializableExtra("billDetail");
        if(bill.is_status()){
            cthd_status.setText(R.string.lblStatus1);
        }else{
            cthd_status.setText(R.string.lblStatus0);
        }
        if(bill.get_type().equals("Dien")) cthd_hdtd.setText(R.string.cthd_hdtd_dien);
        else if(bill.get_type().equals("Nuoc")) cthd_hdtd.setText(R.string.cthd_hdtd_nuoc);
        else if(bill.get_type().equals("Vienthong")) cthd_hdtd.setText(R.string.cthd_hdtd_vienthong);

        cthd_timepayV.setText(bill.get_dueDate());

        cthd_interval.setText(new StringBuilder().append("01/").append(bill.get_month()).append("/")
                .append(bill.get_year()).append(" - 30/").append(bill.get_month()).append("/").append(bill.get_year()));
        cthd_csdk.setText(bill.get_csdk() + "");
        cthd_csck.setText(bill.get_csck() + "");

        DecimalFormat format = new DecimalFormat("#,###.00");
        format.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ITALY));
        double number = Double.parseDouble(""+bill.get_price());
        cthd_money.setText(format.format(number)+"");
    }

}
