package com.versalite.assettracking.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.versalite.assettracking.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 12/9/17.
 *
 */

public class SendEmail extends AppCompatActivity {

    @BindView(R.id.email_box_to)
    EditText emailBoxTo;
    @BindView(R.id.send_email_button)
    Button sendEmailButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_email);
        ButterKnife.bind(this);
        // get the File path here
        // make sure if it can be exported to By Gmail or Separate Mail Box
        if (getIntent() != null){
            String filePath = getIntent().getStringExtra("filePath");

            // export the Image as Attachement to Google MMail

        }


        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }


        });
    }

    private void sendEmail() {
        // // TODO: 12/9/17 how?
    }


}
