package de.mateware.dialog;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

/**
 * Created by mate on 21.10.2015.
 */
public class SupportDialogIndeterminateProgress extends SupportDialogCustomView {

    @Override
    public View getView(LayoutInflater inflater, ViewGroup parent) {

        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setLayoutParams(params);
        int customDialogMargin = getResources().getDimensionPixelSize(R.dimen.custom_dialog_padding);
//        layout.setPadding(customDialogMargin, customDialogMargin, customDialogMargin, (!TextUtils.isEmpty(getPositiveButton())||!TextUtils.isEmpty(getNeutralButton())||!TextUtils.isEmpty(getNegativeButton())) ? 0 : customDialogMargin);
        layout.setGravity(Gravity.CENTER_VERTICAL);

        ProgressBar progressCircle = new ProgressBar(getContext());
        progressCircle.setIndeterminate(true);
        LinearLayout.LayoutParams progressCircleLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(progressCircle, progressCircleLayoutParams);

//        if (hasMessage()) {
//            TextView messageView = new TextView(getContext());
//            messageView.setText(getMessage());
//            LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            textLayoutParams.setMargins(customDialogMargin, 0, 0, 0);
//            layout.addView(messageView, textLayoutParams);
//        }
        return layout;
    }

    public static class Builder extends BaseDialog.AbstractBaseBuilder<Builder, SupportDialogIndeterminateProgress> {
        public Builder() {
            super(SupportDialogIndeterminateProgress.class);
        }
    }
}
