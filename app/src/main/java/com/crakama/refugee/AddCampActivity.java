package com.crakama.refugee;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.crakama.refugee.database.DBHelperAdapter;
import com.crakama.refugee.database.DatabaseStructure;

import java.util.ArrayList;

public class AddCampActivity extends AppCompatActivity {
//    EditText txtcampname;
//    EditText txtcamploc;
//    Button addButton,viewButton;
//    SQLiteDatabase db;
//    DBHelperAdapter dbhelperadapter;
//    //Context context;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_news);
//        final ArrayList<DadaabCamp> av = new ArrayList<DadaabCamp>();
//
//
//        dbhelperadapter = new DBHelperAdapter(this);
//        //final DBAdapter db = new DBAdapter(this);
//
//        /**  INITIALIZE VIEWS FOR ADD CAMP UI*/
//        txtcampname = (EditText) findViewById(R.id.editTextCampName);
//        txtcamploc = (EditText) findViewById(R.id.editTextcamplocation);
//        addButton = (Button) findViewById(R.id.buttonAddCamp);
//        viewButton = (Button) findViewById(R.id.buttonViewCamps);
//        viewButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               String data = dbhelperadapter.getAllData();
//               Message.message(AddCampActivity.this, data);
//            }
//        });
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String campname = txtcampname.getText().toString();
//                String location = txtcamploc.getText().toString();
//
//                if (dbhelperadapter != null) {
//                    long id=dbhelperadapter.insertData(campname,location);
//                    if(id<0){
//                        Message.message(AddCampActivity.this, "Unsuccessful");
//                    }else{
//                        Message.message(AddCampActivity.this, "Inserted a ROW");
//                    }
//                } else {
//                    Message.message(AddCampActivity.this, "DBHelperadapter IS NULLL");
//                }
//
//
////                try {
////                    db = new DBHelperAdapter(getBaseContext() ).getWritableDatabase();
////                    db.beginTransaction();
////                    for (DadaabCamp camp : av) {
////                        ContentValues cv = new ContentValues();
////                        cv.put(DatabaseStructure.CampTable.CAMPNAME, camp.getName());
////                        cv.put(DatabaseStructure.CampTable.LOCATION, camp.getDescription());
////
////                        db.insert(DatabaseStructure.CampTable.TABLE_NAME, DatabaseStructure.CampTable.CAMPNAME, cv);
////                    }
////                    db.setTransactionSuccessful();
////                    db.endTransaction();
////                    db.close();
////                } catch (Exception e) {
////                    Message.message(getBaseContext(), "INSERT METHOD CALLED");
////                }
////                ArrayList<DadaabCamp> av = new ArrayList<DadaabCamp>();
////                DBAccessManager.getsInstance().saveCampData(AddCampActivity.this,av);
//                /**  OPEN DB */
//               // db.openDB();
//                /**  INSERT INTO DB */
////                DBAccessManager result = DBAccessManager.getsInstance(txtcampname.getText().toString(),txtcamploc.getText().toString());
////                if(result >0){
////                    txtcampname.setText("");
////                    txtcamploc.setText("");
////
////                }else{
////                    Toast.makeText(getApplicationContext(),"FAILED TO INSERT TO DB",Toast.LENGTH_SHORT).show();
////                }
//            }
//        });
//    }
//

}
