package de.mateware.dialog;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Mate on 28.10.2016.
 */

public class DialogIndeterminateProgress extends Dialog {

    @Override
    public View addView() {
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setLayoutParams(params);
        int customDialogMargin = getResources().getDimensionPixelSize(R.dimen.custom_dialog_padding);
        layout.setPadding(customDialogMargin, customDialogMargin, customDialogMargin, (!TextUtils.isEmpty(getPositiveButton())||!TextUtils.isEmpty(getNeutralButton())||!TextUtils.isEmpty(getNegativeButton())) ? 0 : customDialogMargin);
        layout.setGravity(Gravity.CENTER_VERTICAL);

        ProgressBar progressCircle = new ProgressBar(getContext());
        progressCircle.setIndeterminate(true);
        LinearLayout.LayoutParams progressCircleLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(progressCircle, progressCircleLayoutParams);

        if (hasMessage()) {
            TextView messageView = new TextView(getContext());
            messageView.setText(getMessage());
            LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textLayoutParams.setMargins(customDialogMargin, 0, 0, 0);
            layout.addView(messageView, textLayoutParams);
        }
        return layout;
    }

    public static class AbstractBuilder<T extends Dialog.AbstractBuilder, K extends Dialog> extends Dialog.AbstractBuilder<T,K> {

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
