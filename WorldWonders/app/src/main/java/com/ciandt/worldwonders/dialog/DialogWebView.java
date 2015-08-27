package com.ciandt.worldwonders.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.model.Wonder;

/**
 * Created by eferraz on 27/08/15.
 */
public class DialogWebView extends DialogFragment {

    public static DialogFragment show(FragmentManager fragmentManager) {

        DialogWebView dialogFragment = new DialogWebView();
        dialogFragment.show(fragmentManager, "webViewDialog");

        return dialogFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_webview, null);

        WebView webview = (WebView)view.findViewById(R.id.webview);
        webview.setWebViewClient(new MyBrowser(getFragmentManager()));

        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        Wonder wonder = (Wonder) getArguments().getSerializable("wonder");

        webview.loadUrl(wonder.getUrl());

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(view).setPositiveButton("OK!", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }

                }).create();

        return alertDialog;
    }
}


class MyBrowser extends WebViewClient {

    DialogFragment dialog;

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    public MyBrowser(FragmentManager fragmentManager) {
        dialog = DialogFragmentAnimation.show(fragmentManager);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        dialog.dismiss();
    }
}
