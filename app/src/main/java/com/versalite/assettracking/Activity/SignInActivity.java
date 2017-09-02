package com.versalite.assettracking.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.versalite.assettracking.R;

/**
 * Created by nandhu on 24/8/17.
 *
 */

public class SignInActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        Button admin = (Button) findViewById(R.id.admin_sign_up);
        Button mLogin= (Button) findViewById(R.id.user_sign_up);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                i.putExtra("isAdmin",true);
            }
        });
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                i.putExtra("isAdmin",false);
            }
        });
    }
}
