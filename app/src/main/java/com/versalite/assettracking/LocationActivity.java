package com.versalite.assettracking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by nandhu on 24/8/17.
 *
 */

public class LocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_act);
        LinearLayout mL = (LinearLayout) findViewById(R.id.location_ll);
        mL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  i = new Intent(getApplicationContext(),DepartMentActivity.class);
                startActivity(i);
            }
        });


    }
}
