package com.example.healthandfitnessapp.Controllers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.healthandfitnessapp.Models.RecipeModel;
import com.example.healthandfitnessapp.Models.SearchResultModel;
import com.example.healthandfitnessapp.Models.StoreModel;
import com.example.healthandfitnessapp.R;
import com.example.healthandfitnessapp.SearchResultActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class DisplayResultsAdapter extends RecyclerView.Adapter<DisplayResultsAdapter.ViewHolder>{
    Context mContext;
    private List<SearchResultModel> mResults;
    private List<RecipeModel> recipeList;

    public DisplayResultsAdapter(Context context, List<SearchResultModel> results) {
        this.mContext = context;
        this.mResults = results;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.search_result_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (mResults != null) {
            final SearchResultModel currentResultItem = mResults.get(position);

            if (currentResultItem != null) {
                String imageUrl = currentResultItem.getImageUrl();

               try {
                    Glide.with(mContext)
                            .load(currentResultItem.getImageUrl())
                            .into(holder.getImageView());

                } catch (Exception e) {
                    e.printStackTrace();
                }

                //holder.getImageView().setBackgroundResource(R.drawable.recipe_image);
                holder.getMainTextView().setText(currentResultItem.getMainText());
                holder.getInfoTextView().setText(currentResultItem.getInfoText());
                holder.getExtraTextView().setText(currentResultItem.getExtraText());

            }

        }


        holder.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(mContext, "Clicked..", Toast.LENGTH_SHORT).show();
                if (recipeList != null) {
                    int position = holder.getAdapterPosition();

                    final RecipeModel currentRecipeItem = recipeList.get(position);

                    Intent intent = new Intent(mContext, SearchResultActivity.class);
                    intent.putExtra("thumbNail", currentRecipeItem.getImageUrl());
                    intent.putExtra("label", currentRecipeItem.getLabel());

                    //JSONArray recipeInstructions = currentRecipeItem.getIngredientLines();
                    int total_ingredients = currentRecipeItem.getIngredientLines().length();
                    intent.putExtra("totalIngredients", total_ingredients);

                    intent.putExtra("time", currentRecipeItem.getTotalTime());
                    intent.putExtra("calories", currentRecipeItem.getCalories());

                    ArrayList<String> prepInstructions = new ArrayList<>();
                    for (int i=0; i < currentRecipeItem.getIngredientLines().length(); i++) {
                        try {
                            prepInstructions.add(currentRecipeItem.getIngredientLines().getString(i));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    intent.putStringArrayListExtra("instructions", prepInstructions);
                    intent.putExtra("moreInfo", currentRecipeItem.getViewOnWebUrl());
                    mContext.startActivity(intent);

                }

            }
        });
    }

    public void setItems(List<SearchResultModel> list) {
        this.mResults = list;
    }

    public void setRecipeModelList(List<RecipeModel> recipeModelList) {
        this.recipeList = recipeModelList;
    }

    @Override
    public int getItemCount() {
        int items = 0;
        if (mResults != null)  items = mResults.size();
        return items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView mainTextView;
        private final TextView infoTextView;
        private final TextView extraTextView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.display_image);
            mainTextView = itemView.findViewById(R.id.main_text);
            infoTextView = itemView.findViewById(R.id.info_text);
            extraTextView = itemView.findViewById(R.id.extra_text);
        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getMainTextView() {
            return mainTextView;
        }

        public TextView getInfoTextView() {
            return infoTextView;
        }

        public TextView getExtraTextView() {
            return extraTextView;
        }
    }

}
