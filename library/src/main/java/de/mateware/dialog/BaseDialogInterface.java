package de.mateware.dialog;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.StyleRes;

/**
 * Created by mate on 28.10.2016.
 */

public interface BaseDialogInterface {
    void setArguments(Bundle args);
    void setCancelable(boolean cancelable);
    Bundle getArguments();
    String getTag();
    Context getContext();
    void dismiss();
    @StyleRes
    int getTheme();
    Resources getResources();
    android.app.Dialog getDialog();
}
