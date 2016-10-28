package de.mateware.dialogsample;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toolbar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mateware.dialog.Dialog;
import de.mateware.dialog.listener.DialogButtonListener;

public class Main2Activity extends Activity implements DialogButtonListener {

    private static final Logger log = LoggerFactory.getLogger(Main2Activity.class);

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void dialog(View view) {
        new Dialog.Builder().setTitle("DialogTitle")
                            .setMessage("This is a standard dialog.")
                            .setStyle(R.style.Dialog)
                            .setPositiveButton()
                            .setTimer(5000)
                            .build()
                            .show(getFragmentManager(), "DIALOG");

    }

    @Override
    public void onDialogClick(String tag, Bundle dialogArguments, int which) {
        log.debug("tag: {}, dialogArguments: {}, which: {}",tag, dialogArguments, which);
    }
}
