package com.crakama.refugee.Fragments;

import android.content.Context;
import android.content.Intent;
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

import com.crakama.refugee.Activities.ShowNoticeDetails;
import com.crakama.refugee.R;
import com.crakama.refugee.database.NewsModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.ContentValues.TAG;


//import com.crakama.mrefugee.R;

/**
 * //A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link //LiveNewsFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * //Use the {@link LiveNewsFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LiveNewsFrag extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_POSITION = "position";
    private int position;

    private OnHomeTabFragListener mListener;
    //private NewsModel newModel = NewsModel.getInstance();


    // Set grid view items titles and images
    DatabaseReference dbref;
    FirebaseRecyclerAdapter<NewsModel,LiveNewsFrag.NewsModelVH> firebasenewsRecycleAdapter ;
    RecyclerView newsrecyclerView;
    LinearLayoutManager nwlinearLayoutManager;
    ProgressBar newsprogressBar;

    public static class NewsModelVH extends RecyclerView.ViewHolder{

        public final TextView newsHead, newsBody,newsOrganization,newsDate;
        View mView;

        public NewsModelVH(View itemView) {
            super(itemView);
            this.mView = itemView;
            this.newsHead = (TextView) mView.findViewById(R.id.listview_item_title);
            this.newsBody = (TextView) mView.findViewById(R.id.listview_item_short_description);
            this.newsOrganization = (TextView) mView.findViewById(R.id.listview_item_organization);
            this.newsDate = (TextView) mView.findViewById(R.id.lv_item_date);


        }

    }// End NewsModelVH class

    public static final String NEWS= "NewsModel";




    public LiveNewsFrag() { // Required empty public constructor
    }

    public static LiveNewsFrag newInstance(int position) {
        LiveNewsFrag fragment = new LiveNewsFrag();
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

    public void setListener(OnHomeTabFragListener listener){ mListener = listener; }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_notice_rv, container, false);
        newsrecyclerView =(RecyclerView) rootView.findViewById(R.id.rv_noticeboard);
        nwlinearLayoutManager = new LinearLayoutManager(getActivity());
        nwlinearLayoutManager.setStackFromEnd(true);

        dbref = FirebaseDatabase.getInstance().getReference();
        newsprogressBar = (ProgressBar) rootView.findViewById(R.id.newsprogress_bar);
        newsprogressBar.setVisibility(View.VISIBLE);

        firebasenewsRecycleAdapter = new FirebaseRecyclerAdapter<NewsModel, NewsModelVH>(
                NewsModel.class,
                R.layout.fragment_live_news,
                NewsModelVH.class,
                dbref.child(NEWS)) {
            //NewsModel dbModel = NewsModel. .getInstance();
            @Override
            protected void populateViewHolder(NewsModelVH viewHolder, final NewsModel model, final int position) {
                viewHolder.newsHead.setText(model.getNewsHead());
                viewHolder.newsBody.setText(model.getNewsBody());
                viewHolder.newsOrganization.setText(model.getNewsorganization());
                //viewHolder.newsDate.setText(DateUtils.getRelativeTimeSpanString((long) model.getTimestamp()));
                newsprogressBar.setVisibility(View.GONE);
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //firebasenewsRecycleAdapter.getRef(position).removeValue();
                        openNewsDetailActivity(model.getNewsHead(), model.getNewsBody(),model.getNewsorganization());
                    }
                });
            }

            private void openNewsDetailActivity(String...details) {
                Intent newsIntent = new Intent(getActivity(), ShowNoticeDetails.class);
                newsIntent.putExtra("TTTLE_KEY", details[0]);
                newsIntent.putExtra("DESC_KEY", details[1]);
                newsIntent.putExtra("ORG_KEY", details[2]);

                startActivity(newsIntent);
            }
        };
        newsrecyclerView.setLayoutManager(nwlinearLayoutManager);
        newsrecyclerView.setAdapter(firebasenewsRecycleAdapter);
        /**
         * SET ADAPTER
         */


        Log.v("RETRIEVE", " dbOperationsHelper.retrieveNews() NEWS=" + dbref);

        return rootView;



        }





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
//    public static interface OnHomeTabFragListener {
//        // TODO: Update argument type and name
//        void itemClicked(int p,long id);
//    }
    public static interface OnHomeTabFragListener {
        // TODO: Update argument type and name
        void itemClicked(int p,long id);
    }
}
