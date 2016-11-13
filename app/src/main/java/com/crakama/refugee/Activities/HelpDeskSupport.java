package com.crakama.refugee.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.EditText;
import android.widget.TextView;

import com.crakama.refugee.R;

public class HelpDeskSupport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_desk_support);


        //CardView helpdeskdetails = (CardView) findViewById(R.id.helpdeskdetails_cv);
        TextView helpdeskdetails = (TextView) findViewById(R.id.txthelpDeskDetails);
        //txtDesc = (TextView) findViewById(R.id.txtdescNewsOrganization);
        //txtOrganization = (TextView) findViewById(R.id.txtdescNewsBody);

        /*
        *GET INTENT
        */
        Intent newsIntent = this.getIntent();

        /*
        * RECEIVE DATA
         */
        String title = newsIntent.getExtras().getString("TTTLE_KEY");


        /*
        * BIND DATA
        */
        helpdeskdetails.setText(title);


    }
}
