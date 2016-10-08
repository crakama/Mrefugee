package com.crakama.refugee.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.crakama.refugee.DadaabCamp;
import com.crakama.refugee.R;

//import com.crakama.mrefugee.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link //ServiceDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ServiceDetailsFragment#//newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServiceDetailsFragment extends ListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private ListView mListView;


    // TODO: Rename and change types of parameters


    private OnListItemFragListener mListener;

    public ServiceDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /**
         * Inflate the fragment_repatriation_services_cv for this fragment
         * return inflater.inflate(R.fragment_repatriation_services_cv.fragment_view_course, container, false);
         * Calling the superclass onCreateView() method gives you the default fragment_repatriation_services_cv
         * for the ListFragment
         */
        //Create a String array of the course names
        String[] names = new String[DadaabCamp.camps.length];
        for (int i = 0; i < names.length; i++) {
            names[i] = DadaabCamp.camps[i].getName();
        }
        /**
         * simple_list_item_1 This is a built-in fragment_repatriation_services_cv resource.
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
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnListItemFragListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(long id);
    }
}
