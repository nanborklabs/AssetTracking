package com.versalite.assettracking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 24/8/17.
 *
 */

public class DepartMentActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.dept_loca_val)
    TextView mLocation;
    @BindView(R.id.dt_1)
    WhitenyBooksFont mDept1;
    @BindView(R.id.dt_2)
    WhitenyBooksFont mDept2;
    @BindView(R.id.dt_3)
    WhitenyBooksFont mDept3;
    @BindView(R.id.dt_4)
    WhitenyBooksFont mDept4;

    String location;
    @BindView(R.id.dept_toolbar)
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dept_act);
        ButterKnife.bind(this);
        if (getIntent() != null){
            location = getIntent().getStringExtra("location");
            mLocation.setText(location);
        }
        mDept1.setOnClickListener(this);
        mDept2.setOnClickListener(this);
        mDept3.setOnClickListener(this);
        mDept4.setOnClickListener(this);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("Atrack");

        try {
            ActionBar ab = getSupportActionBar();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.dt_1:
                gotoDomain(location,"IT - Development");
                break;
            case R.id.dt_2:
                gotoDomain(location,"IT - Security");
                break;
            case R.id.dt_3:
                gotoDomain(location,"warehouse");
                break;
            case R.id.dt_4:
                gotoDomain(location,"Cafeteria");
                break;
        }
    }

    private void gotoDomain(String location, String s) {
        Intent i = new Intent(this,DomainActivity.class);
        i.putExtra("location",location);
        i.putExtra("dept",s);
        startActivity(i);
    }
}
