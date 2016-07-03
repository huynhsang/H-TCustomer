package com.vnpt.hddtcustomer.views.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.vnpt.hddtcustomer.R;
import com.vnpt.hddtcustomer.presenter.SearchBillPresenter;

import java.util.Calendar;

public class LookupFragment extends Fragment implements View.OnClickListener{
    private View lookupView;
    private Spinner billTypeSpinner;
    private TextView  btnBillTimeFrom, btnBillTimeTo;
    private EditText etBillTimeFrom, etBillTimeTo, etBillNumber;
    private Typeface fontAwesome;
    private Button btnToLookup;
    private LinearLayout navigatorBill, lup_dateTo, lup_dateFrom, lup_billNumber;
    private BillFragment childFragment;
    private SearchBillPresenter searchBillPresenter;
    private static final int DATE_PICKER_ID = 1111;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fontAwesome = Typeface.createFromAsset(getActivity().getAssets(),"fonts/fontawesome-webfont.ttf");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        lookupView = inflater.inflate(R.layout.frag_lookup, container, false);

        lup_dateTo = (LinearLayout) lookupView.findViewById(R.id.lup_dateTo);
        lup_dateFrom = (LinearLayout) lookupView.findViewById(R.id.lup_dateFrom);
        lup_billNumber = (LinearLayout) lookupView.findViewById(R.id.lup_billNumber);

        billTypeSpinner = (Spinner) lookupView.findViewById(R.id.billTypeSpinner);
        etBillTimeFrom = (EditText) lookupView.findViewById(R.id.etBillTime_from);
        etBillTimeTo = (EditText) lookupView.findViewById(R.id.etBillTime_to);
        etBillNumber = (EditText) lookupView.findViewById(R.id.etBillNumber);

        btnToLookup = (Button) lookupView.findViewById(R.id.btnToLookup);
        btnToLookup.setOnClickListener(this);
        btnBillTimeFrom = (TextView) lookupView.findViewById(R.id.btnBillTime_from);
        btnBillTimeFrom.setOnClickListener(this);
        btnBillTimeTo = (TextView) lookupView.findViewById(R.id.btnBillTime_to);
        btnBillTimeTo.setOnClickListener(this);

        choiceTypeListChildFragment("all");

        return lookupView;
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        billTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch ((int) id){
                    case 0:
                        lup_dateTo.setVisibility(View.GONE);
                        lup_dateFrom.setVisibility(View.GONE);
                        lup_billNumber.setVisibility(View.VISIBLE);
                        choiceTypeListChildFragment("all");
                        break;

                    default:
                        lup_dateTo.setVisibility(View.VISIBLE);
                        lup_dateFrom.setVisibility(View.VISIBLE);
                        lup_billNumber.setVisibility(View.GONE);
                        choiceTypeListChildFragment(parent.getItemAtPosition(position).toString());
                        break;
                }
                childFragment = (BillFragment)getChildFragmentManager().findFragmentById(R.id.fragContentList);
                childFragment.hideNavigatorBill();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        BillFragment billFragment = (BillFragment)getChildFragmentManager().findFragmentById(R.id.fragContentList);
        View view = billFragment.getView();
        navigatorBill = (LinearLayout) view.findViewById(R.id.navigatorBill);
        navigatorBill.setVisibility(View.GONE);
        searchBillPresenter = new SearchBillPresenter(billFragment.getView(), etBillNumber, billFragment.getListBill());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnBillTime_from:
                DialogFragment dateDialogFragFrom = new SelectDateFragment("from");
                dateDialogFragFrom.show(getFragmentManager(), "DatePickerFrom");
                break;
            case R.id.btnBillTime_to:
                DialogFragment dateDialogFragTo = new SelectDateFragment("to");
                dateDialogFragTo.show(getFragmentManager(), "DatePickerTo");
                break;
            case R.id.btnToLookup:
                if(lup_billNumber.getVisibility() == View.VISIBLE){
                    searchBillPresenter.intializeClickToSearch(etBillNumber.getText().toString());
                }else{
                    FragmentManager fragmentManager = getChildFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    BillFragment billFragment = new BillFragment(etBillTimeFrom.getText().toString(), etBillTimeTo.getText().toString());
                    fragmentTransaction.replace(R.id.fragContentList, billFragment).commit();
                }

                break;
        }
    }

    @SuppressLint("ValidFragment")
    private class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        private String type;

        public SelectDateFragment(String type){
            this.type = type;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            if(type.equals("from")){
                etBillTimeFrom.setText(new StringBuilder().append(year)
                        .append("-").append(monthOfYear + 1).append("-").append(dayOfMonth));
            }else if(type.equals("to")){
                etBillTimeTo.setText(new StringBuilder().append(year)
                        .append("-").append(monthOfYear + 1).append("-").append(dayOfMonth));
            }

        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }
    }

    private void choiceTypeListChildFragment(String type){
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BillFragment billFragment = null;
        switch (type){
            case "Điện":
                billFragment = new BillFragment("Dien");
                fragmentTransaction.replace(R.id.fragContentList, billFragment).commit();
                break;
            case "Nước":
                billFragment = new BillFragment("Nuoc");
                fragmentTransaction.replace(R.id.fragContentList, billFragment).commit();
                break;
            case "Viễn thông":
                billFragment = new BillFragment("Vienthong");
                fragmentTransaction.replace(R.id.fragContentList, billFragment).commit();
                break;
            default:
                billFragment = new BillFragment();
                fragmentTransaction.replace(R.id.fragContentList, billFragment).commit();
                break;
        }
    }
}

