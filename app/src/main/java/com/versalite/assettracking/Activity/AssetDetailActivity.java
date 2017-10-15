package com.versalite.assettracking.Activity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.versalite.assettracking.AppRequestQueue;
import com.versalite.assettracking.LocationActivity;
import com.versalite.assettracking.MainActivity;
import com.versalite.assettracking.OpenSansTextView;
import com.versalite.assettracking.R;
import com.versalite.assettracking.WhitenyBooksFont;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 24/8/17.
 *
 */

public class AssetDetailActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {


    @BindView(R.id.status_image)
    ImageView mStatuImage;
    @BindView(R.id.scan_next)
    ImageView mScanNext;
    @BindView(R.id.close_audit)
    ImageView mCloseAudit;
    @BindView(R.id.aduit_date)
    TextView mAuditDate;
    @BindView(R.id.audit_user_name)
    TextView mUserName;
    @BindView(R.id.details_card)
    CardView mDetailsCard;
    @BindView(R.id.ad_name)
    OpenSansTextView mAssetName;
    @BindView(R.id.desc_val)
    OpenSansTextView mDesc;
    @BindView(R.id.det_type)
    WhitenyBooksFont mType;
    @BindView(R.id.detail_org_name)
    WhitenyBooksFont mOrgName;
    @BindView(R.id.detail_loca_name)
    WhitenyBooksFont mLocation;
    @BindView(R.id.detail_dept_name)
    WhitenyBooksFont mDeptName;
    @BindView(R.id.detail_room_name)
    WhitenyBooksFont mRoomName;
    private String TAG = "AssetDetail";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asset_detail_act);
        ButterKnife.bind(this);



        mUserName.setText(getSharedPreferences("APK", Context.MODE_PRIVATE).getString("email","Rajesh"));
        // We ge thte webservice we want to hit here
        if (getIntent() != null) {
            String Url = getIntent().getStringExtra("bar_text");
//            pd = new ProgressDialog(getApplicationContext());
//            pd.setMessage("Getting Details..!!");
//            pd.show();
            // We have got the URL here , hit the webservice get Details as Json
            makeReq(Url);
        } else {
            throw new NullPointerException("EmtyString");
        }


        mScanNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),QRScanActivity.class);
                startActivity(i);

            }
        });
        mCloseAudit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(),"Closing Audit",Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), LocationActivity.class);
                startActivity(i);
            }
        });
    }

    public void makeReq(String url) {


        try {
            JsonObjectRequest mObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
            AppRequestQueue mAppRequestQueue = AppRequestQueue.getInstance(this);
            mAppRequestQueue.addToRequestQue(mObjectRequest);
        } catch (Exception e) {
//            Log.d(TAG, "makeReq:" + e.getLocalizedMessage());
//            closeDialog();
        }

    }

    public void closeDialog() {
//        if (pd != null && pd.isShowing()) {
//            pd.dismiss();
//
//        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        closeDialog();
        Toast.makeText(getApplicationContext(), "Some Error Occured", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Log.d(TAG, "onResponse: " + response.toString());
        closeDialog();
        try {
            if (response.getInt("status") == 1) {
                Log.d(TAG, "onResponse: Good Status");
                // Success Full response
                int assetStatus = response.getInt("assetStatus");
                String assetName = response.getString("assetName");
                String organisationName = response.getString("orgName");
                String roomNo = response.getString("roomNo");
                String LocationName = response.getString("location");
                String desc = response.getString("description");
                String type = response.getString("type");
                String DepartName = response.getString("deptName");
//                String AssetImage =  response.getString("assetStatus");
                long assetId = response.getLong("assetId");

                mType.setText(type);
                mDesc.setText(desc);
                mLocation.setText(LocationName);
                mOrgName.setText(organisationName);
                mAssetName.setText(assetName);
                mRoomName.setText(roomNo);
                mDeptName.setText(DepartName);

            }
        } catch (JSONException e) {
            e.printStackTrace();

        }
    }
}
