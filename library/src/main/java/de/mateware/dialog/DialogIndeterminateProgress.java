package de.mateware.dialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Mate on 28.10.2016.
 */

public class DialogIndeterminateProgress extends DialogCustomView {

    @Override
    public View getView(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.indeterminate_progress,parent,false);
        TextView message = (TextView) view.findViewById(R.id.message);
        if (hasMessage()) {
            message.setVisibility(View.VISIBLE);
            message.setText(getMessage());
        }
        else
        message.setVisibility(View.GONE);
        return view;
    }

    public static class AbstractBuilder<T extends DialogCustomView.AbstractBuilder, K extends DialogCustomView> extends DialogCustomView.AbstractBuilder<T,K> {

        public AbstractBuilder(Class<K> dialogBaseClass) {
            super(dialogBaseClass);
        }
    }

    public static class Builder extends AbstractBuilder<Builder,DialogIndeterminateProgress> {
        public Builder() {
            super(DialogIndeterminateProgress.class);
        }
    }

}
