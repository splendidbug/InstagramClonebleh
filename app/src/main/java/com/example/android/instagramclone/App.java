package com.example.android.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("QUxHahXbuWUEtdeHD0Jzxc22ZIHGAr7hlXK3XhGj")
                // if defined
                .clientKey("tbWlUY5QuWWOcG70dKkVz8P0M4nu9TsI1StifARC")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
