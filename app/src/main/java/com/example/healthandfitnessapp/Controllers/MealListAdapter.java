package com.example.healthandfitnessapp.Controllers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.healthandfitnessapp.Models.CurrentDayModel;
import com.example.healthandfitnessapp.R;

import java.util.ArrayList;

public class MealListAdapter extends ArrayAdapter<CurrentDayModel> {
    Context context;
    private ArrayList<CurrentDayModel> list;

    public MealListAdapter(@NonNull Context context, ArrayList<CurrentDayModel> items) {
        super(context, R.layout.current_day_list_item, items);
        this.context = context;
        list = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.current_day_list_item, null,true);

            ImageView imageView = (ImageView) convertView.findViewById(R.id.food_imageView);
            TextView titleText = (TextView) convertView.findViewById(R.id.title_textView);
            TextView subtitleText = (TextView) convertView.findViewById(R.id.more_info_textView);

            //imageView.setImageResource(imgid[position]);
            if (list != null) {
                CurrentDayModel currentItem = list.get(position);
                String imageUrl = currentItem.getImageUrl();

                if (imageUrl.matches("")) {
                    Glide.with(context)
                            .load(R.drawable.food_placeholder)
                            .into(imageView);
                } else {
                    Glide.with(context)
                            .load(imageUrl)
                            .into(imageView);
                }

                titleText.setText(currentItem.getTitle());
                subtitleText.setText(currentItem.getExtraInfo());
            }

        }
        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
