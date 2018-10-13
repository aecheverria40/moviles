package com.company.alejandro.p8basededatos;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity implements IOutput{


    private GridView gridView;
    public ArrayList<String> ArrayofName = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_main,ArrayofName));

        DataBaseHelper db = new DataBaseHelper(this);


        Contact juan = new Contact(1,"Juan","91");
        Contact maria = new Contact(2,"Maria","99");
        Contact luis = new Contact(3,"Luis","95");
        Contact ana = new Contact(24,"Ana","93");
        db.addContact(juan);
        db.addContact(maria);
        db.addContact(luis);
        db.addContact(ana);

        db.deleteContact(luis);

        List<Contact> contacts = db.getAllContact();
        for (Contact cn : contacts){
            String cont = cn.getName() + "\n"+ cn.getPhoneNumber();
            ArrayofName.add(cont);
        }

        ArrayofName.add("Cuenta: "+ db.getContactsCount());

        db.updateContact(2,"Carlos","100");
        Contact consulta=db.getContact(2);
        ArrayofName.add(consulta.getName()+"\n"+consulta.getPhoneNumber());
        ListView listView = getListView();
        listView.setTextFilterEnabled(true);



    }



    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        String selectedValue = (String) getListAdapter().getItem(position);
        Toast.makeText(this,selectedValue,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void imprimir() {

    }

    @Override
    public void consulta() {

    }
}
