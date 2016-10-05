package com.crakama.refugee.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.crakama.refugee.R;

public class NoticeDetails extends AppCompatActivity {

 TextView txtTitle, txtDesc, txtOrganization;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_details);

        txtTitle = (TextView) findViewById(R.id.txtdescNewsHead);
        txtDesc = (TextView) findViewById(R.id.txtdescNewsOrganization);
        txtOrganization = (TextView) findViewById(R.id.txtdescNewsBody);

        /*
        *GET INTENT
        */
        Intent newsIntent = this.getIntent();

        /*
        * RECEIVE DATA
         */
        String title = newsIntent.getExtras().getString("TTTLE_KEY");
        String desc = newsIntent.getExtras().getString("DESC_KEY");
        String organization = newsIntent.getExtras().getString("ORG_KEY");

        /*
        * BIND DATA
        */
        txtTitle.setText(title);
        txtDesc.setText(desc);
        txtOrganization.setText(organization);



    }
}
