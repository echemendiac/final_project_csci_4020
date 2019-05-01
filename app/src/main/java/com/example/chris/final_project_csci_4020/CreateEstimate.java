package com.example.chris.final_project_csci_4020;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
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
import android.widget.TextView;

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

    private Button submitB;

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

        //Spinners act like drop downs

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

        submitB = (Button) this.findViewById(R.id.submitEstimate_b);
        submitB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getSignature = new Intent(getApplicationContext(), GetSignature.class);
                startActivity(getSignature);
            }
        });
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
        LinearLayout ll = null;
        for (int j = 0; j <= NUM_LINES; j++) {
            //Below creates a new string that matches the button id
            //Then a variable for resource id
            String buttonID = "removeb" + (j + 1);
            String lineID = "formll" + (j+1);

            int bresourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
            int lresourceID = getResources().getIdentifier(lineID, "id", getPackageName());
            if (bresourceID == v.getId()) { // if the id from the array produces the button id then
                ll = findViewById(lresourceID);
            }
        }
        Log.i("Alert Dialogue Button","Alert Dialogue was called");
        //setup a textView to be used inside the alert dialog
        String titleString = "Logout";
        TextView textView = new TextView(CreateEstimate.this);
        textView.setTextSize(40);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextColor(Color.YELLOW);
        textView.setPadding(40,40,40,40);
        textView.setText("Delete this Line, Are you sure?");

        //Allow the text view to scroll by setting it as a child of a scrollview
//        ScrollView scrollView = new ScrollView(v.getContext());
//        scrollView.addView(textView);

        //only final variables can be inputted into an inner class.
        // So I just saved the generated layout as a final layout.
        final LinearLayout lly  = ll;

        //Create alert dialog and set up attributes
        new AlertDialog.Builder(CreateEstimate.this)
                //set the message of the alert dialog to be a view
                .setView(textView)
//                .setTitle(titleString)


                .setPositiveButton(android.R.string.yes
                        , new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if(lly != null)
                                lly.setVisibility(View.INVISIBLE);
                                else
                                    Log.i("onclick", "Line Linear Layout is null");
                            }
                            }
                        )
                .setNegativeButton(android.R.string.no
                        , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Nothing should happen except the window screen closes.
                            }
                        })

                .show();
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
