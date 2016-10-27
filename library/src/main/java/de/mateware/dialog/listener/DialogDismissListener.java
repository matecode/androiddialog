package de.mateware.dialog.listener;

import android.os.Bundle;

/**
 * Created by Mate on 27.10.2016.
 */

public interface DialogDismissListener {
    void onDialogDismiss(String tag, Bundle dialogArguments);
}
