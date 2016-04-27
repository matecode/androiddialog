package de.mateware.dialogsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import de.mateware.dialog.Dialog;
import de.mateware.dialog.DialogIndeterminateProgress;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void dialog(View view) {
        new Dialog().withTitle("Dialog Title")
                    .withMessage("This is a standard dialog.")
                    .withStyle(R.style.Dialog)
                    .withPositiveButton()
                    .show(getSupportFragmentManager(), "DIALOG");
    }

    public void indeterminateProgress(View view) {
        new DialogIndeterminateProgress().withMessage("And the wheel goes round and round and round")
                                         .withStyle(R.style.Dialog)
                                         .withCancelable(false)
                                         .withTimer(15000)
                                         .show(getSupportFragmentManager(), "PROGRESS_DIALOG");
    }
}
