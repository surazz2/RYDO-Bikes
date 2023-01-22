package com.example.rydobikes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rydobikes.Dialog.ConfirmDialog;
import com.example.rydobikes.R;
import com.example.rydobikes.model.ProductModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {
    private Context context;
    ArrayList<ProductModel> pList;

    public CartAdapter(Context ct, ArrayList<ProductModel> productModels) {
        context = ct;
        pList = productModels;
    }

    @NonNull
    @Override
    public CartAdapter.CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.adapter_cart, parent, false);
        return new CartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartHolder holder, int position) {
        ProductModel productModel = pList.get(position);
        holder.name.setText(productModel.getTitle());
        int totalPrice = Integer.parseInt(productModel.getPrice()) * Integer.parseInt(productModel.getQuantity());
        holder.price.setText(String.valueOf(totalPrice));
        Picasso.get().load(productModel.getImage()).into(holder.productImg);
        holder.qty.setText(String.valueOf(productModel.getQuantity()));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfirmDialog confirmDialog = new ConfirmDialog(productModel.getId(), "product_cart");
                confirmDialog.show(((AppCompatActivity) context).getSupportFragmentManager(), "Logout Dialog Box");
            }
        });
    }

    @Override
    public int getItemCount() {
        return pList.size();
    }

    public class CartHolder extends RecyclerView.ViewHolder {
        TextView price, name, qty;
        ImageView imageView, productImg;

        public CartHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_cart_name);
            price = itemView.findViewById(R.id.product_cart_price);
            imageView = itemView.findViewById(R.id.cart_item_delete);
            productImg = itemView.findViewById(R.id.product_cart_image_view);
            qty = itemView.findViewById(R.id.product_cart_qty);
        }
    }
}
