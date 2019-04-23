package com.example.chris.final_project_csci_4020;

import android.content.Intent;
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
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    public void onClick(View v) {

    }
    private boolean isLoginSession = false; //Boolean for if the user is logged in


    // Holds user information and permissions
    static enum User {
        KIOSK, SALES, MANAGE, ERROR;

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

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        user = User.ERROR;

        //---- Initializing varaibles ----//

        //Setup Spinner
        Spinner username_s = findViewById(R.id.username_s);
        ArrayAdapter<String> username_aa = new ArrayAdapter<String>(
                MainActivity.this,
                R.layout.list_item ,
                getResources().getStringArray(R.array.usernames));
        username_aa.setDropDownViewResource(R.layout.dropdown_item);
        username_s.setAdapter(username_aa);
        username_s.setOnItemSelectedListener(new UsernameListner());


        ((EditText)findViewById(R.id.password_et)).setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        // Identifier of the action. This will be either the identifier you supplied,
                        // or EditorInfo.IME_NULL if being called due to the enter key being pressed.
                        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
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

        Toast toast = Toast.makeText(MainActivity.this,"ERROR; Try to Login again",Toast.LENGTH_LONG);
        TextView textView = new TextView(MainActivity.this);
        toast.setView(textView);

        EditText et = findViewById(R.id.password_et);

        if(user.getPassword().equals(et.getText().toString())) {
            isLoginSession = true;
            textView.setText("Login succesful");
        }else{
            isLoginSession = false;
            textView.setText("PassWord is INCORRECT!!");
        }
        toast.show();
        Log.i("login","Login: " + isLoginSession);
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
        TextView tv = findViewById(v.getId());
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


}
