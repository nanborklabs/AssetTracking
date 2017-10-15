package com.versalite.assettracking.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.versalite.assettracking.MainActivity;
import com.versalite.assettracking.R;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 24/8/17.
 *
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, Response.ErrorListener, Response.Listener<JSONObject> {

    boolean isAdmin;

    LinearLayout mRoot;

//
//    @Nullable @BindView(R.id.name)
//    TextInputLayout mName;
//    @Nullable @BindView(R.id.log_email)
//    TextInputLayout mEmail;
//    @Nullable @BindView(R.id.log_passs)
//    TextInputLayout mPassword;
//    @Nullable @BindView(R.id.log_button)
//    Button mLogin;
    private String LOG_URL = "";
    private String TAG = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.login);
//        ButterKnife.bind(this);
//        mRoot = (LinearLayout) findViewById(R.id.login_root);
        if (getIntent() != null) {
            isAdmin = getIntent().getBooleanExtra("isAdmin", false);
        }

        // if he is an Admin ,
         // both will have normal Sigin Flow

//        mLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                doLogin();
//            }
//
//
//
//
//        });











    }

    private void doLogin() {
        JSONObject mObject = new JSONObject();
        try {

//            mObject.put("email", mEmail.getEditText().getText().toString());
//            mObject.put("pass", mPassword.getEditText().getText().toString());
//            JsonObjectRequest mRequest = new JsonObjectRequest(1, LOG_URL, mObject, this, this);
            Intent i  = new Intent(this,MainActivity.class);
            startActivity(i);

        }catch (Exception e) {
                e.printStackTrace();
            }
    }

    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.sign_btton){
//            // Login Button Clicked
//            //create user and take him to next Activity
//            Intent i  = new Intent(this, MainActivity.class);
//            startActivity(i);
//        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d(TAG, "onErrorResponse: "+error.getLocalizedMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        Log.d(TAG, "onErrorResponse: "+response.toString());
        boolean status = parse(response);
        if (status == true){
            //success full Login,
            //save details, // TODO: 12/9/17 sve user
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);

        }
        else{
            //fucked up login
        }
    }

    private boolean parse(JSONObject response) {
        try {
            return response.getBoolean("status");
        } catch (JSONException e) {

        return false;
        }
    }
}
