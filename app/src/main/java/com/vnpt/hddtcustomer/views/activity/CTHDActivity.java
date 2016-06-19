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
        titleMainView.setText("Chi tiết");

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
        DataDetail dataDetail = new DataDetail(bill);
        cthd_status.setText(dataDetail.get_status());
        cthd_interval.setText(new StringBuilder().append("01/").append(dataDetail.get_month()).append("/")
                .append(dataDetail.get_year()).append(" - 30/").append(dataDetail.get_month()).append("/").append(dataDetail.get_year()));
        cthd_csdk.setText(dataDetail.get_csdk() + "");
        cthd_csck.setText(dataDetail.get_csck() + "");
        cthd_money.setText(dataDetail.get_money() + "");
    }

    private class DataDetail{
        private int _month, _year, _csdk, _csck, _money;
        private Bill _bill;
        private final int pos1, pos2;
        private String _status;

        public DataDetail(Bill bill){
            this._bill = bill;
            pos1 = bill.get_period().indexOf("-");
            pos2 = bill.get_period().indexOf("/") + 1;
        }

        public int get_month() {
            _month = Integer.parseInt(_bill.get_period().substring(pos1+1, pos2-1));
            return _month;
        }

        public int get_year() {
            _year = Integer.parseInt(_bill.get_period().substring(pos2));
            return _year;
        }

        public int get_csdk() {
            _csdk = Integer.parseInt(_bill.get_csdk());
            return _csdk;
        }

        public int get_csck() {
            _csck = Integer.parseInt(_bill.get_csck());
            return _csck;
        }

        public int get_money() {
            _money = _bill.get_money();
            return _money;
        }

        public String get_status() {
            _status = _bill.get_status();
            return _status;
        }
    }


}
