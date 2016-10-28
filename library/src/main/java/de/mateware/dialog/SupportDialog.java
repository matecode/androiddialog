package de.mateware.dialog;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDialog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;


public class SupportDialog extends DialogFragment implements BaseDialogInterface {

    BaseDialog<SupportAlertDialogBuilder, AppCompatDialog, SupportDialog> baseDialog;

    public final static int BUTTON_POSITIVE = DialogInterface.BUTTON_POSITIVE;
    public final static int BUTTON_NEUTRAL = DialogInterface.BUTTON_NEUTRAL;
    public final static int BUTTON_NEGATIVE = DialogInterface.BUTTON_NEGATIVE;


    public static Logger log = LoggerFactory.getLogger(SupportDialog.class);

    public SupportDialog() {
        baseDialog = new BaseDialog<>(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        try {
            return baseDialog.onCreateDialog(SupportAlertDialogBuilder.class);
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


    public static class Builder extends BaseDialog.AbstractBaseBuilder<Builder, SupportDialog> {
        public Builder() {
            super(SupportDialog.class);
        }
    }
}
