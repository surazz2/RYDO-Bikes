package com.example.rydobikes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProductDetailActivity extends AppCompatActivity {

    ImageView imageView, addItem, subItem;
    TextView title, shortDes, description, price, bikeColor, quantityNo;
    String bikeName, bikeColorS, bikePrice, bikeImage, bikeDescription, bikeShortDes;
    int quantityValue = 1;
    LinearLayout progressCart, addToCart;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        fStore = FirebaseFirestore.getInstance();

        title = findViewById(R.id.detail_item_name);
        shortDes = findViewById(R.id.detail_item_short_des);
        description = findViewById(R.id.item_product_description);
        bikeColor = findViewById(R.id.item_detail_color);
        price = findViewById(R.id.item_detail_price);
        imageView = findViewById(R.id.product_detail_image);
        addItem = findViewById(R.id.add_item_btn);
        subItem = findViewById(R.id.sub_item_btn);
        quantityNo = findViewById(R.id.item_quantity_no);
        addToCart = findViewById(R.id.addToShopBucket);
        progressCart = findViewById(R.id.add_to_bucket_progress);
        progressCart.setVisibility(View.GONE);

        bikeName = getIntent().getStringExtra("title");
        bikeColorS = getIntent().getStringExtra("color");
        bikePrice = getIntent().getStringExtra("price");
        bikeImage = getIntent().getStringExtra("image");
        bikeDescription = getIntent().getStringExtra("description");
        bikeShortDes = getIntent().getStringExtra("short_des");

        title.setText(bikeName);
        shortDes.setText(bikeShortDes);
        description.setText(bikeDescription);
        bikeColor.setText(bikeColorS);
        description.setText(bikeDescription);
        price.setText(bikePrice);
        Picasso.get().load(bikeImage).into(imageView);
        quantityNo.setText(String.valueOf(quantityValue));

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (quantityValue < 10) {

                    quantityValue = quantityValue + 1;
                    quantityNo.setText(String.valueOf(quantityValue));

                } else {
                    Toast.makeText(ProductDetailActivity.this, "Only 10 item", Toast.LENGTH_SHORT).show();
                }

            }
        });

        subItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantityValue > 1) {
                    quantityValue = quantityValue - 1;
                    quantityNo.setText(String.valueOf(quantityValue));

                } else {
                    Toast.makeText(ProductDetailActivity.this, "at least one item", Toast.LENGTH_SHORT).show();
                }
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressCart.setVisibility(View.VISIBLE);
                addToCart.setVisibility(View.GONE);


                String id = UUID.randomUUID().toString();
                Map<String, Object> helpingInfo = new HashMap<>();
                helpingInfo.put("id", id);
                helpingInfo.put("title", bikeName);
                helpingInfo.put("short_des", bikeShortDes);
                helpingInfo.put("description", bikeDescription);
                helpingInfo.put("price", bikePrice);
                helpingInfo.put("color", bikeColorS);
                helpingInfo.put("quantity", String.valueOf(quantityValue));
                helpingInfo.put("image", bikeImage);


                fStore.collection("product_cart").document(id)
                        .set(helpingInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressCart.setVisibility(View.GONE);
                                addToCart.setVisibility(View.VISIBLE);
                                finish();
                                Toast.makeText(ProductDetailActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressCart.setVisibility(View.GONE);
                                addToCart.setVisibility(View.VISIBLE);
                                Toast.makeText(ProductDetailActivity.this, "Sorry", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


    }
}