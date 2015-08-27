package com.ciandt.worldwonders.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.ciandt.worldwonders.R;

/**
 * Created by nlopes on 8/27/15.
 */
public class DialogFragmentAnimation extends DialogFragment {

    public static DialogFragment show(FragmentManager fragmentManager) {

        DialogFragmentAnimation dialogFragment = new DialogFragmentAnimation();
        dialogFragment.show(fragmentManager, "animationDialog");

        return dialogFragment;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_fragment_animation, null);

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();

        return alertDialog;
    }

}
