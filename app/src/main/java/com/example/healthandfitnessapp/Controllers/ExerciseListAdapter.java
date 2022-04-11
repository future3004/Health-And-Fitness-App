package com.example.healthandfitnessapp.Controllers;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.healthandfitnessapp.R;

import java.util.List;

public class ExerciseListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final List<String> mainTitle;
    private final List<String> subTitle;
    private final List<String> imgUrl;

    public ExerciseListAdapter(@NonNull Activity context, List<String> mainTitle, List<String> subTitle, List<String> imgUrl) {
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
        if (imgUrl.get(position).matches("")) {
            // no image, load placeholder
            Glide.with(context)
                    .load(R.drawable.exercise_placeholder)
                    .into(imageView);
        } else {
            Glide.with(context)
                    .load(imgUrl.get(position))
                    .into(imageView);
        }
        titleText.setText(mainTitle.get(position));
        subtitleText.setText(subTitle.get(position));

        return rowView;

    }
}
