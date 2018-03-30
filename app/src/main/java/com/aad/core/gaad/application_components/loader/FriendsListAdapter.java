package com.aad.core.gaad.application_components.loader;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aad.core.gaad.R;

import java.util.ArrayList;

public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.CustomVH> {

    private Context mContext;
    private ArrayList<FriendsModel> friendsModels;

    public FriendsListAdapter(Context mContext, ArrayList<FriendsModel> friendsModels) {
        this.mContext = mContext;
        this.friendsModels = friendsModels;
    }

    @Override
    public CustomVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend_list,parent,false);
        return new CustomVH(view);
    }

    @Override
    public void onBindViewHolder(CustomVH holder, int position) {
        FriendsModel model = friendsModels.get(position);

        holder.name.setText(model.getName());
    }

    @Override
    public int getItemCount() {
        return friendsModels.size();
    }

    class CustomVH extends RecyclerView.ViewHolder{
        TextView name;
        public CustomVH(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.friend_name);
        }
    }
}
