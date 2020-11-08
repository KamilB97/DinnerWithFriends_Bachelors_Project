package com.example.dinnerapp.activity;

import android.content.Context;
import android.os.StrictMode;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.dinnerapp.R;
import com.example.dinnerapp.adapter.FriendsAdapter;
import com.example.dinnerapp.adapter.InvitationAdapter;
import com.example.dinnerapp.connection.ADDRESS;
import com.example.dinnerapp.connection.MySingleton;
import com.example.dinnerapp.model.entity.MyProfileData;
import com.example.dinnerapp.model.send.Friend;
import com.example.dinnerapp.model.send.Invitation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InvitationsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Invitation> arrayList = new ArrayList<>();
    ArrayList userInfo;
    Context context = InvitationsActivity.this;
    String json_invitationList_url = "http://"+ ADDRESS.IP + ":8080/api/invitations/" + 38;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitations);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Window window = this.getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.my_statusbar_color));



        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        Log.d("DisplayList url", json_invitationList_url);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, json_invitationList_url, (String) null,
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
                                Invitation invitation = new Invitation(
                                        jsonObject.getInt("invitationId"),
                                        profileBrief.getInt("profileId"),
                                        profileBrief.getString("name"),
                                        profileBrief.getString("surname"),
                                        profileBrief.getString("image")
                                );
                                arrayList.add(invitation);
//                                Log.d("DISPLAYname", jsonObject.getString("profile"));
//                                Log.d("DISPLAYList", arrayList.get(count).getConversationId() + " "
//                                        + arrayList.get(count).getFriendshipId()
//                                );
                                count++;


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                        adapter = new InvitationAdapter(InvitationsActivity.this, arrayList);
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

    }


}
