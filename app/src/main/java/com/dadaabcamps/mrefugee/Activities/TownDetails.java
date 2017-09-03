package com.dadaabcamps.mrefugee.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.dadaabcamps.mrefugee.R;

public class TownDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_town_details);



    /* GET INTENT */
    Intent newsIntent = this.getIntent();

    /* RECEIVE DATA */
    String name = newsIntent.getExtras().getString("TOWN_NAME");
    String school = newsIntent.getExtras().getString("TOWN_SCHOOL");
    String hospital = newsIntent.getExtras().getString("TOWN_HOSP");

    /* FIND BY ID and set text*/
    TextView txtschool = (TextView) findViewById(R.id.txtSchool);
    TextView txthospital = (TextView) findViewById(R.id.txtHospital);

    txtschool.setText(school);
        txthospital.setText(hospital);
    }
}

