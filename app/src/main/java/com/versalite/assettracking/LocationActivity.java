package com.versalite.assettracking;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.versalite.assettracking.Activity.AssetAddActivity;
import com.versalite.assettracking.Activity.SignInActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 24/8/17.
 *
 */

public class LocationActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.imageView2)
    ImageView mImage;
    @BindView(R.id.user_name)
    OpenSansTextView mName;
    @BindView(R.id.emp_id_value)
    TextView mEmpId;
    @BindView(R.id.last_login_val)
    TextView mLogin;
    @BindView(R.id.profile_card)
    CardView profileCard;
    @BindView(R.id.br_1)
    WhitenyBooksFont br1;
    @BindView(R.id.br_2)
    WhitenyBooksFont br2;
    @BindView(R.id.br_3)
    WhitenyBooksFont br3;
    @BindView(R.id.br_4)
    WhitenyBooksFont br4;

    @BindView(R.id.loc_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.loca_card)
    CardView mLocationCard;

    @BindView(R.id.nav_drawer_main)
    NavigationView mNavView;
    @BindView(R.id.main_drawer)
    DrawerLayout mainDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_act);
        ButterKnife.bind(this);
        String email = getIntent().getStringExtra("email");
        String pass = getIntent().getStringExtra("pass");
        mName.setText(email);
        mEmpId.setText("11244246");
        br1.setOnClickListener(this);
        br2.setOnClickListener(this);
        br3.setOnClickListener(this);
        br4.setOnClickListener(this);
        mToolbar.setTitle("Versalite Pvt Ltd.");
        mToolbar.setSubtitle("Revolutionary Asset Tracking");
        profileCard.setOnClickListener(this);

        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mainDrawer, mToolbar, R.string.open_drawer, R.string.close_drawer);
        mActionBarDrawerToggle.syncState();
        mNavView.setNavigationItemSelectedListener(this);
        setTransaltions();
        startAnimation();

    }

    private void startAnimation() {
        profileCard.animate()
                .translationX(0)
                .setDuration(600)
                .setInterpolator(new DecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mLocationCard.animate().translationY(0)
                                .setInterpolator(new DecelerateInterpolator())
                                .setDuration(600)
                                .start();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .start();
    }

    private void setTransaltions() {
        profileCard.setTranslationX(-ScreenUtil.getScreenWidth(this));
        mLocationCard.setTranslationY(ScreenUtil.getScreenHeight(this));


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.br_1:
                gotoDepartment("Bangalore - Whitefield");
                break;
            case R.id.br_2:
                gotoDepartment("Bangalore - koramangala");
                break;
            case R.id.br_3:
                gotoDepartment("Palo Alto");
                break;
            case R.id.br_4:
                gotoDepartment("NCR Noida Road");
                break;
            case R.id.profile_card:
                gotoProfile();
                break;

        }

    }

    private void gotoProfile() {
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }

    private void gotoDepartment(String locationName) {
        Intent i = new Intent(this, DepartMentActivity.class);
        i.putExtra("location", locationName);
        startActivity(i);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               mainDrawer.closeDrawer(GravityCompat.START);
        startActivity(new Intent(this, AssetAddActivity.class));
        return true;
    }
}
