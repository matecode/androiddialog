package de.mateware.dialog;


import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

/**
 * Created by mate on 28.10.2016.
 */

public interface BaseAlertDialogBuilderInterface<K,T> {


    public Context getContext();

    public T setTitle(int titleId);

    public T setTitle(CharSequence title);

    public T setCustomTitle(View customTitleView);

    public T setMessage(int messageId);

    public T setMessage(CharSequence message);

    public T setIcon(int iconId);

    public T setIcon(Drawable icon);

    public T setIconAttribute(int attrId);

    public T setPositiveButton(int textId, DialogInterface.OnClickListener listener);

    public T setPositiveButton(CharSequence text, DialogInterface.OnClickListener listener);

    public T setNegativeButton(int textId, DialogInterface.OnClickListener listener);

    public T setNegativeButton(CharSequence text, DialogInterface.OnClickListener listener);

    public T setNeutralButton(int textId, DialogInterface.OnClickListener listener);

    public T setNeutralButton(CharSequence text, DialogInterface.OnClickListener listener);

    public T setCancelable(boolean cancelable) ;

    public T setOnCancelListener(DialogInterface.OnCancelListener onCancelListener);

    public T setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) ;

    public T setOnKeyListener(DialogInterface.OnKeyListener onKeyListener);

    public T setItems(int itemsId, DialogInterface.OnClickListener listener) ;

    public T setItems(CharSequence[] items, DialogInterface.OnClickListener listener) ;

    public T setAdapter(ListAdapter adapter, DialogInterface.OnClickListener listener) ;

    public T setCursor(Cursor cursor, DialogInterface.OnClickListener listener, String labelColumn) ;

    public T setMultiChoiceItems(int itemsId, boolean[] checkedItems, DialogInterface.OnMultiChoiceClickListener listener) ;

    public T setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems, DialogInterface.OnMultiChoiceClickListener listener) ;

    public T setMultiChoiceItems(Cursor cursor, String isCheckedColumn, String labelColumn, DialogInterface.OnMultiChoiceClickListener listener);

    public T setSingleChoiceItems(int itemsId, int checkedItem, DialogInterface.OnClickListener listener);

    public T setSingleChoiceItems(Cursor cursor, int checkedItem, String labelColumn, DialogInterface.OnClickListener listener) ;

    public T setSingleChoiceItems(CharSequence[] items, int checkedItem, DialogInterface.OnClickListener listener) ;

    public T setSingleChoiceItems(ListAdapter adapter, int checkedItem, DialogInterface.OnClickListener listener);

    public T setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener);

    public T setView(int layoutResId) ;

    public T setView(View view) ;

    /**
     * @deprecated
     */
    @Deprecated
    public T setInverseBackgroundForced(boolean useInverseBackground) ;

    public K create();


}
