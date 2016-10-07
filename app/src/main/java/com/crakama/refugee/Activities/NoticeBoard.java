package com.crakama.refugee.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.crakama.refugee.Adapters.DBAdapter;
import com.crakama.refugee.R;
import com.crakama.refugee.database.NoticeBoardModel;
import com.crakama.refugee.database.DBOperationsHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class NoticeBoard extends AppCompatActivity {
    EditText txtNewsHead,txtNewsBody,txtnewsorganization;
    DatabaseReference db;
    DBOperationsHelper dbOperationsHelper;
    DBAdapter dbAdapter;
    RecyclerView newsrecyclerView;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);

        db = FirebaseDatabase.getInstance().getReference();
        dbOperationsHelper = new DBOperationsHelper(db);


        newsrecyclerView =(RecyclerView)findViewById(R.id.rv_noticeboard);

        txtNewsHead = (EditText) findViewById(R.id.txtNewsHead);
        txtNewsBody = (EditText) findViewById(R.id.txtNewsBody);
        txtnewsorganization = (EditText) findViewById(R.id.txtnewsorganization);


        Button btnNewsUpdate = (Button) findViewById(R.id.btn_add_notice);

        btnNewsUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * GET DATA
                 */
                String newsHead = txtNewsHead.getText().toString();
                String newsBody = txtNewsBody.getText().toString();
                String newsorganization = txtnewsorganization.getText().toString();

                /**
                 * SET DATA
                 */
                NoticeBoardModel dbModel = new NoticeBoardModel();
                dbModel.setNewsHead(newsHead);
                dbModel.setNewsBody(newsBody);
                dbModel.setOrganization(newsorganization);
                /**
                 * SIMPLE VALIDATION
                 */
                if(newsHead != null && newsHead.length() > 0){
                    /**
                     * THEN SAVE
                     */
                if(dbOperationsHelper.save(dbModel)){
                    /**
                     * IF NEWS SAVED, CLEAR EDIT TEXT
                     */
                    txtNewsHead.setText("");
                    txtNewsBody.setText("");
                    txtnewsorganization.setText("");

                    //arrayAdapter = new ArrayAdapter<String>(NoticeBoard.this, android.R.layout.simple_list_item_1,dbOperationsHelper.retrieveNews());

                    //Toast.makeText(getActivity().getApplicationContext(), "MUST NOT BE EMPTY", Toast.LENGTH_SHORT).show();
                    //newsListView.setAdapter(arrayAdapter);
                     //dbAdapter = new DBAdapter(NoticeBoard.this, dbOperationsHelper.retrieveNews());
                       //dbAdapter = new DBAdapter(NoticeBoard.this,dbOperationsHelper.retrieveNews());
                    //newsrecyclerView.setAdapter(dbAdapter);

                }else{
                    Toast.makeText(NoticeBoard.this, "MUST NOT BE EMPTY", Toast.LENGTH_SHORT).show();
                }
                }
            }
        });
    }
}
