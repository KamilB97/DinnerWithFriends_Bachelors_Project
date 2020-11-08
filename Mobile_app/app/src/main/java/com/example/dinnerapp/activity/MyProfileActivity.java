package com.example.dinnerapp.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dinnerapp.R;
import com.example.dinnerapp.model.entity.MyProfileData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MyProfileActivity extends AppCompatActivity {

    EditText mNameEditText, mGenderEditText, mAgeEditText, mCityEditText, mStreetEditText, mAboutEditText;
    TextView mInteresting1, mInteresting2, mInteresting3, mPreference1, mPreference2, mPreference3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);

        Window window = this.getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.my_statusbar_color));

        initProfileData();

        ImageView profileImage = (ImageView) findViewById(R.id.image_profile);
        if(MyProfileData.imagePath != null && MyProfileData.imagePath != "null"){
            loadImageFromStorage(profileImage);
            profileImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(MyProfileActivity.this , FullScreenImageActivity.class);
                    startActivity(intent);

                }
            });
        }else{
            profileImage.setImageResource(R.drawable.nophoto);
        }










    }



private void initProfileData(){
//        Log.d("MYPROFILE", MyProfileData.name + " " + MyProfileData.surname + " " + MyProfileData.age + " " +  MyProfileData.gender +
//                " " + MyProfileData.cityName + " " + MyProfileData.street + " " +  MyProfileData.about + " " +
//                MyProfileData.interestingsList.toString() + " " +  MyProfileData.interestingsList.toString());

    mNameEditText = (EditText) findViewById(R.id.editText_name);
    mNameEditText.setText(MyProfileData.name + " " + MyProfileData.surname);
    mGenderEditText = (EditText) findViewById(R.id.editText_gender);


    if(MyProfileData.gender.contains("female")){
        mGenderEditText.setText("Kobieta");
        Log.d("DISPLAY profile gender", "Kobieta");
    }else{
        mGenderEditText.setText("Mężczyzna");
        Log.d("DISPLAY profile gender", "mężczyzna");
    }

    mAgeEditText = (EditText) findViewById(R.id.editText_age);
    mAgeEditText.setText(MyProfileData.age.toString());
    mCityEditText = (EditText) findViewById(R.id.editText_city);
    mCityEditText.setText(MyProfileData.cityName);
    mStreetEditText = (EditText) findViewById(R.id.editText_street);
    mStreetEditText.setText(MyProfileData.street);
    mAboutEditText = (EditText) findViewById(R.id.editText_about);
    mAboutEditText.setText(MyProfileData.about);

    int interestingsArrayLength = MyProfileData.interestingsList.size();

    if(interestingsArrayLength > 0){
        mInteresting1 = (TextView) findViewById(R.id.interesting1);
        mInteresting1.setText(MyProfileData.interestingsList.get(0).getName());

    }
    if(interestingsArrayLength > 1){
        mInteresting2 = (TextView) findViewById(R.id.interesting2);
        mInteresting2.setText(MyProfileData.interestingsList.get(1).getName());

    }
    if(interestingsArrayLength > 2){
        mInteresting3 = (TextView) findViewById(R.id.interesting3);
        mInteresting3.setText(MyProfileData.interestingsList.get(2).getName());

    }

    int dietaryArrayLength = MyProfileData.dietaryList.size();

    if(dietaryArrayLength > 0 ){
        mPreference1 = (TextView) findViewById(R.id.preference1);
        mPreference1.setText(MyProfileData.dietaryList.get(0).getName());
    }
    if(dietaryArrayLength > 1 ){
        mPreference2 = (TextView) findViewById(R.id.preference2);
        mPreference2.setText(MyProfileData.dietaryList.get(1).getName());
    }
    if(dietaryArrayLength > 2 ){
        mPreference3 = (TextView) findViewById(R.id.preference3);
        mPreference3.setText(MyProfileData.dietaryList.get(2).getName());
    }




}

    private void loadImageFromStorage(ImageView img)
    {
        String path = MyProfileData.imagePath;

        try {
            File f=new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            Log.d("LOAD", b.toString());
           // ImageView img=(ImageView)findViewById(R.id.profile);
            if(img != null) {
                img.setImageBitmap(b);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

}
