package com.vnpt.hddtcustomer.views.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.vnpt.hddtcustomer.R;
import com.vnpt.hddtcustomer.graphics.animation.MenuAnimation;
import com.vnpt.hddtcustomer.models.menu.ExpandableListDataLeftMenu;
import com.vnpt.hddtcustomer.presenter.SearchBillPresenter;
import com.vnpt.hddtcustomer.views.adapter.ExpandableListAdapterMenu;
import com.vnpt.hddtcustomer.views.fragment.BillFragment;
import com.vnpt.hddtcustomer.views.fragment.ChartFragment;
import com.vnpt.hddtcustomer.views.fragment.ConfigFragment;
import com.vnpt.hddtcustomer.views.fragment.ContactFragment;
import com.vnpt.hddtcustomer.views.fragment.GuideFragment;
import com.vnpt.hddtcustomer.views.fragment.HomeFragment;
import com.vnpt.hddtcustomer.views.fragment.LookupFragment;
import com.vnpt.hddtcustomer.views.fragment.NotifyFragment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView btnToLeftMenu, btnToSearch, titleMainView, btnToChart;
    private EditText etSearchBill;
    private MenuAnimation menuAnimation;
    private RelativeLayout mainContentLayout, leftMenuLayout;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> expandableListDataHeader;
    private LinkedHashMap<String, List<String>> expandableListDetail;

    private static final int NOTIFY_TIME_OUT = 3000;
    private static final int NOTIFY_ID = 1111;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Typeface fontAwesome = Typeface.createFromAsset(getAssets(),"fonts/fontawesome-webfont.ttf");

        btnToLeftMenu = (TextView) findViewById(R.id.btnToLeftMenu);
        btnToLeftMenu.setTypeface(fontAwesome);
        btnToLeftMenu.setOnClickListener(this);

        btnToSearch = (TextView) findViewById(R.id.btnToSearch);
        btnToSearch.setTypeface(fontAwesome);
        btnToSearch.setOnClickListener(this);

        btnToChart = (TextView) findViewById(R.id.btnToChart);
        btnToChart.setTypeface(fontAwesome);
        btnToChart.setOnClickListener(this);

        titleMainView = (TextView) findViewById(R.id.titleMainView);

        etSearchBill = (EditText) findViewById(R.id.etSearchBill);

        mainContentLayout = (RelativeLayout) findViewById(R.id.mainContent);
        leftMenuLayout = (RelativeLayout) findViewById(R.id.leftMenuContent);

        // get the expandablelistview
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListMenuView);
        expandableListDetail = ExpandableListDataLeftMenu.getData();
        expandableListDataHeader = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new ExpandableListAdapterMenu(this, expandableListDataHeader, expandableListDetail);
        // setting list adapter
        expandableListView.setAdapter(expandableListAdapter);


        // ExpandableListview on child click listener
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                toListBill();
                btnToChart.setVisibility(View.VISIBLE);
                btnToSearch.setVisibility(View.VISIBLE);
                menuAnimation.toggleMenu();
                return false;
            }
        });

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                handleOnGroupClick(groupPosition);
                return false;
            }
        });


        menuAnimation = new MenuAnimation(this);
        initializeAnimation();

        getFragment();

        setShowNotification();
    }

    private void handleOnGroupClick(int groupId) {
        Fragment fragment = null;
        Class fragmentClass = null;
        etSearchBill.setVisibility(View.GONE);
        titleMainView.setVisibility(View.VISIBLE);
        btnToSearch.setVisibility(View.GONE);
        btnToChart.setVisibility(View.GONE);
        switch (groupId){
            case 0:
                fragmentClass = HomeFragment.class;
                titleMainView.setText(R.string.lblHome);
                btnToSearch.setVisibility(View.VISIBLE);
                break;
            case 1:
                break;
            case 2:
                fragmentClass = LookupFragment.class;
                titleMainView.setText(R.string.lblBillLookup);
                btnToSearch.setVisibility(View.VISIBLE);
                break;
            case 3:
                fragmentClass = ContactFragment.class;
                titleMainView.setText("PHẢN HỒI SAI SÓT");
                break;
            case 4:
                fragmentClass = ConfigFragment.class;
                titleMainView.setText("CẤU HÌNH");
                break;
            case 5:
                fragmentClass = GuideFragment.class;
                titleMainView.setText("HƯỚNG DẪN SỬ DỤNG");
                break;
            case 6:
                toLogout();
                break;
            default:
                break;
        }
        if(fragmentClass != null){
            try{
                fragment = (Fragment) fragmentClass.newInstance();
                //Insert the fragment by replacing any existing fragment
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragMainContent, fragment).commit();
                menuAnimation.toggleMenu();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void initializeAnimation() {
        final ViewTreeObserver leftLayoutObserver = leftMenuLayout.getViewTreeObserver(); //tao 1 giam sat leftlayoutmenu
        leftLayoutObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){ //lang nghe neu leftlayoutmenu thay doi
            @Override
            public void onGlobalLayout() {
                leftMenuLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this); //tao 1 giam sat moi va xoa giam sat cu
                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                int diviceWith = displayMetrics.widthPixels;
                int leftMenuLayoutWidth = (diviceWith*90)/100;

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(leftMenuLayoutWidth, RelativeLayout.LayoutParams.MATCH_PARENT);
                leftMenuLayout.setLayoutParams(params);

                menuAnimation.initializeLeftMenuAnimation(leftMenuLayout);
            }
        });


        final ViewTreeObserver mainConentObserver = mainContentLayout.getViewTreeObserver();
        mainConentObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mainContentLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                menuAnimation.initializeMainContentAnimation(mainContentLayout);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnToLeftMenu:
                menuAnimation.toggleMenu();
                break;
            case R.id.btnToSearch:
                toggleSearch();
                break;
            case R.id.btnToChart:
                toChart();
                break;
        }
    }

    private void toListBill(){
        titleMainView.setText(R.string.lblBill);
        BillFragment billFragment = new BillFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragMainContent, billFragment).commit();
    }

    private void toggleSearch(){
        if(etSearchBill.getVisibility() != View.VISIBLE){
            etSearchBill.setVisibility(View.VISIBLE);
            titleMainView.setVisibility(View.GONE);
            btnToChart.setVisibility(View.GONE);
            toListBill();
        }else{
            etSearchBill.setVisibility(View.GONE);
            titleMainView.setVisibility(View.VISIBLE);
        }
    }

    private void toChart(){
        titleMainView.setText(R.string.lblChart);
        ChartFragment chartFragment = new ChartFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragMainContent, chartFragment).commit();
    }

    private void setShowNotification(){
        // define sound URI, the sound to be played when there's a notification
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("FragmentName", "NotifyFragment");

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        final Notification notification = new Notification.Builder(this)  //Build the notification
            .setTicker("Thông báo điện").setContentTitle("Electronic notices").setContentText("Notification Content")
                .setSmallIcon(R.drawable.icon_notify).setSound(soundUri).setContentIntent(pendingIntent).getNotification();
        notification.flags = Notification.FLAG_AUTO_CANCEL; //make flag to close notification when touched.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(NOTIFY_ID, notification);
            }
        }, NOTIFY_TIME_OUT);
    }

    private void cancelNotification(int notifyID){
        if(Context.NOTIFICATION_SERVICE != null){
            String notifyService = Context.NOTIFICATION_SERVICE;
            NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(notifyService);
            notificationManager.cancel(notifyID);
        }
    }

    private void getFragment(){
        String fragmentName = getIntent().getStringExtra("FragmentName");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(fragmentName != null){
            switch (fragmentName){
                case "NotifyFragment":
                    NotifyFragment notifyFragment = new NotifyFragment();
                    fragmentTransaction.replace(R.id.fragMainContent, notifyFragment).commit();
                    titleMainView.setText(R.string.lblNotify);
                    cancelNotification(NOTIFY_ID);
                    break;
                case "ChartFragment":
                    toChart();
                    break;
                case "SearchBill":
                    toggleSearch();
                    break;
            }
        }else{
            HomeFragment homeFragment = new HomeFragment();
            fragmentTransaction.replace(R.id.fragMainContent, homeFragment).commit();
            titleMainView.setText(R.string.lblHome);
        }
    }

    private void toLogout(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(R.string.lblLgNotices);
        alertDialogBuilder.setMessage(R.string.lblLgMsg);

        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    public EditText getEtSearchBill(){
        return etSearchBill;
    }
}
