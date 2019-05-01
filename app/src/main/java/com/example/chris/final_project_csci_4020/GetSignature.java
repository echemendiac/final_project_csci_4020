package com.example.chris.final_project_csci_4020;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

public class GetSignature extends AppCompatActivity {
    private float downx;
    private float downy;
    private float upx;
    private float upy;

    private SignaturePad sp;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signature_screen);

        downx = 0;
        downy = 0;
        upx = 0;
        upy = 0;

        sp = (SignaturePad) this.findViewById(R.id.signature_pad);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downx = event.getX();
                sp.setDx(downx);
                downy = event.getY();
                sp.setDy(downy);
                break;
            case MotionEvent.ACTION_MOVE:
                upx = event.getX();
                sp.setUx(upx);
                upy = event.getY();
                sp.setUy(upy);
                sp.drawSomething();

                downx = upx;
                sp.setDx(upx);
                downy = upy;
                sp.setDy(upy);
                break;
            case MotionEvent.ACTION_UP:
                upx = event.getX();
                sp.setUx(upx);
                upy = event.getY();
                sp.setUy(upy);

                sp.drawSomething();
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                break;

        }
        return true;
    }

    public void sign(View view) {
    }
}
