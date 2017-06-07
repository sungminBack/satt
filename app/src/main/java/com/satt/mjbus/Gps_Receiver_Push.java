package com.satt.mjbus;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.satt.mjbus.Constants.Constants;
import com.satt.mjbus.Route.ListViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

/**
 * Created by dlgow on 2017-06-07.
 */
public class Gps_Receiver_Push {
    String myJSON;
    Context context;
    int poss;
    Constants.EBusState eState;

    private static final String TAG_RESULT = "result";
    private static final String TAG_LATITUDE = "Latitude";
    private static final String TAG_LONGITUDE = "Longitude";

    double Latitude;
    double Longitude;

    double lat = 37.231224;
    double lon = 127.185555;

    JSONArray gps = null;







    public void getData(String url, Context context, int poss, Constants.EBusState eBusState){
        this.context = context;
        this.poss = poss;
        this.eState = eBusState;
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
        try {
            g.execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
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

            switch(poss){
                case 0:
                    if(Latitude > 37.223344 && Latitude < 37.224647 && Longitude > 127.185406 && Longitude < 127.188389 && eState.equals(Constants.EBusState.Down)){
                        showPush(context);
                        break;
                    }
                case 1:
                    if(Latitude > 37.224650 && Latitude < 37.231973 && Longitude > 127.185514 && Longitude < 127.189827 && eState.equals(Constants.EBusState.Down)){
                        showPush(context);
                        break;
                    }
                case 2:
                    if(Latitude > 37.232000 && Latitude < 37.234106 && Longitude > 127.185514 && Longitude < 127.189767 && eState.equals(Constants.EBusState.Down)){
                        showPush(context);
                        break;
                    }
                case 3:
                    if(Latitude > 37.234110 && Latitude < 37.238209 && Longitude > 127.187995 && Longitude < 127.190728 && eState.equals(Constants.EBusState.Down)){
                        showPush(context);
                        break;
                    }
                case 4:
                    if((Latitude > 37.234691 && Latitude < 37.238851 && Longitude > 127.184025 && Longitude < 127.186986) ||
                            (Latitude > 37.234110 && Latitude < 37.238209 && Longitude > 127.187995 && Longitude < 127.190728) && eState.equals(Constants.EBusState.Up)){
                        showPush(context);
                        break;
                    }
                case 5:
                    if(Latitude > 37.232000 && Latitude < 37.234106 && Longitude > 127.185514 && Longitude < 127.189767 && eState.equals(Constants.EBusState.Up)){
                        showPush(context);
                        break;
                    }
                case 6:
                    if(Latitude > 37.224650 && Latitude < 37.231973 && Longitude > 127.185514 && Longitude < 127.189827 && eState.equals(Constants.EBusState.Up))
                        showPush(context);
                    break;
                case 7:
                    if(Latitude > 37.219400 && Latitude < 37.222200 ){
                        showPush(context);
                        break;
                    }
                default:
                    break;
            }

        }catch(JSONException e){
            e.printStackTrace();
        }
    }
    protected  void showPush(Context context){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(context.getApplicationContext(), ListViewAdapter.class); //인텐트 생성.

        Notification.Builder builder = new Notification.Builder(context.getApplicationContext());
        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //현재 액티비티를 최상으로 올리고, 최상의 액티비티를 제외한 모든 액티비티를 없앤다.

        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(context, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setSmallIcon(R.drawable.bus)
                .setTicker("버스가 도착 예정입니다.")
                .setWhen(System.currentTimeMillis())
                .setContentTitle("도착 예정 알림")
                .setContentText("버스가 도착 예정입니다.")
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .setContentIntent(pendingNotificationIntent)
                .setAutoCancel(true)
                .setOngoing(true);

        notificationManager.notify(1, builder.build());
        // Notification send
    }
}
