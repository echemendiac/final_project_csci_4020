package com.example.chris.final_project_csci_4020;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class GetSignature extends AppCompatActivity{


    private String fileName;

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

                try {
                    makeDocument();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (BadElementException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    private void makeDocument() throws IOException, BadElementException {
        Document document = new Document();

        Log.i("-----------------", "in make documnet");
        if (checkPermissionWRITE_EXTERNAL_STORAGE(this)) {
            Log.i("-----------------", "past permission check");
            for(int i=0;i<100;i++) {
                if (storagePermitted(GetSignature.this)) {
                    Log.i("-----------------", "past storage check");
                    try {
                        Log.i("-----------------", "gets path");
                        directoryPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/outputEstimates";
                        fileName = directoryPath+estimateStyles.get("name") + ".pdf";
                        directory = new File(directoryPath);

                        if (!directory.exists()) {
                            Log.i("-----------------", "makes directory");
                            directory.mkdirs();
                        }
                        Log.i("-----------------", "make the pdf");

                        PdfWriter.getInstance(document, new FileOutputStream((new File(fileName))));

                    } catch (Exception e) {
                        Log.i("Make Document", "File Not Found Error Code: " + e.toString());
                    }

                    Log.i("-----------------", "open doc");
                    document.open();
                    font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);
                    Paragraph p1 = new Paragraph("Name: " +  estimateStyles.get("name"));
                    Paragraph p2 = new Paragraph("Email: " +  estimateStyles.get("email"));
                    Paragraph p3 = new Paragraph("Address: " +  estimateStyles.get("address"));
                    Paragraph p4 = new Paragraph("Phone: "+  estimateStyles.get("phone"));
                    Paragraph p5 = new Paragraph("City: " +  estimateStyles.get("city"));
                    Paragraph p6 = new Paragraph("State: " +  estimateStyles.get("state"));
                    Paragraph p7 = new Paragraph("Zip: " +  estimateStyles.get("zip"));
                    Paragraph p8 = new Paragraph("Roof Type: " +  estimateStyles.get("roofType"));
                    Paragraph p9 = new Paragraph("Roof Color: " +  estimateStyles.get("roofColor"));
                    Paragraph p10 = new Paragraph("Roof Squares: " +  estimateQuantaties.get("roofSqs"));
                    Paragraph p11 = new Paragraph("Tear: " +  estimateQuantaties.get("tear"));
                    Paragraph p12 = new Paragraph("Caps: " +  estimateQuantaties.get("hip"));
                    Paragraph p13 = new Paragraph("Ridge Vents: " +  estimateQuantaties.get("ridge"));
                    Paragraph p14 = new Paragraph("Roof Vents: " +  estimateQuantaties.get("vents"));
                    Paragraph p15 = new Paragraph("Valley: " +  estimateQuantaties.get("valley"));
                    Paragraph p16 = new Paragraph("Flashing Type " +  estimateStyles.get("flashingType"));
                    Paragraph p17 = new Paragraph("Flashing amount: " +  estimateStyles.get("chimeny"));
                    Paragraph p18 = new Paragraph("Shielding: " +  estimateQuantaties.get("shield"));
                    Paragraph p19 = new Paragraph("Sheet replacement: " +  estimateQuantaties.get("sheet"));
                    Paragraph p20 = new Paragraph("Pitch: " +  estimateQuantaties.get("pitch"));
                    Paragraph p21 = new Paragraph("Stories: " +  estimateQuantaties.get("stories"));
                    Paragraph p22 = new Paragraph("Drip edge: " +  estimateQuantaties.get("drip"));
                    Paragraph p23 = new Paragraph("Siding Type " +  estimateStyles.get("sidingType"));
                    Paragraph p24 = new Paragraph("Siding Style "+  estimateStyles.get("sidingStyle"));
                    Paragraph p25 = new Paragraph("Siding Amount "+  estimateQuantaties.get("siding"));
                    Paragraph p26 = new Paragraph("Fascia "+  estimateQuantaties.get("fascia"));
                    Paragraph p27 = new Paragraph("Gutter Size " +  estimateStyles.get("gutterSize"));
                    Paragraph p28 = new Paragraph("Gutter Color "+  estimateStyles.get("gutterStyle"));
                    Paragraph p29 = new Paragraph("Gutter Amount "+  estimateQuantaties.get("gutters"));
                    Paragraph p30 = new Paragraph("Siding Size " + estimateStyles.get("sidingSize"));
                    Paragraph p31 = new Paragraph("Siding Color "+  estimateStyles.get("sidingStyleTwo"));
                    Paragraph p32 = new Paragraph("Fascia: "+  estimateQuantaties.get("sidingTwo"));
                    Paragraph p33 = new Paragraph("Gutter Guards: "+  estimateQuantaties.get("gutterGuards"));


                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    Image img = Image.getInstance(stream.toByteArray());

                    img.scalePercent(10);



                    try {
                        Log.i("-----------------", "write to doc");
                        document.add(p1);
                        document.add(p2);
                        document.add(p3);
                        document.add(p4);
                        document.add(p5);
                        document.add(p6);
                        document.add(p7);
                        document.add(p8);
                        document.add(p9);
                        document.add(p10);
                        document.add(p11);
                        document.add(p12);
                        document.add(p13);
                        document.add(p14);
                        document.add(p15);
                        document.add(p16);
                        document.add(p17);
                        document.add(p18);
                        document.add(p19);
                        document.add(p20);
                        document.add(p21);
                        document.add(p22);
                        document.add(p23);
                        document.add(p24);
                        document.add(p25);
                        document.add(p26);
                        document.add(p27);
                        document.add(p28);
                        document.add(p29);
                        document.add(p30);
                        document.add(p31);
                        document.add(p32);
                        document.add(p33);

                        document.add(img);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    document.close();
                    Toast toast = Toast.makeText(GetSignature.this, "File Saved", Toast.LENGTH_LONG);
//                    TextView textView = new TextView(GetSignature.this);
//                    toast.setView();
//                    textView.setText("File Saved");
//                    textView.setBackgroundColor(Color.WHITE);
//                    textView.setTextColor(Color.BLACK);
//                    textView.
//                    textView.setTextSize(30);
//                    textView.setPadding(20,20,20,20);

                    toast.show();
                    Log.i("Make Document","Created the document");
                    sendEmail();
                    finish();
                    break;
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
    public void sendEmail() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        try {
            String email = estimateStyles.get("email"); // To line on email
            String subject = "Estimate";
            String message = "" + R.string.emailMessage;
            final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{email});
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
            final Uri uri = Uri.fromFile(new File(fileName));
            if (uri != null) {
                emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
                Log.i("Email", "file is attached");
            } else
                Log.i("Email", "File not found");
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
            this.startActivity(Intent.createChooser(emailIntent, "Sending email..."));
            Log.i("Email", "Email should be sent");

        } catch (Throwable t) {
            Log.i("Send Email", "Error Code: " + t.toString());
            Toast.makeText(this, "Request failed try again: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }

}
