package com.example.lab10;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawView extends SurfaceView implements Runnable {
    private boolean mRunning;
    private Thread mGameThread = null;
    private SurfaceHolder mSurfaceHolder;
    private Context mContext;
    private CustomAnimation mCustomAnimation;

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mSurfaceHolder = getHolder();
        mCustomAnimation = new CustomAnimation(mSurfaceHolder, context);
    }

    @Override
    public void run() {
        Canvas canvas;
        long frameStartTime;
        long frameTime;
        final int FPS = 60;

        while (mRunning) {
            if (mSurfaceHolder == null) {
                return;
            }
            if (mSurfaceHolder.getSurface().isValid()) {
                frameStartTime = System.nanoTime();
                canvas = mSurfaceHolder.lockCanvas();
                if (canvas != null) {
                    canvas.save();
                    canvas.drawARGB(255, 0, 0, 0);
                    mCustomAnimation.update();
                    mCustomAnimation.draw(canvas);
                    canvas.restore();
                    mSurfaceHolder.unlockCanvasAndPost(canvas);
                }
                frameTime = (System.nanoTime() - frameStartTime) / 1000000;

                if (frameTime < (1000 / FPS)) {
                    try {
                        Thread.sleep((int) (1000 / FPS) - frameTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void pause() {
        mRunning = false;
        try {
            mGameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        mRunning = true;
        mGameThread = new Thread(this);
        mGameThread.start();
    }
}
