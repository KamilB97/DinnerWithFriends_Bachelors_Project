package com.example.dinnerapp.activity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.dinnerapp.R;
import com.example.dinnerapp.adapter.FriendsAdapter;
import com.example.dinnerapp.adapter.MessageAdapter;
import com.example.dinnerapp.connection.ADDRESS;
import com.example.dinnerapp.connection.MySingleton;
import com.example.dinnerapp.model.entity.Message;
import com.example.dinnerapp.model.entity.MyProfileData;
import com.example.dinnerapp.model.send.Friend;
import com.example.dinnerapp.model.send.ProfileBrief;
import com.example.dinnerapp.model.send.SendMessage;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    MessageAdapter messageAdapter;
    List<SendMessage> mchat;
    RecyclerView recyclerView;
    Intent intent;
    String json_chat_url;
    Context context = ChatActivity.this;

    boolean isUnreadedMessageForProfile;

    ImageButton btnSend;
    EditText textSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Bundle extras = getIntent().getExtras();
        final Integer profileId;
        final Integer conversationId;
        String userName;

        Window window = this.getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.my_statusbar_color));


        if (extras != null) {
            profileId = extras.getInt("profileId");
            conversationId = extras.getInt("conversationId");
            userName = extras.getString("userName");



            //Log.d("ectras", "profile: " + profileId + ", convId: " + conversationId);
            // and get whatever type user account id is
        }else{
            profileId = null;
            conversationId = null;
            userName = null;
        }


        if( extras.getBoolean("isMatchedChat" )){

            ImageView addFriendView = (ImageView) findViewById(R.id.image_add_friends);
            addFriendView.setVisibility(ImageView.VISIBLE);

            addFriendView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("DISPLAY", "targetProfileID: " + profileId);
                    addToFriends(profileId);

                }
            });
        }

        json_chat_url = "http://localhost:8080/api/messages/" + conversationId + "/" + profileId;
        Log.d("DISPLAY", json_chat_url = "http://" + ADDRESS.IP + ":8080/api/messages/" + conversationId + "/" + profileId);

        final Handler handler = new Handler();
        final int delay = 2000; //milliseconds

        handler.postDelayed(new Runnable(){
            public void run(){
              if (checkForNewMessagesInConversation(conversationId, MyProfileData.profileId)){
                  readMessages(profileId, conversationId);
                  Log.d("CHECK", "JEST WWIADOMOść");
              }else{
                  Log.d("CHECK", "NIE MA  WWIADOMOSCI");
              }
                //do something
                handler.postDelayed(this, delay);
            }
        }, delay);



        intent = getIntent();

        btnSend = findViewById(R.id.btn_send);
        textSend = findViewById(R.id.text_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = textSend.getText().toString();
                if(!msg.equals("")){
                    //   sendMessage();

                    Log.d("DISPLAY", "wysyłam wiadomość: " + msg);
                    sendMessage("Kamil",conversationId, msg);


                }else {
                    Toast.makeText(ChatActivity.this, "nie możesz wysłać pustej wiadomości", Toast.LENGTH_SHORT).show();
                }
                textSend.setText("");
            }
        });

        if(extras.getBoolean("isCustomConversation")){
            userName = extras.getString("conversationName");
            // TextView conversationTitleBar = (TextView) findViewById(R.id.username);
            //conversationTitleBar.setText(conversationName);
//            String participantsString = extras.getString("participants");
//            TextView participants = (TextView) findViewById(R.id.username);
//            participants.setText(participantsString);
            Toast.makeText(ChatActivity.this,"Conversation",Toast.LENGTH_SHORT).show();

        }

        TextView topBarTittle = (TextView) findViewById(R.id.username);
        topBarTittle.setText(userName);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        readMessages(profileId, conversationId);


    }

    private void sendMessage (String sender, Integer conversationId, String messageTxt){

//        HashMap<String, Object> hashMap = new HashMap<>();
//        // hashmap.put("sender", sender ...)


        JSONObject jsonBodyObj = new JSONObject();
        String registerUrl = "http://" + ADDRESS.IP + ":8080/api/messages";
        Gson gson = new Gson();
        ProfileBrief pb = new ProfileBrief(MyProfileData.profileId, MyProfileData.name, MyProfileData.surname);
        final SendMessage sendMessage = new SendMessage(pb, conversationId,new Message(messageTxt));
        String json = gson.toJson(sendMessage);

        try {
            jsonBodyObj.put("profile", "{profileId}");
            jsonBodyObj.put("conversationId",conversationId);
            jsonBodyObj.put("messageInfo", new Message(messageTxt));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String requestBody = json;//jsonBodyObj.toString();
        Log.d("requestBody: ", requestBody);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                registerUrl, (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("Response", String.valueOf(response));
                Toast.makeText(getApplicationContext(),"wysyłanie wiadomości pomyślne",Toast.LENGTH_SHORT);
                Log.d("RESPONSE", response.toString());
                mchat.add(sendMessage);
                messageAdapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(mchat.size() -1);
             //   finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                Toast.makeText(getApplicationContext(),"Błąd wysyłania wiadomości",Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }


            @Override
            public byte[] getBody() {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                            requestBody, "utf-8");
                    return null;
                }
            }


        };

        MySingleton.getInstance(getApplicationContext()).addToRequestque(jsonObjectRequest);






    }

    private void readMessages(Integer profileId, Integer conversattionId ){
        mchat = new ArrayList<>();
        Log.d("DISPLAY", "prof/conv: " + profileId + " / " + conversattionId);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, json_chat_url, (String) null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                         Log.d("DispllayList", response.toString());
                        int count = 0;
                        while (count < response.length()) {

                            try {
                                JSONObject jsonObject = response.getJSONObject(count);
                                // Log.d("DISPLAY", jsonObject.toString());
                                JSONObject profileBrief = jsonObject.getJSONObject("profile");
                                JSONObject receivedMessage = jsonObject.getJSONObject("messageInfo");
                                //  Log.d("DISPLAY", "profileObj: " + profileBrief.toString());

                                Log.d("DisplayParticularEntity", "id: " + profileBrief.getString("profileId"));
                                Log.d("DisplayParticularEntity", "name: " + profileBrief.getString("name"));
                                Log.d("DisplayParticularEntity", "surname: " + profileBrief.getString("surname"));
                            //    Log.d("DisplayParticularEntity", "image: " + profileBrief.getString("image"));

                                SendMessage message = new SendMessage(
                                        profileBrief.getInt("profileId"),
                                        profileBrief.getString("name"),
                                        profileBrief.getString("surname"),
                                        profileBrief.getString("image"),

                                        jsonObject.getInt("conversationId"),
                                                receivedMessage.getInt("messageId"),
                                                receivedMessage.getString("messageTxt"),
                                                receivedMessage.getString("date")
                                );
                                mchat.add(message);
                              //  Log.d("DISPLAYname", jsonObject.getString("profile"));
                                Log.d("DISPLAYList", mchat.get(count).getConversationId() + " "
                                        + mchat.get(count).getMessageInfo());
                                count++;


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                        messageAdapter = new MessageAdapter(ChatActivity.this, mchat);
                        recyclerView.setAdapter(messageAdapter);
                        messageAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                error.printStackTrace();
                Log.d("BackgroundTAsk", "error");
            }
        });

        MySingleton.getInstance(context).addToRequestque(jsonArrayRequest);


        //download data ffor .. dodaj do mchat
