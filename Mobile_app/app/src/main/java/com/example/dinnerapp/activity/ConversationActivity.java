package com.example.dinnerapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.dinnerapp.R;
import com.example.dinnerapp.adapter.ConversationAdapter;
import com.example.dinnerapp.connection.ADDRESS;
import com.example.dinnerapp.connection.MySingleton;
import com.example.dinnerapp.model.send.CustomConversation;
import com.example.dinnerapp.model.send.ProfileBrief;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ConversationActivity extends AppCompatActivity {

    private TextView mTextMessage;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<CustomConversation> arrayList = new ArrayList<>();
    ArrayList userInfo;
    Context context = ConversationActivity.this;
    String json_matchedList_url = "http://" + ADDRESS.IP + ":8080/api/conversations/custom/" + 38;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(R.id.navigation_conversation);
        navView.setItemIconTintList(null);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_friends:
                        Intent a = new Intent(ConversationActivity.this, FriendsActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(a);
                        finish();
                        break;
                    case R.id.navigation_conversation:
//                        Intent b = new Intent(ConversationActivity.this, ConversationActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                        startActivity(b);
                        break;

                    case R.id.navigation_matched:
                        Intent c = new Intent(ConversationActivity.this, MatchedActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(c);
                        finish();
                        break;
                }
                return false;
            }
        });

        Window window = this.getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.my_statusbar_color));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        Log.d("DisplayList url", json_matchedList_url);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, json_matchedList_url, (String) null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                         Log.d("DispllayList", response.toString());
                        int count = 0;
                        while (count < response.length()) {

                            ArrayList<ProfileBrief> participantsList = new ArrayList<>();

                            try {
                                JSONObject jsonObject = response.getJSONObject(count);
                                JSONArray jsonParticipantsList = jsonObject.getJSONArray("participants");

                                    for (int i = 0; i < jsonParticipantsList.length() ; i++){

                                        JSONObject jsonParticipant = jsonParticipantsList.getJSONObject(i);

                                        ProfileBrief participant = new ProfileBrief(
                                                jsonParticipant.getInt("profileId"),
                                                jsonParticipant.getString("name"),
                                                jsonParticipant.getString("surname")
                                        );

                                        participantsList.add(participant);
                                    }

                                // Log.d("DISPLAY", jsonObject.toString());
                               // JSONObject profileBrief = jsonObject.getJSONObject("profile");
                                //  Log.d("DISPLAY", "profileObj: " + profileBrief.toString());


                                CustomConversation customConv = new CustomConversation(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("name"),
                                        jsonObject.getInt("customCreated"),
                                        participantsList
                                );

                                arrayList.add(customConv);
              //                  Log.d("DISPLAYname", jsonObject.getString("profile"));
                             //   Log.d("DISPLAYList", "" + arrayList.get(count).getConversationId());
                                count++;


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                        adapter = new ConversationAdapter(ConversationActivity.this, arrayList);
                        recyclerView.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d("BackgroundTAsk", "error");
            }
        });

        MySingleton.getInstance(context).addToRequestque(jsonArrayRequest);
//        MySingleton.getInstance(context).addToRequestque(jsonArrayRequest);
//        BottomNavigationView bottomNavigationView;
//        bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
//        //bottomNavigationView.setOnNavigationItemSelectedListener(myNavigationItemListener);
//        bottomNavigationView.setSelectedItemId(R.id.navigation_notifications);

    }




    }


