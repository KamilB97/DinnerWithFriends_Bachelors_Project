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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.dinnerapp.R;
import com.example.dinnerapp.adapter.MatchedAdapter;
import com.example.dinnerapp.connection.ADDRESS;
import com.example.dinnerapp.connection.MySingleton;
import com.example.dinnerapp.model.send.Matched;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MatchedActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Matched> arrayList = new ArrayList<>();
    ArrayList userInfo;
    Context context = MatchedActivity.this;
    String json_friendsList_url = "http://" + ADDRESS.IP + ":8080/api/matcher/matches/" + 38;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matched);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(R.id.navigation_matched);
        navView.setItemIconTintList(null);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_friends:
                        Intent a = new Intent(MatchedActivity.this, FriendsActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(a);
                        finish();
                        break;

                    case R.id.navigation_conversation:
                        Intent b = new Intent(MatchedActivity.this, ConversationActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(b);
                        finish();
                        break;

                    case R.id.navigation_matched:
//                        Intent c = new Intent(MatchedActivity.this, MatchedActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                        startActivity(c);
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

        Log.d("DisplayList url", json_friendsList_url);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, json_friendsList_url, (String) null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        // Log.d("DispllayList", response.toString());
                        int count = 0;
                        while (count < response.length()) {

                            try {
                                JSONObject jsonObject = response.getJSONObject(count);
                                // Log.d("DISPLAY", jsonObject.toString());
                                JSONObject profileBrief = jsonObject.getJSONObject("profile");
                                //  Log.d("DISPLAY", "profileObj: " + profileBrief.toString());

//                                Log.d("DisplayParticularEntity", "id: " + profileBrief.getString("profileId"));
//                                Log.d("DisplayParticularEntity", "name: " + profileBrief.getString("name"));
//                                Log.d("DisplayParticularEntity", "surname: " + profileBrief.getString("surname"));
//                                Log.d("DisplayParticularEntity", "image: " + profileBrief.getString("image"));
                                Matched matched = new Matched(
                                        profileBrief.getString("profileId"),
                                        profileBrief.getString("name"),
                                        profileBrief.getString("surname"),
                                        profileBrief.getString("image"),
                                        jsonObject.getInt("conversationId")
                                );
                                arrayList.add(matched);
//                                Log.d("DISPLAYname", jsonObject.getString("profile"));
//                                Log.d("DISPLAYList", "" + arrayList.get(count).getConversationId()
//                                );
                                count++;


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                        adapter = new MatchedAdapter(MatchedActivity.this, arrayList);
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
//        BottomNavigationView bottomNavigationView;
//        bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
//        //bottomNavigationView.setOnNavigationItemSelectedListener(myNavigationItemListener);
//        bottomNavigationView.setSelectedItemId(R.id.navigation_dashboard);

    }

}
