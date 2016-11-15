package com.crakama.refugee.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.crakama.refugee.R;
import com.crakama.refugee.database.TownsModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HelpDeskSupport extends AppCompatActivity {

    DatabaseReference dbref;
    FirebaseRecyclerAdapter<TownsModel,TownsModelVH> firebasenewsRecycleAdapter ;
    RecyclerView newsrecyclerView;
    LinearLayoutManager nwlinearLayoutManager;
    ProgressBar newsprogressBar;

    public static class TownsModelVH extends RecyclerView.ViewHolder{

        public final TextView townName, schoolInfo,hospitalInfo,otherInfo;
        View mView;

        public TownsModelVH(View itemView) {
            super(itemView);
            this.mView = itemView;
            this.townName = (TextView) mView.findViewById(R.id.listview_item_title);
            this.schoolInfo = (TextView) mView.findViewById(R.id.listview_item_short_description);
            this.hospitalInfo = (TextView) mView.findViewById(R.id.listview_item_organization);
            this.otherInfo = (TextView) mView.findViewById(R.id.lv_item_date);


        }

    }// End NewsModelVH class

    public static final String TOWNS= "TownsModel";

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

        townsInfo();


    }


    public void townsInfo(){
        newsrecyclerView =(RecyclerView) findViewById(R.id.rv_helpdesk);
        nwlinearLayoutManager = new LinearLayoutManager(this);
        nwlinearLayoutManager.setStackFromEnd(true);

        dbref = FirebaseDatabase.getInstance().getReference();
        newsprogressBar = (ProgressBar) findViewById(R.id.helpdeskprogress_bar);
        newsprogressBar.setVisibility(View.VISIBLE);
        firebasenewsRecycleAdapter = new FirebaseRecyclerAdapter<TownsModel, TownsModelVH>(
                TownsModel.class,
                R.layout.content_help_desk,
                TownsModelVH.class,
                dbref.child(TOWNS)) {
            //NewsModel dbModel = NewsModel. .getInstance();
            @Override
            protected void populateViewHolder(TownsModelVH viewHolder, final TownsModel model, final int position) {
                viewHolder.townName.setText(model.getTownName());
                //viewHolder.schoolInfo.setText(model.getNewsBody());
                //viewHolder.hospitalInfo.setText(model.getNewsorganization());
                //viewHolder.newsDate.setText(DateUtils.getRelativeTimeSpanString((long) model.getTimestamp()));
                newsprogressBar.setVisibility(View.GONE);
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //firebasenewsRecycleAdapter.getRef(position).removeValue();
                        //openNewsDetailActivity(model.getNewsHead(), model.getNewsBody(),model.getNewsorganization());
                    }
                });
            }

//            private void openNewsDetailActivity(String...details) {
//                Intent newsIntent = new Intent(this, ShowNoticeDetails.class);
//                newsIntent.putExtra("TTTLE_KEY", details[0]);
//                newsIntent.putExtra("DESC_KEY", details[1]);
//                newsIntent.putExtra("ORG_KEY", details[2]);
//
//                startActivity(newsIntent);
//            }
        };
        newsrecyclerView.setLayoutManager(nwlinearLayoutManager);
        newsrecyclerView.setAdapter(firebasenewsRecycleAdapter);
        /**
         * SET ADAPTER
         */


        Log.v("RETRIEVE", " dbOperationsHelper.retrieveNews() NEWS=" + dbref);





    }
    }



