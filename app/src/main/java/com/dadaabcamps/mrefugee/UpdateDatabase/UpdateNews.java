package com.dadaabcamps.mrefugee.UpdateDatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dadaabcamps.mrefugee.R;
import com.dadaabcamps.mrefugee.FirebaseModels.DBOperationsHelper;
import com.dadaabcamps.mrefugee.FirebaseModels.NewsModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateNews extends AppCompatActivity {

    EditText txtNewsHead,txtNewsBody,txtnewsorganization;
    DatabaseReference db;
    DBOperationsHelper dbOperationsHelper;
    RecyclerView newsrecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        db = FirebaseDatabase.getInstance().getReference();
        dbOperationsHelper = new DBOperationsHelper(db);


        //newsrecyclerView =(RecyclerView)findViewById(R.id.rv_noticeboard);

        txtNewsHead = (EditText) findViewById(R.id.txtNewsHead);
        txtNewsBody = (EditText) findViewById(R.id.txtNewsBody);
        //txtnewsorganization = (EditText) findViewById(R.id.txtnewsorganization);


        Button btnNewsUpdate = (Button) findViewById(R.id.btn_send_news);
        btnNewsUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * GET DATA
                 */
                String newNoticeHead = txtNewsHead.getText().toString();
                String newNoticeBody = txtNewsBody.getText().toString();


               // String newNoticeorganization = txtnewsorganization.getText().toString();

                /**
                 * SET DATA
                 */
                NewsModel dbModel = new NewsModel();
                dbModel.setNewsHead(newNoticeHead);
                dbModel.setNewsBody(newNoticeBody);
               // dbModel.setDateCreated(ServerValue.TIMESTAMP);
               // dbModel.setNewsorganization(newNoticeorganization);
                /**
                 * SIMPLE VALIDATION
                 */
                if(newNoticeHead != null && newNoticeHead.length() > 0){
                    /**
                     * THEN SAVE
                     */
                    if(dbOperationsHelper.saveNews(dbModel)){
                        /**
                         * IF NEWS SAVED, CLEAR EDIT TEXT
                         */
                        txtNewsHead.setText("");
                        txtNewsBody.setText("");
                        //txtnewsorganization.setText("");

                    }else{
                        Toast.makeText(UpdateNews.this, "MUST NOT BE EMPTY", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }


}
