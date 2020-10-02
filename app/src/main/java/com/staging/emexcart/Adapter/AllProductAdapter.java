package com.staging.emexcart.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.staging.emexcart.R;
import com.staging.emexcart.models.product_model.ProductDetails;

import java.util.List;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.ViewHolder> {
    private List<ProductDetails> listdata;
    private AdapterListner listner;
    Context context;

    public interface AdapterListner {
        void onclick(String id);
    }

    public AllProductAdapter(Context context,List<ProductDetails> allprofilesDataClasses, AdapterListner listner) {
        this.listdata = allprofilesDataClasses;
        this.listner = listner;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_product_vertical, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        setVisibility(holder.name,listdata.get(position).getName());
        setVisibility(holder.discount_price,listdata.get(position).getSalePrice());
        loadImage(listdata.get(position).getImages().get(0).getSrc(), holder.imageView);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listner != null) {
                    listner.onclick(listdata.get(position).getId().toString());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, discount_price, original_price;
        public ImageView imageView;
        public Button button;
        private RatingBar ratingBar;
        public ConstraintLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.tvProductName);
            this.imageView = itemView.findViewById(R.id.imageView);
            this.discount_price = itemView.findViewById(R.id.tvPrice);
            this.relativeLayout = itemView.findViewById(R.id.rlItem);
            this.button = itemView.findViewById(R.id.btnshopnow);

        }
    }

    private void loadImage(String userimage, ImageView imageView) {

        Picasso.with(context)
                .load(userimage)
                .into(imageView);
    }

    public void add(ProductDetails s) {
        listdata.add(s);
        notifyDataSetChanged();
    }


    public void clear() {
        listdata.clear();
        notifyDataSetChanged();
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
