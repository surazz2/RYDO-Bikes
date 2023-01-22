package com.example.rydobikes.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.rydobikes.LoginActivity;
import com.example.rydobikes.R;
import com.google.firebase.auth.FirebaseAuth;

public class LogoutDialog extends AppCompatDialogFragment {

    TextView logoutTitle;
    LinearLayout cancelBtn, okBtn;
    SharedPreferences preferences;
    public static final String MyPREFERENCES = "riderPrefs";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_logout, null);
        builder.setView(view);

        preferences = getContext().getSharedPreferences(MyPREFERENCES, getContext().MODE_PRIVATE);
        String prefsUserName = preferences.getString("username", null);

        logoutTitle = view.findViewById(R.id.logout_dialog_title);
        logoutTitle.setText(prefsUserName);
        cancelBtn = view.findViewById(R.id.logout_cancel);
        okBtn = view.findViewById(R.id.logout_ok);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                FirebaseAuth.getInstance().signOut();
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("rememberMe");
                editor.remove("username");
                editor.remove("email");
                editor.remove("userid");
                editor.remove("userRole");
                editor.apply();

                Intent intent = new Intent(getContext(), LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();

            }
        });

        return builder.create();
    }
}
