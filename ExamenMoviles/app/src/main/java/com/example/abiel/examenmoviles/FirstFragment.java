package com.example.abiel.examenmoviles;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {
    private EditText tbj_nombre;
    private Button enviar, anterior, siguiente;

    private static final String TAG = "etiqueta";
    private static final String KEY_INDEX = "indice";

    private int mCurrentIndex = 0;

    private TrabajadorModel [] trabajadorModels = new TrabajadorModel[]{
            new TrabajadorModel(1, "Jose", "Garcia"),
            new TrabajadorModel(2, "Brenda", "Barboza"),
            new TrabajadorModel(3, "Abiel", "Villegas")
    };

    private void showTrabajador(){
        tbj_nombre.setText(trabajadorModels[mCurrentIndex].getNombre() + " " +  trabajadorModels[mCurrentIndex].getApellido());

    }

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_first, container, false);
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        tbj_nombre = (EditText) view.findViewById(R.id.trabajador_nombre);
        anterior = (Button) view.findViewById(R.id.anterior);
        siguiente = (Button) view.findViewById(R.id.siguiente);
        enviar = (Button) view.findViewById(R.id.enviar);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex=(mCurrentIndex+1)%(trabajadorModels.length);
                showTrabajador();
            }
        });

        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex != 0){
                    mCurrentIndex=(mCurrentIndex-1)%(trabajadorModels.length);
                    showTrabajador();
                }
                else{
                    mCurrentIndex = trabajadorModels.length-1;
                    showTrabajador();
                }
            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new SecondFragment(), null).addToBackStack(null).commit();
            }
        });

        if (savedInstanceState != null){
            mCurrentIndex=savedInstanceState.getInt(KEY_INDEX, 0);
        }
        showTrabajador();
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

}
