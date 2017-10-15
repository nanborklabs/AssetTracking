package com.versalite.assettracking.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.versalite.assettracking.AppRequestQueue;
import com.versalite.assettracking.R;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 24/8/17.
 *
 */

public class AssetAddActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener, Response.ErrorListener, Response.Listener<JSONObject> {


    private static final String ADD_ASSETS = ""; //// TODO: 2/9/17  Add API
    String[] items = new String[]{"Capital", "Non-Capital", "Add New Cateogry"};
    String[] descss = new String[]{"Table", "Chair", "Desks", "Add New Description"};


    String saveAssetURL = "https://us-central1-audit-app-819d8.cloudfunctions.net/app/saveAsset";

    String encodingURL  = "https://us-central1-audit-app-819d8.cloudfunctions.net/app/getAssetDetails";

    String Name;
    String AssetId;
    String assetType;
    String assetDescription;
    String AsssetTag;
    String orgname;
    String  location;
    String deptName;
    String roomNo;
    @BindView(R.id.asset_name)
    TextInputLayout mAssetName;
    @BindView(R.id.asset_type_spinner)
    Spinner mSpinner;
    @BindView(R.id.asset_desc)
    EditText mAssetDesc;
    @BindView(R.id.o_ed)
    EditText oEd;
    @BindView(R.id.l_ed)
    EditText lEd;
    @BindView(R.id.d_ed)
    EditText dEd;
    @BindView(R.id.r_ed)
    EditText rEd;
    @BindView(R.id.save_button)
    Button saveButton;
    private String TAG ="Spinner";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asset_add_act);
        ButterKnife.bind(this);


        // CApital Spiiner

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(this);

        // Description Spinner
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToAssets();
            }
        });
    }

    private void addToAssets() {
        Name = mAssetName.getEditText().getText().toString();
        orgname = oEd.getText().toString();
        location = lEd.getText().toString();
        deptName = dEd.getText().toString();
        roomNo = rEd.getText().toString();
        assetDescription = mAssetDesc.getText().toString();
        // We have all the Variables needed to add assets
        saveAsset(Name,orgname,location,deptName,roomNo);
    }

    private void saveAsset(String name, String orgname, String location, String deptName, String roomNo) {
        JSONObject mJsonObject = new JSONObject();
        try {


            mJsonObject.put("assetName",name);
            mJsonObject.put("orgName",orgname);
            mJsonObject.put("location",location);
            mJsonObject.put("deptName",deptName);
            mJsonObject.put("type",assetType);
            mJsonObject.put("description",assetDescription);
            mJsonObject.put("roomNo",roomNo);
            JsonObjectRequest mRequest = new JsonObjectRequest(1,saveAssetURL,mJsonObject,this,this);
            AppRequestQueue mAppRequestQueue = AppRequestQueue.getInstance(this);
            mAppRequestQueue.addToRequestQue(mRequest);

        }
        catch (Exception e){
            Log.d(TAG, "saveAsset: Exceotion");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemSelected: " + items[position]);
        assetType = items[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),"Some Error Occured ",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Log.d(TAG, "onResponse: "+response.toString());
        try {
            if (response.getInt("status") == 1 ){

                long id = response.getLong("assetId");
                String QRcode = encodingURL + "?id="+id;
                Intent i = new Intent(this,QRViewActivity.class);
                i.putExtra("qr",QRcode);
                i.putExtra("assetId",id);
                i.putExtra("name",response.getString("assetName"));
                i.putExtra("orgName",response.getString("orgName"));
                i.putExtra("location",response.getString("location"));
                i.putExtra("deptName",response.getString("deptName"));
                i.putExtra("roomNo",response.getString("roomNo"));
                i.putExtra("type",response.getString("type"));
                i.putExtra("description",response.getString("description"));

                startActivity(i);





            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
