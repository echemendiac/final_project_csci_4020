package com.example.chris.final_project_csci_4020;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

public class GetSignature extends AppCompatActivity{
    private SignaturePad sp;
    private  Button sign_button;

    private HashMap<String, Integer> estimateQuantaties;
    private HashMap<String, String> estimateStyles;

    private Bitmap bm;
    private Font font;

    private String directoryPath;
    private File directory;
    private static final int REQUESTCODE_STORAGE_PERMISSION = 999;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signature_screen);
        sp = (SignaturePad) findViewById(R.id.signature_pad);

        estimateQuantaties = (HashMap<String, Integer>) getIntent().getSerializableExtra("quant");
        estimateStyles = (HashMap<String, String>) getIntent().getSerializableExtra("styles");

        sign_button = (Button) findViewById(R.id.sign_b);
        sign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sp.setDrawingCacheEnabled(true);
                    bm = sp.getDrawingCache();

                } catch(Exception e){
                    e.printStackTrace();
                }

                makeDocument();
            }
        });

    }
    private void makeDocument(){
        Document document = new Document();

        Log.i("-----------------", "in make documnet");
        if (checkPermissionWRITE_EXTERNAL_STORAGE(this)) {
            Log.i("-----------------", "past permission check");
            for(int i=0;i<100;i++) {
                if (storagePermitted(GetSignature.this)) {
                    Log.i("-----------------", "past storage check");
                    try {
                        directoryPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/outputEstimates";
                        directory = new File(directoryPath);

                        if (!directory.exists()) {
                            directory.mkdirs();
                        }

                        PdfWriter.getInstance(document, new FileOutputStream((directoryPath + estimateStyles.get("name") + ".pdf")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    document.open();
                    font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);
                    Chunk chunk = new Chunk(estimateStyles.get("name"), font);


                    try {
                        document.add(chunk);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    document.close();
                }else{
                    Log.i("sign----------","PDF did not save. External Write permission was never granted");
                }
            }
        }
    }

    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;

    public boolean checkPermissionWRITE_EXTERNAL_STORAGE(
            final Context context) {

        Log.i("-----------------", "in make checkPermission");
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            Log.i("-----------------", "in current api version");
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                Log.i("-----------------", "in ContextCompat.checkSelfPermission");
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    Log.i("-----------------", "in ActivityCompat.shouldShowRequestPermissionRationale");
                    showDialog("External storage", context, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                } else {
                    Log.i("-----------------", "else");
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }

    public void showDialog(final String msg, final Context context,
                           final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[] { permission },
                                MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // do your stuff
                } else {
                    //Toast.makeText(Login.this, "GET_ACCOUNTS Denied",
                    //Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions,
                        grantResults);
        }
    }

    private static boolean storagePermitted(Activity activity) {

        Boolean readPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        Boolean writePermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

        if (readPermission && writePermission) {
            return true;
        }

        ActivityCompat.requestPermissions(activity, new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE }, REQUESTCODE_STORAGE_PERMISSION);
        return false;
    }
}
