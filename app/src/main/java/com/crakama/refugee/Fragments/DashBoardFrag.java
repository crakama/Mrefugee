package com.crakama.refugee.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.crakama.refugee.Adapters.DBAdapter;
import com.crakama.refugee.R;
import com.crakama.refugee.database.DBOperationsHelper;
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
    DatabaseReference db;
    DBOperationsHelper dbOperationsHelper;
    DBAdapter dbAdapter;
    ListView newsListView;



    // Set grid view items titles and images
  /**  String[] listviewTitle = {
            "PROTECTION", "CHILD REGISTRATION", "REPATRIATION", "RSD", "REFERRAL", "RESETTLEMENT",};

    int[] listviewImage = {
            R.drawable.protection, R.drawable.childregistration,
            R.drawable.repatriation, R.drawable.rsd,
            R.drawable.refferal, R.drawable.resettlement,};

    String[] listviewShortDescription = new String[]{
            "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description",
            "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description",
    };*/

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

//        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
//
//        for (int i = 0; i < listviewTitle.length; i++) {
//            HashMap<String, String> hm = new HashMap<String, String>();
//            hm.put("listview_title", listviewTitle[i]);
//            hm.put("listview_discription", listviewShortDescription[i]);
//            hm.put("listview_image", Integer.toString(listviewImage[i]));
//            aList.add(hm);
//        }
        db = FirebaseDatabase.getInstance().getReference();
        dbOperationsHelper = new DBOperationsHelper(db);

        /**
         * SET ADAPTER
         */
         dbAdapter = new DBAdapter(getActivity().getApplicationContext(),dbOperationsHelper.retrieveNews());
         newsListView.setAdapter(dbAdapter);

//        String[] from = {"listview_image", "listview_title", "listview_discription"};
//        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};

      //  SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity().getApplicationContext(), aList, R.layout.fragment_dashboard_imagetext, from, to);

     //   androidListView.setAdapter(simpleAdapter);

        return rootView;

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
