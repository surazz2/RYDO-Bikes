package com.example.rydobikes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rydobikes.adapter.CartAdapter;
import com.example.rydobikes.model.ProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class CartActivity extends AppCompatActivity {

    FirebaseFirestore fStore;
    HashMap<String, String> hashMap;
    RecyclerView recyclerView;
    private ArrayList<ProductModel> list;
    CartAdapter cartAdapter;
    RecyclerView.LayoutManager layoutManager;
    Toolbar toolbar;
    SharedPreferences sharedPreferences;
    String userId;
    public static final String MyPREFERENCES = "InvisibleIllinessPrefs";
    TextView totalPriceSet;
    int totalPriceValue = 0;
    Button orderProductCart;
    LinearLayout cartPriceBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("Cart");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("userid", null);


        fStore = FirebaseFirestore.getInstance();
        hashMap = new HashMap<>();

        recyclerView = findViewById(R.id.cart_recycler_view);
        totalPriceSet = findViewById(R.id.total_price_value);
        cartPriceBox = findViewById(R.id.bottom_cart_price_Show);
        cartPriceBox.setVisibility(View.GONE);
        recyclerView.setHasFixedSize(true);

        orderProductCart = findViewById(R.id.product_book);

        recyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        cartAdapter = new CartAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setAdapter(cartAdapter);

        orderProductCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CartActivity.this, FormActivity.class);
                i.putExtra("totalPrices", String.valueOf(totalPriceValue));
                i.putParcelableArrayListExtra("productlist", (ArrayList<? extends Parcelable>) list);
                startActivity(i);
            }
        });

        getProductData();

    }

    public void getProductData() {

        fStore.collection("product_cart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                list.clear();
                for (DocumentSnapshot snapshot : task.getResult()) {

                    ProductModel productModel = new ProductModel(snapshot.getString("id"), snapshot.getString("title"), snapshot.getString("short_des"), snapshot.getString("description"), snapshot.getString("color"), snapshot.getString("image"), snapshot.getString("quantity"), snapshot.getString("price"));
                    list.add(productModel);

                }
                if (list.size() > 0) {
                    for (int iValue = 0; iValue < list.size(); iValue++) {
                        totalPriceValue = totalPriceValue + Integer.parseInt(list.get(iValue).getPrice()) * Integer.parseInt(list.get(iValue).getQuantity());
                    }
                    if (totalPriceValue > 0) {
                        cartPriceBox.setVisibility(View.VISIBLE);
                    }
                    totalPriceSet.setText(String.valueOf(totalPriceValue));
                }

                cartAdapter.notifyDataSetChanged();

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