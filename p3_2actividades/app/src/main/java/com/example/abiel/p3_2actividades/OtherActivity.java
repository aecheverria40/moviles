package com.example.abiel.p3_2actividades;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.app.Activity.RESULT_OK;

public class OtherActivity {
    //constante
    private static final String SEND_MESSAGE="message";
    private static final String NAME="JOSE";
    private static final String EXTRA_NAME_SHOWN="extraName";
    //variable
    private Button mButton;
    private TextView mMessage;
    private TextView mName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        mMessage = findViewById(R.id.message);
        mButton = findViewById(R.id.show_button);
        mName = findViewById(R.id.name_textView);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = getIntent().getStringExtra(SEND_MESSAGE);
                mMessage.setText(message);
                mName.setText(NAME);
                sendbackName(NAME);
            }
        });
    }
    public void sendbackName(String name){
        Intent data = new Intent();
        //Se le asignas el valor de la variable nombre a al constante
        data.putExtra(EXTRA_NAME_SHOWN,name);
        setResult(RESULT_OK,data);
    }
    //metodo que se manda a llamar desde el main para otbtener el valor que se guardo en la variable name
    public static String wasNameShown(Intent result){return result.getStringExtra(EXTRA_NAME_SHOWN);}

    public static Intent newIntent(Context packageContext, String message){
        Intent i = new Intent(packageContext,OtherActivity.class);
        i.putExtra(SEND_MESSAGE,message);
        return i;
    }
}
