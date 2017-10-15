package com.versalite.assettracking.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.versalite.assettracking.R;
import com.versalite.assettracking.StringValidator;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 15/9/17.
 *
 */

class ForgotPasswordActivity extends AppCompatActivity {

    @BindView(R.id.f_toolbar)
    Toolbar fToolbar;
    @BindView(R.id.login_email_edit)
    TextInputLayout loginEmailEdit;
    @BindView(R.id.f_button)
    Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        ButterKnife.bind(this);


        setSupportActionBar(fToolbar);
        ActionBar ab = getSupportActionBar();
        if (ab!=null){
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);

        }
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgot();
            }
        });
    }

    private void forgot() {
        String email  =  loginEmailEdit.getEditText().getText().toString();
        if (StringValidator.checkeEMail(email)){
            // Correct
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Mail sent");
            alertDialog.setMessage("Reset Instructions has been sent to Mail");
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    startActivity(new Intent(getApplicationContext(),SignInActivity.class));
                }
            });

            alertDialog.show();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return  true;
            default:
                return  true;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}



