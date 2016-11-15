package com.crakama.refugee.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.crakama.refugee.R;
import com.crakama.refugee.database.TownsModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateTownInfo extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference databaseReference;
    private EditText textSchoolInfo,populationInfo,hospInfo;
    Button btnUpdateTownInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_town_info);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        textSchoolInfo = (EditText) findViewById(R.id.editTextSchoolInfo);
        populationInfo = (EditText) findViewById(R.id.editTextPopulationInfo);
        hospInfo = (EditText) findViewById(R.id.editTextHospInfo);
        btnUpdateTownInfo = (Button) findViewById(R.id.btnUpdateTownInfo);

        btnUpdateTownInfo.setOnClickListener(UpdateTownInfo.this);
    }
    public static final String TOWNS= "TownsModel";

    private  void saveTownInfo(){
        String school = textSchoolInfo.getText().toString();
        String population = populationInfo.getText().toString();
        String hospital = hospInfo.getText().toString();

        TownsModel townsModel = new TownsModel(school,population,hospital);
        databaseReference.child(TOWNS).push().setValue(townsModel);
    }
    @Override
    public void onClick(View v) {
        if(v == btnUpdateTownInfo){
            saveTownInfo();
        }

    }
}
