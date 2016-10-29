package de.mateware.dialog;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

import de.mateware.dialog.base.BaseDialogInterface;
import de.mateware.dialog.base.SupportAlertDialogBuilder;


public class SupportDialogFragment<T extends Dialog> extends DialogFragment implements BaseDialogInterface {

    public static Logger log = LoggerFactory.getLogger(SupportDialogFragment.class);
    Dialog<SupportAlertDialogBuilder, AlertDialog, SupportDialogFragment<T>> baseDialog;
    private static final String ARG_BASECLASS = "baseClass";
    Class<T> dialogClass;

    void initBase(Class<T> clazz) {
        dialogClass = clazz;
        try {
            baseDialog = clazz.newInstance();
            baseDialog.setDialogFragment(this);
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Class<T> clazz = (Class<T>) savedInstanceState.getSerializable(ARG_BASECLASS);
            if (clazz != null) initBase(clazz);
        }
        if (baseDialog == null)
            throw new IllegalStateException("initBase must be called, cannot be initialized from outside library");
        super.onCreate(savedInstanceState);
        baseDialog.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        baseDialog.onResume();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        baseDialog.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(ARG_BASECLASS, dialogClass);
        super.onSaveInstanceState(baseDialog.onSaveInstanceState(outState));
    }

    @Override
    public void onPause() {
        baseDialog.onPause();
        super.onPause();
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

    @NonNull
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
}
