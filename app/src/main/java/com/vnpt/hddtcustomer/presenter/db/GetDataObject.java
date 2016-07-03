package com.vnpt.hddtcustomer.presenter.db;


import android.os.StrictMode;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetDataObject {
    private JSONObject jsonObject = null;
    private String txt;

    public GetDataObject(String url){
        this.txt = requestContent(url);
    }

    private String requestContent(String url){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String result = "";
        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpResponse response = httpClient.execute(new HttpGet(url));
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            result = reader.readLine();
            if(!isStringNumberic(result) && !result.equals("null")){
                JSONObject mainObject = new JSONObject(result);
                if(mainObject.getBoolean("status")){
                   jsonObject = mainObject.getJSONObject("data");
                }else{
                    result = mainObject.getString("error_msg");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    private boolean isStringNumberic(String str){
        try{
            int i = Integer.parseInt(str);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    public JSONObject getJsonObject(){
        return this.jsonObject;
    }

    public String getStringResult(){
        return this.txt;
    }
}
