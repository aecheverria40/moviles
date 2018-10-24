package com.example.abiel.p11fragmentactivitycommunication;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.frame_container) != null){
            if (savedInstanceState != null){
                return;
            }
            //Crecion de objeto del fragmento
            MessageFragment messageFragment = new MessageFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().
                    add(R.id.frame_container, messageFragment, null);
            fragmentTransaction.commit();
        }

    }
}
