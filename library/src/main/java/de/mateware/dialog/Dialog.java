package de.mateware.dialog;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialog;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;


public class Dialog extends DialogFragment {

    public final static int BUTTON_POSITIVE = DialogInterface.BUTTON_POSITIVE;
    public final static int BUTTON_NEUTRAL = DialogInterface.BUTTON_NEUTRAL;
    public final static int BUTTON_NEGATIVE = DialogInterface.BUTTON_NEGATIVE;

    DialogButtonListener buttonListener;
    DialogDismissListener dismissListener;
    DialogCancelListener cancelListener;
    CountDownTimer timer;

    public static Logger log = LoggerFactory.getLogger(Dialog.class);

    public void show(@NonNull AppCompatActivity activity) {
        super.show(activity.getSupportFragmentManager(), getBuilder().tag);
    }

    public int show(@NonNull FragmentTransaction transaction) {
        return super.show(transaction, getBuilder().tag);
    }


    DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            log.trace("Button {}", which);
            if (buttonListener != null) buttonListener.onDialogClick(getTag(), getBuilder().bundle, which);
            else log.info(DialogButtonListener.class.getSimpleName() + " not set in Activity " + getActivity().getClass()
                                                                                                              .getSimpleName());
        }
    };

    AlertDialog.Builder alertDialogBuilder;


    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        log.trace(this.getTag());

        alertDialogBuilder = new AlertDialog.Builder(getActivity(), getBuilder().style);

        if (getIcon() != 0) alertDialogBuilder.setIcon(getIcon());

        if (getTitle() != null) alertDialogBuilder.setTitle(getTitle());

        setDialogContent();

        if (getPositiveButton() != null) alertDialogBuilder.setPositiveButton(getPositiveButton(), onClickListener);

        if (getNeutralButton() != null) alertDialogBuilder.setNeutralButton(getNeutralButton(), onClickListener);

        if (getNegativeButton() != null) alertDialogBuilder.setNegativeButton(getNegativeButton(), onClickListener);

        AppCompatDialog dialog = createDialogToReturn();

        return dialog;
    }


    @Override
    public void onResume() {
        super.onResume();
                if (getTimerSeconds() > 0) {
                    timer = new CountDownTimer(getTimerSeconds() * 1000, 1000) {

                        TextView timerText;

                        @Override
                        public void onTick(long millisUntilFinished) {
                            if (timerText == null) {
                                timerText = new TextView(getContext());
                                FrameLayout.LayoutParams lp = getTimerTextViewLayoutParams(timerText);
                                getDialog().addContentView(timerText, lp);
                            }
                            timerText.setText(getTimerText(millisUntilFinished));
                        }

                        @Override
                        public void onFinish() {
                            onTimerFinished();
                        }
                    };
                    timer.start();
                }
    }

    public String getTimerText(long millisUntilFinished) {
        return String.valueOf(millisUntilFinished / 1000);
    }

    public void onTimerFinished() {
        dismiss();
    }

    public FrameLayout.LayoutParams getTimerTextViewLayoutParams(TextView timerTextView) {
        int margin = getContext().getResources()
                                 .getDimensionPixelSize(R.dimen.custom_dialog_padding);
        int topMargin = getContext().getResources()
                                    .getDimensionPixelSize(R.dimen.custom_dialog_padding_top);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(margin, topMargin, margin, 0);
        params.gravity = Gravity.END | Gravity.TOP;

        timerTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_size_title_material));


        TypedArray a = timerTextView.getContext().getTheme().obtainStyledAttributes(new int[]{R.attr.colorPrimary});
        int attributeResourceId = a.getResourceId(0, 0);
        a.recycle();

        timerTextView.setTextColor(ContextCompat.getColor(getContext(), attributeResourceId));



        return params;
    }

    @Override
    public void onPause() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        super.onPause();
    }

    void setDialogContent() {
        if (getMessage() != null) alertDialogBuilder.setMessage(getMessage());
        //        if (hasMessage()) alertDialogBuilder.setMessage(getMessage());
    }

    protected AppCompatDialog createDialogToReturn() {
        return alertDialogBuilder.create();
    }

    protected int getTimerSeconds() {
        return getBuilder().timerSeconds;
    }


    protected String getTitle() {
        return getText(getBuilder().title, getBuilder().titleRes);
    }


    protected String getMessage() {
        return getText(getBuilder().message, getBuilder().messageRes);
    }


    protected
    @DrawableRes
    int getIcon() {
        return getBuilder().iconRes;
    }


    protected String getPositiveButton() {
        return getText(getBuilder().positiveButton, getBuilder().positiveButtonRes);
    }


    protected String getNegativeButton() {
        return getText(getBuilder().negativeButton, getBuilder().negativeButtonRes);
    }


    protected String getNeutralButton() {
        return getText(getBuilder().neutralButton, getBuilder().neutralButtonRes);
    }


    protected String getText(String string, @StringRes int resId) {
        if (string == null && resId != 0) string = getString(resId);
        return string;
    }


    @Override
    public void onAttach(Activity activity) {
        log.trace(this.getTag());
        super.onAttach(activity);
        try {
            buttonListener = (DialogButtonListener) activity;
        } catch (ClassCastException e) {
            log.warn(e.getMessage());
        }
        try {
            dismissListener = (DialogDismissListener) activity;
        } catch (ClassCastException e) {
            log.warn(e.getMessage());
        }
        try {
            cancelListener = (DialogCancelListener) activity;
        } catch (ClassCastException e) {
            log.warn(e.getMessage());
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (getTag() != null) {
            log.trace(getTag());
            if (dismissListener != null) dismissListener.onDialogDismiss(getTag(), getBuilder().bundle);
            else log.info(DialogDismissListener.class.getSimpleName() + " not set in Activity " + getActivity().getClass()
                                                                                                               .getSimpleName());
        }
        super.onDismiss(dialog);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        if (getTag() != null) {
            log.trace(getTag());
            if (cancelListener != null) cancelListener.onDialogCancel(getTag(), getBuilder().bundle);
            else log.info(DialogCancelListener.class.getSimpleName() + " not set in Activity " + getActivity().getClass()
                                                                                                              .getSimpleName());
        }
        super.onCancel(dialog);
    }

    public interface DialogButtonListener {
        void onDialogClick(String tag, Bundle arguments, int which);
    }

    public interface DialogDismissListener {
        void onDialogDismiss(String tag, Bundle arguments);
    }

    public interface DialogCancelListener {
        void onDialogCancel(String tag, Bundle arguments);
    }

    public static void dismissDialog(FragmentManager fm, String dialogTag) {
        log.trace(dialogTag);
        DialogFragment dialog = (DialogFragment) fm.findFragmentByTag(dialogTag);
        if (dialog != null) dialog.dismiss();
    }

    private Builder getBuilder() {
        return (Builder) getArguments().getSerializable(Builder.ARG_BUILDER);
    }

    public static class Builder implements Serializable {

        private static final String ARG_BUILDER = "dialog_builder";

        private String tag;
        private int timerSeconds;
        private
        @StyleRes
        int style;
        private String title;
        private
        @StringRes
        int titleRes;
        private String message;
        private
        @StringRes
        int messageRes;
        private String positiveButton;
        private
        @StringRes
        int positiveButtonRes;
        private String neutralButton;
        private
        @StringRes
        int neutralButtonRes;
        private String negativeButton;
        private
        @StringRes
        int negativeButtonRes;
        private
        @DrawableRes
        int iconRes;
        private boolean cancelable = true;
        private Bundle bundle;

        public Builder(String tag) {
            this.tag = tag;
        }

        public Builder setTimerSeconds(int timerSeconds) {
            this.timerSeconds = timerSeconds;
            return this;
        }

        public Builder setStyle(int style) {
            this.style = style;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setTitle(@StringRes int titleRes) {
            this.titleRes = titleRes;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessage(@StringRes int messageRes) {
            this.messageRes = messageRes;
            return this;
        }

        public Builder setPositiveButton(String positiveButton) {
            this.positiveButton = positiveButton;
            return this;
        }

        public Builder setPositiveButton(@StringRes int positiveButtonRes) {
            this.positiveButtonRes = positiveButtonRes;
            return this;
        }

        public Builder setPositiveButton() {
            this.positiveButtonRes = android.R.string.ok;
            return this;
        }

        public Builder setNeutralButton(String neutralButton) {
            this.neutralButton = neutralButton;
            return this;
        }

        public Builder setNeutralButton(@StringRes int neutralButtonRes) {
            this.neutralButtonRes = neutralButtonRes;
            return this;
        }

        public Builder setNeutralButton() {
            this.neutralButtonRes = android.R.string.untitled;
            return this;
        }

        public Builder setNegativeButton(String negativeButton) {
            this.negativeButton = negativeButton;
            return this;
        }

        public Builder setNegativeButton(@StringRes int negativeButtonRes) {
            this.negativeButtonRes = negativeButtonRes;
            return this;
        }

        public Builder setNegativeButton() {
            this.negativeButtonRes = android.R.string.cancel;
            return this;
        }

        public Builder setIcon(@DrawableRes int iconRes) {
            this.iconRes = iconRes;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setBundle(Bundle bundle) {
            this.bundle = bundle;
            return this;
        }

        public Dialog build() {
            Dialog dialog = new Dialog();
            dialog.setCancelable(cancelable);
            Bundle arguments = new Bundle();
            arguments.putSerializable(ARG_BUILDER, this);
            dialog.setArguments(arguments);
            return dialog;
        }
    }
}
