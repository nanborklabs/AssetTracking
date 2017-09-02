package com.versalite.assettracking.Activity;

import android.content.res.ObbInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.versalite.assettracking.AppRequestQueue;
import com.versalite.assettracking.R;
import com.versalite.assettracking.R2;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by nandhu on 24/8/17.
 *
 */

public class AssetDetailActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {

    private String TAG = "AssetDetail";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asset_detail_act);




        // We ge thte webservice we want to hit here
        if(getIntent() != null){
            String Url = getIntent().getStringExtra("bar_text");
            // We have got the URL here , hit the webservice get Details as Json
            makeReq(Url);
        }
        else{
            throw new NullPointerException("EmtyString");
        }
    }

    public void makeReq(String url){


        try {
            JsonObjectRequest mObjectRequest  = new JsonObjectRequest(1,url,null,this,this);
            AppRequestQueue mAppRequestQueue  = AppRequestQueue.getInstance(this);
            mAppRequestQueue.addToRequestQue(mObjectRequest);
        }
        catch (Exception e){
            Log.d(TAG, "makeReq:"+e.getLocalizedMessage());
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),"Some Error Occured",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            if (response.getInt("status") == 1){
                // Success Full response
                int assetStatus = response.getInt("assetStatus");
                String assetName =  response.getString("assetName");
                String organisationName =  response.getString("organisationName");
                String roomNo =  response.getString("roomNo");
                String LocationName =  response.getString("LocationName");
                String DepartName =  response.getString("DepartName");
//                String AssetImage =  response.getString("assetStatus");
                String assetId =  response.getString("assetId");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
