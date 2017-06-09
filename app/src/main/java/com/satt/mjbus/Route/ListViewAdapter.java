package com.satt.mjbus.Route;

/**
 * Created by hn012 on 2017-04-13.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.satt.mjbus.Constants.Constants;
import com.satt.mjbus.Constants.Constants.EBusState;
import com.satt.mjbus.PushThread;
import com.satt.mjbus.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    String myJSON;
    private static final String TAG_RESULT = "result";
    private static final String TAG_LATITUDE = "Latitude";
    private static final String TAG_LONGITUDE = "Longitude";

    double Latitude;
    double Longitude;

    public EBusState eState = EBusState.Down;
    JSONArray gps = null;



    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;

    // ListViewAdapter의 생성자
    public ListViewAdapter() {
        getData("http://117.17.158.240/test/GpsSender.php");
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


        //거점
        if(Latitude > 37.223344 && Latitude < 37.224647 && Longitude > 127.185406 && Longitude < 127.188389 && eState.equals(Constants.EBusState.Down)){
            if( pos == 0 ){
                iconImageView.setVisibility(View.VISIBLE);
                descTextView.setVisibility(View.VISIBLE);
            }
            // 이마트 하행
        }else if(Latitude > 37.224650 && Latitude < 37.231973 && Longitude > 127.185514 && Longitude < 127.189827 && eState.equals(Constants.EBusState.Down)){
            if( pos == 1 ){
                iconImageView.setVisibility(View.VISIBLE);
                descTextView.setVisibility(View.VISIBLE);
            }
            // 진입로 하행
        }else if(Latitude > 37.232000 && Latitude < 37.234106 && Longitude > 127.185514 && Longitude < 127.189767 && eState.equals(Constants.EBusState.Down)){
            if( pos == 2 ){
                iconImageView.setVisibility(View.VISIBLE);
                descTextView.setVisibility(View.VISIBLE);
            }
            // 명지대역
        }else if(Latitude > 37.236582 && Latitude < 37.238137 && Longitude > 127.188989 && Longitude < 127.190480 && eState.equals(Constants.EBusState.Down)){
            if( pos == 3 ){
                iconImageView.setVisibility(View.VISIBLE);
                descTextView.setVisibility(View.VISIBLE);

            }
            // 진입로 상행
        }else if((Latitude > 37.236285 && Latitude < 37.238843 && Longitude > 127.184196 && Longitude < 127.187267) && eState.equals(EBusState.Down)){
            if( pos ==4 ){
                iconImageView.setVisibility(View.VISIBLE);
                descTextView.setVisibility(View.VISIBLE);
                eState = EBusState.Up;
            }
            // 이마트 상행
        }else if(Latitude > 37.232000 && Latitude < 37.234106 && Longitude > 127.185514 && Longitude < 127.189767 && eState.equals(Constants.EBusState.Up)){
            if( pos == 5 ){
                iconImageView.setVisibility(View.VISIBLE);
                descTextView.setVisibility(View.VISIBLE);
            }
            // 명진당 행
        }else if(Latitude > 37.224650 && Latitude < 37.231973 && Longitude > 127.185514 && Longitude < 127.189827 && eState.equals(Constants.EBusState.Up)){
            if( pos == 6 ){
                iconImageView.setVisibility(View.VISIBLE);
                descTextView.setVisibility(View.VISIBLE);
                eState = Constants.EBusState.Down;
            }
            // 3공행
        }else if(Latitude > 37.219400 && Latitude < 37.222200 ){
            if( pos == 7 ){
                iconImageView.setVisibility(View.VISIBLE);
                descTextView.setVisibility(View.VISIBLE);
                eState = Constants.EBusState.Down;
            }
        }
        //버튼을 클릭했을 때 이벤트 발생
        final ToggleButton btn = (ToggleButton)convertView.findViewById(com.satt.mjbus.R.id.button);
        btn.setTag(position);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int poss = (Integer)v.getTag();
               // Thread t = new Thread(new PushThread(context,poss, eState));
                if(btn.isChecked()){
                    btn.setBackgroundDrawable(
                            context.getResources().getDrawable(R.drawable.bt_alarm_on)
                            );
                    Thread t = new Thread(new PushThread(context, poss, eState));
                    t.start();
                }else{
                    btn.setBackgroundDrawable(
                            context.getResources().getDrawable(R.drawable.bt_alarm_off)
                            );
                }
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

}

