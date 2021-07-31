package com.quangminh.uiexample02.Adapter;

import android.accounts.Account;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.quangminh.uiexample02.R;
import com.quangminh.uiexample02.model.Friends;

import java.util.List;
import java.util.zip.Inflater;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountVH> {

    List<Friends> friends;

    Context context;

    public AccountAdapter(List<Friends> friends, Context context) {
        this.friends = friends;
        this.context = context;
    }

    public void setData(List<Friends> accounts){
        this.friends=accounts;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public AccountVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);

        return new AccountVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountVH holder, int position) {
        Friends friend = friends.get(position);
        holder.roundedImageView.setImageResource(friend.getAvt());
        holder.name.setText(friend.getName());
        holder.chat.setText(friend.getInbox());
        holder.day.setText(friend.getDate());

    }

    @Override
    public int getItemCount() {
        if(friends!=null){
            return friends.size();
        }
        return 0;
    }

    public class AccountVH extends RecyclerView.ViewHolder{

        RoundedImageView roundedImageView;
        TextView name, chat, day;
        public AccountVH(@NonNull View itemView) {
            super(itemView);
            roundedImageView = itemView.findViewById(R.id.avt);
            name = itemView.findViewById(R.id.name);
            chat = itemView.findViewById(R.id.chat);
            day = itemView.findViewById(R.id.day);
        }
    }
}
