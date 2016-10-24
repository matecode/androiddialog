package de.mateware.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ListAdapter;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created by Mate on 18.03.2015.
 */

public abstract class DialogAdapterList extends Dialog {

    private static final Logger log = LoggerFactory.getLogger(DialogAdapterList.class);

    public static final String ARG_ARRAY_PARCABLE_ARCHIVEENTRIES = "archiveEntries";

    DialogAdapterListListener listListener;

    DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            DialogAdapterListEntry entry = getEntries().get(which);
            log.debug("Button {} {}", which,entry);
            if (listListener != null) listListener.onDialogAdapterListClick(getTag(), entry, DialogAdapterList.this.getArguments());
            else log.info(DialogAdapterListListener.class.getSimpleName() + " not set in Activity " + getActivity().getClass()
                                                                                                           .getSimpleName());
        }
    };

    @Override
    void setDialogContent() {
        builder.setAdapter(getAdapter(), onClickListener);
    }



    public abstract ListAdapter getAdapter();

    public abstract ArrayList<? extends DialogAdapterListEntry> getEntries();

    public abstract static class DialogAdapterListEntry implements Parcelable{
        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        log.debug(this.getTag());
        super.onAttach(activity);
        try {
            listListener = (DialogAdapterListListener) activity;
        } catch (ClassCastException e) {
            log.warn(e.getMessage());
        }
    }

    public interface DialogAdapterListListener {
        void onDialogAdapterListClick(String tag, DialogAdapterListEntry entry, Bundle arguments);
    }

    public static abstract class AbstractBuilder<T extends AbstractBuilder,K extends DialogAdapterList> extends Dialog.AbstractBuilder<T, K> {

        AbstractBuilder(Class<K> clazz) {
            super(clazz);
        }

        public T setEntries(ArrayList<? extends DialogAdapterListEntry> entries) {
            builderArgs.putParcelableArrayList(ARG_ARRAY_PARCABLE_ARCHIVEENTRIES, entries);
            return (T) this;
        }
    }

}
