package com.staging.emexcart.TempStaticFiles;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.staging.emexcart.R;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private OrderModel[] listdata;
    Context context;

    public OrderAdapter(Context context, OrderModel[] allprofilesDataClasses) {
        this.listdata = allprofilesDataClasses;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_order, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        setVisibility(holder.name,listdata[position].getProdName());
        setVisibility(holder.price,listdata[position].getPrice());
        loadImage(listdata[position].getImageUrl(), holder.imageView);
        setVisibility(holder.status,listdata[position].getStatus());
        setVisibility(holder.date,listdata[position].getData());
        setVisibility(holder.id,listdata[position].getProdId());
    }

    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name,price,status,date,id;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.prodName);
            this.imageView = itemView.findViewById(R.id.prodImage);
            this.price = itemView.findViewById(R.id.prodPrice);
            this.date = itemView.findViewById(R.id.prodDate);
            this.status = itemView.findViewById(R.id.prodStatus);
            this.id = itemView.findViewById(R.id.prodId);

        }
    }

    private void loadImage(String userimage, ImageView imageView) {

        Picasso.with(context)
                .load(userimage)
                .into(imageView);
    }

    private void setVisibility(TextView textView, String data) {
        if (!TextUtils.isEmpty(data)) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(data);
        } else {
            textView.setVisibility(View.GONE);
        }
    }

}