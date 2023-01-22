package com.example.rydobikes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.rydobikes.model.ProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FormActivity extends AppCompatActivity {

    private AwesomeValidation awesomeValidation;
    EditText first_name, last_name, street_name, apartment_suite, suburb, state, post_code, phone, email_address;
    Button orderSubmitBtn;
    FirebaseFirestore fStore;
    ProgressDialog progressDialog;
    Toolbar toolbar;
    String totalPrice;
    ArrayList<ProductModel> productModel;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("Address Form");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        first_name = findViewById(R.id.product_buying_first_name);
        last_name = findViewById(R.id.product_buying_last_name);
        street_name = findViewById(R.id.product_buying_address);
        apartment_suite = findViewById(R.id.product_buying_apartment_suite);
        suburb = findViewById(R.id.product_buying_suburb);
        state = findViewById(R.id.product_buying_state);
        post_code = findViewById(R.id.product_buying_post_code);
        phone = findViewById(R.id.product_buying_phone);
        email_address = findViewById(R.id.product_buying_email);
        orderSubmitBtn = findViewById(R.id.order_submit);
        fStore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        totalPrice = getIntent().getStringExtra("totalPrices");
        productModel = getIntent().getParcelableArrayListExtra("productlist");
        id = UUID.randomUUID().toString();


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.product_buying_first_name, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.product_buying_last_name, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.product_buying_address, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.product_buying_apartment_suite, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.product_buying_suburb, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.product_buying_state, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.product_buying_post_code, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.product_buying_phone, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.product_buying_email, RegexTemplate.NOT_EMPTY, R.string.valid_required);


        orderSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (awesomeValidation.validate()) {
                    orderFormSubmit();
                }

            }
        });

    }

    public void orderFormSubmit() {
        try {

            String firstName = first_name.getText().toString();
            String lastName = last_name.getText().toString();
            String streetName = street_name.getText().toString();
            String apartmentStatus = apartment_suite.getText().toString();
            String suburbValue = suburb.getText().toString();
            String postcode = post_code.getText().toString();
            String phoneValue = phone.getText().toString();
            String emailAddress = email_address.getText().toString();


            Map<String, Object> productInfo = new HashMap<>();

            productInfo.put("id", id);
            productInfo.put("first_name", firstName);
            productInfo.put("last_name", lastName);
            productInfo.put("street_name", streetName);
            productInfo.put("apartment_status", apartmentStatus);
            productInfo.put("suburb_value", suburbValue);
            productInfo.put("post_code", postcode);
            productInfo.put("phone_value", phoneValue);
            productInfo.put("email_address", emailAddress);
            productInfo.put("total_price", totalPrice);
            productInfo.put("cart", productModel);


            progressDialog.show();
            fStore.collection("product_order").document(id)
                    .set(productInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            progressDialog.dismiss();
                            Intent i = new Intent(FormActivity.this, MainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                            finish();
                            deleteIdValue();
                            Toast.makeText(getApplicationContext(), "Order Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                        }
                    });


        } catch (Exception e) {

        }
    }

    public void deleteIdValue() {
        if (productModel.size() > 0) {
            for (int iValue = 0; iValue < productModel.size(); iValue++) {

                String uuid = UUID.randomUUID().toString();
                Map<String, Object> helpingInfo = new HashMap<>();
                helpingInfo.put("id", uuid);
                helpingInfo.put("product_id", productModel.get(iValue).getId());
                helpingInfo.put("product_name", productModel.get(iValue).getTitle());
                helpingInfo.put("product_price", productModel.get(iValue).getPrice());
                helpingInfo.put("product_color", productModel.get(iValue).getColor());
                helpingInfo.put("product_quantity", productModel.get(iValue).getQuantity());
                helpingInfo.put("product_view_short_des", productModel.get(iValue).getShortDes());
                helpingInfo.put("product_view_des", productModel.get(iValue).getDescription());
                helpingInfo.put("product_view_image", productModel.get(iValue).getImage());
                helpingInfo.put("product_order_id", id);

                fStore.collection("product_order_items").document(uuid)
                        .set(helpingInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                            }
                        });

                fStore.collection("product_cart").document(productModel.get(iValue).getId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }


        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}