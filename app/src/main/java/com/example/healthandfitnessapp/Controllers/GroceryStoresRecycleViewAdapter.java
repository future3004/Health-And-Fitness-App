package com.example.healthandfitnessapp.Controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthandfitnessapp.Models.StoreModel;
import com.example.healthandfitnessapp.R;

import java.util.List;

public class GroceryStoresRecycleViewAdapter extends RecyclerView.Adapter<GroceryStoresRecycleViewAdapter.MyViewHolder> {

    //private List<PlacesPOJO.CustomA> stLstStores;
    Context context;
    private List<StoreModel> models;

    public GroceryStoresRecycleViewAdapter(Context context, List<StoreModel> storeModels) {
        this.context = context;
        this.models = storeModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_list_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       // holder.setData(stLstStores.get(holder.getAdapterPosition()), holder, models.get(holder.getAdapterPosition()));

    }

    @Override
    public int getItemCount() {
        int items = 0;
        if (models != null)  items = models.size();
        return items;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView txtStoreName;
        private TextView txtStoreAddr;
        private TextView txtStoreDist;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.txtStoreDist = itemView.findViewById(R.id.txtStoreDist);
            this.txtStoreName = itemView.findViewById(R.id.txtStoreName);
            this.txtStoreAddr = itemView.findViewById(R.id.txtStoreAddr);

        }


    }
}
