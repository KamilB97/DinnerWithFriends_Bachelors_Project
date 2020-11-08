package com.example.dinnerapp.activity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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
import com.example.dinnerapp.model.entity.ProfileDataUpdateCheckerVariable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView invitations;
    ImageView logout;
    ImageView profileImage;
    RelativeLayout profileLayout, invitationsLayout;
    Context context = MainActivity.this;
    ProfileDataUpdateCheckerVariable profileDataUpdateChecker = new ProfileDataUpdateCheckerVariable();
  //  ImageView profileImage = findViewById(R.id.profileImage);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view_main);
        navView.setSelectedItemId(R.id.navigation_mainMenu);
        navView.setItemIconTintList(null);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_chat:
                        Intent a = new Intent(MainActivity.this, FriendsActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(a);
                        break;
                    case R.id.navigation_mainMenu:
                       // Intent b = new Intent(MainActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        //startActivity(b);
                        break;

                    case R.id.navigation_match:
                        Intent c = new Intent(MainActivity.this, MatchActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(c);
                        finish();
                        break;
                    case R.id.navigation_logout:
                        Intent d = new Intent(MainActivity.this, LoginActivity.class);
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
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,WindowManager.LayoutParams.TYPE_STATUS_BAR);



            getMyProfile();


        profileDataUpdateChecker.setListener(new ProfileDataUpdateCheckerVariable.ChangeListener() {
                    @Override
                    public void onChange() {
//                        Intent intent = new Intent(MainActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                        startActivity(intent);
//                        finish();
                        loadImageFromStorage(MyProfileData.imagePath);
                        Log.d("DISPLAY", "Profile data updated");

                    }
                });


        if(MyProfileData.imagePath != null) {
            loadImageFromStorage(MyProfileData.imagePath);
        }else{
            ImageView img=(ImageView)findViewById(R.id.profile);
            if(img != null) {
               img.setImageResource(R.drawable.nophoto);
            }
        }


        profileImage = (ImageView) findViewById(R.id.profile);
        profileLayout = (RelativeLayout) findViewById(R.id.layout_profile);

        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MyProfileActivity.class);
                startActivity(intent);

            }
        });

        invitations = (ImageView) findViewById(R.id.invitations);
        invitationsLayout = (RelativeLayout) findViewById(R.id.layout_invitations);
        invitationsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, InvitationsActivity.class);
                startActivity(intent);

            }
        });

//        logout = (ImageView) findViewById(R.id.logout);
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                startActivity(intent);
//                finish();
//
//            }
//        });






    }

    public void logout(){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish(); // czy nie potrzeba zczyścić całej historii
    }

    private String saveToInternalStorage(String base){  // Bitmap bitmapImage

        base = base.substring(23);
        byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);

        Bitmap bitmapImage = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);

        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.d("DISPLAY absolutePath", directory.getAbsolutePath());

        return directory.getAbsolutePath();
    }

    private void loadImageFromStorage(String path)
    {
        Log.d("LOAD", path);
        try {
            File f=new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            Log.d("LOAD", b.toString());
            ImageView img=(ImageView)findViewById(R.id.profile);
            if(img != null) {
                img.setImageBitmap(b);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    private void getMyProfile(){


        String myProfile_url = "http://" + ADDRESS.IP + ":8080/api/profiles/myprofile/"+ MyProfileData.profileId;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, myProfile_url, (String) null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //    Log.d("CheckForUnreadMessages", "checkFor UnreadMessages");
                        try{

                            JSONArray interestingsJsonArray = response.getJSONArray("listOfInterestings");
                            JSONArray dietaryPreferencesJsonArray = response.getJSONArray("listOfDietaryPreferences");
                            //  Log.d("DISPLAY", "profileObj: " + profileBrief.toString());

                            //   Log.d("DisplayParticularEntity", "id: " + profileBrief.getString("profileId"));
                            // Log.d("DisplayParticularEntity", "name: " + profileBrief.getString("name"));
                            //Log.d("DisplayParticularEntity", "surname: " + profileBrief.getString("surname"));
                            //Log.d("DisplayParticularEntity", "image: " + profileBrief.getString("image"));
                            MyProfileData.setProfileId(response.getInt("profileId"));
                            MyProfileData.setName(response.getString("firstName"));
                            MyProfileData.setSurname(response.getString("lastName"));
                            MyProfileData.setAge(response.getInt("age"));
                            MyProfileData.setGender(response.getString("gender"));
                            MyProfileData.setCityName(response.getString("cityName"));
                            MyProfileData.setAbout(response.getString("about"));
                            MyProfileData.setStreet(response.getString("street"));

                            MyProfileData.setInterestingsList(fromJsonArrayToInterestingsArray(interestingsJsonArray));
                            MyProfileData.setDietaryList(fromJsonArrayToDietaryArray(dietaryPreferencesJsonArray));

                            Log.d("DISPLAY myProfileInfo", "" + MyProfileData.profileId + " " + MyProfileData.street + " " +
                                    MyProfileData.cityName + " " + MyProfileData.about + " " + MyProfileData.interestingsList + " " +
                                    MyProfileData.dietaryList);
                            if(response.getString("image") != null && response.getString("image") != "null"){

                                String imagePath = saveToInternalStorage(response.getString("image"));
                                MyProfileData.setImagePath(imagePath);

                                Log.d("DISPLAY myProfileInfo", MyProfileData.imagePath);
                            }

                            profileDataUpdateChecker.setProfilUpdated(true);


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


    private ArrayList<Interesting> fromJsonArrayToInterestingsArray(JSONArray jsonArray){

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


}
