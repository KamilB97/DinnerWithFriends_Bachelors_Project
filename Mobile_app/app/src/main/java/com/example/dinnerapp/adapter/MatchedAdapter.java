package com.example.dinnerapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dinnerapp.R;

import com.example.dinnerapp.activity.ChatActivity;
import com.example.dinnerapp.activity.ProfileActivity;
import com.example.dinnerapp.model.send.Matched;

import java.util.ArrayList;

public class MatchedAdapter extends RecyclerView.Adapter<MatchedAdapter.MyViewHolder>{

    private ArrayList<Matched> arrayList = new ArrayList<>();
    private Context context;
    private OnItemClickListener mListener;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;

    }

    public MatchedAdapter(Context context, ArrayList<Matched> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.matched_item,parent,false);
        final MyViewHolder myViewHolder = new MyViewHolder(view, new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("profileId",arrayList.get(position).getProfileBrief().getProfileId());
                intent.putExtra("conversationId",arrayList.get(position).getConversationId());
                intent.putExtra("isMatchedChat", true);
                context.startActivity(intent);


            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(arrayList.get(position).getProfileBrief().getName());
        stringBuilder.append(" ");
        stringBuilder.append(arrayList.get(position).getProfileBrief().getSurname());



        holder.name.setText(stringBuilder.toString());
        if(arrayList.get(position).getProfileBrief().getImage() != null && arrayList.get(position).getProfileBrief().getImage() != "null"){
            String base = arrayList.get(position).getProfileBrief().getImage();
            base = base.substring(23);


            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);

            holder.profileImage.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
        }else{
            holder.profileImage.setImageResource(R.drawable.nophoto);
        }

        holder.chatImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = "" + arrayList.get(position).getProfileBrief().getName() + " " +
                        arrayList.get(position).getProfileBrief().getSurname();

                Log.d("XXXX ", "On Create New View Holder- listener");
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("conversationId",arrayList.get(position).getConversationId());
                intent.putExtra("profileId", arrayList.get(position).getProfileBrief().getProfileId());
                intent.putExtra("userName", userName);
                intent.putExtra("isMatchedChat", true);
                context.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        Log.d("DISPLAY: ","arrayList size: " + arrayList.size());
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView profileImage;
        RelativeLayout relativeLayout;
        ImageView chatImage;

        public MyViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            name = (TextView)  itemView.findViewById(R.id.name);
            profileImage = (ImageView) itemView.findViewById(R.id.profileImage);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relative_layout);
            chatImage = (ImageView) itemView.findViewById(R.id.image_chat);

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
