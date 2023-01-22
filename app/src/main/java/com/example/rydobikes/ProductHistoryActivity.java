package com.example.rydobikes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.rydobikes.adapter.ProductOrderAdapter;
import com.example.rydobikes.model.ProductOrderModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductHistoryActivity extends AppCompatActivity {

    FirebaseFirestore fStore;
    HashMap<String, String> hashMap;
    RecyclerView recyclerView;
    private List<ProductOrderModel> list;
    ProductOrderAdapter productOrderAdapter;
    RecyclerView.LayoutManager layoutManager;
    Toolbar toolbar;
    List<String> orderCartModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("Product History");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fStore = FirebaseFirestore.getInstance();
        hashMap = new HashMap<>();


        recyclerView = findViewById(R.id.product_history_recycler_view);

        list = new ArrayList<>();


        getEventData();
    }

    public void getEventData() {
        fStore.collection("product_order").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                list.clear();


                for (DocumentSnapshot snapshot : task.getResult()) {

                    orderCartModels = (List<String>) snapshot.get("cart");


                    ProductOrderModel productOrderModel = new ProductOrderModel(
                            snapshot.getString("id"),
                            snapshot.getString("first_name"),
                            snapshot.getString("last_name"),
                            snapshot.getString("street_name"),
                            snapshot.getString("apartment_status"),
                            snapshot.getString("suburb_value"),
                            snapshot.getString("post_code"),
                            snapshot.getString("phone_value"),
                            snapshot.getString("email_address"),
                            snapshot.getString("total_price")

                    );
                    list.add(productOrderModel);
                    productOrderAdapter = new ProductOrderAdapter(getBaseContext(), list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    recyclerView.setAdapter(productOrderAdapter);

                }
//                mentoringAdapter.notifyDataSetChanged();
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