package com.example.abiel.p2mvc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mStudentTextView;
    private Button mNextButton;
    private Button mPreviousButton;
    private final static String TAG = "etiqueta";
    private final static String KEY_INDEX = "indice";

    private int mCurrentIndex = 0;

    //(MODELO)
    private Student [] mStudents = new Student[]{
            new Student(111,"Brenda", 80),
            new Student(222,"Abiel", 100),
            new Student(333,"Carlos", 50)
    };

    //ACTUALIZACION DE VISTA CON MODELO
    private void updateStudent(){
        mStudentTextView.setText(mStudents[mCurrentIndex].getmNoControl() + "\n" +
        mStudents[mCurrentIndex].getmScore() + "\n" +
        mStudents[mCurrentIndex].getmName());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //(CONECTAR VISTA CON CONTROLADOR)
        mStudentTextView=(TextView)findViewById(R.id.student_textview);
        mNextButton=(Button) findViewById(R.id.next_button);
        mPreviousButton=(Button) findViewById(R.id.previous_button);

        //Tarea Boton aterior
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex=(mCurrentIndex+1)%(mStudents.length);
                updateStudent();
            }
        });

        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex != 0){
                    mCurrentIndex=(mCurrentIndex-1)%(mStudents.length);
                    updateStudent();
                }
                else{
                    mCurrentIndex = mStudents.length-1;
                    updateStudent();
                }
            }
        });

        if (savedInstanceState != null){
            mCurrentIndex=savedInstanceState.getInt(KEY_INDEX, 0);
        }
        updateStudent();
        Log.d(TAG,"onCreate() llamado");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG,"onSavedInstanceState() llamado");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }
}
