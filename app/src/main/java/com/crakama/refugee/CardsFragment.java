package com.crakama.refugee;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
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

/**
 * Created by cate.rakama@gmail.com on 8/28/2016.
 */
public class CardsFragment extends Fragment {
    private static final String ARG_POSITION = "position";

    private int position;


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

        if (position == 1) {

            View rootView = inflater.inflate(R.layout.activity_gv_services, container, false);

            // Here we inflate the layout we created above
            GridView gridView = (GridView) rootView.findViewById(R.id.gv_services);
            gridView.setAdapter(new GV_ServicesAdapter(getActivity().getApplicationContext(),gridViewString, gridViewImageId));
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                    switch (i) {
                        case 0:
                            //Do some thing here
                            Toast.makeText(getActivity().getApplicationContext(), "GridView Item: " + gridViewString[+i], Toast.LENGTH_LONG).show();
                            break;
                        case 1:
                            //Do some thing here

                            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                            CampListFragment camplistfragment = new CampListFragment();
                            ft.replace(R.id.fragment_container, camplistfragment);
                            ft.addToBackStack(null);
                            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                            ft.commit();
                            break;
                        case 3:
                            //Do some thing here
                            break;
                        case 4:
                            //Do some thing here
                            break;
                        case 5:
                            //Do some thing here
                            break;
                        case 6:
                            //Do some thing here
                            break;

                    }
                }
            });

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


}