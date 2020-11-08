package com.example.dinnerapp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.dinnerapp.R;
import com.example.dinnerapp.connection.ADDRESS;
import com.example.dinnerapp.connection.MySingleton;
import com.example.dinnerapp.model.entity.DietaryPreference;
import com.example.dinnerapp.model.entity.Interesting;
import com.example.dinnerapp.model.entity.MyProfileData;
import com.example.dinnerapp.model.entity.Profile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    EditText mNameEditText, mGenderEditText, mAgeEditText, mCityEditText, mAboutEditText;
    TextView mInteresting1, mInteresting2, mInteresting3, mPreference1, mPreference2, mPreference3;
    ImageView mProfileImage, mChatImage;

    Context context = ProfileActivity.this;

    Integer profileId = null;
    Integer conversationId = null;
    Boolean isMatchedRelation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Window window = this.getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.my_statusbar_color));

        Bundle extras = getIntent().getExtras();


        if (extras != null) {
            profileId = extras.getInt("profileId");
            conversationId = extras.getInt("conversationId");
            isMatchedRelation = extras.getBoolean("isMatchedChat");
        }

        getAndSetupProfileInformations(profileId);


    }


    public void getAndSetupProfileInformations(int profileId) {

        String unreaded_url = "http://" + ADDRESS.IP + ":8080/api/profiles/" + profileId;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, unreaded_url, (String) null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                             Profile profile = new Profile();

                            // Log.d("DISPLAY", jsonObject.toString());
                            profile.setProfileId(response.getInt("profileId"));
                            profile.setAge(response.getInt("age"));
                            profile.setGender(response.getString("gender"));
                            profile.setFirstName(response.getString("firstName"));
                            profile.setLastName(response.getString("lastName"));
                            profile.setCityName(response.getString("cityName"));
                            profile.setAbout(response.getString("about"));
                            profile.setImage(response.getString("image"));

                            ArrayList<Interesting> interestingsArray = fromJsonArrayToInterestingsArray(response.getJSONArray("listOfInterestings"));
                            ArrayList<DietaryPreference> dietaryArray = fromJsonArrayToDietaryArray(response.getJSONArray("listOfDietaryPreferences"));

                            profile.setInterestingsList(interestingsArray);
                            profile.setDietaryList(dietaryArray);

                            setupProfileInformations(profile);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                error.printStackTrace();
                Log.d("BackgroundTAsk", "error");
            }
        });

        MySingleton.getInstance(context).addToRequestque(jsonObjectRequest);

    }

    public void setupProfileInformations(final Profile profile){


        mChatImage = findViewById(R.id.image_chat);
        mChatImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ChatActivity.class);
                intent.putExtra("profileId", profileId);
                intent.putExtra("conversationId", conversationId);
                intent.putExtra("userName", profile.getFirstName() + " " + profile.getLastName());
                intent.putExtra("isMatchedChat", isMatchedRelation);

                startActivity(intent);

            }
        });

        mNameEditText = (EditText) findViewById(R.id.editText_name);
        mNameEditText.setText(profile.getFirstName() + " " + profile.getLastName());
        mGenderEditText = (EditText) findViewById(R.id.editText_gender);

       // String gender = "Mężczyzna";
        Log.d("DISPLAY profile gender", profile.getGender());
        if(profile.getGender().contains("female")){
            mGenderEditText.setText("Kobieta");
            Log.d("DISPLAY profile gender", "Kobieta");
        }else {
            mGenderEditText.setText("Mężczyzna");
            Log.d("DISPLAY profile gender", "mężczyzna");
        }

       // mGenderEditText.setText(gender);
        mAgeEditText = (EditText) findViewById(R.id.editText_age);
        mAgeEditText.setText(profile.getAge().toString());
        mCityEditText = (EditText) findViewById(R.id.editText_city);
        mCityEditText.setText(profile.getCityName());
        mAboutEditText = (EditText) findViewById(R.id.editText_about);
        mAboutEditText.setText(profile.getAbout());

        mProfileImage = (ImageView) findViewById(R.id.image_profile);

        if(profile.getImage() != null && profile.getImage() != "null" ){
            String base = profile.getImage();
            base = base.substring(23);
            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
            mProfileImage.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
        }else{
            mProfileImage.setImageResource(R.drawable.nophoto);
        }



        int interestingsArrayLength = profile.getInterestingsList().size();

        if(interestingsArrayLength > 0){
            mInteresting1 = (TextView) findViewById(R.id.interesting1);
            mInteresting1.setText(profile.getInterestingsList().get(0).getName());

        }
        if(interestingsArrayLength > 1){
            mInteresting2 = (TextView) findViewById(R.id.interesting2);
            mInteresting2.setText(profile.getInterestingsList().get(1).getName());

        }
        if(interestingsArrayLength > 2){
            mInteresting3 = (TextView) findViewById(R.id.interesting3);
            mInteresting3.setText(profile.getInterestingsList().get(2).getName());

        }

        int dietaryArrayLength = profile.getDietaryList().size();

        if(dietaryArrayLength > 0 ){
            mPreference1 = (TextView) findViewById(R.id.preference1);
            mPreference1.setText(profile.getDietaryList().get(0).getName());
        }
        if(dietaryArrayLength > 1 ){
            mPreference2 = (TextView) findViewById(R.id.preference2);
            mPreference2.setText(profile.getDietaryList().get(1).getName());
        }
        if(dietaryArrayLength > 2 ){
            mPreference3 = (TextView) findViewById(R.id.preference3);
            mPreference3.setText(profile.getDietaryList().get(2).getName());
        }


    }

    private ArrayList<Interesting> fromJsonArrayToInterestingsArray(JSONArray jsonArray) {

        ArrayList<Interesting> interestingsArray = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            try {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Interesting item = new Interesting(jsonObject.getInt("id"), jsonObject.getString("name"));
                interestingsArray.add(item);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return interestingsArray;

    }

    private ArrayList<DietaryPreference> fromJsonArrayToDietaryArray(JSONArray jsonArray) {

        ArrayList<DietaryPreference> dietaryArray = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            try {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                DietaryPreference item = new DietaryPreference(jsonObject.getInt("id"), jsonObject.getString("name"));
                dietaryArray.add(item);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return dietaryArray;

    }


}
