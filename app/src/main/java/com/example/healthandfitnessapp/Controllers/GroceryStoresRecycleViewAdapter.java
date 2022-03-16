package com.example.healthandfitnessapp.Controllers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.healthandfitnessapp.Models.SearchResultModel;
import com.example.healthandfitnessapp.Models.StoreModel;
import com.example.healthandfitnessapp.R;
import com.example.healthandfitnessapp.SearchResultActivity;

import java.util.List;

public class GroceryStoresRecycleViewAdapter extends RecyclerView.Adapter<GroceryStoresRecycleViewAdapter.MyViewHolder> {

    //private List<PlacesPOJO.CustomA> stLstStores;
    Context context;
    private List<StoreModel> storeResults;
    private String API_KEY = "AIzaSyCq_l8CRNgCkyuSSkHMxBDv6f0x5AAHzik";

    public GroceryStoresRecycleViewAdapter(Context context, List<StoreModel> storeModelList) {
        this.context = context;
        this.storeResults = storeModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_list_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
       // holder.setData(stLstStores.get(holder.getAdapterPosition()), holder, models.get(holder.getAdapterPosition()));


        if (storeResults != null) {
            final StoreModel currentResultItem = storeResults.get(position);

            if (currentResultItem != null) {
                //String imageUrl = currentResultItem.getImageUrl();
                String store_photoUrl = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photo_reference="
                        + currentResultItem.getPhotoReference() + "&key=" + API_KEY;

              try {
                    Glide.with(context)
                            .load(store_photoUrl)
                            .into(holder.getStoreImage());

                } catch (Exception e) {
                    e.printStackTrace();
                }

                //holder.getStoreImage().setBackgroundResource(R.drawable.recipe_image);

                holder.getTxtStoreName().setText(currentResultItem.getStoreName());

                String storeRating = "Rating : " + currentResultItem.getStoreRating();
                holder.getTxtStoreRating().setText(storeRating);

                holder.getTxtStoreAddress().setText(currentResultItem.getStoreAddress());

                Boolean storeAvailability = currentResultItem.getStoreOpenClose();

                if (!storeAvailability) {
                    // here store open now availability is false
                    holder.getTxtStoreInfo().setText("Closed");
                    holder.getTxtStoreInfo().setTextColor(Color.RED);
                } else {
                    // store open now availability = true
                    holder.getTxtStoreInfo().setText("Open");
                }


            }

        }

    }

    public void setItems(List<StoreModel> stores) {
        this.storeResults = stores;
    }

    @Override
    public int getItemCount() {
        int items = 0;
        if (storeResults != null)  items = storeResults.size();
        return items;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView storeImage;
        private final TextView txtStoreName;
        private final TextView txtStoreRating;
        private final TextView txtStoreAddress;
        private final TextView txtStoreInfo;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            storeImage = itemView.findViewById(R.id.grocery_store_image);
            txtStoreInfo = itemView.findViewById(R.id.txtStoreInfo);
            txtStoreRating = itemView.findViewById(R.id.txtStoreRating);
            txtStoreName = itemView.findViewById(R.id.txtStoreName);
            txtStoreAddress = itemView.findViewById(R.id.txtStoreAddr);

        }

        public ImageView getStoreImage() {
            return storeImage;
        }

        public TextView getTxtStoreName() {
            return txtStoreName;
        }

        public TextView getTxtStoreRating() {
            return txtStoreRating;
        }

        public TextView getTxtStoreAddress() {
            return txtStoreAddress;
        }

        public TextView getTxtStoreInfo() {
            return txtStoreInfo;
        }
    }
}
