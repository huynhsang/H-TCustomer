package com.vnpt.hddtcustomer.views.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import android.util.Log;

import com.vnpt.hddtcustomer.R;
import com.vnpt.hddtcustomer.models.bill.Bill;

import java.util.Currency;
import java.util.List;
import java.util.Locale;


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

        holder.setTextView(bill.get_month(), bill.get_year(), bill.get_csdk(), bill.get_csck(), bill.get_sl(), bill.get_price(), bill.get_status(), bill.get_type());
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
        private TextView tvMonth, tvYear, tvCSDK, tvCSCK, tvSL, tvMoney, tvStatus;
        private CheckBox checkBox;

        public ViewHolder(View v){
            tvMonth = (TextView) v.findViewById(R.id.monthBill);
            tvYear = (TextView) v.findViewById(R.id.yearBill);
            tvCSDK = (TextView) v.findViewById(R.id.csdkBill);
            tvCSCK = (TextView) v.findViewById(R.id.csckBill);
            tvSL = (TextView) v.findViewById(R.id.slBill);
            tvMoney = (TextView) v.findViewById(R.id.moneyBill);
            tvStatus = (TextView) v.findViewById(R.id.statusBill);
            checkBox = (CheckBox) v.findViewById(R.id.checkbox);
        }

        public void setTextView(int monthBill, int yearBill, int csdkBill, int csckBill, int slBill, int moneyBill, String statusBill, String typeBill ){
            tvMonth.setText("T"+monthBill);
            tvYear.setText(""+yearBill);
            tvCSDK.setText(csdkBill + "");
            tvCSCK.setText(csckBill + "");
            if (typeBill.equals("Dien")) {
                tvSL.setText(slBill + " kWh");
            } else if (typeBill.equals("Nuoc")){
                tvSL.setText(slBill + " m\u00B3");
            } else if (typeBill.equals("Vienthong")){
                tvSL.setText("" + slBill);
            }

            DecimalFormat format = new DecimalFormat("#,###.00");
            format.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ITALY));
            double number = Double.parseDouble(""+moneyBill);
            tvMoney.setText(format.format(number) + "đ");

            if(statusBill.equals("0")){
                tvStatus.setText(R.string.lblStatus0);
                checkBox.setChecked(false);
            }else{
                tvStatus.setText(R.string.lblStatus1);
                checkBox.setChecked(true);
            }
        }

    }
}
