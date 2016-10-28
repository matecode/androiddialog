package de.mateware.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

import de.mateware.dialog.listener.DialogButtonListener;
import de.mateware.dialog.listener.DialogCancelListener;
import de.mateware.dialog.listener.DialogDismissListener;

/**
 * Created by mate on 28.10.2016.
 */

public class Dialog<T extends BaseAlertDialogBuilderInterface, K, M extends BaseDialogInterface> {

    private static final Logger log = LoggerFactory.getLogger(Dialog.class);

    static final String ARG_INT_STYLE = "style";
    static final String ARG_INT_TITLE = "title_resid";
    static final String ARG_STRING_TITLE = "title_text";

    static final String ARG_INT_MESSAGE = "message_resid";
    static final String ARG_STRING_MESSAGE = "message";

    static final String ARG_INT_ICONID = "icon_id";

    static final String ARG_LONG_TIMER = "timer";


    static final String ARG_INT_BUTTONTEXTPOSITIVE = "positive_button_resid";
    static final String ARG_INT_BUTTONTEXTNEGATIVE = "negative_button_resid";
    static final String ARG_INT_BUTTONTEXTNEUTRAL = "neutral_button_resid";
    static final String ARG_STRING_BUTTONTEXTPOSITIVE = "positive_button_text";
    static final String ARG_STRING_BUTTONTEXTNEGATIVE = "negative_button_text";
    static final String ARG_STRING_BUTTONTEXTNEUTRAL = "neutral_button_text";

    private M dialogFragment;
    private T builder;

    CountDownTimer timer;
    long timermillis;

    DialogButtonListener buttonListener;
    DialogDismissListener dismissListener;
    DialogCancelListener cancelListener;

    DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            log.trace("Button", which);
            Bundle dialogArguments = getArguments();
            dialogArguments.putAll(addArgumentsToDialogAfterButtonClick(dialogArguments, which));
            if (buttonListener != null)
                buttonListener.onDialogClick(dialogFragment.getTag(), dialogArguments, which);
            else
                log.info(DialogButtonListener.class.getSimpleName() + " not set in Activity " + getContext().getClass()
                                                                                                             .getSimpleName());
        }
    };

    void setDialogFragment(M dialogFragment) {
        this.dialogFragment = dialogFragment;
    }

    protected Bundle getArguments() {
        return dialogFragment.getArguments();
    }

    protected Context getContext() {
        return dialogFragment.getContext();
    }

    protected Resources getResources() {
        return dialogFragment.getResources();
    }

    protected int getStyle() {
        return getArguments().getInt(ARG_INT_STYLE, 0);
    }

    protected boolean hasTimer() {
        return getArguments().containsKey(ARG_LONG_TIMER) && getArguments().getLong(ARG_LONG_TIMER, 0) > 0;
    }

    protected boolean hasTitle() {
        return (getArguments().containsKey(ARG_STRING_TITLE) || getArguments().containsKey(ARG_INT_TITLE));
    }

    protected String getTitle() {
        return getText(ARG_STRING_TITLE, ARG_INT_TITLE);
    }

    protected boolean hasMessage() {
        return (getArguments().containsKey(ARG_STRING_MESSAGE) || getArguments().containsKey(ARG_INT_MESSAGE));
    }

    protected String getMessage() {
        return getText(ARG_STRING_MESSAGE, ARG_INT_MESSAGE);
    }

    protected boolean hasIcon() {
        return (getArguments().containsKey(ARG_INT_ICONID));
    }

    protected int getIcon() {
        return getArguments().getInt(ARG_INT_ICONID);
    }

    protected boolean hasPositiveButton() {
        return (getArguments().containsKey(ARG_STRING_BUTTONTEXTPOSITIVE) || getArguments().containsKey(ARG_INT_BUTTONTEXTPOSITIVE));
    }

    protected String getPositiveButton() {
        return getText(ARG_STRING_BUTTONTEXTPOSITIVE, ARG_INT_BUTTONTEXTPOSITIVE);
    }

    protected boolean hasNegativeButton() {
        return (getArguments().containsKey(ARG_STRING_BUTTONTEXTNEGATIVE) || getArguments().containsKey(ARG_INT_BUTTONTEXTNEGATIVE));
    }

    protected String getNegativeButton() {
        return getText(ARG_STRING_BUTTONTEXTNEGATIVE, ARG_INT_BUTTONTEXTNEGATIVE);
    }

    protected boolean hasNeutralButton() {
        return (getArguments().containsKey(ARG_STRING_BUTTONTEXTNEUTRAL) || getArguments().containsKey(ARG_INT_BUTTONTEXTNEUTRAL));
    }

    protected String getNeutralButton() {
        return getText(ARG_STRING_BUTTONTEXTNEUTRAL, ARG_INT_BUTTONTEXTNEUTRAL);
    }

    protected String getText(String arg_string, String arg_int) {
        String result = null;
        if (getArguments().containsKey(arg_string)) result = getArguments().getString(arg_string);
        else if (getArguments().containsKey(arg_int))
            result = dialogFragment.getContext()
                                   .getString(getArguments().getInt(arg_int));
        return result;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        timermillis = getArguments().getLong(ARG_LONG_TIMER, 0);
        if (savedInstanceState != null) timermillis = savedInstanceState.getLong(ARG_LONG_TIMER, 0);
    }

    public void onActivityCreated(Bundle saveInstanceState) {
        log.debug("saveInstanceState: {}",saveInstanceState);
            try {
                buttonListener = (DialogButtonListener) getContext();
            } catch (ClassCastException e) {
                log.warn(e.getMessage());
            }
            try {
                dismissListener = (DialogDismissListener) getContext();
            } catch (ClassCastException e) {
                log.warn(e.getMessage());
            }
            try {
                cancelListener = (DialogCancelListener) getContext();
            } catch (ClassCastException e) {
                log.warn(e.getMessage());
            }

    }

    public void onDismiss(DialogInterface dialog) {
        if (dialogFragment.getTag() != null) {
            log.trace(dialogFragment.getTag());
            if (dismissListener != null)
                dismissListener.onDialogDismiss(dialogFragment.getTag(), getArguments());
            else
                log.info(DialogDismissListener.class.getSimpleName() + " not set in Activity " + dialogFragment.getContext()
                                                                                                               .getClass()
                                                                                                               .getSimpleName());
        }
    }

    public void onCancel(DialogInterface dialog) {
        if (dialogFragment.getTag() != null) {
            log.trace(dialogFragment.getTag());
            if (cancelListener != null)
                cancelListener.onDialogCancel(dialogFragment.getTag(), getArguments());
            else
                log.info(DialogCancelListener.class.getSimpleName() + " not set in Activity " + dialogFragment.getContext()
                                                                                                              .getClass()
                                                                                                              .getSimpleName());
        }
    }

    public void onResume() {
        if (getTimerMillis() > 0) {
            timer = new CountDownTimer(getTimerMillis(), 100) {

                TextView timerText;

                @Override
                public void onTick(long millisUntilFinished) {
                    log.debug("millisUntilFinished: {}", millisUntilFinished);
                    if (timerText == null) {
                        timerText = new TextView(dialogFragment.getContext());
                        FrameLayout.LayoutParams lp = getTimerTextViewLayoutParams(timerText);
                        dialogFragment.getDialog()
                                      .addContentView(timerText, lp);
                    }
                    timermillis = millisUntilFinished;
                    timerText.setText(getTimerText(millisUntilFinished));
                }

                @Override
                public void onFinish() {
                    log.debug("Finished timer with millis {}", timermillis);
                    timermillis = 0;
                    timerText.setText(getTimerText(0));
                    onTimerFinished();
                }
            };
            timer.start();
        }
    }

    public void onPause() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public Bundle onSaveInstanceState(Bundle outState) {
        outState.putLong(ARG_LONG_TIMER, timermillis);
        return outState;
    }

    public String getTimerText(long millisUntilFinished) {
        return String.valueOf(Math.round(((double) millisUntilFinished) / 1000));
    }

    public long getTimerMillis() {
        return timermillis;
    }

    public void onTimerFinished() {
        dialogFragment.dismiss();
    }

    public FrameLayout.LayoutParams getTimerTextViewLayoutParams(TextView timerTextView) {
        int margin = dialogFragment.getContext()
                                   .getResources()
                                   .getDimensionPixelSize(R.dimen.custom_dialog_padding);
        int topMargin = dialogFragment.getContext()
                                      .getResources()
                                      .getDimensionPixelSize(R.dimen.custom_dialog_padding_top);
        int textPadding = dialogFragment.getContext()
                                        .getResources()
                                        .getDimensionPixelSize(R.dimen.text_padding_timer);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, textPadding, textPadding, 0);


        params.gravity = Gravity.END | Gravity.TOP;

        timerTextView.setPadding(textPadding, 0, textPadding, 0);

        timerTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, dialogFragment.getResources()
                                                                            .getDimension(R.dimen.text_size_timer));

        TypedArray a = dialogFragment.getContext()
                                     .obtainStyledAttributes(dialogFragment.getTheme(), new int[]{R.attr.colorPrimary});

        int attributeResourceId = a.getResourceId(0, 0);
        a.recycle();

        int bgcolor = ContextCompat.getColor(dialogFragment.getContext(), attributeResourceId);
        double y = (299 * Color.red(bgcolor) + 587 * Color.green(bgcolor) + 114 * Color.blue(bgcolor)) / 1000;
        int textColor = y >= 128 ? Color.BLACK : Color.WHITE;

        timerTextView.setBackgroundColor(bgcolor);
        timerTextView.setTextColor(textColor);

        //timerTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));

        return params;
    }


    public K onCreateDialog(Class<T> clazz) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        builder = clazz.getDeclaredConstructor(Context.class, int.class)
                       .newInstance(dialogFragment.getContext(), getStyle());

        if (hasIcon()) builder.setIcon(getIcon());

        if (hasTitle()) builder.setTitle(getTitle());

        setDialogContent();

        if (hasPositiveButton()) builder.setPositiveButton(getPositiveButton(), onClickListener);

        if (hasNeutralButton()) builder.setNeutralButton(getNeutralButton(), onClickListener);

        if (hasNegativeButton()) builder.setNegativeButton(getNegativeButton(), onClickListener);

        Object dialog = builder.create();

        View addView = addView();
        if (addView!=null) {
            if (dialog instanceof android.support.v7.app.AlertDialog) {
                ((android.support.v7.app.AlertDialog) dialog).setView(addView);
            } else if (dialog instanceof android.app.AlertDialog) {
                ((android.app.AlertDialog) dialog).setView(addView);
            }
        }
        return (K) dialog;

    }

    protected View addView() {
        return null;
    }

    void setDialogContent() {
        if (hasMessage()) builder.setMessage(getMessage());
    }

    public Bundle addArgumentsToDialogAfterButtonClick(Bundle dialogArguments, int which) {
        return dialogArguments;
    }

    static class AbstractBaseBuilder<T extends AbstractBaseBuilder,K extends Dialog> {
        Bundle builderArgs = new Bundle();
        boolean cancelable = true;

        private Class<K> dialogBaseClass;

        public AbstractBaseBuilder(Class<K> dialogBaseClass) {
            this.dialogBaseClass = dialogBaseClass;
        }

        public T setTimer(long millis) {
            builderArgs.putLong(ARG_LONG_TIMER, millis);
            return (T) this;
        }

        public T setStyle(@StyleRes int style) {
            builderArgs.putInt(ARG_INT_STYLE, style);
            return (T) this;
        }

        public T setTitle(String title) {
            builderArgs.putString(ARG_STRING_TITLE, title);
            return (T) this;
        }

        public T setTitle(int resId) {
            builderArgs.putInt(ARG_INT_TITLE, resId);
            return (T) this;
        }

        public T setMessage(String message) {
            builderArgs.putString(ARG_STRING_MESSAGE, message);
            return (T) this;
        }

        public T setMessage(int resId) {
            builderArgs.putInt(ARG_INT_MESSAGE, resId);
            return (T) this;
        }

        public T setPositiveButton(String text) {
            builderArgs.putString(ARG_STRING_BUTTONTEXTPOSITIVE, text);
            return (T) this;
        }

        public T setPositiveButton(int resId) {
            builderArgs.putInt(ARG_INT_BUTTONTEXTPOSITIVE, resId);
            return (T) this;
        }

        public T setPositiveButton() {
            return setPositiveButton(android.R.string.ok);
        }

        public T setNeutralButton(String text) {
            builderArgs.putString(ARG_STRING_BUTTONTEXTNEUTRAL, text);
            return (T) this;
        }

        public T setNeutralButton(int resId) {
            builderArgs.putInt(ARG_INT_BUTTONTEXTNEUTRAL, resId);
            return (T) this;
        }

        public T setNeutralButton() {
            return setNeutralButton(android.R.string.untitled);
        }

        public T setNegativeButton(String text) {
            builderArgs.putString(ARG_STRING_BUTTONTEXTNEGATIVE, text);
            return (T) this;
        }

        public T setNegativeButton(int resId) {
            builderArgs.putInt(ARG_INT_BUTTONTEXTNEGATIVE, resId);
            return (T) this;
        }

        public T setNegativeButton() {
            return setNegativeButton(android.R.string.cancel);
        }

        public T setIcon(int resId) {
            builderArgs.putInt(ARG_INT_ICONID, resId);
            return (T) this;
        }

        public T setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return (T) this;
        }


        public T addBundle(Bundle bundle) {
            builderArgs.putAll(bundle);
            return (T) this;
        }

//        public K build() {
//            try {
//                K result = clazz.newInstance();
//                result.setArguments(builderArgs);
//                result.setCancelable(cancelable);
//                return result;
//            } catch (java.lang.InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }

        @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
        public DialogFragment build() {
            DialogFragment result = new DialogFragment();
            result.initBase(dialogBaseClass);
            result.setArguments(builderArgs);
            result.setCancelable(cancelable);
            return result;
        }

        public SupportDialogFragment buildSupport() {
            SupportDialogFragment result = new SupportDialogFragment();
            result.initBase(dialogBaseClass);
            result.setArguments(builderArgs);
            result.setCancelable(cancelable);
            return result;
        }

    }

    public static class Builder extends AbstractBaseBuilder<Builder,Dialog> {
        public Builder() {
            super(Dialog.class);
        }
    }
}
