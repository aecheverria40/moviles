package com.example.abiel.hello_world;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button hello_Button;
    Button world_Button;
    TextView text_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hello_Button = (Button) findViewById(R.id.hello);
        world_Button = (Button)findViewById(R.id.world);
        text_message = (TextView)findViewById(R.id.message);

        hello_Button.setOnClickListener(Listener);
        world_Button.setOnClickListener(Listener);

    }


    private View.OnClickListener Listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Context context = getApplicationContext();
            CharSequence text = "Hello World!";
            int duration = Toast.LENGTH_SHORT;
            if (view.getId() == R.id.hello)
            {
                text_message.setText("Hola");
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            else
            {
                text_message.setText("Mundo!");
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    };
}
