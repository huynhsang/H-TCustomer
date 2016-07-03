package com.vnpt.hddtcustomer.presenter.db;


import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetDataArray {

    private JSONArray jsonArray = null;
    private String txt;

    public GetDataArray(String url){
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
            JSONObject mainObject = new JSONObject(result);
            if(mainObject.getBoolean("status")){
                jsonArray = mainObject.getJSONArray("data");
            }else{
                result = mainObject.getString("error_msg");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public JSONArray getJsonArray(){
        return this.jsonArray;
    }

    public String getStringResult(){
        return this.txt;
    }

}
