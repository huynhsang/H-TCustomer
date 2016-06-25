package com.vnpt.hddtcustomer.presenter;


import android.content.Context;
import android.content.SharedPreferences;

public class HDDTSharedPreference{
    private static final String PREFS_NAME = "HDDT_PREFS";
    private SharedPreferences sharedPreferences;

    public HDDTSharedPreference(Context context){
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

    }

    public void save(String key, String value){
        SharedPreferences.Editor editor;
        editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getValue(String key){
        String text;
        text = sharedPreferences.getString(key, null);
        return text;
    }

    public void clearSharedPreference(){
        SharedPreferences.Editor editor;
        editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public void removeValue(String key){
        SharedPreferences.Editor editor;
        editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }
}
