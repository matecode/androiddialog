package de.mateware.dialogsample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mateware.dialog.Dialog;

public class MainActivity extends AppCompatActivity implements Dialog.DialogButtonListener, Dialog.DialogCancelListener, Dialog.DialogDismissListener {
    private static final Logger log = LoggerFactory.getLogger(MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();
            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                new Dialog().withMessage("Test")
                //                            .withStyle(R.style.Dialog)
                //                        .withTimer(10)
                //                            .withPositiveButton()
                //                            .show(getSupportFragmentManager(), "DIALOGTEST");

                Bundle extraStuff = new Bundle();
                extraStuff.putString("TEST", "test");

                new Dialog.Builder("DIALOGBUILDERTEST").setTitle("Title")
                                                       .setIcon(R.mipmap.ic_launcher)
                                                       .setMessage("Test")
                                                       .setPositiveButton()
                                                       .setNeutralButton(R.string.app_name)
                                                       .setNegativeButton("Nö")
                                                       .setCancelable(false)
                                                       .setTimerSeconds(10)
                                                       .setStyle(R.style.Dialog)
                                                       .setBundle(extraStuff)
                                                       .build()
                                                       .show(MainActivity.this);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDialogClick(String tag, Bundle arguments, int which) {
        log.debug("tag: {}, arguments: {}, which: {}", tag, arguments.toString(), which);
    }

    @Override
    public void onDialogCancel(String tag, Bundle arguments) {
        log.debug("tag: {}, arguments: {}", tag, arguments.toString());
    }

    @Override
    public void onDialogDismiss(String tag, Bundle arguments) {
        log.debug("tag: {}, arguments: {}", tag, arguments.toString());
    }
}
