package com.crakama.refugee.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.crakama.refugee.R;
import com.crakama.refugee.database.RepatriationChildFragModel;

import java.util.ArrayList;

/**
 * Created by User on 10/12/2016.
 */

public class RepatriationChildFragAdapter extends RecyclerView.Adapter<RepatriationChildFragAdapter.ViewHolder> {

   private ArrayList<RepatriationChildFragModel> repatriationChildFragModels;
    private Context mcontext;

    public RepatriationChildFragAdapter(Context context, ArrayList<RepatriationChildFragModel> repatriationChildFragModels) {
        this.repatriationChildFragModels = repatriationChildFragModels;
        this.mcontext = context;
    }


    @Override
    public RepatriationChildFragAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rv_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_repatriation_child_one_cv,parent,false);
        return new ViewHolder(rv_view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
          holder.stepsImg.setImageResource(repatriationChildFragModels.get(position).getRvImages());
        holder.stepsTitle.setText(repatriationChildFragModels.get(position).getRvTitleText());
    }

    @Override
    public int getItemCount() {
        return repatriationChildFragModels.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView stepsTitle;
        private ImageView stepsImg;

        public ViewHolder(View itemView) {
            super(itemView);
            stepsImg = (ImageView) itemView.findViewById(R.id.lv_welcome_img);
            stepsTitle = (TextView) itemView.findViewById(R.id.lv_steps_ifo);
        }
    }
}
