package com.vnpt.hddtcustomer.graphics.font;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

import com.vnpt.hddtcustomer.R;

public class CustomFontButton extends Button {

    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public CustomFontButton(Context context) {
        super(context);
        applyCustomFont(context, null);
    }

    public CustomFontButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context, attrs);
    }

    public CustomFontButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context, attrs);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {
        TypedArray attributeArray = context.obtainStyledAttributes(attrs, R.styleable.CustomFontApp);

        String fontName = attributeArray.getString(R.styleable.CustomFontApp_font);
        int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);

        Typeface customFont = selectType(context, fontName, textStyle);
        setTypeface(customFont);

        attributeArray.recycle();

    }

    private Typeface selectType(Context context, String fontName, int textStyle) {
        if(fontName.contentEquals(context.getString(R.string.font_fontawesome))){
            return FontManager.getTypeface("fonts/fontawesome-webfont.ttf", context);
        }else if(fontName.contentEquals(context.getString(R.string.VNFGotham))){
            switch (textStyle){
                default:
                    return FontManager.getTypeface("fonts/VNF-GothamBook.ttf", context);
            }
        }
        return null;
    }
}
