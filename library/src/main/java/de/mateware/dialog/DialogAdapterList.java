package de.mateware.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ListAdapter;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import de.mateware.dialog.listener.DialogAdapterListListener;

/**
 * Created by Mate on 29.10.2016.
 */

public abstract class DialogAdapterList<T extends DialogAdapterList.DialogAdapterListEntry> extends Dialog {

    private static final Logger log = LoggerFactory.getLogger(DialogAdapterList.class);

    public static final String ARG_ARRAY_PARCABLE_ENTRIES = "parcelableListEntries";

    DialogAdapterListListener listListener;
    DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            DialogAdapterListEntry entry = getEntries().get(which);
            log.debug("Button {} {}", which, entry);
            if (listListener != null)
                listListener.onDialogAdapterListClick(getTag(), entry, getArguments());
            else
                log.info(DialogAdapterListListener.class.getSimpleName() + " not set in Activity " + getContext().getClass()
                                                                                                                 .getSimpleName());
        }
    };

    @Override
    public void setDialogContent() {
        builder.setAdapter(getAdapter(), onClickListener);
    }

    public abstract ListAdapter getAdapter();

    public ArrayList<T> getEntries() {
        return getArguments().getParcelableArrayList(ARG_ARRAY_PARCABLE_ENTRIES);
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);
        try {
            listListener = (DialogAdapterListListener) getContext();
        } catch (ClassCastException e) {
            log.warn(e.getMessage());
        }
    }

    public abstract static class DialogAdapterListEntry implements Parcelable {
        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }

    public static abstract class AbstractBuilder<M extends DialogAdapterListEntry, T extends AbstractBuilder, K extends DialogAdapterList> extends Dialog.AbstractBuilder<T, K> {

        ArrayList<M> entries = new ArrayList<>();

        public AbstractBuilder(Class<K> dialogBaseClass) {
            super(dialogBaseClass);
        }

        public T addEntry(M entry) {
            entries.add(entry);
            return (T) this;
        }

        public T setEntries(ArrayList<M> list){
            entries = list;
            return (T) this;
        }

        @Override
        public void preBuild() {
            super.preBuild();
            builderArgs.putParcelableArrayList(ARG_ARRAY_PARCABLE_ENTRIES,entries);
        }
    }
}
