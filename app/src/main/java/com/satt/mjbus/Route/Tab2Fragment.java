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

import com.satt.mjbus.Map.GmapActivity;
import com.satt.mjbus.R;

import static com.satt.mjbus.R.id.listview2;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab2Fragment extends Fragment {


    public Tab2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(com.satt.mjbus.R.layout.fragment_tab2,null);

        final ListView listview;
        ListViewAdapter adapter;
        listview = (ListView) view.findViewById(listview2);

        //Adapter 생성
        adapter = new ListViewAdapter();
        listview.setAdapter(adapter);
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.empty),ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.bus_line2),"버스관리사무소(기점)"," ");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.empty),ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.bus_line2),"이마트 앞(시내 방면)"," ");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.empty),ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.bus_line2),"진입로(시내 방면)"," ");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.empty),ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.bus_line2),"동부경찰서 중앙지구대 버스정류장"," ");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.empty),ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.bus_line2),"롯데시네마 뚜레주르"," ");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.empty),ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.bus_line2),"중앙공영주차장"," ");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.empty),ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.bus_line2),"진입로(학교 방면)"," ");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.empty),ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.bus_line2),"이마트 앞(학교 방면)"," ");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.empty),ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.bus_line2),"제 1공학관"," ");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.empty),ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.bus_line2),"제 3공학관"," ");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.empty),ContextCompat.getDrawable(getActivity(), com.satt.mjbus.R.drawable.bus_line2),""," ");


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(),GmapActivity.class);
                intent.putExtra("RoadState", "Downtown");
                startActivity(intent);


            }
        });

        return view;
    }

}
