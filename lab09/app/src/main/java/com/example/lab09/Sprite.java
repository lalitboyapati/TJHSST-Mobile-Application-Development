package com.example.lab09;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Sprite extends RectF {
    private int dX, dY,color;
    public Sprite(float left, float top, float right, float bottom, int dX, int dY, int color){
        super(left, top, right, bottom);
        this.dX = dX;
        this.dY = dY;
        this.color = color;
    }

    public Sprite(float left, float top, float right, float bottom){
        this(left,top,right,bottom,1,2, Color.MAGENTA);
    }

    public Sprite(int dX,int dY,int color){
        this(1,1,110,110,dX,dY,color);
    }

    public Sprite(){
        this(2,3,Color.GREEN);
    }
    public void update(int screenWidth, int screenHeight) {
        offset(dX, dY);
        if (left < 0 || right > screenWidth) {
            dX = -dX;
        }
        if (top < 0 || bottom > screenHeight) {
            dY = -dY;
        }
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawCircle(centerX(),centerY(),width()/2,paint);
    }

    public int getdX() {
        return dX;
    }

    public void setdX(int dX) {
        this.dX = dX;
    }

    public int getdY() {
        return dY;
    }

    public void setdY(int dY) {
        this.dY = dY;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}

