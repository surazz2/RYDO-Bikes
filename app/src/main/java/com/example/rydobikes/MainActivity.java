package com.example.rydobikes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.rydobikes.Dialog.LogoutDialog;
import com.example.rydobikes.adapter.ProductAdapter;
import com.example.rydobikes.model.ProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseFirestore fStore;
    HashMap<String, String> hashMap;
    private List<ProductModel> list;
    ProductAdapter productAdapter;
    RecyclerView.LayoutManager layoutManager;

    FloatingActionButton floatingActionButton;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.app_nav_bar);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    drawerLayout.closeDrawer(GravityCompat.START);

                } else if (id == R.id.nav_cart) {
                    Intent i = new Intent(MainActivity.this, CartActivity.class);
                    startActivity(i);
                    drawerLayout.closeDrawer(GravityCompat.START);

                } else if (id == R.id.nav_history) {
                    Intent hIntent = new Intent(MainActivity.this, ProductHistoryActivity.class);
                    startActivity(hIntent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                } else if (id == R.id.nav_logout) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    LogoutDialog logoutDialogBox = new LogoutDialog();
                    logoutDialogBox.show(getSupportFragmentManager(), "Logout Dialog Box");
                }
                return false;
            }
        });

        recyclerView = findViewById(R.id.product_recycler_view);
        floatingActionButton = findViewById(R.id.add_fab);


        fStore = FirebaseFirestore.getInstance();
        hashMap = new HashMap<>();

        recyclerView = findViewById(R.id.product_recycler_view);

        list = new ArrayList<>();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CartActivity.class);
                startActivity(i);
            }
        });

        getProductData();
    }


    public void getProductData() {
        fStore.collection("product").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                list.clear();
                for (DocumentSnapshot snapshot : task.getResult()) {
                    ProductModel productModel = new ProductModel(snapshot.getString("id"), snapshot.getString("title"), snapshot.getString("short_des"), snapshot.getString("description"), snapshot.getString("color"), snapshot.getString("image"), snapshot.getString("quantity"), snapshot.getString("price"));
                    list.add(productModel);
                    productAdapter = new ProductAdapter(MainActivity.this, list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    recyclerView.setAdapter(productAdapter);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

}