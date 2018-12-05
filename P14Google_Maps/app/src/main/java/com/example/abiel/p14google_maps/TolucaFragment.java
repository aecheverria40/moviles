package com.example.abiel.p14google_maps;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class TolucaFragment extends DialogFragment {
    public static final String ARGIUMENTO_TITLE = "TITTLE";
    public static final String ARGUMENTO_FULL_SNNIPET = "FULL_SNNIPET";

    private String title;
    private String fullSnippet;

    public static TolucaFragment newInstance(String title, String fullSnippet){
        TolucaFragment fragment = new TolucaFragment();
        Bundle b = new Bundle();
        b.putString(ARGIUMENTO_TITLE, title);
        b.putString(ARGUMENTO_FULL_SNNIPET, fullSnippet);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        Bundle args = getArguments();
        title = args.getString(ARGIUMENTO_TITLE);
        fullSnippet = args.getString(ARGUMENTO_FULL_SNNIPET);
    }

    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState){
        Dialog dialog = new AlertDialog.Builder(getActivity()).setTitle(title).setMessage(fullSnippet).
                create();
        return dialog;
    }
}
