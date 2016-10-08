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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.crakama.refugee.R;
import com.crakama.refugee.database.RepatriationModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.ContentValues.TAG;

//import com.crakama.mrefugee.R;

/**
 * A simple {@link //Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RepatriationFrag.OnGridItemFragInteractionListener} interface
 * to handle interaction events.
 * Use the {@link //RepatriationFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RepatriationFrag extends RootFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_POSITION = "position";
    private int position;

    private OnGridItemFragInteractionListener mListener;

    DatabaseReference dbref;
    FirebaseRecyclerAdapter<RepatriationModel,RepatriationModelVH> firebasenewsRecycleAdapter ;
    RecyclerView newsrecyclerView;
    LinearLayoutManager nwlinearLayoutManager;
    ProgressBar newsprogressBar;

    public RepatriationFrag() {
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
     * @return A new instance of fragment RepatriationFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static RepatriationFrag newInstance(int position) {
        RepatriationFrag fragment = new RepatriationFrag();
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
        // Inflate the fragment_repatriation_services_cv for this fragment
            View rootView = inflater.inflate(R.layout.fragment_repatriation_services_rv, container, false);
            newsrecyclerView =(RecyclerView) rootView.findViewById(R.id.rv_repatriationServices);
            newsprogressBar = (ProgressBar) rootView.findViewById(R.id.newsprogress_bar);

            return rootView;
    }

    public void setListener(OnGridItemFragInteractionListener listener){
        mListener = listener;

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnGridItemFragInteractionListener) {
            mListener = (OnGridItemFragInteractionListener) context;
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


        nwlinearLayoutManager = new LinearLayoutManager(getActivity());
        nwlinearLayoutManager.setStackFromEnd(true);
        RepatriationModel repatriationModel = new RepatriationModel();

        newsprogressBar.setVisibility(View.VISIBLE);
        dbref = FirebaseDatabase.getInstance().getReference();

        firebasenewsRecycleAdapter = new FirebaseRecyclerAdapter<RepatriationModel, RepatriationModelVH>(
                RepatriationModel.class,
                R.layout.fragment_repatriation_services_cv,
                RepatriationModelVH.class,
                dbref.child(NEWS)) {
            //NewsModel dbModel = NewsModel. .getInstance();
            @Override
            protected void populateViewHolder(RepatriationModelVH viewHolder, final RepatriationModel model, final int position) {

//                model.setCountryInfo("KENYA");
//                model.setStagesInvolved("jjsdjhdfsjfdjsfjkdsfjdfss");
//                model.setWelcomeMessage("WELCOME");
                viewHolder.repWelcomeMsg.setText(model.getWelcomeMessage());
                viewHolder.repStages.setText(model.getStagesInvolved());
                viewHolder.repCountryInfo.setText(model.getCountryInfo());
                //viewHolder.newsDate.setText(DateUtils.getRelativeTimeSpanString((long) model.getTimestamp()));
                newsprogressBar.setVisibility(View.GONE);
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.w(TAG, "You clicked on "+ position);
                        //firebasenewsRecycleAdapter.getRef(position).removeValue();
                        //openNewsDetailActivity(model.getNewsHead(), model.getNewsBody(),model.getNewsorganization());
                    }
                });
            }


//            private void openNewsDetailActivity(String...details) {
//                Intent newsIntent = new Intent(getActivity(), ShowNoticeDetails.class);
//                newsIntent.putExtra("TTTLE_KEY", details[0]);
//                newsIntent.putExtra("DESC_KEY", details[1]);
//                newsIntent.putExtra("ORG_KEY", details[2]);
//
//                startActivity(newsIntent);
//            }

        };
        newsrecyclerView.setLayoutManager(nwlinearLayoutManager);
        newsrecyclerView.setAdapter(firebasenewsRecycleAdapter);


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
    public static interface OnGridItemFragInteractionListener {
        // TODO: Update argument type and name
        void itemClicked(int p,long id);
    }


}
