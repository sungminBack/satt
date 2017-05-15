package com.ebookfrenzy.tablayoutdemo;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by hn012 on 2017-04-28.
 */

public class GmapActivity extends Activity implements OnMapReadyCallback {

    static final LatLng YongIn = new LatLng(37.221822, 127.186657);
    private GoogleMap googleMap;

    @Override
    public void onMapReady(final GoogleMap map){
        googleMap = map;
        googleMap.addMarker(new MarkerOptions().position(YongIn).title("YongIn"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(YongIn,17.0f));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gmap_layout);
        Log.d("GmapActivity","Init Gmap Activity!!");

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);
    }

}
