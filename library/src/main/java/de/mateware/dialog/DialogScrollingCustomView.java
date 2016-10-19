package de.mateware.dialog;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ScrollView;


/**
 * Created by Mate on 18.10.2015.
 */
public abstract class DialogScrollingCustomView extends DialogCustomView {

    @Override
    public AppCompatDialog createDialogToReturn() {
        AlertDialog result = builder.create();
        ScrollView scroll = new ScrollView(getContext());
        scroll.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT));
        int padding = getContext().getResources().getDimensionPixelSize(R.dimen.custom_dialog_padding);
        int topPadding = getContext().getResources().getDimensionPixelSize(R.dimen.custom_dialog_padding_top);
        scroll.setPadding(padding, topPadding, padding, 0);
        scroll.setClipToPadding(false);
        scroll.addView(getView(LayoutInflater.from(getContext()), scroll));
        result.setView(scroll);
        return result;
    }
}
