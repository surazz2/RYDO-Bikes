package com.example.rydobikes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SignupActivity extends AppCompatActivity {

    EditText fullName, password, emailNo, confirmPassword;
    Button createAccount;
    ImageView backBtn;
    TextView signInBack;
    ImageView showPasswordOne, showPasswordTwo;

    private AwesomeValidation awesomeValidation;
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "riderPrefs";
    private String LogTag = "Error";
    ProgressDialog progressDialog;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        signInBack = findViewById(R.id.signin_back_account);
        backBtn = findViewById(R.id.signup_back_arrow);
        createAccount = findViewById(R.id.signup_create_account);
        fullName = findViewById(R.id.fullNameForm);
        password = findViewById(R.id.signUpPasswordForm);
        emailNo = findViewById(R.id.emailForm);
        showPasswordOne = findViewById(R.id.register_show_pass_btn);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        awesomeValidation.addValidation(this, R.id.fullNameForm, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.signUpPasswordForm, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.emailForm, RegexTemplate.NOT_EMPTY, R.string.valid_required);

        showPasswordOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    ((ImageView) (view)).setImageResource(R.drawable.ic_baseline_visibility_24);
                    //Show Password
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    ((ImageView) (view)).setImageResource(R.drawable.ic_baseline_visibility_off_24);

                    //Hide Password
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }
            }
        });


        signInBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(i);
                finish();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    if (awesomeValidation.validate()) {
                        try {
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                        } catch (Exception e) {
                            Log.i(LogTag, "Error");
                        }
                        progressDialog.show();

                        String userFullName = fullName.getText().toString();
                        String userEmail = emailNo.getText().toString();
                        String userPassword = password.getText().toString();


                        String id = UUID.randomUUID().toString();
                        DocumentReference df = fStore.collection("Users").document(id);
                        Map<String, Object> userInfo = new HashMap<>();
                        userInfo.put("phone", userEmail);
                        userInfo.put("fullname", userFullName);
                        userInfo.put("password", userPassword);
                        userInfo.put("userId", id);
                        userInfo.put("role", "user");
                        df.set(userInfo);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("rememberMe", true);
                        editor.putString("username", userFullName);
                        editor.putString("phone", userEmail);
                        editor.putString("userid", id);
                        editor.putString("userRole", "user");
                        editor.commit();

                        Intent intentSignup = new Intent(SignupActivity.this, MainActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intentSignup);

                        finish();
                        progressDialog.dismiss();
                        Toast.makeText(SignupActivity.this, "You are Signed Up,", Toast.LENGTH_LONG).show();


                    }
                } catch (Exception e) {
                    Log.i("error", e.toString());
                }
            }
        });
    }
}