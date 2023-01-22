package com.example.rydobikes.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rydobikes.OrderHistoryActivity;
import com.example.rydobikes.R;
import com.example.rydobikes.model.ProductOrderModel;

import java.util.List;

public class ProductOrderAdapter extends RecyclerView.Adapter<ProductOrderAdapter.ProductOrderHolder> {
    List<ProductOrderModel> list;
    Context context;


    public ProductOrderAdapter(Context ct, List<ProductOrderModel> plist) {
        context = ct;
        list = plist;
    }

    @NonNull
    @Override
    public ProductOrderAdapter.ProductOrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.adapter_product_order, parent, false);
        return new ProductOrderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductOrderAdapter.ProductOrderHolder holder, int position) {
        ProductOrderModel donationModel = list.get(position);
        holder.firstName.setText(donationModel.getFirst_name());
        holder.lastName.setText(donationModel.getLast_name());
        holder.suburb.setText(donationModel.getSuburb_value());
        holder.streetName.setText(donationModel.getStreet_name());
        holder.apartmentStatus.setText(donationModel.getApartment_status());
        holder.postCode.setText(donationModel.getPost_code());
        holder.phoneNo.setText(donationModel.getPhone_value());
        holder.emailAddress.setText(donationModel.getEmail_address());
        holder.donationPrice.setText(donationModel.getTotal_price());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, OrderHistoryActivity.class);
                i.putExtra("product_order_id", donationModel.getId());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ProductOrderHolder extends RecyclerView.ViewHolder {
        TextView firstName, lastName, streetName, apartmentStatus, suburb, postCode, phoneNo, emailAddress, donationPrice;
        CardView cardView;


        public ProductOrderHolder(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.admin_product_first_name);
            lastName = itemView.findViewById(R.id.admin_product_last_name);
            streetName = itemView.findViewById(R.id.admin_product_street_name);
            apartmentStatus = itemView.findViewById(R.id.admin_product_apartment_status);
            suburb = itemView.findViewById(R.id.admin_product_suburb_value);
            postCode = itemView.findViewById(R.id.admin_product_post_code);
            phoneNo = itemView.findViewById(R.id.admin_product_phone_value);
            emailAddress = itemView.findViewById(R.id.admin_product_email_address);
            donationPrice = itemView.findViewById(R.id.admin_product_donation_price);
            cardView = itemView.findViewById(R.id.cart_product_cart_view);

        }
    }
}
