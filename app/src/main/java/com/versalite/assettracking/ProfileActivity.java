package com.versalite.assettracking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by nandhu on 15/9/17.
 *
 */

public class ProfileActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_profile);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.change_profile_toolbar);
        mToolbar.setTitle("Profile");
        setSupportActionBar(mToolbar);

        try {
            ActionBar ab = getSupportActionBar();
            ab.setTitle("Profile");
            ab.setDisplayShowHomeEnabled(true);
            ab.setDisplayHomeAsUpEnabled(true);

        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Error Occured",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
}
