package de.mateware.dialog;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ListAdapter;

import java.util.ArrayList;

/**
 * Created by Mate on 30.10.2016.
 */

public abstract class DialogRecyclerView<T extends DialogAdapterList.DialogAdapterListEntry> extends DialogAdapterList<T> {

    @Override
    public ListAdapter getAdapter(ArrayList<T> entries) {
        //nothing
        return null;
    }

    public abstract RecyclerView getRecyclerView(ArrayList<T> entries);

    @Override
    public void setDialogContent() {
        //do Nothing
    }

    @Override
    public View addView() {
        RecyclerView recyclerView = getRecyclerView(getEntries());
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        recyclerView.setLayoutParams(layoutParams);
        return recyclerView;
    }


}
