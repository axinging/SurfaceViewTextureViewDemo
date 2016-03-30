package com.example.xxu42.surfaceviewdemo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

//From http://stackoverflow.com/questions/8772862/surfaceview-flashes-black-on-load
//XWALK-4876, XWALK-6522
public class SurfaceViewDemoActivity extends AppCompatActivity {
    TextureView mTextureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupTextureViewWithScrollXML2();
    }

    void setupSurfaceViewWithScroll() {
        RelativeLayout root;
        root = new RelativeLayout(this);
        GameSurfaceView gameSurfaceView = new GameSurfaceView(this);
        gameSurfaceView.setZOrderOnTop(true);
        root.addView(gameSurfaceView);
        gameSurfaceView.setVisibility(View.VISIBLE);

        ScrollView scroll = new ScrollView(this);
        scroll.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.FILL_PARENT));
        scroll.addView(root);

        setContentView(scroll);
    }

    void setupSurfaceView() {
        RelativeLayout root;
        root = new RelativeLayout(this);
        root.setBackgroundColor(Color.RED);
        SurfaceView surfaceView = new SurfaceView(this);
        surfaceView.setLayoutParams(new ViewGroup.LayoutParams(0, 0));
        root.addView(surfaceView);

        GameSurfaceView view = new GameSurfaceView(this);
        view.setBackgroundColor(Color.TRANSPARENT);
        view.setZOrderOnTop(true);
        root.addView(view);
        setContentView(root);
    }

    void setupTextureView() {
        RelativeLayout root;
        root = new RelativeLayout(this);
        mTextureView = new TextureView(this);
        mTextureView.setSurfaceTextureListener(new MySurfaceTextureListener());
        //RelativeLayout rl = (RelativeLayout) findViewById(R.id.my_container);
        //root.setBackgroundColor(Color.RED);
        root.addView(mTextureView);
        setContentView(root);
    }

    void setupTextureViewWithScroll() {
        LinearLayout root;
        root = new LinearLayout(this);
        ;// new RelativeLayout(this);
        mTextureView = new TextureView(this);
        mTextureView.setSurfaceTextureListener(new MySurfaceTextureListener());
        //RelativeLayout rl = (RelativeLayout) findViewById(R.id.my_container);
        //root.setBackgroundColor(Color.RED);
        root.addView(mTextureView);

        ScrollView scroll = new ScrollView(this);
        scroll.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.FILL_PARENT));
        scroll.addView(root);
        setContentView(scroll);
    }

    void setupTextureViewWithScrollXML() {
        //http://stackoverflow.com/questions/23500027/sub-classed-textureview-not-scrolling-in-scrollview
        setContentView(R.layout.activity_surface_view_demo);
        mTextureView = (TextureView) findViewById(R.id.textureview);
        mTextureView.setSurfaceTextureListener(new MySurfaceTextureListener());
    }

    void setupTextureViewWithScrollXML2() {
        //http://stackoverflow.com/questions/23500027/sub-classed-textureview-not-scrolling-in-scrollview
        setContentView(R.layout.activity_surface_view_demo_work2);
        mTextureView = (TextureView) findViewById(R.id.textureview);
        mTextureView.setSurfaceTextureListener(new MySurfaceTextureListener());
    }

    private class MySurfaceTextureListener implements TextureView.SurfaceTextureListener {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            Canvas canvas = mTextureView.lockCanvas();
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.WHITE);
            paint.setTextSize(56);
            canvas.drawColor(Color.BLUE);
            canvas.drawText("From TextureView Canvas", 100, 100, paint);
            mTextureView.unlockCanvasAndPost(canvas);
            Log.i("GTV", "GTV::onSurfaceTextureAvailable width=" + width + ";height=" + height);
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture,
                                                int width, int height) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            return true;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            // Do nothing since the SurfaceTexture won't be updated via updateTexImage().
        }
    }
}
