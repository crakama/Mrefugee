package com.dadaabcamps.mrefugee.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dadaabcamps.mrefugee.Activities.HelpDeskSupport;
import com.dadaabcamps.mrefugee.Adapters.RepatriationAdapter;
import com.dadaabcamps.mrefugee.OnRepChildItemClickListener;
import com.dadaabcamps.mrefugee.R;
import com.dadaabcamps.mrefugee.FirebaseModels.RepartChildModel;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

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
    //FirebaseRecyclerAdapter<NewsModel,RepatriationChildFrag.NewsModelVH> firebasenewsRecycleAdapter ;
    RecyclerView newsrecyclerView;
    LinearLayoutManager nwlinearLayoutManager;
    ProgressBar newsprogressBar;
    private List<RepartChildModel> repartModelList = new ArrayList<>();
    private RepatriationAdapter repatriationAdapter;

    private RepatriationChildFragListener mListener;
    public TextView helpDesk, medicalScreening,movementPass, departure;


    public static final String NEWS= "NewsModel";
    //final  String rv_StepsDesc[] = {"This service is offered by by NRC.At this stage you informed of the current status and facts about your country of origin","This process involves screening of your heads also known as headcount","Get registered by UNHRC and DRA","This is the last stage of the process where you are issued with scope card and travel details"};

    final  String rv_HelpDesk = "People expressing interest in returning to Somalia go to the UNHCR Return helpdesks in their respective camp " +
            "where they get information and counselling on the voluntary return programme and get registered as persons with the intention to return." +
            " NRC (Norwegian Refugee Council) provides information on the conditions in the return areas in Somalia (Country of origin information) at the UNHCR helpdesk.";
    final  String rv_Screening ="Persons go through medical checks and get issued with medical clearances.";

    final  String rv_MovementPass ="Step 6. UNHCR generates movement passes and submits to RAS (Kenya Refugee Affairs Secretariat, the former DRA) for facilitation of exit formalities.\n" +
            "Step 7. RAS takes movement passes to Kenya Immigration office for stamping\n ";

    final  String rv_Departure ="Step 8. Individuals go to the UNHCR helpdesk in their respective camp and are transported to one of the Transit Centres (managed by DRC) in Dagahaley or Hagadera, where DRC and UNHCR perform a final verification based on the UNHCR-issued pre-manifest with the list of family members in each household that have expressed willingness to return to Somalia. By this point, around 30% of the initial number of refugees on the pre-manifest that have expressed willingness to return to Somalia do not show up for repatriation, either having given up or taking longer to further reflect on the decision.\n" +
            "Refugees Selected or booked for travelling are accommodated at the transit centres a day before departure in the afternoon. Mobilization to the transit centres are done in four consecutive days of Sundays, Mondays, Tuesday and Wednesdays.\n" +
            "\n" +
            "At the Transit Centre, NRC distributes non-food items and are provided with food and shelter for the night.\n" +
            "Step 9. On the morning of departure, refugees are transported to the Dadaab airstrip, where RAS delivers signed and stamped movement passes, and collects manual fingerprints (on paper) of adult returnees.\n" +
            "DRC delivers UNHCR’s cash return grant and has the head of household sign (by way of manual fingerprint) the Voluntary Repatriation Form.\n" +
            "Returnees board the aircraft to Mogadishu, other locations or the bus to Dobhley.\n";




    public RepatriationChildFrag() {
        // Required empty public constructor

    }

    // TODO: Rename and change types and number of parameters
    public static RepatriationChildFrag newInstance(int position) {
        RepatriationChildFrag fragment = new RepatriationChildFrag();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION,position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        if (getArguments() != null) {
        //            mParam1 = getArguments().getString(ARG_PARAM1);
        //            mParam2 = getArguments().getString(ARG_PARAM2);
        //        }
        prepareRepartData();

    }


    private void prepareRepartData() {
        RepartChildModel model = new RepartChildModel("1. Information Desk Support.",rv_HelpDesk, R.mipmap.ic_helpdesk);
        repartModelList.add(model);

        model = new RepartChildModel("2. Medical Screening ",rv_Screening,R.mipmap.ic_screening);
        repartModelList.add(model);

        model = new RepartChildModel("3. Process Movement Pass",rv_MovementPass,R.mipmap.ic_registration);
        repartModelList.add(model);

        model = new RepartChildModel("4. Departure",rv_Departure,R.mipmap.ic_ifo);
        repartModelList.add(model);
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
        newsrecyclerView =(RecyclerView) rootView.findViewById(R.id.rv_repatriation);
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof RepatriationChildFragListener) {
//            mListener = (RepatriationChildFragListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    /*
     *  During startup, check if there are arguments passed to the fragment.
     *  onStart is a good place to do this because the layout has already been
     *  applied to the fragment at this point so we can safely call the method
     *  below that sets the article text.
     */
    @Override
    public void onStart() {
        super.onStart();

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
        repatriationAdapter = new RepatriationAdapter(repartModelList);
        newsrecyclerView.setLayoutManager(nwlinearLayoutManager);
       newsrecyclerView.setAdapter(repatriationAdapter);
        newsrecyclerView.addOnItemTouchListener(new OnRepChildItemClickListener(getContext(), new
                OnRepChildItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                switch (i){
                    case 0:

                        openHelpDeskSupport(rv_HelpDesk);

                        break;
                    case 1:
                       Toast.makeText(view.getContext(), "NO MORE INFORMATION TO SHOW",Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(view.getContext(), "NO MORE INFORMATION TO SHOW",Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(view.getContext(), "NO MORE INFORMATION TO SHOW",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }));


    }
//


    private void openHelpDeskSupport(String rv_helpDesk) {
        Intent newsIntent = new Intent(getActivity(), HelpDeskSupport.class);
        newsIntent.putExtra("TTTLE_KEY", rv_helpDesk);
        //newsIntent.putExtra("DESC_KEY", details[1]);
        //newsIntent.putExtra("ORG_KEY", details[2]);
        startActivity(newsIntent);
    }

//    private void openMedicalScreening(String ...details) {
//        Intent newsIntent = new Intent(getActivity(), MedicalScreening.class);
//        newsIntent.putExtra("TTTLE_KEY", details[0]);
//        //newsIntent.putExtra("DESC_KEY", details[1]);
//        //newsIntent.putExtra("ORG_KEY", details[2]);
//        startActivity(newsIntent);
//    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    public static interface RepatriationChildFragListener {
        // TODO: Update argument type and name
        void onRepartBtnClick(int p);
        //void helpDeskSupport(View v,rv_HelpDesk);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current RepatriationChildFrag selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }
}
