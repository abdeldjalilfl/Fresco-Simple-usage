package com.upshotdev.frescosampleusage;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Fresco takes care of image loading and display
        //initialisation
        Fresco.initialize(this);
    }
}
