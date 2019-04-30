package com.example.chris.final_project_csci_4020;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CreateEstimate extends AppCompatActivity {
    Spinner     roofType_s,
                roofColor_s,
                pipeFlahsingColor_s,
                sidingType_s,
                sidingColor_s,
                gutterSize_s,
                gutterColor_s,
                downspoutSize_s;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_estimate);

        //Spinneers act like drop downs

        //---- Setting Up Spinners ----//
        setupSpinner(roofType_s, R.id.roofType_s, R.array.roofType);
        setupSpinner(roofColor_s,R.id.roofColor_s,R.array.shingleRoofColors);
       // setupSpinner(roofColor_s,R.id.roofColor_s,R.array.shingleRoofColors);



//                pipeFlahsingColor_s = new
//                sidingType_s,
//                sidingColor_s,
//                gutterSize_s,
//                gutterColor_s,
//                downspoutSize_s


    }
    private void setupSpinner(Spinner spinner, int viewId, int arrayId){
        final Context context = CreateEstimate.this;
        //Setup Spinner
        spinner = findViewById(viewId);
        ArrayAdapter<String> spinner_aa = new ArrayAdapter<String>(
                context,
                R.layout.list_item ,
                getResources().getStringArray(arrayId));
        spinner_aa.setDropDownViewResource(R.layout.dropdown_item);
        spinner.setAdapter(spinner_aa);
    }

}
