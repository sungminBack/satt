package com.satt.mjbus.Route;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.satt.mjbus.Map.GmapActivity;

import static com.satt.mjbus.R.id.listview1;



/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1Fragment extends Fragment{

    ViewFlipper flipper;

    public Tab1Fragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(com.satt.mjbus.R.layout.fragment_tab1,null);



        final ListView listview;
        ListViewAdapter adapter;
        listview = (ListView) view.findViewById(listview1);

        //Adapter 생성
        adapter = new ListViewAdapter();
        listview.setAdapter(adapter);
        adapter.addItem(ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.ic_bus_gray),ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.bus_line2),"버스관리사무소(기점)"," ");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.ic_bus_gray),ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.bus_line2),"이마트 앞(경전철 방면)"," ");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.ic_bus_gray),ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.bus_line2),"진입로(경전철 방면)"," ");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.ic_bus_gray),ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.bus_line2),"명지대역","잠시후 도착 예정");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.ic_bus_gray),ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.bus_line2),"진입로(학교 방면)"," ");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.ic_bus_gray),ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.bus_line2),"이마트 앞(학교 방면)"," ");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.ic_bus_gray),ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.bus_line2),"명진당"," ");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.ic_bus_gray),ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.bus_line2),"3공학관"," ");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.ic_bus_gray),ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.bus_line2),""," ");

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
                     public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(),GmapActivity.class);
                startActivity(intent);

            }
        });

        return view;
    }

}
