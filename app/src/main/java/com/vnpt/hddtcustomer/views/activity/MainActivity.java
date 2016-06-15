package com.vnpt.hddtcustomer.views.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.vnpt.hddtcustomer.R;
import com.vnpt.hddtcustomer.graphics.animation.MenuAnimation;
import com.vnpt.hddtcustomer.models.menu.ExpandableListDataLeftMenu;
import com.vnpt.hddtcustomer.views.adapter.ExpandableListAdapterMenu;
import com.vnpt.hddtcustomer.views.fragment.BillFragment;
import com.vnpt.hddtcustomer.views.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView btnToLeftMenu, btnToSearch, titleMainView;
    private MenuAnimation menuAnimation;
    private RelativeLayout mainContentLayout, leftMenuLayout;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    List<String> expandableListDataHeader;
    LinkedHashMap<String, List<String>> expandableListDetail;

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

        titleMainView = (TextView) findViewById(R.id.titleMainView);
        titleMainView.setText(R.string.lblHome);

        mainContentLayout = (RelativeLayout) findViewById(R.id.mainContent);
        leftMenuLayout = (RelativeLayout) findViewById(R.id.leftMenuContent);

        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragMainContent, homeFragment).commit();

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
                /*Toast.makeText(getApplicationContext(), expandableListDataHeader.get(groupPosition)+ " : " +
                            expandableListDetail.get(expandableListDataHeader.get(groupPosition)).get(childPosition),Toast.LENGTH_LONG).show();*/
                titleMainView.setText(R.string.lblBill);
                BillFragment billFragment = new BillFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragMainContent, billFragment).commit();
                menuAnimation.toggleMenu();
                return false;
            }
        });

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Toast.makeText(getApplicationContext(), expandableListDataHeader.get(groupPosition) + " Clicked",
                        Toast.LENGTH_SHORT).show();
                handleOnGroupClick(groupPosition);
                return false;
            }
        });


        menuAnimation = new MenuAnimation(this);
        initializeAnimation();
    }

    private void handleOnGroupClick(int groupId) {
        Fragment fragment = null;
        Class fragmentClass = null;
        switch (groupId){
            case 0:
                fragmentClass = HomeFragment.class;
                titleMainView.setText(R.string.lblHome);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            default:
                break;
        }
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
                break;
        }
    }
}
