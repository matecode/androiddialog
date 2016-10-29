package de.mateware.dialog;

import android.content.DialogInterface;
import android.os.Bundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mateware.dialog.listener.DialogListListener;

/**
 * Created by Mate on 29.10.2016.
 */

public class DialogList extends Dialog {

    private static final Logger log = LoggerFactory.getLogger(DialogList.class);

    static final String ARG_ARRAY_STRING_ITEMS = "list_items";

    DialogListListener listListener;
    DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            String[] items = getItems();
            String value = items[which];
            log.debug("Button {} {}", which, value);
            if (listListener != null)
                listListener.onDialogListClick(getTag(), DialogList.this.getArguments(), which, value, getItems());
            else
                log.info(DialogListListener.class.getSimpleName() + " not set in Activity " + getContext().getClass()
                                                                                                          .getSimpleName());
        }
    };

    protected boolean hasItems() {
        return getArguments().containsKey(ARG_ARRAY_STRING_ITEMS);
    }

    protected String[] getItems() {
        return getArguments().getStringArray(ARG_ARRAY_STRING_ITEMS);
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);
        log.debug(this.getTag());
        try {
            listListener = (DialogListListener) getContext();
        } catch (ClassCastException e) {
            log.warn(e.getMessage());
        }
    }

    @Override
    public void setDialogContent() {
        if (hasItems()) builder.setItems(getItems(), onClickListener);
        super.setDialogContent();
    }

    public static abstract class AbstractBuilder<T extends AbstractBuilder, K extends DialogList> extends Dialog.AbstractBuilder<T, K> {
        public AbstractBuilder(Class<K> dialogBaseClass) {
            super(dialogBaseClass);
        }

        public T setList(String... items) {
            builderArgs.putStringArray(ARG_ARRAY_STRING_ITEMS, items);
            return (T) this;
        }
    }

    public static class Builder extends AbstractBuilder<Builder, DialogList> {
        public Builder() {
            super(DialogList.class);
        }
    }
}
