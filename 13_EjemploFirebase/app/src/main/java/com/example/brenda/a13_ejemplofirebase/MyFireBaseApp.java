package com.example.brenda.a13_ejemplofirebase;
import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class MyFireBaseApp extends Application{
    public void onCreate(){
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
