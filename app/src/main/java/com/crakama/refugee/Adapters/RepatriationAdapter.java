package com.crakama.refugee.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.crakama.refugee.R;
import com.crakama.refugee.database.RepartChildModel;

import java.util.List;

/**
 * Created by User on 10/12/2016.
 */

public class RepatriationAdapter extends RecyclerView.Adapter<RepatriationAdapter.RepartVH> {
    private Context mcontext;
    private List<RepartChildModel> repartList;
    public class RepartVH extends RecyclerView.ViewHolder{
        private TextView stepsTitle, stepsBody, stepsDesc;
        private ImageView stepsImg;

        public RepartVH(View itemView) {
            super(itemView);
            stepsImg = (ImageView) itemView.findViewById(R.id.img_helpdesk);
            stepsTitle = (TextView) itemView.findViewById(R.id.headhealpdesk);
            stepsBody = (TextView) itemView.findViewById(R.id.txthelpDesk);
        }
    }

    public RepatriationAdapter(List<RepartChildModel> repartList){
        this.repartList = repartList;
    }
    @Override
    public RepartVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View rv_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_repatriation_child_one_cv,parent,false);
        return new RepartVH(rv_view);
    }
    @Override
    public void onBindViewHolder(RepartVH holder, int position) {
        RepartChildModel repartChildModel = repartList.get(position);
        holder.stepsImg.setImageResource(repartChildModel.getRepimage());
        holder.stepsTitle.setText(repartChildModel.getTitle());
        holder.stepsBody.setText(repartChildModel.getBody());
    }
        @Override
    public int getItemCount() {
        return repartList.size() ;
    }


}
