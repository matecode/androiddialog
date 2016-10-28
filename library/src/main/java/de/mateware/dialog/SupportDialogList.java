package de.mateware.dialog;

import android.app.Activity;
import android.content.DialogInterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mateware.dialog.listener.DialogListListener;

/**
 * Created by Mate on 18.03.2015.
 */

public class SupportDialogList extends SupportDialog {

    private static final Logger log = LoggerFactory.getLogger(SupportDialogList.class);

    static final String ARG_ARRAY_STRING_ITEMS = "list_items";

    DialogListListener listListener;

    DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            String[] items = getItems();
            String value = items[which];

            log.debug("Button {} {}", which,value);
            if (listListener != null) listListener.onDialogListClick(getTag(), SupportDialogList.this.getArguments(), which,value,getItems());
            else log.info(DialogListListener.class.getSimpleName() + " not set in Activity " + getActivity().getClass()
                                                                                                             .getSimpleName());
        }
    };


    static abstract class AbstractBuilder<T extends AbstractBuilder,K extends SupportDialogList> extends BaseDialog.AbstractBaseBuilder<T, K> {

        AbstractBuilder(Class<K> clazz) {
            super(clazz);
        }

        public T setList(String... items)
        {
            builderArgs.putStringArray(ARG_ARRAY_STRING_ITEMS, items);
            return (T) this;
        }
    }


    public static class Builder extends AbstractBuilder<Builder,SupportDialogList> {

        public Builder() {
            super(SupportDialogList.class);
        }
    }




//    @Override
//    void setDialogContent() {
//        if (hasItems())
//            builder.setItems(getItems(), onClickListener);
//    }

    @Override
    public void onAttach(Activity activity) {
        log.debug(this.getTag());
        super.onAttach(activity);
        try {
            listListener = (DialogListListener) activity;
        } catch (ClassCastException e) {
            log.warn(e.getMessage());
        }
    }

    protected boolean hasItems()
    {
        return getArguments().containsKey(ARG_ARRAY_STRING_ITEMS);
    }

    protected String[] getItems()
    {
        return getArguments().getStringArray(ARG_ARRAY_STRING_ITEMS);
    }

}
