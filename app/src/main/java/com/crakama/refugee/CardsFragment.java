package com.crakama.refugee;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;
//import com.flyco.dialogsamples.utils.T;

import java.util.ArrayList;


/**
 * Created by cate.rakama@gmail.com on 8/28/2016.
 */
public class CardsFragment extends Fragment implements DialogInterface.OnClickListener
{
    private static final String ARG_POSITION = "position";
    private int position;


    /**  Alert dialogue variables   */
    //private  android.content.Context mContext = getActivity().getApplicationContext();
   // Context thiscontext = getActivity();
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
    private String[] mStringItems = {"favor", "download", "share", "delete", "artist", "album"};
    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

   ArrayList<String> camps = new ArrayList<String>();
    /**  Alert dialogue functions   */
    public void setBasIn(BaseAnimatorSet bas_in) {
        this.mBasIn = bas_in;
    }

    public void setBasOut(BaseAnimatorSet bas_out) {
        this.mBasOut = bas_out;
    }



    String[] gridViewString = {
            "PROTECTION", "CHILD REGISTRATION", "REPATRIATION", "RSD", "REFERRAL", "RESETTLEMENT",};

    int[] gridViewImageId = {
            R.drawable.protection, R.drawable.childregistration,
            R.drawable.repatriation, R.drawable.rsd,
            R.drawable.refferal, R.drawable.resettlement,};

    //Create a new instance of a fragment at a specific pisition
    public static CardsFragment newInstance(int position) {
        CardsFragment f = new CardsFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        position = getArguments().getInt(ARG_POSITION);


    }

    //Populate the fragment with TextViews and grid view images
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       final Context thiscontext = container.getContext();

        final DBAdapter sqlDBAdapter = new DBAdapter(thiscontext);
        if (position == 1) {

            View rootView = inflater.inflate(R.layout.activity_gv_services, container, false);
            View rv = inflater.inflate(R.layout.layout,container,false);

            // Here we inflate the layout we created above
            GridView gridView = (GridView) rootView.findViewById(R.id.gv_services);
            gridView.setAdapter(new GV_ServicesAdapter(getActivity().getApplicationContext(),gridViewString, gridViewImageId));


            return rootView;
        }else {
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
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }


    @Override
    public void onClick(DialogInterface dialoginterface, int pos) {
     Toast.makeText(getActivity().getApplicationContext(),camps.get(pos), Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onGridFragInteraction(Uri uri);
    }

}