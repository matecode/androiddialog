package de.mateware.dialog;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListAdapter;

/**
 * Created by Mate on 30.10.2016.
 */

public abstract class DialogRecyclerView<T extends DialogAdapterList.DialogAdapterListEntry> extends DialogAdapterList<T> {
    @Override
    public ListAdapter getAdapter() {
        //nothing
        return null;
    }

    public abstract RecyclerView getRecyclerView();

    @Override
    public void setDialogContent() {
        //do Nothing
    }

    @Override
    public View addView() {
        return getRecyclerView();
    }


}
