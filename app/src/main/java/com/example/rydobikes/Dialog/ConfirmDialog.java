package com.example.rydobikes.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.rydobikes.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class ConfirmDialog extends AppCompatDialogFragment {

    LinearLayout confirmOrder, confirmOrderCancel;

    String deleteItemId, tokenValue;


    FirebaseFirestore fStore;
    String tableName;

    public ConfirmDialog(String deleteId, String table_name) {
        deleteItemId = deleteId;
        tableName = table_name;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.confirm_layout, null);
        builder.setView(view);


        confirmOrder = view.findViewById(R.id.confirm_ok);
        confirmOrderCancel = view.findViewById(R.id.confirm_cancel);
        fStore = FirebaseFirestore.getInstance();

        confirmOrderCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        confirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmedBucketOrder();
            }
        });


        return builder.create();
    }

    public void confirmedBucketOrder() {
        fStore.collection(tableName).document(deleteItemId).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (!task.isSuccessful()) {
                    dismiss();

                    Toast.makeText(getActivity(), "Sorry", Toast.LENGTH_SHORT).show();
                }

                dismiss();
                getActivity().finish();
                Toast.makeText(getActivity(), "Successfully Delete", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dismiss();
                Toast.makeText(getActivity(), "Sorry", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
