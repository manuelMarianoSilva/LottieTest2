package com.example.domiesharpin.lottietest;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    private LottieAnimationView animationView;
    private Button coso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        animationView = findViewById(R.id.animationView);
        coso = findViewById(R.id.coso);

        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            int repeticion = 0;

            @Override
            public void onAnimationStart(Animator animation) {
                Toast.makeText(MainActivity.this, "Start", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(MainActivity.this, "End", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Toast.makeText(MainActivity.this, "Repeat", Toast.LENGTH_SHORT).show();
                repeticion ++;
                if (repeticion > 3){


                        animationView.setAnimation(R.raw.dropping);
                        repeticion = 0;
                        animationView.playAnimation();


                }
            }
        });


        animationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!animationView.isAnimating()){
                    animationView.playAnimation();
                    animationView.loop(true);
                } else {
                    animationView.reverseAnimationSpeed();
                }
            }
        });

        animationView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                animationView.cancelAnimation();
                animationView.setAnimation(R.raw.boxing);
                return true;
            }
        });


        coso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OtraActivity.class));
            }
        });


    }
}
