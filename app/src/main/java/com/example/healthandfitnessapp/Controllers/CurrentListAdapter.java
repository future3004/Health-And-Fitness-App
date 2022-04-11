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

import com.bumptech.glide.Glide;
import com.example.healthandfitnessapp.Models.CurrentDayList;
import com.example.healthandfitnessapp.R;

import java.util.List;

public class CurrentListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] mainTitle;
    private final String[] subTitle;
    private final String[] imgUrl;

    public CurrentListAdapter(@NonNull Activity context, String[] mainTitle, String[] subTitle, String[] imgUrl) {
        super(context, R.layout.current_day_list_item, mainTitle);

        this.context = context;
        this.mainTitle = mainTitle;
        this.subTitle = subTitle;
        this.imgUrl = imgUrl;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.current_day_list_item, null,true);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.food_imageView);
        TextView titleText = (TextView) rowView.findViewById(R.id.title_textView);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.more_info_textView);

        //imageView.setImageResource(imgid[position]);
        if (imgUrl[position].matches("")) {
            Glide.with(context)
                    .load(R.drawable.food_placeholder)
                    .into(imageView);
        } else {
            Glide.with(context)
                    .load(imgUrl[position])
                    .into(imageView);
        }
        titleText.setText(mainTitle[position]);
        subtitleText.setText(subTitle[position]);

        return rowView;

    }
}
