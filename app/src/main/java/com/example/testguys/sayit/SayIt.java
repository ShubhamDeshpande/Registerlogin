package com.example.testguys.sayit;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Shubham Deshpande on 09-06-2017.
 */

public class SayIt extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);


    }
}
