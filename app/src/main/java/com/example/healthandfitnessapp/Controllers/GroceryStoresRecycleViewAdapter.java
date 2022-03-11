package com.example.healthandfitnessapp.Controllers;

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

    private List<PlacesPOJO.CustomA> stLstStores;
    private List<StoreModel> models;

    public GroceryStoresRecycleViewAdapter(List<PlacesPOJO.CustomA> stores, List<StoreModel> storeModels) {
        this.stLstStores = stores;
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
        holder.setData(stLstStores.get(holder.getAdapterPosition()), holder, models.get(holder.getAdapterPosition()));

    }

    @Override
    public int getItemCount() {
        return Math.min(5, stLstStores.size());
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView txtStoreName;
        private TextView txtStoreAddr;
        private TextView txtStoreDist;
        private StoreModel model;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.txtStoreDist = (TextView) itemView.findViewById(R.id.txtStoreDist);
            this.txtStoreName = (TextView) itemView.findViewById(R.id.txtStoreName);
            this.txtStoreAddr = (TextView) itemView.findViewById(R.id.txtStoreAddr);

        }


        public void setData(PlacesPOJO.CustomA info, MyViewHolder holder, StoreModel storeModel) {

            this.model = storeModel;
            holder.txtStoreDist.setText(model.distance + "\n" + model.duration);
            holder.txtStoreName.setText(info.name);
            holder.txtStoreAddr.setText(info.vicinity);


        }

    }
}
