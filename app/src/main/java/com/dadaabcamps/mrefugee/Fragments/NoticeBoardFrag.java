package com.dadaabcamps.mrefugee.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dadaabcamps.mrefugee.Activities.ShowNoticeDetails;
import com.dadaabcamps.mrefugee.R;
import com.dadaabcamps.mrefugee.FirebaseModels.NoticeBoardModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import static android.content.ContentValues.TAG;

//import com.dadaabcamps.mrefugee.R;

public class NoticeBoardFrag extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_POSITION = "position";
    private int position;
    private OnDashBoardFragListener mListener;

    /**
     *
     */
    DatabaseReference dbref;
    FirebaseRecyclerAdapter<NoticeBoardModel,NoticeBoardModelVH> firebasenewsRecycleAdapter ;
    RecyclerView newsrecyclerView;
    LinearLayoutManager nwlinearLayoutManager;
    ProgressBar newsprogressBar;


    public static class NoticeBoardModelVH extends RecyclerView.ViewHolder{

        public TextView newsHead, newsBody,newsOrganization;
        View mView;

        public NoticeBoardModelVH(View itemView) {
            super(itemView);
            this.mView = itemView;
            this.newsHead = (TextView) mView.findViewById(R.id.listview_item_title);
            this.newsBody = (TextView) mView.findViewById(R.id.listview_item_short_description);
            this.newsOrganization = (TextView) mView.findViewById(R.id.listview_item_organization);
           }

        }// End NewsModelVH class

    public static final String Notice = "NoticeBoardModel";

    public NoticeBoardFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    // TODO: Rename and change types and number of parameters
    public static NoticeBoardFrag newInstance(int position) {
        NoticeBoardFrag fragment = new NoticeBoardFrag();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION,position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment_repatriation_child_cv for this fragment
        View rootView = inflater.inflate(R.layout.fragment_notice_rv, container, false);
        newsrecyclerView =(RecyclerView) rootView.findViewById(R.id.rv_noticeboard);
        nwlinearLayoutManager = new LinearLayoutManager(getActivity());
        nwlinearLayoutManager.setStackFromEnd(true);

        dbref = FirebaseDatabase.getInstance().getReference();
        newsprogressBar = (ProgressBar) rootView.findViewById(R.id.newsprogress_bar);
        newsprogressBar.setVisibility(View.VISIBLE);

        firebasenewsRecycleAdapter = new FirebaseRecyclerAdapter<NoticeBoardModel, NoticeBoardModelVH>(
                NoticeBoardModel.class,
                R.layout.fragment_notice_cv,
                NoticeBoardModelVH.class,
                dbref.child(Notice)) {
            @Override
            protected void populateViewHolder(NoticeBoardModelVH viewHolder, final NoticeBoardModel model, final int position) {
                viewHolder.newsHead.setText(model.getNewsHead());
                viewHolder.newsBody.setText(model.getNewsBody());
                viewHolder.newsOrganization.setText(model.getOrganization());
                newsprogressBar.setVisibility(View.GONE);
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.w(TAG, "You clicked on "+ position);
                        //firebasenewsRecycleAdapter.getRef(position).removeValue();
                        openNewsDetailActivity(model.getNewsHead(), model.getNewsBody(),model.getOrganization());
                    }
                });
            }

            private void openNewsDetailActivity(String...details) {
                Intent newsIntent = new Intent(getActivity(), ShowNoticeDetails.class);
                newsIntent.putExtra("TTTLE_KEY", details[0]);
                newsIntent.putExtra("DESC_KEY", details[1]);
                newsIntent.putExtra("ORG_KEY", details[2]);

                startActivity(newsIntent);
            }
        };


       newsrecyclerView.setLayoutManager(nwlinearLayoutManager);
        newsrecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).build());
        newsrecyclerView.setAdapter(firebasenewsRecycleAdapter);

        /**
         * SET ADAPTER
         */


        Log.v("RETRIEVE", " dbOperationsHelper.retrieveNews() NEWS=" + dbref);

        return rootView;

    }



    public void setListener(OnDashBoardFragListener listener){
        mListener = listener; }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDashBoardFragListener) {
            mListener = (OnDashBoardFragListener) context;

            //newsprogressBar.setVisibility(View.GONE);
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onResume() {
        super.onResume();


    }
    @Override
    public void onPause() {
        super.onPause();



    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    public static interface OnDashBoardFragListener {
        // TODO: Update argument type and name
        void itemClicked(int p,long id);
    }
}
