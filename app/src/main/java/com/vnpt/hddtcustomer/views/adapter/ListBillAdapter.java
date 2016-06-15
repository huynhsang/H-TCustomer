package com.vnpt.hddtcustomer.views.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import android.util.Log;

import com.vnpt.hddtcustomer.R;
import com.vnpt.hddtcustomer.models.bill.Bill;

import java.util.List;


public class ListBillAdapter extends ArrayAdapter<Bill>{

    private Context _context;
    private int _layoutID;
    private List<Bill> _listBill;

    public ListBillAdapter(Context context, int resource, List<Bill> listBill) {
        super(context, resource, listBill);
        this._context = context;
        this._layoutID = resource;
        this._listBill = listBill;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Get the data item for this position
        ViewHolder holder = null;
        Bill bill = getItem(position);
        //check if an existing view is being reused, otherwise inflate the view
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(_layoutID, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Typeface fontAwesome = Typeface.createFromAsset(_context.getAssets(),"fonts/fontawesome-webfont.ttf");
        TextView tvIcon_clock = (TextView) convertView.findViewById(R.id.tvIcon_clock);
        TextView tvIcon_dollar = (TextView) convertView.findViewById(R.id.tvIcon_dollar);
        TextView tvIcon_check_circle = (TextView) convertView.findViewById(R.id.tvIcon_check_circle);
        tvIcon_clock.setTypeface(fontAwesome);
        tvIcon_dollar.setTypeface(fontAwesome);
        tvIcon_check_circle.setTypeface(fontAwesome);

        holder.setTextView(bill.get_period(), bill.get_csdk(), bill.get_csck(), bill.get_sl(), bill.get_money(), bill.get_status());
        return convertView;
    }

    @Override
    public int getCount() {
        return _listBill.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Bill getItem(int position) {
        return _listBill.get(position);
    }

    private class ViewHolder{
        private TextView tvPeriod, tvCSDK, tvCSCK, tvSL, tvMoney, tvStatus;

        public ViewHolder(View v){
            tvPeriod = (TextView) v.findViewById(R.id.periodBill);
            tvCSDK = (TextView) v.findViewById(R.id.csdkBill);
            tvCSCK = (TextView) v.findViewById(R.id.csckBill);
            tvSL = (TextView) v.findViewById(R.id.slBill);
            tvMoney = (TextView) v.findViewById(R.id.moneyBill);
            tvStatus = (TextView) v.findViewById(R.id.statusBill);
        }

        public void setTextView(String periodBill, String csdkBill, String csckBill, int slBill, int moneyBill, String statusBill ){
            tvPeriod.setText(periodBill);
            tvCSDK.setText(csdkBill);
            tvCSCK.setText(csckBill);
            tvSL.setText(slBill + " KWh");
            tvMoney.setText(moneyBill + " d");
            tvStatus.setText(statusBill);
        }

    }
}
