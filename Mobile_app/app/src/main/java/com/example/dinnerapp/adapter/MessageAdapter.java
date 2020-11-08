package com.example.dinnerapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dinnerapp.R;
import com.example.dinnerapp.activity.ProfileActivity;
import com.example.dinnerapp.model.entity.Message;
import com.example.dinnerapp.model.send.SendMessage;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    private Context mContext;
    private List<SendMessage> mChat;
    private String imageurl;


    public MessageAdapter(Context mContext, List<SendMessage> mChat) { // , String imageurl
        this.mContext = mContext;
        this.mChat = mChat;
      //  this.imageurl = imageurl;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType) {

        if(viewType == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }else{

            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }


    }

    @Override
    public void onBindViewHolder( @NonNull MessageAdapter.ViewHolder holder, int position) {
        final SendMessage message = mChat.get(position);
        holder.show_message.setText(message.getMessageInfo().getMessageTxt());
        if(holder.sender != null){
            holder.sender.setText("" + message.getProfile().getName() + " " + message.getProfile().getSurname());
            holder.sender.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ProfileActivity.class);
                    intent.putExtra("profileId",message.getProfile().getProfileId());
                    mContext.startActivity(intent);

                }
            });
        }
        // holder.image...


    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView show_message;
        TextView sender;

        // ImageView profileImage;

        public ViewHolder(View itemView) {
            super(itemView);
            show_message = (TextView)  itemView.findViewById(R.id.show_message);
            sender = (TextView) itemView.findViewById(R.id.show_sender);

            //   profileImage = (ImageView) itemView.findViewById(R.id.profileImage);



        }

    }

    @Override
    public int getItemViewType(int position) {

        if(mChat.get(position).getProfile().getProfileId() == 38){
            return MSG_TYPE_RIGHT;
        }else {
            return MSG_TYPE_LEFT;
        }

    }
}



