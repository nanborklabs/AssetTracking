package com.versalite.assettracking.Activity;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.versalite.assettracking.LocationActivity;
import com.versalite.assettracking.R;
import com.versalite.assettracking.ScreenUtil;
import com.versalite.assettracking.StringValidator;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 24/8/17.
 *
 */

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.email_cont)
    LinearLayout mEmailContainer;
    @BindView(R.id.pass_container)
    LinearLayout mPassContainer;

    private static final String TAG = "SIGN In";
    @BindView(R.id.login_title_text)
    TextView mTitle;
    @BindView(R.id.login_email_edit)
    TextInputLayout mEmail;
    @BindView(R.id.login_password)
    TextInputLayout mPass;
    @BindView(R.id.fp)
    TextView mForgot;
    @BindView(R.id.login_btton)
    Button mLoginButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        ButterKnife.bind(this);
        setTransalations();
        startAnimation();

        mLoginButton.setOnClickListener(this);
        mForgot.setOnClickListener(this);
    }

    private void setTransalations() {
        mTitle.setAlpha(0f);
        mEmailContainer.setTranslationY(ScreenUtil.getScreenHeight(this));
        mPassContainer.setTranslationY(ScreenUtil.getScreenHeight(this));
        mForgot.setAlpha(0f);
    }

    private void startAnimation() {
        mTitle.animate().alpha(1).setInterpolator(new DecelerateInterpolator()).setDuration(500).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mEmailContainer.animate()
                        .translationY(0)
                        .setInterpolator(new DecelerateInterpolator(1.5f))
                        .setStartDelay(600)
                        .setDuration(1200)
                        .start();
                mPassContainer.animate()
                        .translationY(0)
                        .setInterpolator(new DecelerateInterpolator(1.5f))
                        .setStartDelay(1000)
                        .setDuration(1200)
                        .start();
                mLoginButton.animate()
                        .translationY(0)
                        .setInterpolator(new DecelerateInterpolator(1.5f))
                        .setStartDelay(1400)
                        .setDuration(600)
                        .start();
                mForgot.animate().alpha(1f)
                        .setDuration(600)
                        .setStartDelay(2000)
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


    private void gotoMain() {
        // Take Login Details


        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        Log.d(TAG, "gotoMain: ");
        String email = mEmail.getEditText().getText().toString();
        String password = mPass.getEditText().getText().toString();
        //Validating Fieldds

        //validating Password
        if (StringValidator.checkPassword(password)) {
            //pass word is valid , check for Email
            if (StringValidator.checkeEMail(email)) {


                SharedPreferences mSharedPreferences = this.getSharedPreferences("APK", Context.MODE_PRIVATE);
                SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                mEditor.putString("email",email);
                mEditor.apply();
                Intent i = new Intent(this, LocationActivity.class);
                i.putExtra("email", email);
                i.putExtra("pass", password);
                startActivity(i);
                finish();
                } else {
                    //phone number is not valid
                    Toast.makeText(this, "Please Enter Email Address", Toast.LENGTH_SHORT).show();
                    mEmailContainer.startAnimation(shake);
                    mEmail.getEditText().setText("");
                }
            } else {
                //Password is Not valid
                Toast.makeText(this, "Please Enter Valid Name", Toast.LENGTH_SHORT).show();
                mPassContainer.startAnimation(shake);
                mPass.getEditText().setText("");
            }

        }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.login_btton) {
            gotoMain();
        }
        if (v.getId() == R.id.fp) {
            gotoForgot();
        }
    }

    private void gotoForgot() {
        Intent i = new Intent(this, ForgotPasswordActivity.class);
        startActivity(i);
        finish();
    }
}
