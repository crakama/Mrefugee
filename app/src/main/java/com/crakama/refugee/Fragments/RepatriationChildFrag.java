package com.crakama.refugee.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.crakama.refugee.Adapters.RepatriationChildFragAdapter;
import com.crakama.refugee.R;
import com.crakama.refugee.database.NewsModel;
import com.crakama.refugee.database.RepatriationChildFragModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link //RepatriationChildFrag.//OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RepatriationChildFrag#//newInstance} factory method to
 * create an instance of this fragment.
 */
public class RepatriationChildFrag extends RootFragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;

    // Set grid view items titles and images
    DatabaseReference dbref;
    FirebaseRecyclerAdapter<NewsModel,RepatriationChildFrag.NewsModelVH> firebasenewsRecycleAdapter ;
    RecyclerView newsrecyclerView;
    LinearLayoutManager nwlinearLayoutManager;
    ProgressBar newsprogressBar;

    private RepatriationChildFragListener mListener;


    public static class NewsModelVH extends RecyclerView.ViewHolder{

        public final TextView newsHead, newsBody,newsOrganization;
        View mView;

        public NewsModelVH(View itemView) {
            super(itemView);
            this.mView = itemView;
            this.newsHead = (TextView) mView.findViewById(R.id.lv_welcome_msg);
            this.newsBody = (TextView) mView.findViewById(R.id.lv_country_ifo);
            this.newsOrganization = (TextView) mView.findViewById(R.id.lv_repatriation_stages);
           // this.newsDate = (TextView) mView.findViewById(R.id.lv_item_date);


        }

    }// End RepatriationRootFragModel class

    public static final String NEWS= "NewsModel";
    final  String rv_StepsDesc[] = {"This service is offered by by NRC.At this stage you informed of the current status and facts about your country of origin","This process involves screening of your heads also known as headcount","Get registered by UNHRC and DRA","This is the last stage of the process where you are issued with scope card and travel details"};

    final String rv_StepBody[] = {"Start Repatriation Process in just 3 Steps!","","",""};

    final int rv_Images[] = {R.mipmap.ic_helpdesk,R.mipmap.ic_screening,R.mipmap.ic_registration,R.mipmap.ic_scopecard};

    final String rv_TitleText[] = {"1. Get HelpDesk Support",
            "2. Do Head screening ",
            "3. Registration",
            "4. Get your scope card"};


    public RepatriationChildFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param //param1 Parameter 1.
     * @param //param2 Parameter 2.
     * @return A new instance of fragment RepatriationChildFrag.

    // TODO: Rename and change types and number of parameters
    public static RepatriationChildFrag newInstance(String param1, String param2) {
        RepatriationChildFrag fragment = new RepatriationChildFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    } */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_repatriation_child, container, false);
        View rootView = inflater.inflate(R.layout.fragment_repatriation_child_rv, container, false);
        newsrecyclerView =(RecyclerView) rootView.findViewById(R.id.rv_repatriationServices);
        //newsprogressBar = (ProgressBar) rootView.findViewById(R.id.newsprogress_bar);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event

//    public void onButtonPressed() {
////        if (mListener != null) {
////            mListener.RepatriationChildFragListener(p);
////        }
//
   // }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RepatriationChildFragListener) {
            mListener = (RepatriationChildFragListener) context;
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
    public void onStart() {
        super.onStart();
        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateArticleView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // Set article based on saved instance state defined during onCreateView
            updateArticleView(mCurrentPosition);
        }

    }


    public void updateArticleView(int position) {



        nwlinearLayoutManager = new LinearLayoutManager(getActivity());
        nwlinearLayoutManager.setStackFromEnd(true);


        ArrayList<RepatriationChildFragModel> av = preparedData();
        RepatriationChildFragAdapter repatriationChildFragAdapter = new RepatriationChildFragAdapter(getContext(),av);
        newsrecyclerView.setLayoutManager(nwlinearLayoutManager);
        newsrecyclerView.setAdapter(repatriationChildFragAdapter);
    }

    private  ArrayList<RepatriationChildFragModel> preparedData(){
        ArrayList<RepatriationChildFragModel> av = new ArrayList<>();
        for(int i = 0; i < rv_TitleText.length; i++ ){
            RepatriationChildFragModel mRepatriationChildFragModel = new RepatriationChildFragModel();
            mRepatriationChildFragModel.setRvTitleText(rv_TitleText[i]);
            mRepatriationChildFragModel.setRvImages(rv_Images[i]);
            mRepatriationChildFragModel.setRv_StepsBody(rv_StepBody[i]);
            mRepatriationChildFragModel.setRv_StepsDesc(rv_StepsDesc[i]);
            av.add(mRepatriationChildFragModel);
        }
        return av;
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public static interface RepatriationChildFragListener {
        // TODO: Update argument type and name
        void onRepartBtnClick(int p);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current RepatriationChildFrag selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }
}
