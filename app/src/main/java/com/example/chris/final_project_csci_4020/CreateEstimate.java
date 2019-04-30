package com.example.chris.final_project_csci_4020;

import android.content.Context;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class CreateEstimate extends AppCompatActivity {
    Spinner     roofType_s,
                roofColor_s,
                pipeFlahsingColor_s,
                sidingType_s,
                sidingColor_s,
                gutterSize_s,
                gutterColor_s,
                downspoutSize_s,
                downspoutColor_s;
    final int NUM_LINES = 17;

    Bundle bundle; //store information passed from the first activity
    MainActivity.User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_estimate);

        // Getting the intent that started and pulling out the key
        bundle = getIntent().getExtras();
        int i = bundle.getInt("USER_MODE");
        Log.i("OnCreate", "The User mode is: "+ i);

        user = MainActivity.User.ERROR;
        //Then using a method from user to set itself to the correct value
        user = user.getEnum(i);

        //Check mode
        checkMode();

        //Spinneers act like drop downs

        //---- Setting Up Spinners ----//
        setupSpinner(roofType_s, R.id.roofType_s, R.array.roofType);
        setupSpinner(roofColor_s,R.id.roofColor_s,R.array.shingleRoofColors);
        setupSpinner(pipeFlahsingColor_s, R.id.pipeFlashing_s, R.array.gutterColors);
        setupSpinner(sidingType_s, R.id.sidingType_et,R.array.sidingType);
        setupSpinner(sidingColor_s,R.id.sidingColor_s,R.array.gutterColors);
        setupSpinner(gutterSize_s, R.id.gutterType_s, R.array.gutterSizes);
        setupSpinner(gutterColor_s, R.id.gutterColor_s, R.array.gutterColors);
        setupSpinner(downspoutSize_s, R.id.downspoutType_s, R.array.downspoutSize);
        setupSpinner(downspoutColor_s, R.id.downspoutColor_s, R.array.gutterColors);



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

    /**
     * This function removes a line from being an entry
     * @param v view passed in by the remove button pressed.
     */
    public void removeLine(View v) {
        for (int j = 0; j <= NUM_LINES; j++) {
            //Below creates a new string that matches the button id
            //Then a variable for resource id
            String buttonID = "removeb" + (j + 1);
            String lineID = "formll" + (j+1);

            int bresourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
            int lresourceID = getResources().getIdentifier(lineID, "id", getPackageName());
            if (bresourceID == v.getId()) { // if the id from the array produces the button id then
                LinearLayout ll = findViewById(lresourceID);
                ll.setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * Check the mode that was passed with the bundle. Then disable/enable features for ot
     */
    private void checkMode(){
        if (user == user.KIOSK){
            findViewById(R.id.estimateForm_ll).setVisibility(View.INVISIBLE);
        }else if (user != user.SALES){
            Log.i("Mode Check", "Wrong mode. In mode: " + user.toString());
        }else
            findViewById(R.id.estimateForm_ll).setVisibility(View.VISIBLE);
    }

}
