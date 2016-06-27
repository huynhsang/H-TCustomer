package com.vnpt.hddtcustomer.views.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.vnpt.hddtcustomer.R;
import com.vnpt.hddtcustomer.views.fragment.BillFragment;
import com.vnpt.hddtcustomer.views.fragment.ChartFragment;
import com.vnpt.hddtcustomer.views.fragment.ConfigFragment;
import com.vnpt.hddtcustomer.views.fragment.ContactFragment;
import com.vnpt.hddtcustomer.views.fragment.GuideFragment;
import com.vnpt.hddtcustomer.views.fragment.HomeFragment;
import com.vnpt.hddtcustomer.views.fragment.LookupFragment;
import com.vnpt.hddtcustomer.views.fragment.NotifyFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{

    private TextView btnToLeftMenu, btnToSearch, titleMainView, tvUsername, tvUserphone;
    private EditText etSearchBill;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    private static final int NOTIFY_TIME_OUT = 3000;
    private static final int NOTIFY_ID = 1111;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToLeftMenu = (TextView) findViewById(R.id.btnToLeftMenu);
        btnToLeftMenu.setOnClickListener(this);

        btnToSearch = (TextView) findViewById(R.id.btnToSearch);
        btnToSearch.setOnClickListener(this);

        titleMainView = (TextView) findViewById(R.id.titleMainView);

        etSearchBill = (EditText) findViewById(R.id.etSearchBill);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getFragment();

        setShowNotification();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnToLeftMenu:
                drawer.openDrawer(GravityCompat.START);
                break;
            case R.id.btnToSearch:
                toSearchListBill();
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass = null;
        titleMainView.setVisibility(View.VISIBLE);
        etSearchBill.setVisibility(View.GONE);
        btnToSearch.setVisibility(View.GONE);
        switch (id){
            case R.id.drawer_home:
                fragmentClass = HomeFragment.class;
                titleMainView.setText(R.string.lblHome);
                btnToSearch.setVisibility(View.VISIBLE);
                break;
            case R.id.drawer_lookup:
                fragmentClass = LookupFragment.class;
                titleMainView.setText(R.string.lblBillLookup);
                btnToSearch.setVisibility(View.GONE);
                break;
            case R.id.drawer_elec:
                titleMainView.setText(R.string.lblElecBill);
                fragment = (BillFragment) new BillFragment("Dien");
                break;
            case R.id.drawer_water:
                titleMainView.setText(R.string.lblWaterBill);
                fragment = (BillFragment) new BillFragment("Nuoc");
                break;
            case R.id.drawer_tele:
                titleMainView.setText(R.string.lblTeleBill);
                fragment = (BillFragment) new BillFragment("Vienthong");
                break;
            case R.id.drawer_guide:
                fragmentClass = GuideFragment.class;
                titleMainView.setText(R.string.guideTitle);
                break;
            case R.id.drawer_config:
                fragmentClass = ConfigFragment.class;
                titleMainView.setText(R.string.configTitle);
                break;
            case R.id.drawer_contact:
                fragmentClass = ContactFragment.class;
                titleMainView.setText(R.string.phssTitle);
                break;
            case R.id.drawer_logout:
                toLogout();
                return false;
        }
        if((id != R.id.drawer_elec) || (id != R.id.drawer_water) || (id != R.id.drawer_tele)){
            try{
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        //Insert the fragment by replacing any existing fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragMainContent, fragment).commit();

        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    public void toggleSearch(){
        if(etSearchBill.getVisibility() != View.VISIBLE){
            etSearchBill.setVisibility(View.VISIBLE);
            titleMainView.setVisibility(View.GONE);
        }else{
            etSearchBill.setVisibility(View.GONE);
            titleMainView.setVisibility(View.VISIBLE);
        }
    }

    public void toChart(){
        btnToSearch.setVisibility(View.VISIBLE);
        titleMainView.setText(R.string.lblChart);
        ChartFragment chartFragment = new ChartFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragMainContent, chartFragment).commit();
    }

    private void toSearchListBill(){
        titleMainView.setText(R.string.lblBill);
        BillFragment billFragment = new BillFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragMainContent, billFragment).commit();

        titleMainView.setVisibility(View.GONE);
        btnToSearch.setVisibility(View.GONE);
        etSearchBill.setVisibility(View.VISIBLE);
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
                    toSearchListBill();
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

        alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        alertDialogBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
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
