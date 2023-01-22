package com.example.rydobikes.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rydobikes.ProductDetailActivity;
import com.example.rydobikes.R;
import com.example.rydobikes.model.ProductCartModel;
import com.example.rydobikes.model.ProductModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductCartAdapter extends RecyclerView.Adapter<ProductCartAdapter.ProductCartHolder> {

    Context context;
    private List<ProductCartModel> plist;

    public ProductCartAdapter(Context ct, List<ProductCartModel> list) {
        context = ct;
        plist = list;

    }

    @NonNull
    @Override
    public ProductCartAdapter.ProductCartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.adapter_cart_product_details, parent, false);
        return new ProductCartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCartAdapter.ProductCartHolder holder, int position) {
        ProductCartModel productModel = plist.get(position);
        holder.title.setText(productModel.getProduct_name());
        holder.price.setText(productModel.getProduct_price());
        holder.color.setText(productModel.getProduct_color());
        Picasso.get().load(productModel.getProduct_view_image()).into(holder.imageView);
        holder.quantity.setText(productModel.getProduct_quantity());

    }

    @Override
    public int getItemCount() {
        return plist.size();
    }

    public class ProductCartHolder extends RecyclerView.ViewHolder {
        TextView title, color, price, quantity;
        ImageView imageView;

        public ProductCartHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.product_cart_title);
            color = itemView.findViewById(R.id.product_cart_color);
            price = itemView.findViewById(R.id.product_cart_price);
            imageView = itemView.findViewById(R.id.product_cart_image);
            quantity = itemView.findViewById(R.id.product_cart_quantity);
        }
    }
}
