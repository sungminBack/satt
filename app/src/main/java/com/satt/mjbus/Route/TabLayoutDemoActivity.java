package com.satt.mjbus.Route;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.satt.mjbus.Help.ViewPagerActivity;
import com.satt.mjbus.R;
import com.satt.mjbus.Timetable.TimeTableActivity;
import com.tsengvn.typekit.TypekitContextWrapper;

public class TabLayoutDemoActivity extends AppCompatActivity {

    //플롯팅버튼 변수
    Animation FabOpen, FabClose, FabRClockwise, FabRanticlockWise;
    private FloatingActionButton fabMain, fabQue, fabBus, fabTime;
    boolean isOpen = false;

    //notification 변수
   // private NotificationManager notificationManager;
    //private NotificationCompat.Builder builder;

    //팁변수
    ViewFlipper flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(com.satt.mjbus.R.layout.activity_tab_layout_demo);

        //notification
      /*  Button button = (Button)findViewById(R.id.alarm_Btn);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                notificationManager = (NotificationManager)TabLayoutDemoActivity.this.getSystemService(TabLayoutDemoActivity.this.NOTIFICATION_SERVICE);

                if (notificationManager != null){
                    notificationManager.cancel(0);
                }

                builder = new NotificationCompat.Builder(getApplicationContext());

                builder.setContentTitle(getResources().getString(R.string.app_name))
                        .setContentText("NotificationText")
                        .setTicker("NotificationCompat")
                        .setSmallIcon(R.drawable.bus);

                Intent intent1 = new Intent(TabLayoutDemoActivity.this.getApplicationContext(),TabLayoutDemoActivity.class);

                PendingIntent pendingNotificationIntent = PendingIntent.getActivity(TabLayoutDemoActivity.this,0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
                //PendingIntent는 일회용 인텐트 같은 개념입니다.

                notificationManager.notify(1,builder.build());

            }
        });*/

        //팁
        flipper = (ViewFlipper)findViewById(com.satt.mjbus.R.id.flipper);
        flipper.startFlipping();
        Animation showIn= AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        flipper.setInAnimation(showIn);
        flipper.setOutAnimation(this,android.R.anim.slide_out_right);
        flipper.setFlipInterval(3000);

        //플롯팅변수
        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(), com.satt.mjbus.R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(), com.satt.mjbus.R.anim.fab_close);
        FabRClockwise = AnimationUtils.loadAnimation(getApplicationContext(), com.satt.mjbus.R.anim.rotate_clockwise);
        FabRanticlockWise = AnimationUtils.loadAnimation(getApplicationContext(), com.satt.mjbus.R.anim.rotate_anticlockwise);

        fabMain = (FloatingActionButton)findViewById(com.satt.mjbus.R.id.fabMain);
        fabTime = (FloatingActionButton)findViewById(com.satt.mjbus.R.id.fabTime);
        fabQue = (FloatingActionButton)findViewById(com.satt.mjbus.R.id.fabQue);

        fabMain.setOnClickListener(clickListener);
        fabQue.setOnClickListener(clickListener);
        fabTime.setOnClickListener(clickListener);
        //fabBus.setOnClickListener(clickListener);



        Toolbar toolbar = (Toolbar) findViewById(com.satt.mjbus.R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);


        TabLayout tabLayout =
                (TabLayout) findViewById(com.satt.mjbus.R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("진입로 셔틀버스"));
        tabLayout.addTab(tabLayout.newTab().setText("시내 셔틀버스"));

        final ViewPager viewPager =
                (ViewPager) findViewById(com.satt.mjbus.R.id.pager);
        final PagerAdapter adapter = new TabPagerAdapter
                (getSupportFragmentManager(),
                        tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new
                TabLayout.OnTabSelectedListener() {
                   @Override
                   public void onTabSelected(TabLayout.Tab tab) {
                        viewPager.setCurrentItem(tab.getPosition());

                   }

                   @Override
                   public void onTabUnselected(TabLayout.Tab tab) {

                   }

                   @Override
                   public void onTabReselected(TabLayout.Tab tab) {

                   }
        });

    }


    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case com.satt.mjbus.R.id.fabMain :
                    if (!isOpen) {
                      //  fabBus.startAnimation(FabOpen);
                        fabQue.startAnimation(FabOpen);
                        fabTime.startAnimation(FabOpen);

                        fabMain.startAnimation(FabRClockwise);

                    //    fabBus.setClickable(true);
                        fabQue.setClickable(true);
                        fabTime.setClickable(true);
                        isOpen = true;
                    } else {
                      //  fabBus.startAnimation(FabClose);
                        fabQue.startAnimation(FabClose);
                        fabTime.startAnimation(FabClose);

                        fabMain.startAnimation(FabRanticlockWise);

                        fabTime.setClickable(false);
                        fabQue.setClickable(false);
                        fabTime.setClickable(false);
                        isOpen = false;
                    }
                    break;
            }

            switch (v.getId()){
                case com.satt.mjbus.R.id.fabQue:
                    Intent intent = new Intent(getApplicationContext(),ViewPagerActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    break;

                case com.satt.mjbus.R.id.fabTime:
                    Intent intent2 = new Intent(getApplicationContext(), TimeTableActivity.class);
                    intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent2);


            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.satt.mjbus.R.menu.menu_tab_layout_demo, menu);
        return true;
    }

  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == com.satt.mjbus.R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    //폰트적용
    @Override
    protected void attachBaseContext(Context newBase) {

        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));

    }

}
