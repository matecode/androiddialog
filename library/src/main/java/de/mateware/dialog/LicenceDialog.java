package de.mateware.dialog;

import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mateware.dialog.licences.AbstractLicence;
import de.mateware.dialog.listener.DialogAdapterListListener;

/**
 * Created by Mate on 30.10.2016.
 */

public class LicenceDialog extends DialogAdapterList<AbstractLicence> {

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


    private class LicencesAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return getEntries().size();
        }

        @Override
        public Object getItem(int position) {
            if (!getEntries().isEmpty() && getEntries().size() > position)
                return getEntries().get(position);
            return null;
        }

        @Override
        public long getItemId(int pos) {
            return pos;
        }

        @Override
        public View getView(int pos, View convertView, ViewGroup parent) {
            LicenceViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                                            .inflate(R.layout.licence_layout, parent, false);
                viewHolder = new LicenceViewHolder();
                viewHolder.title = (TextView) convertView.findViewById(R.id.titleText);
                viewHolder.subTitle = (TextView) convertView.findViewById(R.id.subtitleText);
                viewHolder.licenceText = (TextView) convertView.findViewById(R.id.licenceText);
                convertView.setTag(viewHolder);
            } else viewHolder = (LicenceViewHolder) convertView.getTag();

            AbstractLicence licence = getEntries().get(pos);
            viewHolder.title.setText(licence.getTitle());
            viewHolder.subTitle.setText(licence.getSubTitle());
            viewHolder.licenceText.setText(licence.getLicenceText());
            return convertView;
        }
    }

    static class LicenceViewHolder {
        TextView title;
        TextView subTitle;
        TextView licenceText;
    }

    public static class AbstractBuilder<M extends DialogAdapterListEntry, T extends AbstractBuilder, K extends DialogAdapterList> extends DialogAdapterList.AbstractBuilder<M, T, K> {
        public AbstractBuilder(Class<K> dialogBaseClass) {
            super(dialogBaseClass);
        }
    }

    public static class Builder extends AbstractBuilder<AbstractLicence,Builder,LicenceDialog> {
        public Builder() {
            super(LicenceDialog.class);
        }
    }
}
