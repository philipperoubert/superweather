package com.sn660016793.weatherapp;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Location extends DialogFragment implements View.OnClickListener{
    Button ok;
    Communicator communicator;
    EditText location;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        communicator = (Communicator) activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState) {
        View view = inflater.inflate(R.layout.location,null);
        ok = (Button) view.findViewById(R.id.ok);
        ok.setOnClickListener(this);
        location = (EditText) view.findViewById(R.id.location);
        getDialog().setTitle("Change location");
        return view;
    }

    @Override
    public void onClick(View view) {

        communicator.onDialogMessage(location.getText().toString());
        dismiss();


    }

    interface Communicator
    {
        void onDialogMessage(String message);
    }


}

