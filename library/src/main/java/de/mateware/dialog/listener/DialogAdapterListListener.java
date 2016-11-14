package de.mateware.dialog.listener;

import android.os.Bundle;

import de.mateware.dialog.DialogAdapterList;

/**
 * Created by Mate on 27.10.2016.
 */
public interface DialogAdapterListListener<T extends DialogAdapterList.DialogAdapterListEntry> {
    void onDialogAdapterListClick(String tag, T entry, Bundle arguments);
}