//        messageAdapter = new MessageAdapter(ChatActivity.this, mchat);
//        recyclerView.setAdapter(messageAdapter);



    }

    public boolean checkForNewMessagesInConversation(Integer conversationId, Integer profileId){
        String unreaded_url = "http://" + ADDRESS.IP + ":8080/api/messages/unread/"+ conversationId +"/" + profileId;
        Log.d("BOOLEANURL", "" + unreaded_url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, unreaded_url, (String) null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                  //    Log.d("CheckForUnreadMessages", "checkFor UnreadMessages");

                      //  int count = 0;
                        //while (count < response.length()) {

                        try {

                            Boolean responseBoolean = response.getBoolean("unread");
                            isUnreadedMessageForProfile = responseBoolean;

                            Log.d("BOOLEANRESPONSE", responseBoolean.toString());
                            Log.d("BOOLEANRESPONSE", "isUnreadedMessageForProf" + responseBoolean.toString());
                          //  JSONObject jsonObject = response;
                            // Log.d("DISPLAY", jsonObject.toString());
                            //JSONObject profileBrief = jsonObject.getJSONObject("profile");
                            //JSONObject receivedMessage = jsonObject.getJSONObject("messageInfo");
                            //  Log.d("DISPLAY", "profileObj: " + profileBrief.toString());

                           // Log.d("DisplayParticularEntity", "id: " + profileBrief.getString("profileId"));



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


        return isUnreadedMessageForProfile;
    }

    private void addToFriends(Integer profileId){

        sendFriendInvitation(profileId);


    }

    private void sendFriendInvitation(Integer targetId){


        String unreaded_url = "http://" + ADDRESS.IP + ":8080/api/invitations/"+ MyProfileData.profileId +"/" + targetId;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, unreaded_url, (String) null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //    Log.d("CheckForUnreadMessages", "checkFor UnreadMessages");

                        //  int count = 0;
                        //while (count < response.length()) {

                        try {

                            Boolean responseBoolean = response.getBoolean("successful");

                            if( responseBoolean){

                                Toast.makeText(context, "Wysłano zaproszenie", Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(context, "Wysłanie zaproszenia nie powiodło się ", Toast.LENGTH_SHORT).show();
                            }

                            //Log.d("BOOLEANRESPONSE", responseBoolean.toString());
                            //  JSONObject jsonObject = response;
                            // Log.d("DISPLAY", jsonObject.toString());
                            //JSONObject profileBrief = jsonObject.getJSONObject("profile");
                            //JSONObject receivedMessage = jsonObject.getJSONObject("messageInfo");
                            //  Log.d("DISPLAY", "profileObj: " + profileBrief.toString());

                            // Log.d("DisplayParticularEntity", "id: " + profileBrief.getString("profileId"));



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
