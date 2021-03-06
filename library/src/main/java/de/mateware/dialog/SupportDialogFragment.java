package de.mateware.dialog;


import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import android.util.TypedValue;
import android.view.View;

import java.lang.reflect.InvocationTargetException;

import de.mateware.dialog.base.BaseDialogInterface;
import de.mateware.dialog.base.SupportAlertDialogBuilder;


public class SupportDialogFragment<T extends Dialog> extends DialogFragment implements BaseDialogInterface {

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


        TypedValue value = new TypedValue();
        getContext().getTheme().resolveAttribute(R.attr.dialogPreferredPadding,value,true);
        baseDialog.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        baseDialog.onResume();
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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        baseDialog.onViewCreated(view, savedInstanceState);
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
    public void onDestroy() {
        baseDialog.onDestroy();
        super.onDestroy();
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
