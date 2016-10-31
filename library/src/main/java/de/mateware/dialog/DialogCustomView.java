package de.mateware.dialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Mate on 28.10.2016.
 */

public abstract class DialogCustomView extends Dialog {

    @Override
    public void setDialogContent() {
        //do nothing
    }

    @Override
    public View addView() {
        return getView(LayoutInflater.from(getContext()), null);
    }

    public abstract View getView(LayoutInflater inflater, ViewGroup parent);

    public static abstract class AbstractBuilder<T extends AbstractBuilder,K extends DialogCustomView> extends Dialog.AbstractBuilder<T,K> {

        public AbstractBuilder(Class<K> dialogBaseClass) {
            super(dialogBaseClass);
        }

        public T setPaddingLeft(int padding) {
          builderArgs.putInt(ARG_INT_LEFTPADDING,padding);
          return (T) this;
        }

        public T setPaddingRight(int padding) {
            builderArgs.putInt(ARG_INT_RIGHTPADDING,padding);
            return (T) this;
        }
        public T setPaddingTop(int padding) {
            builderArgs.putInt(ARG_INT_TOPPADDING,padding);
            return (T) this;
        }
        public T setPaddingBottom(int padding) {
            builderArgs.putInt(ARG_INT_BOTTOMPADDING,padding);
            return (T) this;
        }

        public T setPadding(int padding) {
            setPaddingTop(padding);
            setPaddingBottom(padding);
            setPaddingLeft(padding);
            setPaddingRight(padding);
            return (T) this;
        }
    }
}
