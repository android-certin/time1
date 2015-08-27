package com.ciandt.worldwonders.dialog;

import android.app.Dialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

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

    private void setAnimation(View view) {

        ImageView cameraView = (ImageView)view.findViewById(R.id.camera);
        ((AnimationDrawable)cameraView.getBackground()).start();

        ImageView tajMahalView = (ImageView)view.findViewById(R.id.taj_mahal);
        ((AnimationDrawable)tajMahalView.getBackground()).start();

        ImageView suitcaseView = (ImageView)view.findViewById(R.id.suitcase);
        ((AnimationDrawable)suitcaseView.getBackground()).start();

        ImageView eiffelTowerView = (ImageView)view.findViewById(R.id.eiffel_tower);
        ((AnimationDrawable)eiffelTowerView.getBackground()).start();

        ImageView pyramidsView = (ImageView)view.findViewById(R.id.pyramids);
        ((AnimationDrawable)pyramidsView.getBackground()).start();

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_fragment_animation, null);

        setAnimation(view);

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();



        return alertDialog;
    }

}
