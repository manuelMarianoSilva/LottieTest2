package com.example.domiesharpin.lottietest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

public class UnaMasActivity extends AppCompatActivity {

    private LottieAnimationView animationView;
    private GestureDetector mGestureDetector;
    private int x, y, x1;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_una_mas);

        animationView = findViewById(R.id.animationViewUnaMas);
        textView = findViewById(R.id.muestraDePosicion);

        animationView.setAnimation(R.raw.spiral_timer);
        animationView.setRepeatCount(LottieDrawable.INFINITE);
        animationView.playAnimation();

        mGestureDetector = new GestureDetector(this, new MyGestureListener());



        View.OnTouchListener touchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x = (int)event.getX();
                y = (int)event.getY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: stopAnimationAndGetStartValue(x);
                    case MotionEvent.ACTION_MOVE: changeAnimationProgress(x);
                    case MotionEvent.ACTION_UP:
                }
                return mGestureDetector.onTouchEvent(event);
            }
        };



        animationView.setOnTouchListener(touchListener);

    }

    private void changeAnimationProgress(int x) {
        int z = x - x1;

        float changeProgress = (float) z / 10000;

        float progress = animationView.getProgress();

            animationView.setProgress(progress + changeProgress);



        textView.setText(String.valueOf(progress));



    }

    private void stopAnimationAndGetStartValue(int x) {
        x1 = x;
        animationView.pauseAnimation();
    }


    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }


        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Toast.makeText(UnaMasActivity.this, "onDoubleTap", Toast.LENGTH_SHORT).show();
            if (!animationView.isAnimating()){
                animationView.resumeAnimation();
            } else {
                animationView.pauseAnimation();

            }
            return true;
        }

    }
}
