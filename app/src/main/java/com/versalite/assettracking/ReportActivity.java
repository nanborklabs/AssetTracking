package com.versalite.assettracking;

import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import butterknife.ButterKnife;

/**
 * Created by nandhu on 28/8/17.
 *
 */

public class ReportActivity extends AppCompatActivity {

    Spinner s1;
    Spinner s2;

    Button s;
    String[] items = new String[]{"ABC", "XYZ", "PQJ"};
    String[] descss = new String[]{"Bangalore", "Dubai", "Coimbatore", "London"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_act);
        ButterKnife.bind(this);

        s = (Button) findViewById(R.id.rep_gen_butt);
        // CApital Spiiner
        Spinner spinner = (Spinner) findViewById(R.id.rep_first);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Description Spinner
        Spinner desc = (Spinner) findViewById(R.id.rep_second);
        ArrayAdapter<String> adaptera = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, descss);
        adaptera.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        desc.setAdapter(adaptera);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReport();
            }


        });
    }

    private void showReport() {
        Intent i = new Intent(this,ReportGraphActivity.class);
        startActivity(i);
    }
}
