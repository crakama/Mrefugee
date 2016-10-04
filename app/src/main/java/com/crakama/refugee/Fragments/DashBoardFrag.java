package com.crakama.refugee.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.crakama.refugee.Adapters.DBAdapter;
import com.crakama.refugee.R;
import com.crakama.refugee.database.NewsModel;
import com.crakama.refugee.database.DBOperationsHelper;
import com.crakama.refugee.database.DatabaseModel;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

//import com.crakama.mrefugee.R;

public class DashBoardFrag extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_POSITION = "position";
    private int position;
    private OnDashBoardFragListener mListener;

    /**
     *
     */
    DatabaseReference dbref;
    FirebaseRecyclerAdapter<NewsModel,NewsModelVH> firebasenewsRecycleAdapter ;
    RecyclerView newsrecyclerView;
    LinearLayoutManager nwlinearLayoutManager;
    ArrayList<NewsModel> newsArraylist = new ArrayList<>();



    public static class NewsModelVH extends RecyclerView.ViewHolder{

        public TextView newsHead, newsBody;

        public NewsModelVH(View itemView) {
            super(itemView);
            this.newsHead = (TextView) itemView.findViewById(R.id.listview_item_title);
            this.newsBody = (TextView) itemView.findViewById(R.id.listview_item_short_description);
           }
        }// End NewsModelVH class

    public static final String NEWS= "NewsModel";

    public DashBoardFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    // TODO: Rename and change types and number of parameters
    public static DashBoardFrag newInstance(int position) {
        DashBoardFrag fragment = new DashBoardFrag();
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
        // Inflate the fragment_dash_board for this fragment
        View rootView = inflater.inflate(R.layout.fragment_news_rv, container, false);

        //newsListView = (ListView) rootView.findViewById(R.id.newslist_view);
        newsrecyclerView =(RecyclerView) rootView.findViewById(R.id.rv_noticeboard);
        nwlinearLayoutManager = new LinearLayoutManager(getActivity());
        nwlinearLayoutManager.setStackFromEnd(true);
        //newsrecyclerView.setLayoutManager(nwlinearLayoutManager);

        dbref = FirebaseDatabase.getInstance().getReference();
       // dbOperationsHelper = new DBOperationsHelper(dbref);

        firebasenewsRecycleAdapter = new FirebaseRecyclerAdapter<NewsModel, NewsModelVH>(
                NewsModel.class,
                R.layout.fragment_dashboard_imagetext,
                NewsModelVH.class,
                dbref.child(NEWS)) {
            @Override
            protected void populateViewHolder(NewsModelVH viewHolder, NewsModel model, int position) {
                viewHolder.newsHead.setText(model.getNewsHead());
                viewHolder.newsBody.setText(model.getNewsBody());
            }
        };

        firebasenewsRecycleAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver(){
            @Override
             public void onItemRangeInserted(int positionStart, int itemCount){
                super.onItemRangeInserted(positionStart, itemCount);
                int newsCount = firebasenewsRecycleAdapter.getItemCount();
                int lastVisiblePosition = nwlinearLayoutManager.findLastVisibleItemPosition();
                if(lastVisiblePosition == -1 || (positionStart>= (newsCount -1) && lastVisiblePosition == (positionStart -1))){
                    newsrecyclerView.scrollToPosition(positionStart);
                }
            }
        });
        newsrecyclerView.setLayoutManager(nwlinearLayoutManager);
        newsrecyclerView.setAdapter(firebasenewsRecycleAdapter);



//
//        dbref.limitToLast(10).addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                fetchData(dataSnapshot);
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                fetchData(dataSnapshot);
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });



        /**
         * SET ADAPTER
         */

        ProgressBar newsprogressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        newsprogressBar.setVisibility(View.VISIBLE);



        Log.v("RETRIEVE", " dbOperationsHelper.retrieveNews() NEWS=" + dbref);

        return rootView;

    }
    /**IMPLEMENT FETCH FUNCTION THAT FILLS THE ARRAYLIST  */
    private void fetchData(DataSnapshot dataSnapshot){
        newsArraylist.clear();
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            NewsModel news = ds.getValue(NewsModel.class);
            newsArraylist.add(news);
            Log.v("FIREBASE RETRIEVE", "index=" + ds.getValue(NewsModel.class));
        }
       // tell the adapter that we changed its data
        //firebaseRecyclerAdapter.notifyDataSetChanged();

    }

    public void setListener(OnDashBoardFragListener listener){
        mListener = listener; }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDashBoardFragListener) {
            mListener = (OnDashBoardFragListener) context;
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState); }


    public static interface OnDashBoardFragListener {
        // TODO: Update argument type and name
        void itemClicked(int p,long id);
    }
}
