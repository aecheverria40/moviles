package com.example.abiel.p3_2actividades;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mOtherActivityButton;
    private TextView mhello_textView;

    private static final int REQUEST_CODE_NAME=0;
    private static final String TAG="Etiqueta";
    private String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //inflar widgets
        mOtherActivityButton = findViewById(R.id.btn_otherAct);
        mhello_textView = findViewById(R.id.hello_textView);

        mOtherActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //obtener valor del textbox
                String message = mhello_textView.getText().toString();

                Intent i = OtherActivity.newIntent(MainActivity.this,message);

                // Intent i = new Intent(MainActivity.this,OtherActivity.class);
                startActivityForResult(i, REQUEST_CODE_NAME);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        if (resultCode!= Activity.RESULT_OK)
        {
            return;
        }
        if (requestCode==REQUEST_CODE_NAME)
        {
            if (data==null)
            {
                return;
            }
            //Asignancion del valor de la variable name de la clase OtherACtivity a la variable mName de esta clase
            mName=OtherActivity.wasNameShown(data);
            mhello_textView.setText(mName);
        }
        Log.d(TAG,"ActivityResult() llamado");
    }
}
