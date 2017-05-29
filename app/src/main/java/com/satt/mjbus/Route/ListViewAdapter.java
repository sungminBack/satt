package com.satt.mjbus.Route;

/**
 * Created by hn012 on 2017-04-13.
 */

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;

    // ListViewAdapter의 생성자
    public ListViewAdapter() {
    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }
    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(com.satt.mjbus.R.layout.listview_item, parent, false);
        }

        // 서버요청 <------------------------------

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        final ImageView iconImageView = (ImageView) convertView.findViewById(com.satt.mjbus.R.id.imageView1) ;
        ImageView lineImageView = (ImageView) convertView.findViewById(com.satt.mjbus.R.id.imageView2) ;


        TextView titleTextView = (TextView) convertView.findViewById(com.satt.mjbus.R.id.textView1) ;
        TextView descTextView = (TextView) convertView.findViewById(com.satt.mjbus.R.id.textView2) ;

        //버스아이콘 전체 안보이게
        iconImageView.setVisibility(View.INVISIBLE);
        descTextView.setVisibility(View.INVISIBLE);

        //리스트위치1일때 아이콘 보이게
        if(pos==1){
            iconImageView.setVisibility(View.VISIBLE);
            descTextView.setVisibility(View.VISIBLE);
        }

        //버튼을 클릭했을 때 이벤트 발생
        Button btn = (Button)convertView.findViewById(com.satt.mjbus.R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context,listViewItemList.get(pos),Toast.LENGTH_SHORT).show();
               // System.out.println("$$$$$$$$$$$$$$$"+pos);

            }
        });
        btn.setFocusable(false);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItem listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        iconImageView.setImageDrawable(listViewItem.getIcon());
        lineImageView.setImageDrawable(listViewItem.getline());
        titleTextView.setText(listViewItem.getTitle());
        descTextView.setText(listViewItem.getDesc());

        return convertView;
    }


    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(Drawable icon,Drawable line ,String title, String desc) {
        ListViewItem item = new ListViewItem();

        item.setIcon(icon);
        item.setline(line);
        item.setTitle(title);
        item.setDesc(desc);

        listViewItemList.add(item);
    }
}

