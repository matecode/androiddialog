package de.mateware.dialog;

import android.content.DialogInterface;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import de.mateware.dialog.licences.StandardLicence;
import de.mateware.dialog.listener.DialogAdapterListListener;
import de.mateware.dialog.log.Log;

/**
 * Created by Mate on 30.10.2016.
 */

public class LicenceDialog extends DialogRecyclerView<StandardLicence> {


    DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            DialogAdapterListEntry entry = getEntries().get(which);
            Log.d("Button" + which + " " + entry);
            if (listListener != null)
                listListener.onDialogAdapterListClick(getTag(), entry, getArguments());
            else
                Log.w(DialogAdapterListListener.class.getSimpleName() + " not set in Activity " + getContext().getClass()
                                                                                                              .getSimpleName());
        }
    };


    @Override
    public RecyclerView getRecyclerView(ArrayList<StandardLicence> entries) {
        RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new LicencesAdapter(entries));

        DisplayMetrics displayMetrics = getContext().getResources()
                                                    .getDisplayMetrics();
        int pixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, displayMetrics);
        recyclerView.addItemDecoration(new SpacesItemDecoration(pixels));
        return recyclerView;
    }


    public static class LicencesAdapter extends RecyclerView.Adapter<LicenceViewHolder> {

        public LicencesAdapter(ArrayList<StandardLicence> entries) {
            this.entries = entries;
        }

        ArrayList<StandardLicence> entries;

        @Override
        public LicenceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LicenceViewHolder viewHolder = new LicenceViewHolder(LayoutInflater.from(parent.getContext())
                                                                               .inflate(R.layout.licence_layout, parent, false));
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(LicenceViewHolder holder, int position) {
            StandardLicence licence = entries.get(position);
            holder.title.setText(licence.getTitle());
            if (TextUtils.isEmpty(licence.getSubTitle())) {
                holder.subTitle.setVisibility(View.GONE);
            } else {
                holder.subTitle.setVisibility(View.VISIBLE);
                holder.subTitle.setText(licence.getSubTitle());
            }
            holder.licenceText.setText(licence.getLicenceText());
        }

        @Override
        public int getItemCount() {
            return entries.size();
        }
    }


    static class LicenceViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView subTitle;
        TextView licenceText;

        public LicenceViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.titleText);
            subTitle = (TextView) itemView.findViewById(R.id.subtitleText);
            licenceText = (TextView) itemView.findViewById(R.id.licenceText);
        }
    }

    public static class AbstractBuilder<M extends DialogAdapterListEntry, T extends AbstractBuilder, K extends DialogAdapterList> extends DialogAdapterList.AbstractBuilder<M, T, K> {
        public AbstractBuilder(Class<K> dialogBaseClass) {
            super(dialogBaseClass);
        }

        @Override
        public void preBuild() {
            super.preBuild();
        }
    }

    public static class Builder extends AbstractBuilder<StandardLicence, Builder, LicenceDialog> {
        public Builder() {
            super(LicenceDialog.class);
        }
    }

    static class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int pixels) {
            this.space = pixels;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            if (parent.getChildPosition(view) != 0)
                outRect.top = space;
        }
    }
}
