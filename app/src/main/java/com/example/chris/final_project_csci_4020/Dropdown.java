package com.example.chris.final_project_csci_4020;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Dropdown extends android.support.v7.widget.AppCompatSpinner {
    int ViewId; // R.id.spinner
    int arrayId; // R.array.text
    Spinner spinner;

    public Dropdown(Context context, int ViewId, int arrayId) {
        super(context);
//        Spinner username_s = findViewById(R.id.username_s);
//        ArrayAdapter<String> username_aa = new ArrayAdapter<String>(
//                MainActivity.this,
//                R.layout.list_item ,
//                getResources().getStringArray(R.array.usernames));
//        username_aa.setDropDownViewResource(R.layout.dropdown_item);
//        username_s.setAdapter(username_aa);
//        username_s.setOnItemSelectedListener(new MainActivity.UsernameListner());
        this.ViewId = ViewId;
        this.arrayId = arrayId;
        spinner = findViewById(ViewId);
        ArrayAdapter<String> spinner_aa = new ArrayAdapter<>(
                context,
                R.layout.list_item,
                getResources().getStringArray(arrayId)
        );
        spinner_aa.setDropDownViewResource(R.layout.dropdown_item);
        spinner.setAdapter(spinner_aa);

    }
    public Spinner getSpinner (){
        return spinner;
    }
}
