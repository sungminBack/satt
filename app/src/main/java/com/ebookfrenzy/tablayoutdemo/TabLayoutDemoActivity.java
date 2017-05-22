package com.ebookfrenzy.tablayoutdemo;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.tsengvn.typekit.TypekitContextWrapper;

public class TabLayoutDemoActivity extends AppCompatActivity {

    //플롯팅버튼 변수

    Animation FabOpen, FabClose, FabRClockwise, FabRanticlockWise;
    private FloatingActionButton fabMain, fabQue, fabBus, fabTime;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_demo);


        //플롯팅변수
        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        FabRClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
        FabRanticlockWise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlockwise);

        fabMain = (FloatingActionButton)findViewById(R.id.fabMain);
        fabTime = (FloatingActionButton)findViewById(R.id.fabTime);
        fabBus = (FloatingActionButton)findViewById(R.id.fabBus);
        fabQue = (FloatingActionButton)findViewById(R.id.fabQue);

        fabMain.setOnClickListener(clickListener);
        fabQue.setOnClickListener(clickListener);
        fabTime.setOnClickListener(clickListener);
        fabBus.setOnClickListener(clickListener);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        TabLayout tabLayout =
                (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("진입로 셔틀버스"));
        tabLayout.addTab(tabLayout.newTab().setText("시내 셔틀버스"));
       // final View tabView = LayoutInflater.from(this).inflate(R.layout.view_custom_tab, null, false);
       // tabLayout.addTab(tabLayout.newTab().setCustomView(tabView));

        final ViewPager viewPager =
                (ViewPager) findViewById(R.id.pager);
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
                case R.id.fabMain :
                    if (!isOpen) {
                        fabBus.startAnimation(FabOpen);
                        fabQue.startAnimation(FabOpen);
                        fabTime.startAnimation(FabOpen);

                        fabMain.startAnimation(FabRClockwise);

                        fabBus.setClickable(true);
                        fabQue.setClickable(true);
                        fabTime.setClickable(true);
                        isOpen = true;
                    } else {
                        fabBus.startAnimation(FabClose);
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
                case R.id.fabQue:
                    Intent intent = new Intent(getApplicationContext(),ViewPagerActivity.class);
                    startActivity(intent);
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab_layout_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void attachBaseContext(Context newBase) {

        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));

    }

}
