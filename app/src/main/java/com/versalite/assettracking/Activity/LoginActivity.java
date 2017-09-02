package com.versalite.assettracking.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.versalite.assettracking.MainActivity;
import com.versalite.assettracking.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 24/8/17.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    boolean isAdmin;

    RelativeLayout mRoot;
    @Nullable @BindView(R.id.name)
    TextInputLayout mName;
    @Nullable @BindView(R.id.email)
    TextInputLayout mEmail;
    @Nullable @BindView(R.id.pass)
    TextInputLayout mPassword;
    @Nullable @BindView(R.id.user_sign_up)
    Button mSignUpButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
        mRoot = (RelativeLayout) findViewById(R.id.login_root);
        if (getIntent() != null) {
            isAdmin = getIntent().getBooleanExtra("isAdmin", false);
        }
        View mView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.normal_user, mRoot, false);
        mSignUpButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.user_sign_up){
            // Login Button Clicked
            //create user and take him to next Activity
            Intent i  = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }
}
