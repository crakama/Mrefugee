package com.crakama.refugee;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;

//import com.crakama.mrefugee.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link //HomeTabFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeTabFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeTabFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_POSITION = "position";


    // TODO: Rename and change types of parameters
    private int position;

    private OnHomeTabFragListener mListener;

    // Set grid view items titles and images
    String[] gridViewString = {
            "PROTECTION", "CHILD REGISTRATION", "REPATRIATION", "RSD", "REFERRAL", "RESETTLEMENT",};

    int[] gridViewImageId = {
            R.drawable.protection, R.drawable.childregistration,
            R.drawable.repatriation, R.drawable.rsd,
            R.drawable.refferal, R.drawable.resettlement,};

    public HomeTabFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * //@param param1 Parameter 1.
     * //@param param2 Parameter 2.
     * @return A new instance of fragment ServiceListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeTabFrag newInstance(int position) {
        HomeTabFrag fragment = new HomeTabFrag();
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
        // Inflate the layout for this fragment

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT);

            FrameLayout fl = new FrameLayout(getActivity());
            fl.setLayoutParams(params);

            final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources()
                    .getDisplayMetrics());
            TextView v = new TextView(getActivity());
            params.setMargins(margin, margin, margin, margin);
            v.setLayoutParams(params);
            v.setLayoutParams(params);
            v.setGravity(Gravity.CENTER);
            v.setBackgroundResource(R.drawable.background_card);
            v.setText("CARD " + (position + 1));

            fl.addView(v);
            return fl;
        }



    public void setListener(OnHomeTabFragListener listener){
        mListener = listener;

    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(long id) {
//        if (mListener != null) {
//            mListener.itemClicked(id);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnHomeTabFragListener) {
            mListener = (OnHomeTabFragListener) context;
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
        super.onActivityCreated(savedInstanceState);


    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public static interface OnHomeTabFragListener {
        // TODO: Update argument type and name
        void itemClicked(int p,long id);
    }
}
