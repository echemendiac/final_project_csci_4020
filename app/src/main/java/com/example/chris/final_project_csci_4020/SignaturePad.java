package com.example.chris.final_project_csci_4020;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

public class SignaturePad extends android.support.v7.widget.AppCompatImageView {
    private int lineSize;
    private int my_color;
    private Bitmap originalBm;
    private Bitmap bm;
    private Bitmap altered;
    private Canvas canvas;
    private Paint paint;
    private Matrix matrix;
    private float downx;
    private float downy;
    private float upx;
    private float upy;

    private Rect src;
    private Rect dst;
    private int originalWidth;
    private int originalHeight;



    public SignaturePad(Context context){
        super(context);
        setup(null);
    }
    public SignaturePad(Context context, AttributeSet attrs){
        super(context, attrs);
        setup(attrs);
    }
    public SignaturePad(Context context, AttributeSet attrs, int defaultStyleAttr){
        super(context, attrs, defaultStyleAttr);
        setup(attrs);
    }

    private void setup(AttributeSet attrs) {
        lineSize = 6;
        my_color = Color.BLACK;
        downx = 0;
        downy = 0;
        upx = 0;
        upy = 0;

        originalBm = BitmapFactory.decodeResource(getResources(), R.drawable.signature);
        originalHeight = originalBm.getHeight();
        originalWidth = originalBm.getWidth();

        src = new Rect(0, 0, originalWidth, originalHeight);
        dst = new Rect(0, 0, this.getWidth(), this.getHeight());


        altered = Bitmap.createBitmap(this.getWidth(), this.getHeight(), originalBm.getConfig());

        canvas = new Canvas(altered);
        paint = new Paint();
        paint.setColor(my_color);
        paint.setStrokeWidth(lineSize);
        matrix = new Matrix();
        canvas.drawBitmap(originalBm, src, dst, paint);
        setImageBitmap(altered);

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
}
