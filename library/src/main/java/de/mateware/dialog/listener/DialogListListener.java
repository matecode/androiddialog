package de.mateware.dialog.listener;

import android.os.Bundle;

/**
 * Created by Mate on 27.10.2016.
 */
public interface DialogListListener {
    void onDialogListClick(String tag, Bundle arguments, int which, String value, String[] items);
}
