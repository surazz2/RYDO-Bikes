package com.example.rydobikes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.rydobikes.adapter.ProductAdapter;
import com.example.rydobikes.adapter.ProductCartAdapter;
import com.example.rydobikes.model.ProductCartModel;
import com.example.rydobikes.model.ProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity {

    private List<ProductCartModel> list;
    ProductCartAdapter productCartAdapter;
    RecyclerView.LayoutManager layoutManager;
    Toolbar toolbar;
    FirebaseFirestore fStore;
    RecyclerView recyclerView;
    HashMap<String, String> hashMap;

    String product_order_id_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("Product Items");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView = findViewById(R.id.product_cart_detail_recycler_view);

        fStore = FirebaseFirestore.getInstance();
        product_order_id_value = getIntent().getStringExtra("product_order_id");
        hashMap = new HashMap<>();


        list = new ArrayList<>();


        getProductData();
    }

    public void getProductData() {
        fStore.collection("product_order_items").whereEqualTo("product_order_id", product_order_id_value).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                list.clear();
                for (DocumentSnapshot snapshot : task.getResult()) {
                    ProductCartModel productCartModel = new ProductCartModel(
                            snapshot.getString("id"),
                            snapshot.getString("product_id"),
                            snapshot.getString("product_name"),
                            snapshot.getString("product_price"),
                            snapshot.getString("product_color"),
                            snapshot.getString("product_quantity"),
                            snapshot.getString("product_view_short_des"),
                            snapshot.getString("product_view_des"),
                            snapshot.getString("product_view_image"),
                            snapshot.getString("product_order_id")
                    );

                    list.add(productCartModel);
                    productCartAdapter = new ProductCartAdapter(OrderHistoryActivity.this, list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    recyclerView.setAdapter(productCartAdapter);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}