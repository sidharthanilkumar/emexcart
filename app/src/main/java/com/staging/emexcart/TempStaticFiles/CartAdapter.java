package com.staging.emexcart.TempStaticFiles;

import android.content.Context;
import android.graphics.Paint;
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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private CartModel[] listdata;
    Context context;

    public CartAdapter(Context context, CartModel[] allprofilesDataClasses) {
        this.listdata = allprofilesDataClasses;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_cart_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        setVisibility(holder.tv_title,listdata[position].getProdName());
        setVisibility(holder.tv_total,listdata[position].getSubTotal());
        loadImage(listdata[position].getImageUrl(), holder.iv_product);
        setVisibility(holder.tv_price,listdata[position].getOriginalPrice());
    }

    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title,tv_total,tv_price;
        public ImageView iv_product;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.iv_product = itemView.findViewById(R.id.iv_product);
            this.tv_total = itemView.findViewById(R.id.tv_total);
            this.tv_price = itemView.findViewById(R.id.tv_price);
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

    private void strikeTextview(TextView tv) {
        tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

}