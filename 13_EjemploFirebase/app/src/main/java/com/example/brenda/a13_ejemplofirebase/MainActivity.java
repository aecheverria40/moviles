package com.example.brenda.a13_ejemplofirebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    //List y Array
    private List<Persona> listPerson=new ArrayList<Persona>();
    ArrayAdapter<Persona> arrayAdapterPersona;

    private EditText edNom,edAp,edCor,edPas;
    private ListView listV_Personas;

    //objetos
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    //Persona
    Persona personaSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edNom= findViewById(R.id.txt_nombrePersona);
        edAp= findViewById(R.id.txt_apellidoPersona);
        edCor= findViewById(R.id.txt_correoPersona);
        edPas= findViewById(R.id.txt_passwordPersona);
        listV_Personas=findViewById(R.id.lv_datosPersonas);

        initializeFirebase();

        //ArrayList
        ListData();

        //Update
        listV_Personas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                personaSelected = (Persona) adapterView.getItemAtPosition(i);
                edNom.setText(personaSelected.getNombre());
                edAp.setText(personaSelected.getApellido());
                edCor.setText(personaSelected.getCorreo());
                edPas.setText(personaSelected.getPassword());
            }
        });
    }

    private void ListData(){
        databaseReference.child("Persona").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPerson.clear();
                for (DataSnapshot objSnappShot:dataSnapshot.getChildren()){
                    Persona p =objSnappShot.getValue(Persona.class);
                    listPerson.add(p);
                    arrayAdapterPersona= new ArrayAdapter<Persona>(MainActivity.this,android.R.layout.simple_list_item_1,listPerson);
                    listV_Personas.setAdapter(arrayAdapterPersona);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String nombre=edNom.getText().toString();
        String apellido=edAp.getText().toString();
        String correo=edCor.getText().toString();
        String password=edPas.getText().toString();
        switch (item.getItemId()){
            case R.id.icon_add:
                if(nombre.equals("")||apellido.equals("")||correo.equals("")||password.equals("")){
                    validation();
                }else {
                    Persona p=new Persona();
                    p.setUid(UUID.randomUUID().toString());
                    p.setNombre(nombre);
                    p.setApellido(apellido);
                    p.setCorreo(correo);
                    p.setPassword(password);
                    databaseReference.child("Persona").child(p.getUid()).setValue(p);

                    Toast.makeText(this,"Agregar",Toast.LENGTH_LONG).show();
                    clearText();
                }
                break;
            case R.id.icon_save:
                Persona p = new Persona();
                p.setUid(personaSelected.getUid());
                p.setNombre(edNom.getText().toString().trim());
                p.setApellido(edAp.getText().toString().trim());
                p.setCorreo(edCor.getText().toString().trim());
                p.setPassword(edPas.getText().toString().trim());
                databaseReference.child("Persona").child(p.getUid()).setValue(p);
                Toast.makeText(this,"Gruardar",Toast.LENGTH_LONG).show();
                clearText();
                break;
            case R.id.icon_delete:
                Toast.makeText(this,"Eliminar",Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        return true;
    }

    public void clearText(){
        // limpiar campos
        edNom.setText("");
        edAp.setText("");
        edCor.setText("");
        edPas.setText("");
    }

    private void validation(){
        String nombre = edNom.getText().toString();
        String apellido = edAp.getText().toString();
        String correo = edCor.getText().toString();
        String password = edPas.getText().toString();
        if(nombre.equals("")){
            edNom.setError("Requerido");
        }else if (apellido.equals("")){
            edAp.setError("Requerido");
        }else if(correo.equals("")){
            edCor.setError("Requerido");
        }else if(password.equals("")){
            edPas.setError("Requerido");
        }
    }

    //Inicializar firebase
    private void initializeFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        //Persistencia de datos
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference=firebaseDatabase.getReference();
    }

}
