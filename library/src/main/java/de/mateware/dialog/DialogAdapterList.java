package de.mateware.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ListAdapter;

import de.mateware.dialog.listener.DialogAdapterListListener;
import de.mateware.dialog.log.Log;

import java.util.ArrayList;

/**
 * Created by Mate on 29.10.2016.
 */

public abstract class DialogAdapterList<T extends DialogAdapterList.DialogAdapterListEntry> extends Dialog {

    public static final String ARG_ARRAY_PARCABLE_ENTRIES = "parcelableListEntries";

    DialogAdapterListListener listListener;
    DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            DialogAdapterListEntry entry = getEntries().get(which);
            Log.d("Button" + which + " " + entry);
            if (listListener != null)
                listListener.onDialogAdapterListClick(getTag(), entry, getArguments());
            else
                Log.w(DialogAdapterListListener.class.getSimpleName() + " not set in Activity " + getContext().getClass()
                                                                                                              .getSimpleName());
        }
    };

    @Override
    public void setDialogContent() {
        builder.setAdapter(getAdapter(getEntries()), onClickListener);
    }

    public abstract ListAdapter getAdapter(ArrayList<T> entries);

    public ArrayList<T> getEntries() {
        return getArguments().getParcelableArrayList(ARG_ARRAY_PARCABLE_ENTRIES);
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);
        try {
            listListener = (DialogAdapterListListener) getContext();
        } catch (ClassCastException e) {
            Log.w(e.getMessage());
        }
    }

    public abstract static class DialogAdapterListEntry implements Parcelable {

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

        public T setEntries(ArrayList<M> list) {
            entries = list;
            return (T) this;
        }

        @Override
        public void preBuild() {
            super.preBuild();
            builderArgs.putParcelableArrayList(ARG_ARRAY_PARCABLE_ENTRIES, entries);
        }
    }
}
