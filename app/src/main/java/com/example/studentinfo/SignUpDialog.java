package com.example.studentinfo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

/**
 * SignUpDialog
 *
 ************************************
 *  ###   Date         Batch            Group Id      Group     Author          Description     ITNumber
 *-------------------------------------------------------------------------------------------------------
 *    1   03-10-2020   Metro-Weekend    Metro_WE_05   Maniacs   Yoganathan.J.A    Created       IT19067902
 *
 ************************************
 */

public class SignUpDialog extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Student Sign Up ")
                .setMessage("Student details have been\nsubmitted successfully.\nDetails will be available post approval. ")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent login = new Intent(getActivity() ,Login.class);

                        startActivity(login);

                    }
                });

                return builder.create();
    }
}
