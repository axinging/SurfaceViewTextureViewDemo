package com.example.xxu42.surfaceviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.util.Log;
import android.view.View;

/**
 * Created by xxu42 on 2/26/2016.
 */
public class GameSurfaceView extends SurfaceView implements
        SurfaceHolder.Callback {

    public static float y = 0;
    public static float x = 0;
    SurfaceHolder mSurfaceHolder;

    public GameSurfaceView(Context context) {
        super(context);
        mSurfaceHolder = this.getHolder();
        mSurfaceHolder.setFormat(PixelFormat.TRANSPARENT);
        mSurfaceHolder.addCallback(this);
        this.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        Log.i("GSV", "GSV::GameSurfaceView");

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i("GSV","GSV::surfaceCreated");
        new CanvasThread().start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        Log.i("GSV","GSV::surfaceChanged width="+width+";height="+height);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    class CanvasThread extends Thread {
        public void run() {
            Canvas canvas = mSurfaceHolder.lockCanvas(null);
            //canvas.drawColor(Color.RED);
            int i = 0;
            //while (i<100000000)
            //    i++;
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawPaint(paint);

            paint.setColor(Color.BLACK);
            paint.setTextSize(100);
            canvas.drawText("Some Text", 10, 250, paint);
            mSurfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
}
