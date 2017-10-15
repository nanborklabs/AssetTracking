package com.versalite.assettracking;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.versalite.assettracking.Activity.SignInActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 13/9/17.
 *
 */

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.sp_title)
    OpenSansTextView spTitle;
    @BindView(R.id.sp_sub)
    WhitenyBooksFont spSub;
    @BindView(R.id.sp_bottom)
    WhitenyBooksFont spBottom;
    private long SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        ButterKnife.bind(this);
        startAnimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, SignInActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }



    private void startAnimation() {
        spTitle.animate().alpha(1).setInterpolator(new DecelerateInterpolator()).setDuration(500).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                spSub.animate()
                        .alpha(1f)
                        .setInterpolator(new AccelerateInterpolator(1.5f))
                        .setStartDelay(600)
                        .setDuration(600)
                        .start();

                spBottom.animate()
                        .alpha(1f)
                        .setInterpolator(new AccelerateInterpolator(1.5f))
                        .setStartDelay(1400)
                        .setDuration(600)
                        .start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).start();
    }
}
