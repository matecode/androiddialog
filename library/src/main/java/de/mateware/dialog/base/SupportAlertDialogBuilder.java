package de.mateware.dialog.base;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AlertDialog;

/**
 * Created by mate on 28.10.2016.
 */

public class SupportAlertDialogBuilder extends AlertDialog.Builder implements BaseAlertDialogBuilderInterface<AlertDialog,AlertDialog.Builder> {
    public SupportAlertDialogBuilder(@NonNull Context context) {
        super(context);
    }

    public SupportAlertDialogBuilder(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }
}
