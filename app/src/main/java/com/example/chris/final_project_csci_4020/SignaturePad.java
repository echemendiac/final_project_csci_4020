package com.example.chris.final_project_csci_4020;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class SignaturePad extends android.support.v7.widget.AppCompatImageView {
    private int lineSize; //Stores the line thickness
    private int my_color;
    private Bitmap bm;
    private Bitmap altered;
    private Canvas canvas;
    private Paint paint;
    private Matrix matrix;
    private float downx;
    private float downy;
    private float upx;
    private float upy;
    private String currentMode;
    private float radius;
    private float height;
    private float width;
    private float xAxis;
    private float yAxis;
    private float tempUx;
    private float tempUy;
    private float tempDx;
    private float tempDy;


    public SignaturePad(Context context){
        super(context);
        setup(null);
    }
    public SignaturePad(Context context, AttributeSet attrs){
        super(context, attrs);
        setup(attrs);
    }
    public SignaturePad(Context context, AttributeSet attrs, int defaultStyleAttr){
        super(context, attrs,defaultStyleAttr);
        setup(attrs);
    }

    private void setup(AttributeSet attrs) {
        lineSize = 6; //Stores the line thickness
        my_color = Color.BLACK;
        downx = 0;
        downy = 0;
        upx = 0;
        upy = 0;

        bm = BitmapFactory.decodeResource(getResources(), R.drawable.signature);
        altered = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), bm.getConfig());

        canvas = new Canvas(altered);
        paint = new Paint();
        paint.setColor(my_color);
        paint.setStrokeWidth(lineSize);
        matrix = new Matrix();
        canvas.drawBitmap(bm, matrix, paint);
        setImageBitmap(altered);

        height = canvas.getHeight();
        width = canvas.getWidth();
        xAxis = (canvas.getWidth() / 2);
        yAxis = (canvas.getHeight() / 2);
    }

    public void setUx(float nUx){
        upx = nUx;
    }
    public void setUy(float nUy){
        upy = nUy;
    }
    public void setDx(float nDx){
        downx = nDx;
    }
    public void setDy(float nDy){
        downy = nDy;
    }

    public void drawSomething(){
        canvas.drawLine(downx, downy, upx, upy, paint);
        invalidate();
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downx = event.getX();
                setDx(downx);
                downy = event.getY();
                setDy(downy);
                break;
            case MotionEvent.ACTION_MOVE:
                upx = event.getX();
                setUx(upx);
                upy = event.getY();
                setUy(upy);
                drawSomething();

                downx = upx;
                setDx(upx);
                downy = upy;
                setDy(upy);
                break;
            case MotionEvent.ACTION_UP:
                upx = event.getX();
                setUx(upx);
                upy = event.getY();
                setUy(upy);

                drawSomething();
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                break;

        }
        return true;
    }
}
