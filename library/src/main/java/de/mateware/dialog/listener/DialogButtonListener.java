package de.mateware.dialog.listener;

import android.os.Bundle;

/**
 * Created by Mate on 27.10.2016.
 */

public interface DialogButtonListener {
    void onDialogClick(String tag, Bundle dialogArguments, int which);
}
