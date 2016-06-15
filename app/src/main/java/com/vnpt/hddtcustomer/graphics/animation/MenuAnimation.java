package com.vnpt.hddtcustomer.graphics.animation;


import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.vnpt.hddtcustomer.R;

public class MenuAnimation implements Animation.AnimationListener{
    private Context context;
    private Animation leftMenuIn, leftMenuOut, mainContentIn, mainContentOut;
    private RelativeLayout leftMenu, mainContent;
    private static int deviceWidth, margin, mainContentWidth, mainContentHeight;
    private Boolean isMainContentOut = false;

    public MenuAnimation(Context context){
        this.context = context;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        deviceWidth = displayMetrics.widthPixels;
    }

    public void initializeLeftMenuAnimation(RelativeLayout leftMenu){
        this.leftMenu = leftMenu;
        leftMenuIn = AnimationUtils.loadAnimation(context, R.anim.left_menu_in);
        leftMenuOut = AnimationUtils.loadAnimation(context, R.anim.left_menu_out);
    }

    public void initializeMainContentAnimation(RelativeLayout mainContent){
        this.mainContent = mainContent;

        mainContentWidth = mainContent.getWidth();
        mainContentHeight = mainContent.getHeight();

        mainContentIn = AnimationUtils.loadAnimation(context, R.anim.main_content_in);
        mainContentIn.setAnimationListener(this);

        mainContentOut = AnimationUtils.loadAnimation(context, R.anim.main_content_out);
        mainContentOut.setAnimationListener(this);
    }

    public void toggleMenu(){
        if(isMainContentOut){
            mainContent.startAnimation(mainContentIn);
            leftMenu.setVisibility(View.INVISIBLE);
            leftMenu.startAnimation(leftMenuOut);
        }else{
            mainContent.startAnimation(mainContentOut);
            leftMenu.setVisibility(View.VISIBLE);
            leftMenu.startAnimation(leftMenuIn);
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if(isMainContentOut){
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(mainContentWidth, mainContentHeight);
            mainContent.setLayoutParams(params);
            isMainContentOut = false;
        }else{
            margin = (deviceWidth*90)/100;
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(mainContentWidth, mainContentHeight);

            params.leftMargin = margin;
            params.rightMargin = -margin;

            mainContent.setLayoutParams(params);
            isMainContentOut = true;
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    private void dimMainContentLayout(){
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.7f);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(200);
        mainContent.startAnimation(alphaAnimation);
    }
}
