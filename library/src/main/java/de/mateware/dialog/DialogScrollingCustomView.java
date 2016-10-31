package de.mateware.dialog;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;

/**
 * Created by Mate on 29.10.2016.
 */

public abstract class DialogScrollingCustomView extends DialogCustomView {

    @Override
    public View addView() {
        ScrollView scroll = new ScrollView(getContext());
        scroll.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
        int padding = getContext().getResources()
                                  .getDimensionPixelSize(R.dimen.custom_dialog_padding);
        int topPadding = getContext().getResources()
                                     .getDimensionPixelSize(R.dimen.custom_dialog_padding_top);
        scroll.setPadding(padding, topPadding, padding, 0);
        scroll.setClipToPadding(false);
        scroll.addView(getView(LayoutInflater.from(getContext()), scroll));
        return scroll;
    }

    public static abstract class AbstractBuilder<T extends AbstractBuilder,K extends DialogScrollingCustomView> extends DialogCustomView.AbstractBuilder<T,K>{
        public AbstractBuilder(Class<K> dialogBaseClass) {
            super(dialogBaseClass);
        }
    }
}
