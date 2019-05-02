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
                        Log.i("-----------------", "gets path");
                        directoryPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/outputEstimates2/";
                        directory = new File(directoryPath);

                        if (!directory.exists()) {
                            Log.i("-----------------", "makes directory");
                            directory.mkdirs();
                        }
                        Log.i("-----------------", "make the pdf");
                        PdfWriter.getInstance(document, new FileOutputStream((estimateStyles.get("name") + ".pdf")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Log.i("-----------------", "open doc");
                    document.open();
                    font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);
                    Chunk chunk = new Chunk(estimateStyles.get("name"), font);
                    Chunk name = new Chunk("Name:" +  estimateStyles.get("name"), font);
                    Chunk email = new Chunk("Email:" +  estimateStyles.get("email"), font);
                    Chunk address = new Chunk("Address:" +  estimateStyles.get("address"), font);
                    Chunk phone = new Chunk("Phone:"+  estimateStyles.get("phone"), font);
                    Chunk city = new Chunk("City: " +  estimateStyles.get("city"), font);
                    Chunk state = new Chunk("State: " +  estimateStyles.get("state"), font);
                    Chunk zip = new Chunk("Zip: " +  estimateStyles.get("zip"), font);


                    Chunk roofType = new Chunk("Roof Type:" +  estimateStyles.get("roofType"), font);
                    Chunk roofColor = new Chunk("Roof Color:" +  estimateStyles.get("roofColor"), font);
                    Chunk roofAmmount = new Chunk("Roof Squares:" +  estimateQuantaties.get("roofSqs"), font);

                    Chunk tearOffAmount = new Chunk("Tear:" +  estimateQuantaties.get("tear"), font);
                    Chunk caps = new Chunk("Caps:" +  estimateQuantaties.get("hip"), font);
                    Chunk ridgeVents = new Chunk("Ridge Vents:" +  estimateQuantaties.get("ridge"), font);
                    Chunk roofVents = new Chunk("Roof Vents:" +  estimateQuantaties.get("vents"), font);
                    Chunk valley = new Chunk("Valley:" +  estimateQuantaties.get("valley"), font);


                    Chunk flashingType = new Chunk("Flashing Type" +  estimateStyles.get("flashingType"), font);
                    Chunk flashing = new Chunk("Flashing amount:" +  estimateStyles.get("chimeny"), font);

                    Chunk shielding = new Chunk("Shielding:" +  estimateQuantaties.get("shield"), font);
                    Chunk sheet = new Chunk("Sheet replacement:" +  estimateQuantaties.get("sheet"), font);
                    Chunk pitch = new Chunk("Pitch:" +  estimateQuantaties.get("pitch"), font);
                    Chunk stories = new Chunk("Stories:" +  estimateQuantaties.get("stories"), font);
                    Chunk edge = new Chunk("Drip edge:" +  estimateQuantaties.get("drip"), font);


                    Chunk sidingType = new Chunk("Siding Type" +  estimateStyles.get("sidingType"), font);
                    Chunk sidingStyle = new Chunk("Siding Style"+  estimateStyles.get("sidingStyle"), font);
                    Chunk sidingAmmounts = new Chunk("Siding Amount"+  estimateQuantaties.get("siding"), font);

                    Chunk fascia = new Chunk("Fascia"+  estimateQuantaties.get("fascia"), font);


                    Chunk gutterSize = new Chunk("Gutter Size" +  estimateStyles.get("gutterSize"), font);
                    Chunk gutterStyle = new Chunk("Gutter Color"+  estimateStyles.get("gutterStyle"), font);
                    Chunk gutterAmmount = new Chunk("Gutter Amount"+  estimateQuantaties.get("gutters"), font);


                    Chunk sidingSize = new Chunk("Siding Size" + estimateStyles.get("sidingSize"), font);
                    Chunk sidingStyleTwo = new Chunk("Siding Color"+  estimateStyles.get("sidingStyleTwo"), font);
                    Chunk sidingTwo = new Chunk("Fascia: "+  estimateQuantaties.get("sidingTwo"), font);

                    Chunk gutterGuards = new Chunk("Gutter Guards: "+  estimateQuantaties.get("gutterGuards"), font);


                    try {
                        Log.i("-----------------", "write to doc");
                        document.add(chunk);
                        document.add(name );
                        document.add(email);
                        document.add(address);
                        document.add(phone);
                        document.add(city);
                        document.add(state);
                        document.add(zip);
                        document.add(roofType);
                        document.add(roofColor);
                        document.add(roofAmmount);
                        document.add(tearOffAmount);
                        document.add(caps);
                        document.add(ridgeVents);
                        document.add(roofVents);
                        document.add(valley);
                        document.add(flashingType);
                        document.add(flashing);
                        document.add(shielding);
                        document.add(sheet);
                        document.add(pitch);
                        document.add(stories);
                        document.add(edge);
                        document.add(sidingType);
                        document.add(sidingStyle);
                        document.add(sidingAmmounts);
                        document.add(fascia);
                        document.add(gutterSize);
                        document.add(gutterStyle);
                        document.add(gutterAmmount);
                        document.add(sidingSize);
                        document.add(sidingStyleTwo);
                        document.add(sidingTwo);
                        document.add(gutterGuards);
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

//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String[] permissions, int[] grantResults) {
//        switch (requestCode) {
//            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE:
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    // do your stuff
//                } else {
//                    //Toast.makeText(Login.this, "GET_ACCOUNTS Denied",
//                    //Toast.LENGTH_SHORT).show();
//                }
//                break;
//            default:
//                super.onRequestPermissionsResult(requestCode, permissions,
//                        grantResults);
//        }
//    }

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
