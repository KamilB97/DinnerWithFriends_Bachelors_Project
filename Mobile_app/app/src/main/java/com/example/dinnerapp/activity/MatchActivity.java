package com.example.dinnerapp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.dinnerapp.R;
import com.example.dinnerapp.adapter.CardsAdapter;
import com.example.dinnerapp.adapter.FriendsAdapter;
import com.example.dinnerapp.adapter.MessageAdapter;
import com.example.dinnerapp.connection.ADDRESS;
import com.example.dinnerapp.connection.MySingleton;
import com.example.dinnerapp.model.entity.DietaryPreference;
import com.example.dinnerapp.model.entity.Interesting;
import com.example.dinnerapp.model.entity.MyProfileData;
import com.example.dinnerapp.model.entity.Profile;
import com.example.dinnerapp.model.send.Friend;
import com.example.dinnerapp.model.send.SendMessage;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MatchActivity extends AppCompatActivity {


    private Profile cardsData[];
    private CardsAdapter cardsAdapter;
    private int i;
    private Context context = MatchActivity.this;
    private int profileId = 38;
    private String proposed_candidates_url = "http://"+ ADDRESS.IP +":8080/api/matcher/candidates/" + profileId;
    private Typeface font1;
    private Typeface font2;

    ListView listView;
    List<Profile> rowItems;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(R.id.navigation_match);
        navView.setItemIconTintList(null);

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_chat:
                        Intent a = new Intent(MatchActivity.this, FriendsActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(a);

                        break;
                    case R.id.navigation_mainMenu:
                         Intent b = new Intent(MatchActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(b);
                        finish();
                        break;

                    case R.id.navigation_match:
//                        Intent c = new Intent(MatchActivity.this, MatchActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                        startActivity(c);
                        break;
                    case R.id.navigation_logout:
                        Intent d = new Intent(MatchActivity.this, LoginActivity.class);
                        startActivity(d);
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
        //window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,WindowManager.LayoutParams.TYPE_STATUS_BAR);

        rowItems = new ArrayList<>();
        addCardsToArray();

        cardsAdapter = new CardsAdapter(this, R.layout.match_item, rowItems );

        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
        flingContainer.setAdapter(cardsAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                rowItems.remove(0);
                cardsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Profile obj = (Profile) dataObject;
                Integer id = obj.getProfileId();

                Log.d("DISPLAY", "swiped profile ID: " + id);

               // updateSwapNotInterested(MyProfileData.profileId, id); // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

                if(rowItems.size() <= 0 ){
                    addCardsToArray();
                    //cardsAdapter.notifyDataSetChanged();
                }


                Toast.makeText(MatchActivity.this, "Odrzucono",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onRightCardExit(Object dataObject) {

                Profile obj = (Profile) dataObject;
                Integer id = obj.getProfileId();

              //  updateSwapInterested(MyProfileData.profileId, id); // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

                if(rowItems.size() <= 0 ){
                    addCardsToArray();
                    //cardsAdapter.notifyDataSetChanged();
                }

                Toast.makeText(MatchActivity.this, "Przyjęto",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here

            }

            @Override
            public void onScroll(float scrollProgressPercent) {

            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
              //  Toast.makeText(MatchActivity.this, "click!",Toast.LENGTH_SHORT).show();
            }
        });

        addCardsToArray();



    }

    public void addCardsToArray(){

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, proposed_candidates_url, (String) null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        // Log.d("DispllayList", response.toString());
                        int count = 0;
                        while (count < response.length()) {

                            try {
                                JSONObject jsonObject = response.getJSONObject(count);
                                 Log.d("DISPLAY", jsonObject.toString());
                                JSONArray interestingsJsonArray = jsonObject.getJSONArray("listOfInterestings");
                                JSONArray dietaryPreferencesJsonArray = jsonObject.getJSONArray("listOfDietaryPreferences");
                                //  Log.d("DISPLAY", "profileObj: " + profileBrief.toString());

                             //   Log.d("DisplayParticularEntity", "id: " + profileBrief.getString("profileId"));
                               // Log.d("DisplayParticularEntity", "name: " + profileBrief.getString("name"));
                                //Log.d("DisplayParticularEntity", "surname: " + profileBrief.getString("surname"));
                                //Log.d("DisplayParticularEntity", "image: " + profileBrief.getString("image"));
                                Profile profile = new Profile(
                                        jsonObject.getInt("profileId"),
                                        jsonObject.getInt("age"),
                                        jsonObject.getString("gender"),
                                        jsonObject.getString("firstName"),
                                        jsonObject.getString("lastName"),
                                        jsonObject.getString("cityName"),
                                        jsonObject.getString("about"),
                                        jsonObject.getString("street"),
                                        jsonObject.getString("image")
                                );
                                profile.setInterestingsList(fromJsonArrayToInterestingsArray(interestingsJsonArray));
                                profile.setDietaryList(fromJsonArrayToDietaryArray(dietaryPreferencesJsonArray));
                                rowItems.add(profile);

                                Log.d("DISPLAY", profile.toString());

                                count++;


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }



                        }
                        cardsAdapter.notifyDataSetChanged();
                     //   adapter = new FriendsAdapter(arrayList);
                       // recyclerView.setAdapter(adapter);

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

    private  ArrayList<Interesting> fromJsonArrayToInterestingsArray(JSONArray jsonArray){

        ArrayList<Interesting> interestingsArray = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++){

            try {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Interesting item = new Interesting(jsonObject.getInt("id"), jsonObject.getString("name"));
                interestingsArray.add(item);

            }catch (JSONException e){
                e.printStackTrace();
            }

        }
        return interestingsArray;

    }

    private  ArrayList<DietaryPreference> fromJsonArrayToDietaryArray(JSONArray jsonArray){

        ArrayList<DietaryPreference> dietaryArray = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++){

            try {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                DietaryPreference item = new DietaryPreference(jsonObject.getInt("id"), jsonObject.getString("name"));
                dietaryArray.add(item);

            }catch (JSONException e){
                e.printStackTrace();
            }

        }
        return dietaryArray;

    }

    public void updateSwapInterested(Integer myProfileId, Integer proposedProfileId){

        String json_swap_update_url = "http://"+ ADDRESS.IP +":8080/api/matcher/updateswipe/"+ myProfileId + "/"+ proposedProfileId + "/" + myProfileId + "/2";
        JsonObjectRequest jsonObjectRequestnew = new JsonObjectRequest(Request.Method.GET, json_swap_update_url, (String) null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("DispllayList", response.toString());

                        Log.d("DISPLAY", "Updated: swiled right");

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                error.printStackTrace();
                Log.d("BackgroundTAsk", "error");
            }
        });

        MySingleton.getInstance(context).addToRequestque(jsonObjectRequestnew);
    }
    public void updateSwapNotInterested(Integer myProfileId, Integer proposedProfileId){

        String json_swap_update_url = "http://" + ADDRESS.IP+ ":8080/api/matcher/updateswipe/"+ myProfileId + "/"+ proposedProfileId + "/" + myProfileId + "/1";
        JsonObjectRequest jsonObjectRequestnew = new JsonObjectRequest(Request.Method.GET, json_swap_update_url, (String) null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("DispllayList", response.toString());

                        Log.d("DISPLAY", "Updated: swiled right");

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                error.printStackTrace();
                Log.d("BackgroundTAsk", "error");
            }
        });

        MySingleton.getInstance(context).addToRequestque(jsonObjectRequestnew);


    }


}
