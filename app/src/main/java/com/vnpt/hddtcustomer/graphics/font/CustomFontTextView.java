package com.vnpt.hddtcustomer.graphics.font;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.vnpt.hddtcustomer.R;


public class CustomFontTextView extends TextView {

    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";


    public CustomFontTextView(Context context) {
        super(context);
        applyCustomFont(context, null);
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context, attrs);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context, attrs);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {
        TypedArray attributeArray = context.obtainStyledAttributes(attrs, R.styleable.CustomFontApp);

        String fontName = attributeArray.getString(R.styleable.CustomFontApp_font);
        int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);

        Typeface customFont = selectTypeface(context, fontName);
        setTypeface(customFont);

        attributeArray.recycle();
    }

    private Typeface selectTypeface(Context context, String fontName) {
        if (fontName.contentEquals(context.getString(R.string.font_fontawesome))) {
            return FontManager.getTypeface("fonts/fontawesome-webfont.ttf", context);
        } else if (fontName.contentEquals(context.getString(R.string.VNFGotham))) {
            return FontManager.getTypeface("fonts/VNF-GothamBook.ttf", context);
        }
        return null;
    }
}
