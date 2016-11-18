package de.mateware.dialog;

import android.content.DialogInterface;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import de.mateware.dialog.licences.StandardLicence;
import de.mateware.dialog.listener.DialogAdapterListListener;

/**
 * Created by Mate on 30.10.2016.
 */

public class LicenceDialog extends DialogRecyclerView<StandardLicence> {

    private static final Logger log = LoggerFactory.getLogger(LicenceDialog.class);


    DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            DialogAdapterListEntry entry = getEntries().get(which);
            log.debug("Button {} {}", which, entry);
            if (listListener != null)
                listListener.onDialogAdapterListClick(getTag(), entry, getArguments());
            else
                log.info(DialogAdapterListListener.class.getSimpleName() + " not set in Activity " + getContext().getClass()
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
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
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
            holder.subTitle.setText(licence.getSubTitle());
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
}
