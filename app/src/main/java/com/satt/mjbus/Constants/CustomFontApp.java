package com.satt.mjbus.Constants;

/**
 * Created by hn012 on 2017-05-06.
 */
import android.app.Application;

import com.tsengvn.typekit.Typekit;

public class CustomFontApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        Typekit.getInstance()
                .addCustom1(Typekit.createFromAsset(this, "123RF.ttf"))
                .addCustom2(Typekit.createFromAsset(this, "BMJUA_ttf.ttf"))
                .addCustom3(Typekit.createFromAsset(this, "NanumBarunpenR.ttf"))
                .addCustom4(Typekit.createFromAsset(this, "NanumBarunpenB.ttf"));
    }
}
