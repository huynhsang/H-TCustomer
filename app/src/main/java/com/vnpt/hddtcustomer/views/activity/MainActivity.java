package com.vnpt.hddtcustomer.views.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vnpt.hddtcustomer.R;
import com.vnpt.hddtcustomer.graphics.animation.MenuAnimation;
import com.vnpt.hddtcustomer.views.fragment.HomeFragment;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView btnToLeftMenu, btnToSearch, titleMainView;
    private MenuAnimation menuAnimation;
    private RelativeLayout mainContentLayout, leftMenuLayout;

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

        menuAnimation = new MenuAnimation(this);
        initializeAnimation();
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
