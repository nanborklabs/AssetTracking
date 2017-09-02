package com.versalite.assettracking.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.versalite.assettracking.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 24/8/17.
 *
 */

public class AssetAddActivity extends AppCompatActivity {


    String[] items = new String[]{"Capital", "Non-Capital", "Add New Cateogry"};
    String[] descss = new String[]{"Table", "Chair", "Desks", "Add New Description"};
    @BindView(R.id.textInputLayout)
    TextInputLayout textInputLayout;
    @BindView(R.id.asset_name)
    TextView assetName;
    @BindView(R.id.asset_type_spinner)
    Spinner assetTypeSpinner;
    @BindView(R.id.asset_desc)
    TextView assetDesc;
    @BindView(R.id.asset_desc_spinner)
    Spinner assetDescSpinner;
    @BindView(R.id.asset_tag)
    TextView assetTag;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.save_button)
    Button addButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asset_add_act);
        ButterKnife.bind(this);


        // CApital Spiiner
        Spinner spinner = (Spinner) findViewById(R.id.asset_type_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Description Spinner
        Spinner desc = (Spinner) findViewById(R.id.asset_desc_spinner);
        ArrayAdapter<String> adaptera = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, descss);
        adaptera.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        desc.setAdapter(adaptera);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToAssets();
            }


        });

    }
    private void addToAssets() {
        Toast.makeText(getApplicationContext(),"Added !..",Toast.LENGTH_LONG).show();
        // Goto new Activity Here
        Intent i = new Intent(this,QRViewActivity.class);
        startActivity(i);
    }
    
}
