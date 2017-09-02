package com.versalite.assettracking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.versalite.assettracking.Activity.AssetAddActivity;
import com.versalite.assettracking.Activity.QRScanActivity;

/**
 * Created by nandhu on 24/8/17.
 *
 */

public class DomainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.domain_act);

        Button mL = (Button) findViewById(R.id.button_scan);
        mL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),QRScanActivity.class);
                startActivity(i);
            }
        });
        Button add = (Button) findViewById(R.id.button_add_asset);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),AssetAddActivity.class);

                startActivity(i);
            }
        });


    }
}
