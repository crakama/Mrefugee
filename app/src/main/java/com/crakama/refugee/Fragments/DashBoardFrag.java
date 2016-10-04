package com.crakama.refugee.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.crakama.refugee.Adapters.DBAdapter;
import com.crakama.refugee.R;
import com.crakama.refugee.database.DBModel;
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
import java.util.HashMap;
import java.util.List;

//import com.crakama.mrefugee.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link //DashBoardFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DashBoardFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashBoardFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_POSITION = "position";


    // TODO: Rename and change types of parameters
    private int position;
    private OnDashBoardFragListener mListener;

    /**
     *
     */
    DatabaseReference dbref;
    DBOperationsHelper dbOperationsHelper;
    DBAdapter dbAdapter;
    ArrayAdapter<String> arrayAdapter;
    FirebaseListAdapter firebaseListAdapter;
    FirebaseRecyclerAdapter firebaseRecyclerAdapter;
    ListView newsListView;
    ArrayList<DBModel> newsArraylist = new ArrayList<>();


    // Set grid view items titles and images
  /** String[] listviewTitle = {
            "PROTECTION", "CHILD REGISTRATION", "REPATRIATION", "RSD", "REFERRAL", "RESETTLEMENT",};

    int[] listviewImage = {
            R.drawable.protection, R.drawable.childregistration,
            R.drawable.repatriation, R.drawable.rsd,
            R.drawable.refferal, R.drawable.resettlement,};

    String[] listviewShortDescription = new String[]{
            "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description",
            "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description",
    };

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
        View rootView = inflater.inflate(R.layout.fragment_dash_board, container, false);

        newsListView = (ListView) rootView.findViewById(R.id.newslist_view);

        dbref = FirebaseDatabase.getInstance().getReference();
        dbOperationsHelper = new DBOperationsHelper(dbref);

        dbref.limitToLast(10).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        /**
         * SET ADAPTER
         */

//        firebaseListAdapter = new FirebaseListAdapter<DBModel>(getActivity(), DBModel.class, R.layout.fragment_dashboard_imagetext, dbref) {
//            DBModel dbModel = new DBModel();
//            @Override
//            protected void populateView(View view, DBModel news, int position) {
//                ((TextView)view.findViewById(R.id.listview_item_title)).setText(dbModel.getNewsHead());
//                ((TextView)view.findViewById(R.id.listview_item_short_description)).setText(dbModel.getNewsBody());
//
//            }
//        };
//        newsListView.setAdapter(firebaseListAdapter);
        // Initialize recycler view
        RecyclerView newsrecycler = (RecyclerView) rootView.findViewById(R.id.rv_noticeboard);
        newsrecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        //newsrecycler.setHasFixedSize(true);


        ProgressBar newsprogressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        newsprogressBar.setVisibility(View.VISIBLE);



        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<DBModel, DatabaseModel>(DBModel.class, R.layout.fragment_dashboard_imagetext, DatabaseModel.class, dbref) {
            @Override
            public void populateViewHolder(final  DatabaseModel newsMessageViewHolder, DBModel news, int position) {
                newsMessageViewHolder.newsHead.setText(news.getNewsHead());
                newsMessageViewHolder.newsBody.setText(news.getNewsBody());
            }
        };
        firebaseRecyclerAdapter.notifyDataSetChanged();
        newsrecycler.setAdapter(firebaseRecyclerAdapter);
         //dbAdapter = new DBAdapter(getActivity().getApplicationContext(),dbOperationsHelper.retrieveNews());
        //arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, R.layout.fragment_dashboard_imagetext,dbOperationsHelper.retrieveNews());

        Log.v("RETRIEVE", " dbOperationsHelper.retrieveNews() NEWS=" + dbOperationsHelper.retrieveNews());

        //newsListView.setAdapter(dbAdapter);

        //Toast.makeText(getActivity().getApplicationContext(), "MUST NOT BE EMPTY", Toast.LENGTH_SHORT).show();
         //newsListView.setAdapter(arrayAdapter);

        return rootView;

    }
    /**IMPLEMENT FETCH FUNCTION THAT FILLS THE ARRAYLIST  */
    private void fetchData(DataSnapshot dataSnapshot){
        newsArraylist.clear();
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            DBModel news = ds.getValue(DBModel.class);
            newsArraylist.add(news);
            Log.v("FIREBASE RETRIEVE", "index=" + ds.getValue(DBModel.class));
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
