package com.example.lab10;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class CustomAnimation {

    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;
    private int xPos, yPos, speedX, speedY;

    public CustomAnimation(SurfaceHolder surfaceHolder, Context context) {
        mSurfaceHolder = surfaceHolder;
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        xPos = 0;
        yPos = 0;
        speedX = 5;
        speedY = 5;
    }

    public void update() {
        xPos += speedX;
        yPos += speedY;
        if (xPos <= 0 || xPos >= mSurfaceHolder.getSurfaceFrame().width()) {
            speedX = -speedX;
        }
        if (yPos <= 0 || yPos >= mSurfaceHolder.getSurfaceFrame().height()) {
            speedY = -speedY;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(xPos, yPos, 50, mPaint); // Adjust the radius as needed
    }
}
