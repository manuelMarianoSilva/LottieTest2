package com.example.domiesharpin.lottietest;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

public class OtraActivity extends AppCompatActivity {

    private LottieAnimationView animationView;
    private Button botonUno, botonDos, botonTres, botonCuatro;
    private float progress;
    private TextView crono;
    private SeekBar seekbar;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otra);

        botonUno = findViewById(R.id.botonUno);
        botonDos = findViewById(R.id.botonDos);
        botonTres = findViewById(R.id.botonTres);
        botonCuatro = findViewById(R.id.botonCuatro);

        crono = findViewById(R.id.crono);

        frameLayout = findViewById(R.id.frameLayout);

        seekbar = findViewById(R.id.seekbar);

        animationView = findViewById(R.id.animationViewOtraActivity);
        animationView.setAnimation(R.raw.spiral_timer);

        progress =  0.0f;


        animationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                animationView.setSpeed(1.0f);

                if (!animationView.isAnimating() && progress == 0.0f){
                    animationView.playAnimation();
                    animationView.setRepeatCount(LottieDrawable.INFINITE);
                } else if (!animationView.isAnimating() && progress > 0.0f && progress < 1.0f){
                    animationView.resumeAnimation();
                } else {
                    animationView.pauseAnimation();
                }
            }
        });



        botonUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationView.setProgress(0.2f);
                animationView.pauseAnimation();
            }
        });

        botonDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationView.setProgress(0.4f);
                animationView.pauseAnimation();
            }
        });

        botonTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationView.setProgress(0.6f);
                animationView.pauseAnimation();
            }
        });

        botonCuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationView.setProgress(0.8f);
                animationView.pauseAnimation();
            }
        });


        animationView.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                    progress = animationView.getProgress();
                    crono.setText(String.valueOf(Math.round(progress * 10)));
                    seekbar.setProgress(Math.round(progress * 100));

            }
        });



        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                animationView.setProgress((float) progress / 100);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        frameLayout.setOnGenericMotionListener(new View.OnGenericMotionListener() {
            @Override
            public boolean onGenericMotion(View v, MotionEvent event) {
                return false;
            }
        });

        animationView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                startActivity(new Intent(OtraActivity.this, UnaMasActivity.class));


                return true;
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();

        if (animationView.getRepeatCount() < 1)
            animationView.setProgress(1.0f);
            animationView.playAnimation();
            animationView.setSpeed(25.0f);
            animationView.reverseAnimationSpeed();
    }
}
