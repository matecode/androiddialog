package de.mateware.dialogsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mateware.dialog.Dialog;
import de.mateware.dialog.DialogIndeterminateProgress;
import de.mateware.dialog.listener.DialogButtonListener;

public class MainActivity extends AppCompatActivity implements DialogButtonListener {

    private static final Logger log = LoggerFactory.getLogger(MainActivity.class);

    static final String TAG_DIALOG_CUSTOMVIEWEXAMPLE = "cudtomVieExample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void dialog(View view) {
        new Dialog.Builder().setTitle("DialogTitle")
                            .setMessage("This is a standard dialog.")
                            .setStyle(R.style.Dialog)
                            .setPositiveButton()
                            .build()
                            .show(getSupportFragmentManager(), "DIALOG");

    }

    public void indeterminateProgress(View view) {
        new DialogIndeterminateProgress.Builder().setMessage("And the wheel goes round and round and round")
                                                 .setStyle(R.style.Dialog)
                                                 .setCancelable(false)
                                                 .setTimer(15000)
                                                 .build()
                                                 .show(getSupportFragmentManager(), "PROGRESS_DIALOG");

//        new DialogIndeterminateProgress().withMessage("And the wheel goes round and round and round")
//                                         .withStyle(R.style.Dialog)
//                                         .withCancelable(false)
//                                         .withTimer(15000)
//                                         .show(getSupportFragmentManager(), "PROGRESS_DIALOG");
    }

    public void customView(View view) {
        new DialogCustomViewExample.Builder().setTitle("Custom View")
                                             .setStyle(R.style.Dialog)
                                             .setPositiveButton()
                                             .build()
                                             .show(getSupportFragmentManager(), TAG_DIALOG_CUSTOMVIEWEXAMPLE);
    }

    @Override
    public void onDialogClick(String tag, Bundle dialogArguments, int which) {
        if (TAG_DIALOG_CUSTOMVIEWEXAMPLE.equals(tag)) {
            log.debug("EXTRA_TEST_ARGUMENT: {}", dialogArguments.getString(DialogCustomViewExample.EXTRA_TEST_ARGUMENT));
        }
    }
}
