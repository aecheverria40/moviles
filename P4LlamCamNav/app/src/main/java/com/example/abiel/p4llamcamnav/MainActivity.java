package com.example.abiel.p4llamcamnav;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.Build;
import android.widget.Toast;
import android.content.pm.PackageManager;

import java.util.NavigableMap;

public class MainActivity extends AppCompatActivity {
    Button btnCall, btnCamera, btnNav;
    private static final int CAMERA_PIC_REQUEST = 1337;
    private static final int PERMISSIONS_REQUEST_PHONE_CALL = 100;
    private static String[] PERMISSIONS_PHONECALL = {Manifest.permission.CALL_PHONE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCall = (Button) findViewById(R.id.call);
        btnCamera = (Button) findViewById(R.id.camera);
        btnNav = (Button) findViewById(R.id.navigation);

        final Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final Intent navigation = new Intent();
        final Intent call = new Intent(Intent.ACTION_DIAL);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(camera, CAMERA_PIC_REQUEST);
            }
        });

        btnNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigation.setAction(Intent.ACTION_VIEW);
                navigation.addCategory(Intent.CATEGORY_BROWSABLE);
                navigation.setData(Uri.parse("https://www.stackoverflow.com"));
                startActivity(navigation);
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                call.setData(Uri.parse("tel:6642952892"));
//                startActivity(call);
                call();
            }
        });
    }

    private void call() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PERMISSIONS_REQUEST_PHONE_CALL);
        } else {
            //Open call function
            String number = "6642952892";
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + number));
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_PHONE_CALL) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                call();
            } else {
                Toast.makeText(this, "Sorry!!! Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
