package de.mateware.dialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Mate on 28.10.2016.
 */

public abstract class DialogCustomView extends Dialog {

    @Override
    void setDialogContent() {
        //do nothing
    }

    @Override
    protected View addView() {
        return getView(LayoutInflater.from(getContext()), null);
    }

    public abstract View getView(LayoutInflater inflater, ViewGroup parent);

    public static abstract class AbstractBuilder<T extends AbstractBuilder,K extends DialogCustomView> extends Dialog.AbstractBuilder<T,K> {
        public AbstractBuilder(Class<K> dialogBaseClass) {
            super(dialogBaseClass);
        }
    }
}
