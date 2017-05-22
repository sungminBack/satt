package com.satt.mjbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.satt.mjbus.Route.TabLayoutDemoActivity;

/**
 * Created by hn012 on 2017-05-04.
 */

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        startActivity(new Intent(this,TabLayoutDemoActivity.class));
        finish();

    }
}
