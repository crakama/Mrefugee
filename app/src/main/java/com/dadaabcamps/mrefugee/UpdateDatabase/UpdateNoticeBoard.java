package com.dadaabcamps.mrefugee.UpdateDatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dadaabcamps.mrefugee.R;
import com.dadaabcamps.mrefugee.FirebaseModels.DBOperationsHelper;
import com.dadaabcamps.mrefugee.FirebaseModels.NoticeBoardModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UpdateNoticeBoard extends AppCompatActivity {
    EditText noticeHead,noticeBody,noticeOrganization;
    DatabaseReference db;
    DBOperationsHelper dbOperationsHelper;
    //RecyclerView newsrecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);

        db = FirebaseDatabase.getInstance().getReference();
        dbOperationsHelper = new DBOperationsHelper(db);


       // newsrecyclerView =(RecyclerView)findViewById(R.id.rv_noticeboard);

        noticeHead = (EditText) findViewById(R.id.txtNoticeHead);
        noticeBody = (EditText) findViewById(R.id.txtNoticeBody);
        noticeOrganization = (EditText) findViewById(R.id.txtnoticeorganization);


        Button btnNewsUpdate = (Button) findViewById(R.id.btn_add_notice);

        btnNewsUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * GET DATA
                 */
                String newNoticeHead = noticeHead.getText().toString();
                String newNoticeBody = noticeBody.getText().toString();
                String newNoticeorganization = noticeOrganization.getText().toString();

                /**
                 * SET DATA
                 */
                NoticeBoardModel dbModel = new NoticeBoardModel();
                dbModel.setNewsHead(newNoticeHead);
                dbModel.setNewsBody(newNoticeBody);
                dbModel.setOrganization(newNoticeorganization);
                /**
                 * SIMPLE VALIDATION
                 */
                if(newNoticeHead != null && newNoticeHead.length() > 0){
                    /**
                     * THEN SAVE
                     */
                if(dbOperationsHelper.saveNotice(dbModel)){
                    /**
                     * IF NEWS SAVED, CLEAR EDIT TEXT
                     */
                    noticeHead.setText("");
                    noticeBody.setText("");
                    noticeOrganization.setText("");

                }else{
                    Toast.makeText(UpdateNoticeBoard.this, "MUST NOT BE EMPTY", Toast.LENGTH_SHORT).show();
                }
                }
            }
        });
    }
}
