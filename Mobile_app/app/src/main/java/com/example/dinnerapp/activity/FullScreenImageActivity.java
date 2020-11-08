package com.example.dinnerapp.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.dinnerapp.R;
import com.example.dinnerapp.model.entity.MyProfileData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FullScreenImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);

        Window window = this.getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.my_statusbar_color));

        ImageView fullScreenImageView = (ImageView) findViewById(R.id.fullScreenImageView);

        Intent callingActivityIntent = getIntent();

        loadImageFromStorage(MyProfileData.imagePath);

    }


    private void loadImageFromStorage(String path)
    {
        Log.d("LOAD", path);
        try {
            File f=new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            Log.d("LOAD", b.toString());
            ImageView img=(ImageView)findViewById(R.id.fullScreenImageView);
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
