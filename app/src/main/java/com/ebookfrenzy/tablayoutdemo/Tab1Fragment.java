package com.ebookfrenzy.tablayoutdemo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import static com.ebookfrenzy.tablayoutdemo.R.id.listview1;



/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1Fragment extends Fragment {


    public Tab1Fragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_tab1,null);

        final ListView listview;
        ListViewAdapter adapter;
        listview = (ListView) view.findViewById(listview1);

        //Adapter 생성
        adapter = new ListViewAdapter();
        listview.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(getActivity(),R.drawable.ic_bus_gray),ContextCompat.getDrawable(getActivity(),R.drawable.bus_line2),"진입로(경전철 방면)"," ");
        adapter.addItem(ContextCompat.getDrawable(getActivity(),R.drawable.ic_bus_gray),ContextCompat.getDrawable(getActivity(),R.drawable.bus_line2),"명지대역","잠시후 도착 예정");
        adapter.addItem(ContextCompat.getDrawable(getActivity(),R.drawable.ic_bus_gray),ContextCompat.getDrawable(getActivity(),R.drawable.bus_line2),"진입로(이마트 방면"," ");
        adapter.addItem(ContextCompat.getDrawable(getActivity(),R.drawable.ic_bus_gray),ContextCompat.getDrawable(getActivity(),R.drawable.bus_line2),"이마트 앞"," ");
        adapter.addItem(ContextCompat.getDrawable(getActivity(),R.drawable.ic_bus_gray),ContextCompat.getDrawable(getActivity(),R.drawable.bus_line2),"명진당"," ");
        adapter.addItem(ContextCompat.getDrawable(getActivity(),R.drawable.ic_bus_gray),ContextCompat.getDrawable(getActivity(),R.drawable.bus_line2),"1공학관"," ");

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
