package de.mateware.dialog.base;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import de.mateware.dialog.base.BaseAlertDialogBuilderInterface;

/**
 * Created by mate on 28.10.2016.
 */

public class AlertDialogBuilder extends AlertDialog.Builder implements BaseAlertDialogBuilderInterface<AlertDialog,AlertDialog.Builder> {
    public AlertDialogBuilder(Context context) {
        super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public AlertDialogBuilder(Context context, int themeResId) {
        super(context, themeResId);
    }
}
