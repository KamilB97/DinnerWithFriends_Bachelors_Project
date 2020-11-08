package com.example.dinnerapp.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.dinnerapp.R;
import com.example.dinnerapp.model.entity.Profile;

import org.w3c.dom.Text;

import java.util.List;

public class CardsAdapter extends ArrayAdapter<Profile> {
    Context context;

    public CardsAdapter(Context context, int resourceId, List<Profile> items){
        super(context, resourceId, items);
    }

    public View getView(int position, View convertView, ViewGroup parent){

        Profile profileItem = getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.match_item, parent, false);
        }

        final TextView name = (TextView) convertView.findViewById(R.id.name);
        final ImageView image = (ImageView) convertView.findViewById(R.id.image);
        final ImageView informations = (ImageView) convertView.findViewById(R.id.informations);
        final ScrollView scrollView = (ScrollView) convertView.findViewById(R.id.scroll_view);

        informations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image.getVisibility() == ImageView.VISIBLE){
                    name.setVisibility(TextView.INVISIBLE);
                    image.setVisibility(ImageView.INVISIBLE);
                    scrollView.setVisibility(ScrollView.VISIBLE);

                }
                else if(image.getVisibility() == ImageView.INVISIBLE){
                    name.setVisibility(TextView.VISIBLE);
                    image.setVisibility(ImageView.VISIBLE);
                    scrollView.setVisibility(ScrollView.INVISIBLE);


                }



            }
        });



        TextView informationsName = (TextView) convertView.findViewById(R.id.informations_name);
        TextView informationsAge = (TextView) convertView.findViewById(R.id.informations_age);
        TextView informationsGender = (TextView) convertView.findViewById(R.id.informations_gender);
        TextView informationsCity = (TextView) convertView.findViewById(R.id.informations_city);
        TextView informationsAbout = (TextView) convertView.findViewById(R.id.informations_about);

        name.setText(profileItem.getFirstName() + " " + profileItem.getLastName());
        informationsName.setText(profileItem.getFirstName() + " " + profileItem.getLastName());

        informationsAge.setText("" + profileItem.getAge());
        if(profileItem.getGender().contains("female")){
            informationsGender.setText("Kobieta");
        }else {
            informationsGender.setText("Mężczyzna");
        }
        informationsCity.setText(profileItem.getCityName());
        informationsAbout.setText(profileItem.getAbout());

        if(profileItem.getImage() != null && profileItem.getImage() !="null" ){
            String base = profileItem.getImage();
            base = base.substring(23);

            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);

            image.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
        }else {
            image.setImageResource(R.drawable.nophoto);
        }




        return convertView;

    }
}
