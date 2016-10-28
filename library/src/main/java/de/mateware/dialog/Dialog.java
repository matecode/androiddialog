package de.mateware.dialog;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by mate on 28.10.2016.
 */

@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class Dialog extends DialogFragment implements BaseDialogInterface {

    private static final Logger log = LoggerFactory.getLogger(Dialog.class);

    BaseDialog<AlertDialogBuilder, android.app.Dialog, Dialog> baseDialog;

    public Dialog() {
        this.baseDialog = new BaseDialog<>(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseDialog.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        baseDialog.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(baseDialog.onSaveInstanceState(outState));
    }

    @Override
    public void onPause() {
        baseDialog.onPause();
        super.onPause();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        baseDialog.onAttach(context);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        baseDialog.onDismiss(dialog);
        super.onDismiss(dialog);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        baseDialog.onCancel(dialog);
        super.onCancel(dialog);
    }

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        try {
            return baseDialog.onCreateDialog(AlertDialogBuilder.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("No Dialog created");
    }

    public int showAllowStateLoss(@NonNull FragmentManager manager, String tag) {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(this, tag);
        return fragmentTransaction.commitAllowingStateLoss();
    }

    public int showAllowStateLoss(@NonNull FragmentTransaction transaction, String tag) {
        return transaction.commitAllowingStateLoss();
    }

    public static void dismissDialog(FragmentManager fm, String dialogTag) {
        log.trace(dialogTag);
        DialogFragment dialog = (DialogFragment) fm.findFragmentByTag(dialogTag);
        if (dialog != null) dialog.dismiss();
    }

    public static class Builder extends BaseDialog.AbstractBaseBuilder<Builder, Dialog> {
        public Builder() {
            super(Dialog.class);
        }
    }

}
