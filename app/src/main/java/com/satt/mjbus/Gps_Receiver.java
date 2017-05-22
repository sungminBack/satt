package com.satt.mjbus;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by dlgow on 2017-05-22.
 */
public class Gps_Receiver{
    String myJSON;

    private static final String TAG_RESULT = "result";
    private static final String TAG_LATITUDE = "Latitude";
    private static final String TAG_LONGITUDE = "Longitude";

    double Latitude;
    double Longitude;



    JSONArray gps = null;

    public double Get_Latitude(){
        return Latitude;
    }

    public double Get_Longitude(){
        return Longitude;
    }

    public Gps_Receiver(String url){
        getData(url);
    }


    protected void showList(String s){
        try{
            JSONObject jsonObject = new JSONObject(s);
            gps = jsonObject.getJSONArray(TAG_RESULT);
            JSONObject c = gps.getJSONObject(0);
            String strLat = c.getString(TAG_LATITUDE);
            String strLon = c.getString(TAG_LONGITUDE);



            Latitude = Double.parseDouble(strLat);
            Longitude = Double.parseDouble(strLon);


        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    public void getData(String url){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                String uri = params[0];
                BufferedReader bufferedReader = null;
                try{
                    URL url = new URL(uri);
                    URLConnection con = (URLConnection)url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    con.setDoInput(true);
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;

                    while((json = bufferedReader.readLine())!=null){
                        System.out.print(json);
                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();



                }catch (Exception e){
                    //return null;
                    e.printStackTrace();
                    return "";
                }

            }

            @Override
            protected void onPostExecute(String result){
                myJSON = result;

                showList(myJSON);
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }
}