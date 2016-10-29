package de.mateware.dialog.listener;

import android.os.Bundle;

import de.mateware.dialog.DialogAdapterList;

/**
 * Created by Mate on 27.10.2016.
 */
public interface DialogAdapterListListener {
    void onDialogAdapterListClick(String tag, DialogAdapterList.DialogAdapterListEntry entry, Bundle arguments);
}
