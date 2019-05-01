package com.example.chris.final_project_csci_4020;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

public class GetSignature extends AppCompatActivity{
    private SignaturePad sp;
    private  Button sign_button;

    private HashMap<String, Integer> estimateQuantaties;
    private HashMap<String, String> estimateStyles;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signature_screen);
        sp = (SignaturePad) findViewById(R.id.signature_pad);

        estimateQuantaties = (HashMap<String, Integer>) getIntent().getSerializableExtra("quant");
        estimateStyles = (HashMap<String, String>) getIntent().getSerializableExtra("styles");

        Log.i("values-----------", "test name: " + estimateStyles.get("name"));

        sign_button = (Button) findViewById(R.id.sign_b);
        sign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sp.setDrawingCacheEnabled(true);
                    Bitmap bitmap = sp.getDrawingCache();

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}
