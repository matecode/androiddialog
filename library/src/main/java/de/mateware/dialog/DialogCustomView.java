package de.mateware.dialog;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Mate on 18.10.2015.
 */
public abstract class DialogCustomView extends Dialog {

    @Override
    void setDialogContent() {
        //Override to do nothing
    }

    @Override
    public AppCompatDialog createDialogToReturn() {
        AlertDialog result = builder.create();
        result.setView(getView(LayoutInflater.from(getContext()), null));
        return result;
    }

    public abstract View getView(LayoutInflater inflater, ViewGroup parent);

    public abstract static class AbstractBuilder<T extends AbstractBuilder, K extends DialogCustomView> extends Dialog.AbstractBuilder<T,K> {

        public AbstractBuilder(Class<K> clazz) {
            super(clazz);
        }

    }

}
