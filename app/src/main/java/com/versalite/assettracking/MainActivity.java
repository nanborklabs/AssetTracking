package com.versalite.assettracking;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.versalite.assettracking.Activity.AssetAddActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @BindView(R.id.imageView2)
    ImageView mProfile;
    @BindView(R.id.user_name)
    OpenSansTextView mName;
    @BindView(R.id.emp_id_value)
    TextView mEmpId;
    @BindView(R.id.last_login_val)
    TextView mlastlogin;
    @BindView(R.id.profile_card)
    CardView mProfileCard;
    @BindView(R.id.br_1)
    WhitenyBooksFont br1;
    @BindView(R.id.br_2)
    WhitenyBooksFont br2;
    @BindView(R.id.br_3)
    WhitenyBooksFont br3;
    @BindView(R.id.br_4)
    WhitenyBooksFont br4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_act);
        ButterKnife.bind(this);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        String email = getIntent().getStringExtra("email");
        String pass = getIntent().getStringExtra("pass");
        mName.setText(email);
        mEmpId.setText("11244246");
        br1.setOnClickListener(this);
        br2.setOnClickListener(this);
        br3.setOnClickListener(this);
        br4.setOnClickListener(this);
        mProfileCard.setOnClickListener(this);




//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(),ReportActivity.class);
//                startActivity(i);
//            }
//        });

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        LinearLayout mLa = (LinearLayout) findViewById(R.id.org_layout);
        mLa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLocActivity();
            }


        });
    }

    private void goToLocActivity() {

        Intent i = new Intent(this, DepartMentActivity.class);
        startActivity(i);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        startActivity(new Intent(this, AssetAddActivity.class));
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.br_1:
//                goToLocActivity("");
//                break;
//            case R.id.br_2:
//                break;
//            case R.id.br_3:
//                break;
//            case R.id.br_4:
//                break;
//
//        }
        }
    }
}
