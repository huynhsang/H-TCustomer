package com.vnpt.hddtcustomer.views.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vnpt.hddtcustomer.R;
import com.vnpt.hddtcustomer.presenter.SearchBillPresenter;

import java.util.Calendar;

public class LookupFragment extends Fragment implements View.OnClickListener{
    private View lookupView;
    private Spinner billTypeSpinner;
    private TextView btnBillTime;
    private EditText etBillTime;
    private Typeface fontAwesome;
    private Button btnToLookup;
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
        billTypeSpinner = (Spinner) lookupView.findViewById(R.id.billTypeSpinner);
        etBillTime = (EditText) lookupView.findViewById(R.id.etBillTime);
        btnToLookup = (Button) lookupView.findViewById(R.id.btnToLookup);
        btnToLookup.setOnClickListener(this);
        btnBillTime = (TextView) lookupView.findViewById(R.id.btnBillTime);
        btnBillTime.setTypeface(fontAwesome);
        btnBillTime.setOnClickListener(this);


        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BillFragment billFragment = new BillFragment();
        fragmentTransaction.add(R.id.fragContentList, billFragment).commit();

        return lookupView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        billTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*Toast.makeText(parent.getContext(),
                        "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();*/
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
        searchBillPresenter = new SearchBillPresenter(billFragment.getView(), etBillTime, billFragment.getListBill());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnBillTime:
                DialogFragment dateDialogFrag = new SelectDateFragment();
                dateDialogFrag.show(getFragmentManager(), "DatePicker");
                break;
            case R.id.btnToLookup:
                searchBillPresenter.intializeClickToSearch(etBillTime.getText().toString());
                break;
        }
    }

    private class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            etBillTime.setText(new StringBuilder().append(monthOfYear + 1)
                .append("/").append(year));
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
}

