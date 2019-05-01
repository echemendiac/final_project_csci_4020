package com.example.chris.final_project_csci_4020;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Dimension;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class SignaturePad extends View implements OnTouchListener {
    private int lineSize;
    private int my_color;
    private Paint paint;
    private Path signature;



    private int currentWidth;
    private int currentHeight;


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
        signature = new Path();

        paint = new Paint();
        paint.setColor(my_color);
        paint.setStrokeWidth(lineSize);
        paint.setStyle(Paint.Style.STROKE);
        this.setOnTouchListener(this);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(signature, paint);
    }

    public boolean onTouch(View v, MotionEvent event) {
        float xPos = event.getX();
        float yPos = event.getY();

        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                signature.moveTo(xPos, yPos);
                return true;
            case MotionEvent.ACTION_MOVE:
                signature.lineTo(xPos, yPos);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;

        }
        invalidate();
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);
        currentWidth = w;
        currentHeight = h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int desiredWidth = 100;
        int desiredHeight = 100;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(desiredWidth, widthSize);
        } else {
            width = desiredWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(desiredHeight, heightSize);
        } else {
            height = desiredHeight;
        }

        setMeasuredDimension(width, height);
    }
}
