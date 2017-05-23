package com.satt.mjbus.Map;

import android.app.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.satt.mjbus.Constants.Constants;
import com.satt.mjbus.Gps_Receiver;
import com.satt.mjbus.R;

/**
 * Created by hn012 on 2017-04-28.
 */

public class GmapActivity extends Activity implements OnMapReadyCallback {
    public Gps_Receiver receiver;
    public FloatingActionButton refreshBtn;
    private double Latitude;
    private double Longitude;
    public Marker m1;
    public static final LatLng chapel_Gwan = new LatLng(Constants.chapel_Gwan_Lat, Constants.chapel_Gwan_Lon);
    public static final LatLng e_Mart_Down = new LatLng(Constants.e_Mart_Down_Lat, Constants.e_Mart_Down_Lon);
    public static final LatLng enter_Down = new LatLng(Constants.enter_Down_Lat, Constants.enter_Down_Lon);
    public static final LatLng mj_Station = new LatLng(Constants.mj_Station_Lat, Constants.mj_Station_Lon);
    public static final LatLng enter_Up = new LatLng(Constants.enter_Up_Lat, Constants.enter_Up_Lon);
    public static final LatLng e_Mart_Up = new LatLng(Constants.e_Mart_Up_Lat, Constants.e_Mart_Up_Lon);
    public static final LatLng myong_Jin = new LatLng(Constants.myong_Jin_Lat, Constants.myong_Jin_Lon);
    public static final LatLng third_Gong = new LatLng(Constants.third_Gong_Lat, Constants.third_Gong_Lon);
    public static final LatLng center_police = new LatLng(Constants.center_police_Lat, Constants.center_police_Lon);
    public static final LatLng missha = new LatLng(Constants.missha_Lat, Constants.missha_Lon);
    public static final LatLng park_Station = new LatLng(Constants.park_Station_Lat, Constants.park_Station_Lon);
    public static final LatLng first_Gong = new LatLng(Constants.first_Gong_Lat, Constants.first_Gong_Lon);




   public GoogleMap googleMap;

    @Override
    public void onMapReady(GoogleMap map){
        googleMap = map;

        googleMap.addMarker(new MarkerOptions().position(chapel_Gwan));
        googleMap.addMarker(new MarkerOptions().position(e_Mart_Down));
        googleMap.addMarker(new MarkerOptions().position(enter_Down));
        googleMap.addMarker(new MarkerOptions().position(mj_Station));
        googleMap.addMarker(new MarkerOptions().position(enter_Up));
        googleMap.addMarker(new MarkerOptions().position(e_Mart_Up));
        googleMap.addMarker(new MarkerOptions().position(myong_Jin));
        googleMap.addMarker(new MarkerOptions().position(third_Gong));
        googleMap.addMarker(new MarkerOptions().position(center_police));
        googleMap.addMarker(new MarkerOptions().position(missha));
        googleMap.addMarker(new MarkerOptions().position(park_Station));
        googleMap.addMarker(new MarkerOptions().position(first_Gong));

        m1 = googleMap.addMarker(new MarkerOptions().position(missha));
        receiver = new Gps_Receiver();
        receiver.getData(Constants.url, googleMap, m1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.satt.mjbus.R.layout.gmap_layout);
        Log.d("GmapActivity","Init Gmap Activity!!");

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(com.satt.mjbus.R.id.googleMap);
        mapFragment.getMapAsync(this);
        refreshBtn = (FloatingActionButton)findViewById(R.id.refreshBtn);
        refreshBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                receiver.getData(Constants.url, googleMap, m1);


            }
        });
    }



}
