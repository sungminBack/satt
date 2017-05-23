package com.satt.mjbus;

import android.os.AsyncTask;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

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
    public GoogleMap googleMap;


    private static final String TAG_RESULT = "result";
    private static final String TAG_LATITUDE = "Latitude";
    private static final String TAG_LONGITUDE = "Longitude";

    double Latitude;
    double Longitude;


    private Marker bus_Marker;
    JSONArray gps = null;

    public double Get_Latitude(){
        return Latitude;
    }

    public double Get_Longitude(){
        return Longitude;
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
                    LatLng bus_Position;

                    bus_Position = new LatLng(Latitude, Longitude);
                    bus_Marker = googleMap.addMarker(new MarkerOptions().position(bus_Position));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bus_Position,16));


                }catch(JSONException e){
                    e.printStackTrace();
        }
    }

    public void getData(String url, GoogleMap map){
        googleMap = map;
        class GetDataJSON extends AsyncTask<String, Void, String> {
            @Override
            protected void onPreExecute(){
                bus_Marker.remove();
            }
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