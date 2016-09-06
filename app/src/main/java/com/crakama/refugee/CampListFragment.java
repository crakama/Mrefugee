package com.crakama.refugee;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//import com.crakama.mrefugee.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link //CampListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CampListFragment#//newInstance} factory method to
 * create an instance of this fragment.
 */
public class CampListFragment extends ListFragment {
    private ListView mListView;
    // Required empty public constructor
    public CampListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /**
         * Inflate the layout for this fragment
         * return inflater.inflate(R.layout.fragment_view_course, container, false);
         * Calling the superclass onCreateView() method gives you the default layout
         * for the ListFragment
         */
        //Create a String array of the course names
        String[] names = new String[DadaabCamp.camps.length];
             for (int i = 0; i < names.length; i++) {
             names[i] = DadaabCamp.camps[i].getName();
         }
        /**
         * simple_list_item_1 This is a built-in layout resource.
         * It tells the array adapter to display each item in the array in a single text view
         */
        View campview = inflater.inflate(R.layout.fragment_camp_list, container, false);
        //mListView = (ListView) campview.findViewById(R.id.item_list_view);
        //mListView.setAdapter(adapter);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(),android.R.layout.simple_list_item_1, names);
        mListView = (ListView) campview.findViewById(R.id.list_frag);
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

    }












    /**
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_POSITION = "position";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int position;


    private OnCampListFragListener listener;

    static interface OnCampListFragListener {
        // TODO: Update argument type and name
        void onCampListFragItemClicked(Long id);
    }

    public CampListFragment() {
        // Required empty public constructor
    }



//    //Add a listener to a fragment by creating an Interface


    @Override
    public void   onListItemClick(ListView lst, View vw, int pos, long id){

        if (listener != null){
            //Tell the listener when an item in the ListView is clicked.
            listener.onCampListFragItemClicked(id);
        }else{
            throw new RuntimeException(" LISTENER IS NULL,");
        }
    }


    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_camp_list, container, false);
        //Create a String array of the course names
        String[] names = new String[DadaabCamp.camps.length];
  /**      for (int i = 0; i < names.length; i++) {
            names[i] = DadaabCamp.camps[i].getName();
        }
        /**
         * simple_list_item_1 This is a built-in layout resource.
         * It tells the array adapter to display each item in the array in a single text view

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(),R.layout.layout, names);
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCampListFragListener) {
            listener = (OnCampListFragListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }



    /**The onStart() method is called when the fragment is about to become visible.
    @Override
    public void onStart() {
        super.onStart();
//        try {
//            listener = (OnCampListFragListener) getActivity();
//        } catch (ClassCastException e) {
//            throw new ClassCastException(getActivity().toString()
//                    + " MUST IMPLEMENT CourseListListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    //Add a listener to a fragment by creating an Interface

}

