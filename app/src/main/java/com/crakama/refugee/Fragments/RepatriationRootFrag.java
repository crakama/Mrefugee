package com.crakama.refugee.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.crakama.refugee.R;
import com.crakama.refugee.database.RepatriationModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

//import com.crakama.mrefugee.R;

/**
 * A simple {@link //Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link //RepatriationRootFrag.OnGridItemFragInteractionListener} interface
 * to handle interaction events.
 * Use the {@link //RepatriationRootFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RepatriationRootFrag extends RootFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_POSITION = "position";
    private int position;
    private OnRepartButtonClickedListener mListener;

    DatabaseReference dbref;
    FirebaseRecyclerAdapter<RepatriationModel,RepatriationModelVH> firebasenewsRecycleAdapter ;
    RecyclerView newsrecyclerView;
    LinearLayoutManager nwlinearLayoutManager;
    ProgressBar newsprogressBar;
    Button buttonRepart;

    public RepatriationRootFrag() {
        // Required empty public constructor
    }


    public static class RepatriationModelVH extends RecyclerView.ViewHolder{

        public final TextView repWelcomeMsg, repCountryInfo,repStages;
        View mView;

        public RepatriationModelVH(View itemView) {
            super(itemView);
            this.mView = itemView;
            this.repWelcomeMsg = (TextView) mView.findViewById(R.id.lv_welcome_msg);
            this.repCountryInfo = (TextView) mView.findViewById(R.id.lv_country_ifo);
            this.repStages = (TextView) mView.findViewById(R.id.lv_repatriation_stages);
            //this.newsDate = (TextView) mView.findViewById(R.id.lv_item_date);


        }

    }// End NewsModelVH class

    public static final String NEWS= "RepatriationModel";

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * //@param param1 Parameter 1.
     * //@param param2 Parameter 2.
     * @return A new instance of fragment RepatriationRootFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static RepatriationRootFrag newInstance(int position) {
        RepatriationRootFrag fragment = new RepatriationRootFrag();
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
            View rootView = inflater.inflate(R.layout.fragment_repatriation_root_frag, container, false);
            //newsrecyclerView =(RecyclerView) rootView.findViewById(R.id.rv_repatriationServices);
            //newsprogressBar = (ProgressBar) rootView.findViewById(R.id.newsprogress_bar);

           rootView.findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Notify the parent activity of the button being clicked
                mListener.onRepartBtnClick(position);
            }
        });

            return rootView;
    }



    public void setListener(OnRepartButtonClickedListener listener){
        mListener = listener;

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRepartButtonClickedListener) {
            mListener = (OnRepartButtonClickedListener) context;
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

//


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
    /** Called by RepartRootFrag when a button is clicked */
    public static interface OnRepartButtonClickedListener {
        // TODO: Update argument type and name
        void onRepartBtnClick(int p);
    }

}
