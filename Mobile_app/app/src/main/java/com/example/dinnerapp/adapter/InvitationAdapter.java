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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.dinnerapp.R;
import com.example.dinnerapp.activity.ChatActivity;
import com.example.dinnerapp.connection.ADDRESS;
import com.example.dinnerapp.connection.MySingleton;
import com.example.dinnerapp.model.send.Friend;
import com.example.dinnerapp.model.send.Invitation;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InvitationAdapter extends RecyclerView.Adapter<InvitationAdapter.MyViewHolder>{


    ArrayList<Invitation> arrayList = new ArrayList<>();
    Context context;
    //  private OnItemClickListener mListener;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

//    public void setOnItemClickListener(OnItemClickListener listener){
//        mListener = listener;
//
//    }

    public InvitationAdapter(Context context,ArrayList<Invitation> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public InvitationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invitation_item,parent,false);
        final InvitationAdapter.MyViewHolder myViewHolder = new InvitationAdapter.MyViewHolder(view, new InvitationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

//                String userName = "" + arrayList.get(position).getProfile().getName() + " " +
//                        arrayList.get(position).getProfile().getSurname();
//
//                Log.d("XXXX ", "On Create New View Holder- listener");
//                Intent intent = new Intent(context, ChatActivity.class);
//                intent.putExtra("conversationId",arrayList.get(position).());
//                intent.putExtra("profileId",38);
//                intent.putExtra("userName", userName);
//
//                context.startActivity(intent);

            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(InvitationAdapter.MyViewHolder holder, final int position) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(arrayList.get(position).getProfile().getName());
        //stringBuilder.append(arrayList.get(position).getConversationId());
        stringBuilder.append(" ");
        stringBuilder.append(arrayList.get(position).getProfile().getSurname());
        // stringBuilder.append(arrayList.get(position).getFriendshipId());

        holder.name.setText(stringBuilder.toString());
        String base = arrayList.get(position).getProfile().getImage();
        base = base.substring(23);

        byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);

        holder.profileImage.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));

        holder.acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  addToFriends(arrayList.get(position).getInvitationId());
                Toast.makeText(context, "Zaakceptowano zaproszenie", Toast.LENGTH_SHORT).show();
            }
        });
        holder.denialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //rejectToFriends(arrayList.get(position).getInvitationId());
                Toast.makeText(context, "Odrzucono zaproszenie", Toast.LENGTH_SHORT).show();
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
          ImageView acceptBtn;
         ImageView denialBtn;

        public MyViewHolder(View itemView, final InvitationAdapter.OnItemClickListener listener) {
            super(itemView);
            name = (TextView)  itemView.findViewById(R.id.name);
            profileImage = (ImageView) itemView.findViewById(R.id.profileImage);
            acceptBtn = (ImageView) itemView.findViewById(R.id.accept_btn);
            denialBtn = (ImageView) itemView.findViewById(R.id.denial_btn);


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

    public void addToFriends(Integer invitationId){
        String unreaded_url = "http://" + ADDRESS.IP + ":8080/api/invitations/accept/"+ invitationId;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, unreaded_url, (String) null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //    Log.d("CheckForUnreadMessages", "checkFor UnreadMessages");

                        try {

                            Boolean responseBoolean = response.getBoolean("unread");
                            Toast.makeText(context, "Zaakceptowano zaproszenie", Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                error.printStackTrace();
                Log.d("BackgroundTAsk", "error");
            }
        });

        MySingleton.getInstance(context).addToRequestque(jsonObjectRequest);

    }

    public void rejectToFriends(Integer invitationId){

        String unreaded_url = "http://" + ADDRESS.IP + ":8080/api/invitations/denial/"+ invitationId;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, unreaded_url, (String) null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //    Log.d("CheckForUnreadMessages", "checkFor UnreadMessages");

                        try {

                            Boolean responseBoolean = response.getBoolean("unread");

                            Toast.makeText(context, "Odrzucono zaproszenie", Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                error.printStackTrace();
                Log.d("BackgroundTAsk", "error");
            }
        });

        MySingleton.getInstance(context).addToRequestque(jsonObjectRequest);

    }


}
