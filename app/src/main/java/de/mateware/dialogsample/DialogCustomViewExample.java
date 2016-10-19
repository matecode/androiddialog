package de.mateware.dialogsample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.mateware.dialog.DialogCustomView;

/**
 * Created by mate on 19.10.2016.
 */

public class DialogCustomViewExample extends DialogCustomView {

    @Override
    public View getView(LayoutInflater inflater, ViewGroup parent) {
        View result = inflater.inflate(R.layout.custom_view_dialog_content,parent);
        return result;
    }

    public static class Builder extends DialogCustomView.AbstractBuilder<Builder,DialogCustomViewExample> {
        public Builder() {
            super(DialogCustomViewExample.class);
        }
    }

}
