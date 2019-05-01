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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;

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

    private HashMap<String, Integer> estimateQuantaties;
    private HashMap<String, String> estimateStyles;

    private EditText cusName;
    private EditText email;
    private EditText address;
    private EditText phone;
    private EditText city;
    private EditText state;
    private EditText zip;

    private Spinner roofType;
    private Spinner roofColor;

    private EditText roofSquares;
    private EditText tearOff;
    private EditText hipRdgeCap;
    private EditText ridgeVents;
    private EditText roofVents;
    private EditText valley;

    private Spinner chimenyWallFlashingType;

    private EditText chimenyWallFlashing;
    private EditText iceWaterShield;
    private EditText sheetReplacement;
    private EditText roofPitch;
    private EditText stories;
    private EditText dripEdge;

    private Spinner sidingType;
    private Spinner sidingStyle;

    private EditText siding;
    private EditText fasciaBoards;

    private Spinner gutterSize;
    private Spinner gutterStyle;

    private EditText gutters;

    private Spinner sidingSize;
    private Spinner sidingStyleTwo;

    private EditText sidingTwo;
    private EditText gutterGaurds;


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

        estimateQuantaties = new HashMap<String, Integer>();
        estimateStyles = new HashMap<String, String>();

        cusName = (EditText) findViewById(R.id.ownerName_et);
        email = (EditText) findViewById(R.id.email_et);
        address = (EditText) findViewById(R.id.street_et);
        phone = (EditText) findViewById(R.id.phoneNum_et);
        city = (EditText) findViewById(R.id.city_et);
        state = (EditText) findViewById(R.id.state_et);
        zip = (EditText) findViewById(R.id.zipcode_et);
        roofType = (Spinner) findViewById(R.id.roofType_s);
        roofColor = (Spinner) findViewById(R.id.roofColor_s);
        roofSquares = (EditText) findViewById(R.id.roof_Quatity);
        tearOff = (EditText) findViewById(R.id.tearOff_et);
        hipRdgeCap = (EditText) findViewById(R.id.hip_et);
        ridgeVents = (EditText) findViewById(R.id.ridge_et);
        roofVents = (EditText) findViewById(R.id.roofVents_et);
        valley = (EditText) findViewById(R.id.valley_et);
        chimenyWallFlashingType = (Spinner) findViewById(R.id.pipeFlashing_s);
        chimenyWallFlashing = (EditText) findViewById(R.id.pipe_et);
        iceWaterShield = (EditText) findViewById(R.id.ice_et);
        sheetReplacement = (EditText) findViewById(R.id.sheet_et);
        roofPitch = (EditText) findViewById(R.id.roofPitch_et);
        stories = (EditText) findViewById(R.id.stories_et);
        dripEdge = (EditText) findViewById(R.id.drip_et);
        sidingType = (Spinner) findViewById(R.id.sidingType_et);
        sidingStyle = (Spinner) findViewById(R.id.sidingColor_s);
        siding = (EditText) findViewById(R.id.sidingQuantity_et);
        fasciaBoards = (EditText) findViewById(R.id.fascia_et);
        gutterSize = (Spinner) findViewById(R.id.gutterType_s);
        gutterStyle = (Spinner) findViewById(R.id.gutterColor_s);
        gutters = (EditText) findViewById(R.id.gutterQuantity);
        sidingSize = (Spinner) findViewById(R.id.downspoutType_s);
        sidingStyleTwo = (Spinner) findViewById(R.id.downspoutColor_s);
        sidingTwo = (EditText) findViewById(R.id.downspoutQuantity);
        gutterGaurds = (EditText) findViewById(R.id.gutterGuard_et);


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
                if(roofSquares.getText().toString().isEmpty()){
                    estimateQuantaties.put("roofSqs", 0);
                }
                else{
                    estimateQuantaties.put("roofSqs", Integer.parseInt(roofSquares.getText().toString()));
                }
                if(tearOff.getText().toString().isEmpty()){
                    estimateQuantaties.put("tear", 0);
                }
                else{
                    estimateQuantaties.put("tear", Integer.parseInt(tearOff.getText().toString()));
                }
                if(hipRdgeCap.getText().toString().isEmpty()){
                    estimateQuantaties.put("hip", 0);
                }
                else{
                    estimateQuantaties.put("hip", Integer.parseInt(hipRdgeCap.getText().toString()));
                }
                if(ridgeVents.getText().toString().isEmpty()){
                    estimateQuantaties.put("ridge", 0);
                }
                else{
                    estimateQuantaties.put("ridge", Integer.parseInt(ridgeVents.getText().toString()));
                }
                if(roofVents.getText().toString().isEmpty()){
                    estimateQuantaties.put("vents", 0);
                }
                else{
                    estimateQuantaties.put("vents", Integer.parseInt(roofVents.getText().toString()));
                }
                if(valley.getText().toString().isEmpty()){
                    estimateQuantaties.put("valley", 0);
                }
                else{
                    estimateQuantaties.put("valley", Integer.parseInt(valley.getText().toString()));
                }
                if(chimenyWallFlashing.getText().toString().isEmpty()){
                    estimateQuantaties.put("chimeny", 0);
                }
                else{
                    estimateQuantaties.put("chimeny", Integer.parseInt(chimenyWallFlashing.getText().toString()));
                }
                if(iceWaterShield.getText().toString().isEmpty()){
                    estimateQuantaties.put("shield", 0);
                }
                else{
                    estimateQuantaties.put("shield", Integer.parseInt(iceWaterShield.getText().toString()));
                }
                if(sheetReplacement.getText().toString().isEmpty() ){
                    estimateQuantaties.put("sheet", 0);
                }
                else{
                    estimateQuantaties.put("sheet", Integer.parseInt(sheetReplacement.getText().toString()));
                }
                if(roofPitch.getText().toString().isEmpty()){
                    estimateQuantaties.put("pitch", 0);
                }
                else{
                    estimateQuantaties.put("pitch", Integer.parseInt(roofPitch.getText().toString()));
                }
                if(stories.getText().toString().isEmpty()){
                    estimateQuantaties.put("stories", 0);
                }
                else{
                    estimateQuantaties.put("stories", Integer.parseInt(stories.getText().toString()));
                }
                if(dripEdge.getText().toString().isEmpty()){
                    estimateQuantaties.put("drip", 0);
                }
                else{
                    estimateQuantaties.put("drip", Integer.parseInt(dripEdge.getText().toString()));
                }
                if(siding.getText().toString().isEmpty()){
                    estimateQuantaties.put("siding", 0);
                }
                else{
                    estimateQuantaties.put("siding", Integer.parseInt(siding.getText().toString()));
                }
                if(fasciaBoards.getText().toString().isEmpty()){
                    estimateQuantaties.put("fascia", 0);
                }
                else{
                    estimateQuantaties.put("fascia", Integer.parseInt(fasciaBoards.getText().toString()));
                }
                if(gutters.getText().toString().isEmpty()){
                    estimateQuantaties.put("gutters", 0);
                }
                else{
                    estimateQuantaties.put("gutters", Integer.parseInt(gutters.getText().toString()));
                }
                if(sidingTwo.getText().toString().isEmpty()){
                    estimateQuantaties.put("sidingTwo", 0);
                }
                else{
                    estimateQuantaties.put("sidingTwo", Integer.parseInt(sidingTwo.getText().toString()));
                }
                if(gutterGaurds.getText().toString().isEmpty()){
                    estimateQuantaties.put("gutterGuards", 0);
                }
                else{
                    estimateQuantaties.put("gutterGuards", Integer.parseInt(gutterGaurds.getText().toString()));
                }

                estimateStyles.put("name", cusName.getText().toString());
                estimateStyles.put("email", email.getText().toString());
                estimateStyles.put("address", address.getText().toString());
                estimateStyles.put("phone", phone.getText().toString());
                estimateStyles.put("city", city.getText().toString());
                estimateStyles.put("state", state.getText().toString());
                estimateStyles.put("zip", zip.getText().toString());
                estimateStyles.put("roofType", roofType.getSelectedItem().toString());
                estimateStyles.put("roofColor", roofColor.getSelectedItem().toString());
                estimateStyles.put("flashingType", chimenyWallFlashingType.getSelectedItem().toString());
                estimateStyles.put("sidingType", sidingType.getSelectedItem().toString());
                estimateStyles.put("sidingStyle", sidingStyle.getSelectedItem().toString());
                estimateStyles.put("gutterSize", gutterSize.getSelectedItem().toString());
                estimateStyles.put("gutterStyle", gutterStyle.getSelectedItem().toString());
                estimateStyles.put("sidingSize", sidingSize.getSelectedItem().toString());
                estimateStyles.put("sidingStyleTwo", sidingStyleTwo.getSelectedItem().toString());

                //String Cats
                String name = "Name:" +  estimateStyles.get("name");
                String email = "Email:" +  estimateStyles.get("email");
                String address = "Address:" +  estimateStyles.get("address");
                String phone = "Phone:"+  estimateStyles.get("phone");
                String city = "City: " +  estimateStyles.get("");
                String state = "State: " +  estimateStyles.get("state");
                String zip = "Zip: " +  estimateStyles.get("zip");
                String roofType = "Roof Type:" +  estimateStyles.get("roofType");
                String roofColor = "Roof Color:" +  estimateStyles.get("roofColor");
                String flashingType = "Flashing Type" +  estimateStyles.get("flashingType");
                String sidingType = "Siding Type" +  estimateStyles.get("sidingType");
                String sidingStyle = "Siding Style"+  estimateStyles.get("sidingStyle");
                String gutterSize = "Gutter Size" +  estimateStyles.get("gutterSize");
                String gutterStyle = "Gutter Color"+  estimateStyles.get("gutterStyle");
                String sidingSize = "Siding Size" + estimateStyles.get("sidingSize");
                String sidingStyleTwo = "Siding Color"+  estimateStyles.get("sidingStyleTwo");

                Intent getSignature = new Intent(getApplicationContext(), GetSignature.class);

                getSignature.putExtra("quant", estimateQuantaties);
                getSignature.putExtra("styles", estimateStyles);
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
