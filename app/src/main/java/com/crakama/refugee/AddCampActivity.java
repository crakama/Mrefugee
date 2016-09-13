package com.crakama.refugee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crakama.refugee.database.DBAccessManager;

import java.util.ArrayList;
import java.util.List;

public class AddCampActivity extends AppCompatActivity {
    EditText txtcampname;
    EditText txtcamploc;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_camp);



        //final DBAdapter db = new DBAdapter(this);

        /**  INITIALIZE VIEWS FOR ADD CAMP UI*/
        txtcampname = (EditText) findViewById(R.id.editTextCampName);
        txtcamploc = (EditText) findViewById(R.id.editTextcamplocation);
        addButton = (Button) findViewById(R.id.buttonAddCamp);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<DadaabCamp> av = new ArrayList<DadaabCamp>();
                DBAccessManager.getsInstance().saveCampData(AddCampActivity.this,av);
                /**  OPEN DB */
               // db.openDB();
                /**  INSERT INTO DB */
//                DBAccessManager result = DBAccessManager.getsInstance(txtcampname.getText().toString(),txtcamploc.getText().toString());
//                if(result >0){
//                    txtcampname.setText("");
//                    txtcamploc.setText("");
//
//                }else{
//                    Toast.makeText(getApplicationContext(),"FAILED TO INSERT TO DB",Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }


}
