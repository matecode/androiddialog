package de.mateware.dialogsample;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentManager;
import android.view.View;

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
import de.mateware.dialog.log.Log;

/**
 * Created by mate on 04.01.2017.
 */

public class TestActivty extends Activity implements DialogButtonListener, DialogListListener {

    static final String TAG_DIALOG_CUSTOMVIEWEXAMPLE = "customViewExample";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager.enableDebugLogging(true);
        setContentView(R.layout.content_test);
//            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//            setSupportActionBar(toolbar);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void dialog(View view) {
        new Dialog.Builder().setTitle("DialogTitle")
                            .setMessage("This is a standard dialog.")
                            .setStyle(R.style.Dialog)
                            .setPositiveButton()
                            .build()
                            .show(getFragmentManager(), "DIALOG");

    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void indeterminateProgress(View view) {
        new DialogIndeterminateProgress.Builder().setMessage("And the wheel goes round and round and round")
                                                 .setStyle(R.style.Dialog)
                                                 .setCancelable(false)
                                                 .setTimer(15000)
                                                 .build()
                                                 .show(getFragmentManager(), "PROGRESS_DIALOG");
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void customView(View view) {
        new DialogCustomViewExample.Builder().setTitle("Custom View")
                                             .setStyle(R.style.Dialog)
                                             .setPositiveButton()
                                             .build()
                                             .show(getFragmentManager(), TAG_DIALOG_CUSTOMVIEWEXAMPLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void list(View view) {
        new DialogList.Builder().setTitle("List")
                                .setList("Item1", "Item2", "Item3")
                                .build()
                                .show(getFragmentManager(), "DIALOG_LIST");
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void licence(View view) {
        new LicenceDialog.Builder().setTitle("Open Source Lizenzen")
                                   .addEntry(new Apache20Licence(this, "Project1", "Mate", 2014))
                                   .addEntry(new MitLicence(this, "Project2", "Peter", 2013))
                                   .addEntry(new Agpl30Licence(this, "Project3", "Stefan", 2012))
                                   .addEntry(new MitLicence(this, "Project4", "Klass", 2011))
                                   .addEntry(new BsdLicence(this, "Project5", "MATEYEAH", 2011))
                                   .setPositiveButton()
                                   .build()
                                   .show(getFragmentManager(), "LICENCESDIALOG");
    }

    @Override
    public void onDialogClick(String tag, Bundle dialogArguments, int which) {
        Log.d("tag: " + tag + " dialogArguments: " + dialogArguments + " which: " + which);
        if (TAG_DIALOG_CUSTOMVIEWEXAMPLE.equals(tag)) {
            Log.d("EXTRA_TEST_ARGUMENT: " + dialogArguments.getString(DialogCustomViewExample.EXTRA_TEST_ARGUMENT));
        }
    }

    @Override
    public void onDialogListClick(String tag, Bundle arguments, int which, String value, String[] items) {
        Log.d("tag: " + tag + " arguments: " + arguments + ", which: " + which + ", value: " + value + ", items: " + items);
    }
}
