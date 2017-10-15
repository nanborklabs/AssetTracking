package com.versalite.assettracking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.versalite.assettracking.Activity.QRScanActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 24/8/17.
 *
 */

public class DomainActivity extends AppCompatActivity {


    @BindView(R.id.domain_org_name)
    OpenSansTextView mOrgName;
    @BindView(R.id.dom_loc_val)
    WhitenyBooksFont mLocation;
    @BindView(R.id.dom_dept_val)
    WhitenyBooksFont mDept;
    @BindView(R.id.cardView)
    CardView mCard;
    @BindView(R.id.button_scan)
    Button mScan;
    @BindView(R.id.dom_need_help)
    WhitenyBooksFont mHelp;
    @BindView(R.id.dom_toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.domain_act);
        ButterKnife.bind(this);

        if (getIntent() != null){
            mLocation.setText(getIntent().getStringExtra("location"));
            mDept.setText(getIntent().getStringExtra("dept"));
            mOrgName.setText("Versalite Tech Pvt Ltd");
        }

        setSupportActionBar(mToolbar);

        try {
            ActionBar ab = getSupportActionBar();
            mToolbar.setTitle("Atrack");
            ab.setDisplayShowHomeEnabled(true);
            ab.setDisplayHomeAsUpEnabled(true);

        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Error Occured",Toast.LENGTH_SHORT).show();
        }

        Button mL = (Button) findViewById(R.id.button_scan);
        mL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), QRScanActivity.class);
                startActivity(i);
            }
        });
        mHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),FAQActivity.class));

            }
        });
//        Button add = (Button) findViewById(R.id.button_add_asset);
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent i = new Intent(getApplicationContext(),AssetAddActivity.class);
//
//                startActivity(i);
//            }
//        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
}
