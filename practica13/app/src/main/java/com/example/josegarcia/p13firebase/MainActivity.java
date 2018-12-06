package com.example.josegarcia.p13firebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Persona> listPersona = new ArrayList<>();
    ArrayAdapter<Persona> personaArrayAdapter;

    EditText edNombre;
    EditText edapellido;
    EditText edcorreo;
    EditText edpassword;

    private ListView listV_Persona;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edNombre = findViewById(R.id.txt_nombrePersona);
        edapellido = findViewById(R.id.txt_apellidoPersona);
        edcorreo = findViewById(R.id.txt_correoPersona);
        edpassword = findViewById(R.id.txt_passwordPerona);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        String nombre = edNombre.getText().toString();
        String apellido =edapellido.getText().toString();
        String correo = edcorreo.getText().toString();
        String password = edpassword.getText().toString();
        //switch (menu.)



        return super.onCreateOptionsMenu(menu);
    }

    private void validation(){
        String nombre = edNombre.getText().toString();
        String apellido = edapellido.getText().toString();
        String correo = edcorreo.getText().toString();
        String password = edpassword.getText().toString();
        if(nombre.equals("")){
            edNombre.setError("Requerido");
        }
        else if(apellido.equals("")){
            edapellido.setError("Requerido");
        }else if(correo.equals("")){
            edcorreo.setError("Requerido");
        }else if(password.equals("")){
            edpassword.setError("Requerido");
        }
    }

    private void clearText(){
        edNombre.setText("");
        edapellido.setText("");
        edcorreo.setText("");
        edpassword.setText("");
    }

    private  void listData(){
        databaseReference.child("Persona");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPersona.clear();
                for (DataSnapshot objSnapshot :
                        dataSnapshot.getChildren()) {
                    Persona p = objSnapshot.getValue(Persona.class);
                    listPersona.add(p);
                    personaArrayAdapter = new ArrayAdapter<Persona>(MainActivity.this, android.R.)

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
