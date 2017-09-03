package com.dadaabcamps.mrefugee.Activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dadaabcamps.mrefugee.R;
import com.dadaabcamps.mrefugee.FirebaseModels.TownsModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HelpDeskSupport extends AppCompatActivity {

    DatabaseReference dbref;
    FirebaseRecyclerAdapter<TownsModel,TownsModelVH> firebasenewsRecycleAdapter ;
    RecyclerView newsrecyclerView;
    LinearLayoutManager nwlinearLayoutManager;
    ProgressBar newsprogressBar;

    private ImageView mImageView;
    private Bitmap mBitmap;

    private Resources mResources;

//    int [] gridViewImageId = {
//            R.drawable.childregistration, R.drawable.protection, R.drawable.refferal, R.drawable.resettlement, R.drawable.rsd,
//            R.drawable.repatriation,     };

    public static class TownsModelVH extends RecyclerView.ViewHolder{

        public final TextView townName, schoolInfo,hospitalInfo;

        View mView;
        ImageView imageurl;


        public TownsModelVH(View itemView) {
            super(itemView);
            this.mView = itemView;
            this.townName = (TextView) mView.findViewById(R.id.txttown_name);
            this.schoolInfo = (TextView) mView.findViewById(R.id.txtSchool);
            this.hospitalInfo = (TextView) mView.findViewById(R.id.txtHospital);
            //this.imageurl = (ImageView) mView.findViewById(R.id.cv_img_helpdesk);


        }

    }// End NewsModelVH class

    public static final String TOWNS= "TownsModel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_desk_support);

        //CardView helpdeskdetails = (CardView) findViewById(R.id.helpdeskdetails_cv);
        //TextView helpdeskdetails = (TextView) findViewById(R.id.txthelpDeskDetails);
        //txtDesc = (TextView) findViewById(R.id.txtdescNewsOrganization);
        //txtOrganization = (TextView) findViewById(R.id.txtdescNewsBody);

        /* GET INTENT */
        Intent newsIntent = this.getIntent();

        /* RECEIVE DATA */
        String title = newsIntent.getExtras().getString("TTTLE_KEY");

        /* BIND DATA */
       // helpdeskdetails.setText(title);
        townsInfo();
        //mImageView = (ImageView) findViewById(R.id.cv_img_helpdesk);


    }



    public void townsInfo(){
        newsrecyclerView =(RecyclerView) findViewById(R.id.rv_helpdesk);
        nwlinearLayoutManager = new LinearLayoutManager(this);
        //nwlinearLayoutManager.setStackFromEnd(true);

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
                viewHolder.schoolInfo.setText(model.getHospitalInfo());
                viewHolder.hospitalInfo.setText(model.getSchoolInfo());
//                viewHolder.hospitalInfo.setText(model.getTownImgUrl());
                //PicassoClient.downloadProductImage(HelpDeskSupport.this,model.getTownImgUrl(),viewHolder.imageurl);
                newsprogressBar.setVisibility(View.GONE);
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //firebasenewsRecycleAdapter.getRef(position).removeValue();
                        openNewsDetailActivity(model.getTownName(), model.getSchoolInfo(),model.getHospitalInfo());
                    }
                });
            }

            private void openNewsDetailActivity(String...details) {
                Intent newsIntent = new Intent(HelpDeskSupport.this, TownDetails.class);
                newsIntent.putExtra("TOWN_NAME", details[0]);
                newsIntent.putExtra("TOWN_SCHOOL", details[1]);
                newsIntent.putExtra("TOWN_HOSP", details[2]);

                startActivity(newsIntent);
            }
        };
        newsrecyclerView.setLayoutManager(nwlinearLayoutManager);
        newsrecyclerView.setAdapter(firebasenewsRecycleAdapter);
        /**
         * SET ADAPTER
         */


        Log.v("RETRIEVE", " dbOperationsHelper.retrieveNews() NEWS=" + dbref);





    }
    }



