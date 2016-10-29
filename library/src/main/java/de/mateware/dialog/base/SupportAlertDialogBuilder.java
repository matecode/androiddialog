package de.mateware.dialog.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;

import de.mateware.dialog.base.BaseAlertDialogBuilderInterface;

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
