package com.example.chris.final_project_csci_4020;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    public void onClick(View v) {

        Intent intent = null;
        switch (v.getId()){
            case R.id.createEstimate_b:
                intent = new Intent(MainActivity.this,CreateEstimate.class);
                break;
            case R.id.viewEstimate_b:
                break;
            case R.id.viewWebsite_b:
                break;
            default:
                Log.i("Onclick","Unathorized button was clicked.");
        }
        //Create a Bundle to hold information
        Bundle bundle = new Bundle();

        //Store the User Mode in the bundle
        bundle.putInt("USER_MODE", user.getBundleInt());

        //Attach the bundle to intent
        intent.putExtras(bundle);

        if(intent != null)
            //Start the new activity depending on which button was clicked
            startActivity(intent);
        else{
            Log.i("Onclick", "Intent is null");
        }

    }
    private boolean isLoginSession = false; //Boolean for if the user is logged in


    // Holds user information and permissions
    public static enum User {
        KIOSK, SALES, MANAGE, ERROR;

        public User getEnum(int type){
            switch (type){
                case 0:
                    return User.KIOSK;
                case 1:
                    return User.SALES;
                case 2:
                    return User.MANAGE;
                case -999:
                    return User.ERROR;
                default:
                    return User.ERROR;
            }
        }


        /**
         * This returns an int for a bundle to package.
         * When next activity is called. It can create this object with that int
         * @return
         */
        public int getBundleInt(){
            switch (this) {
                case KIOSK:
                    return 0;
                case SALES:
                    return 1;
                case MANAGE:
                    return 2;
                case ERROR:
                    return -999;
                default:
                    return -999;
            }
        }

        /**
         *  returns the password depending on User selected
         */
        public String getPassword(){
            switch(this){
                case KIOSK: return "cup1";
                case SALES: return "cup2";
                case MANAGE: return "cup3";
                default: return "cup4";
            }
        }

    };

    User user; //Variable used for the app mode
    Button createEstimate_b;
    Button viewEstimate_b;
    Button viewWebsite_b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        showLoginScreen(); //show the login screen after app is created

        //---- Initialize Varaibles ----//
        createEstimate_b = findViewById(R.id.createEstimate_b);
        viewEstimate_b = findViewById(R.id.viewEstimate_b);
        viewWebsite_b = findViewById(R.id.viewWebsite_b);

        //Hide Buttons
        createEstimate_b.setVisibility(View.INVISIBLE);
        viewEstimate_b.setVisibility(View.INVISIBLE);
        viewWebsite_b.setVisibility(View.INVISIBLE);

        //Setup OnClickListners
        createEstimate_b.setOnClickListener(this);
        viewEstimate_b.setOnClickListener(this);
        viewWebsite_b.setOnClickListener(this);

        user = User.KIOSK;


        //Setup Spinner
        Spinner username_s = findViewById(R.id.username_s);
        ArrayAdapter<String> username_aa = new ArrayAdapter<String>(
                MainActivity.this,
                R.layout.list_item ,
                getResources().getStringArray(R.array.usernames));
        username_aa.setDropDownViewResource(R.layout.dropdown_item);
        username_s.setAdapter(username_aa);
        username_s.setOnItemSelectedListener(new UsernameListner());


        //This enables the user to hit enter or done in the password field intead of having to hit
        //login
        ((EditText)findViewById(R.id.password_et)).setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        // Identifier of the action. This will be either the identifier you supplied,
                        // or EditorInfo.IME_NULL if being called due to the enter key being pressed.

//                        Log.i("PassWord KeyEvent","Keycode is: " + event.getKeyCode());

                        //Added result so that the done key on the soft keypad would work
                        int result = actionId & EditorInfo.IME_MASK_ACTION;
                        Log.i("Password", "the result is: " + result);
                        if (result == 6 || event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                            login(v);
                            return true;
                        }
                        // Return true if you have consumed the action, else false.
                        return false;
                    }
                });

    }

    /**
     * Onclick function for "login" button
     * @param v button information required for being passed in
     *
     */
    public void login(View v){
        Log.i("login","I have been called");

        if(v.getId() != R.id.logout_b) {

            Toast toast = Toast.makeText(MainActivity.this, "ERROR; Try to Login again", Toast.LENGTH_LONG);
            TextView textView = new TextView(MainActivity.this);
            toast.setView(textView);
            textView.setText("Error: Try to Login again");
            textView.setBackgroundColor(Color.YELLOW);
            textView.setTextColor(Color.RED);
            textView.setTextSize(30);
            textView.setPadding(20,20,20,20);



            EditText et = findViewById(R.id.password_et);

            if (user.getPassword().equals(et.getText().toString())) {
                isLoginSession = true;
                textView.setText("Login succesful");
            } else {
                isLoginSession = false;
                textView.setText("Password is INCORRECT!!");
            }
            toast.show();
            Log.i("login", "Login: " + isLoginSession);
            showMenuButons();
        }else{
            isLoginSession = false;
        }

        showLoginScreen();

    }
    class UsernameListner implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch(position) {
                case 0:
                    user = User.KIOSK;
                    break;
                case 1:
                    user = User.SALES;
                    break;
                case 2:
                    user = User.MANAGE;
                    break;
                default:
                    user = User.ERROR;
            }
            Log.i("selectUser", "User type is: " + user.toString());

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    /**
     * Clears out text for a TextView
     * @param v TextView to have text cleared
     */
    public void clearText(View v){
        Log.i("clearText","I have been called");
        EditText tv = findViewById(v.getId());
        tv.setText("");
    }

    /**
     * method that turns the login screen on or off.
     */
    private void showLoginScreen(){
        ConstraintLayout loginScreen = findViewById(R.id.loginScreen_cl);
        if(isLoginSession == false){
            loginScreen.setVisibility(View.VISIBLE);
            Log.i("showLoginScreen", "Login screen should be visible");
        }else{
            loginScreen.setVisibility(View.INVISIBLE);
        }
    }

    public void showMenuButons(){
        Log.i("showMenuButtons", "User mode is " + user.toString());
        switch(user){
            case KIOSK:
                createEstimate_b.setVisibility(View.VISIBLE);
                viewEstimate_b.setVisibility(View.INVISIBLE);
                viewWebsite_b.setVisibility(View.INVISIBLE);
                break;
            case SALES:
                createEstimate_b.setVisibility(View.VISIBLE);
                viewEstimate_b.setVisibility(View.VISIBLE);
                viewWebsite_b.setVisibility(View.VISIBLE);
                break;
            case MANAGE:
                createEstimate_b.setVisibility(View.INVISIBLE);
                viewEstimate_b.setVisibility(View.VISIBLE);
                viewWebsite_b.setVisibility(View.INVISIBLE);
                break;
            default:
                Log.i("showMenuButtons", "No mode selected");
                createEstimate_b.setVisibility(View.INVISIBLE);
                viewEstimate_b.setVisibility(View.INVISIBLE);
                viewWebsite_b.setVisibility(View.INVISIBLE);
        }
    }


}
