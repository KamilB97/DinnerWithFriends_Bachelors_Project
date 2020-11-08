package com.example.dinnerapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dinnerapp.R;
import com.example.dinnerapp.activity.ChatActivity;
import com.example.dinnerapp.model.send.CustomConversation;
import com.example.dinnerapp.model.send.ProfileBrief;


import java.util.ArrayList;
import java.util.List;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.MyViewHolder>{

    ArrayList<CustomConversation> arrayList = new ArrayList<>();
    Context context;
    private OnItemClickListener mListener;



    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public ConversationAdapter(Context context, ArrayList<CustomConversation> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.conversation_item,parent,false);
        final MyViewHolder myViewHolder = new MyViewHolder(view, new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("XXXX ", "On Create New View Holder- listener");
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("conversationId",arrayList.get(position).getId());
                intent.putExtra("profileId",38);
                intent.putExtra("isMatchedChat", false);
                intent.putExtra("isCustomConversation", true);
                intent.putExtra("conversationName", arrayList.get(position).getName());
               // intent.putExtra("participants", stringBuilder.toString());
                context.startActivity(intent);



            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(arrayList.get(position).getName());

        holder.name.setText(stringBuilder.toString());

        List<ProfileBrief> participantList = arrayList.get(position).getParticipatns();
        StringBuilder participantNamesString = new StringBuilder();



        if(participantList.size() >= 6){
            for(int i = 0; i<6; i++){
                participantNamesString.append(participantList.get(i).getSurname());
                if(i == 5){
                    participantNamesString.append("...");
                    break;
                }
                participantNamesString.append(", ");
            }
        }else {
            for(int i = 0; i<participantList.size(); i++) {
                participantNamesString.append(participantList.get(i).getSurname());
                if (i != participantList.size()-1) {
                    participantNamesString.append(", ");
                }
            }
        }

        holder.participants.setText(participantNamesString.toString());



//        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, ChatActivity.class);
//                intent.putExtra("conversationId",arrayList.get(position).getConversationId());
//                context.startActivity(intent);
//            }

//        });

//        String base = arrayList.get(position).getProfileBrief().getImage();
//        base = base.substring(23);
//
//        byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
//
//        holder.profileImage.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));


    }

    @Override
    public int getItemCount() {
        Log.d("DISPLAY: ","arrayList size: " + arrayList.size());
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        LinearLayout linearLayout;
        TextView participants;

        public MyViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            name = (TextView)  itemView.findViewById(R.id.name);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_layout);
            participants = (TextView) itemView.findViewById(R.id.participants);


            // dodaj ksienia
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position !=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }

                }
            });

        }

    }


}
