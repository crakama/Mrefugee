package com.crakama.refugee;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
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

        /**  Alert dialogue list menu items to be displayed on list view  */
//        mMenuItems.add(new DialogMenuItem("favor", R.mipmap.ic_winstyle_favor));
//        mMenuItems.add(new DialogMenuItem("download", R.mipmap.ic_winstyle_download));
//        mMenuItems.add(new DialogMenuItem("share", R.mipmap.ic_winstyle_share));
//        mMenuItems.add(new DialogMenuItem("delete", R.mipmap.ic_winstyle_delete));
//        mMenuItems.add(new DialogMenuItem("artist", R.mipmap.ic_winstyle_artist));
//        mMenuItems.add(new DialogMenuItem("album", R.mipmap.ic_winstyle_album));
//
//        mBasIn = new BounceTopEnter();
//        mBasOut = new SlideBottomExit();

    }

    //Populate the fragment with TextViews and grid view images
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       final Context thiscontext = container.getContext();
        /////////////////////////////


        ////////////////////////////////
        final DBAdapter sqlDBAdapter = new DBAdapter(thiscontext);
        if (position == 1) {

            View rootView = inflater.inflate(R.layout.activity_gv_services, container, false);
            View rv = inflater.inflate(R.layout.layout,container,false);

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
                            //setContentView(R.layout.list_item_main);
//                            CardsFragment f = new CardsFragment();
//                            f.NormalListDialogCustomAttr();
                            camps.clear();
                            sqlDBAdapter.openDB();
                            Cursor c = sqlDBAdapter.getAllCampDetails();
                            while(c.moveToNext()){
                                // 1 is column index in the table
                                String campname = c.getString(1);
                                camps.add(campname);
                            }
                            sqlDBAdapter.close();
                            private void showDiaglog(){

                          if(thiscontext != null){
                                AlertDialog.Builder builder = new AlertDialog.Builder(thiscontext);
                                int campNum = camps.size();
                                String[] campnames = new String[campNum];
                                for(int cm =0;cm <campNum; cm++){
                                    campnames[cm]= camps.get(cm);
                                }
                                //set items
                                builder.setItems(campnames, thiscontext);
                            }else{
                                throw new RuntimeException(" CONTEXT IS NULL,");
                            }
                        }

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


    @Override
    public void onClick(DialogInterface dialoginterface, int pos) {
     Toast.makeText(getActivity().getApplicationContext(),camps.get(pos), Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onCampListFragItemClicked(Long id) {
//        CampDetailsFragment campdetailsfragment = new CampDetailsFragment();
//        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
//        campdetailsfragment.setCamp(id);
//        ft.replace(R.id.content, campdetailsfragment);
//        ft.addToBackStack(null);
//        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//        ft.commit();
//

//    }

//    private void NormalListDialogCustomAttr(){
//        final NormalListDialog dialog = new NormalListDialog(mContext, mMenuItems);
//        dialog.title("Alert Dialog")//
//                .titleTextSize_SP(18)//
//                .titleBgColor(Color.parseColor("#409ED7"))//
//                .itemPressColor(Color.parseColor("#85D3EF"))//
//                .itemTextColor(Color.parseColor("#303030"))//
//                .itemTextSize(14)//
//                .cornerRadius(0)//
//                .widthScale(0.8f)//
//                .show(R.style.myDialogAnim);
//
//        dialog.setOnOperItemClickL(new OnOperItemClickL() {
//            @Override
//            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //T.showShort(mContext, mMenuItems.get(position).mOperName);
//                dialog.dismiss();
//            }
//        });
//    }



}