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
import com.example.rydobikes.model.ProductModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {
    Context context;
    private List<ProductModel> plist;

    public ProductAdapter(Context ct, List<ProductModel> list) {
        context = ct;
        plist = list;

    }

    @NonNull
    @Override
    public ProductAdapter.ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.adapter_product, parent, false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductHolder holder, int position) {
        ProductModel productModel = plist.get(position);
        holder.title.setText(productModel.getTitle());
        holder.price.setText(productModel.getPrice());
        holder.color.setText(productModel.getColor());
        Picasso.get().load(productModel.getImage()).into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ProductDetailActivity.class);
                i.putExtra("title", productModel.getTitle());
                i.putExtra("color", productModel.getColor());
                i.putExtra("price", productModel.getPrice());
                i.putExtra("image", productModel.getImage());
                i.putExtra("description", productModel.getDescription());
                i.putExtra("short_des", productModel.getShortDes());

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return plist.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {
        TextView title, color, price;
        ImageView imageView;
        CardView cardView;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.product_title);
            color = itemView.findViewById(R.id.product_color);
            price = itemView.findViewById(R.id.product_price);
            imageView = itemView.findViewById(R.id.product_image);
            cardView = itemView.findViewById(R.id.wrapItems);
        }
    }
}
