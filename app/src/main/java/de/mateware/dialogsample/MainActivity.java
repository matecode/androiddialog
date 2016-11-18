package de.mateware.dialogsample;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mateware.dialog.Dialog;
import de.mateware.dialog.DialogIndeterminateProgress;
import de.mateware.dialog.DialogList;
import de.mateware.dialog.LicenceDialog;
import de.mateware.dialog.licences.Agpl30Licence;
import de.mateware.dialog.licences.Apache20Licence;
import de.mateware.dialog.licences.BsdLicence;
import de.mateware.dialog.licences.MitLicence;
import de.mateware.dialog.listener.DialogButtonListener;
import de.mateware.dialog.listener.DialogListListener;

public class MainActivity extends AppCompatActivity implements DialogButtonListener, DialogListListener {

    private static final Logger log = LoggerFactory.getLogger(MainActivity.class);

    static final String TAG_DIALOG_CUSTOMVIEWEXAMPLE = "customViewExample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager.enableDebugLogging(true);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void dialog(View view) {
        new Dialog.Builder().setTitle("DialogTitle")
                            .setMessage("This is a standard dialog.")
                            .setStyle(R.style.Dialog)
                            .setPositiveButton()
                            .buildSupport()
                            .show(getSupportFragmentManager(), "DIALOG");

    }

    public void indeterminateProgress(View view) {
        new DialogIndeterminateProgress.Builder().setMessage("And the wheel goes round and round and round")
                                                 .setStyle(R.style.Dialog)
                                                 .setCancelable(false)
                                                 .setTimer(15000)
                                                 .buildSupport()
                                                 .show(getSupportFragmentManager(), "PROGRESS_DIALOG");
    }

    public void customView(View view) {
        new DialogCustomViewExample.Builder().setTitle("Custom View")
                                             .setStyle(R.style.Dialog)
                                             .setPositiveButton()
                                             .buildSupport()
                                             .show(getSupportFragmentManager(), TAG_DIALOG_CUSTOMVIEWEXAMPLE);
    }

    public void list(View view) {
        new DialogList.Builder().setTitle("List")
                                .setList("Item1", "Item2", "Item3")
                                .buildSupport()
                                .show(getSupportFragmentManager(), "DIALOG_LIST");
    }

    public void licence(View view) {
        new LicenceDialog.Builder().setTitle("Open Source Lizenzen")
                                   .addEntry(new Apache20Licence(this, "Project1","Mate",2014))
                                   .addEntry(new MitLicence(this, "Project2","Peter",2013))
                                   .addEntry(new Agpl30Licence(this, "Project3","Stefan",2012))
                                   .addEntry(new MitLicence(this, "Project4","Klass",2011))
                                   .addEntry(new BsdLicence(this, "Project5","MATEYEAH",2011))
                                   .setPositiveButton()
                                   .buildSupport()
                                   .show(getSupportFragmentManager(), "LICENCESDIALOG");
    }

    @Override
    public void onDialogClick(String tag, Bundle dialogArguments, int which) {
        log.debug("tag: {}, dialogArguments: {}, which: {}", tag, dialogArguments, which);
        if (TAG_DIALOG_CUSTOMVIEWEXAMPLE.equals(tag)) {
            log.debug("EXTRA_TEST_ARGUMENT: {}", dialogArguments.getString(DialogCustomViewExample.EXTRA_TEST_ARGUMENT));
        }
    }

    @Override
    public void onDialogListClick(String tag, Bundle arguments, int which, String value, String[] items) {
        log.debug("tag: {}, arguments: {}, which: {}, value: {}, items: {}", tag, arguments, which, value, items);
    }
}
