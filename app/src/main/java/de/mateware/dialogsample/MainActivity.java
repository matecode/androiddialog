package de.mateware.dialogsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void appactivity(View view){
        Intent intent = new Intent(this, TestActivty.class);
        startActivity(intent);
    }

    public void supportactivity(View view){
        Intent intent = new Intent(this, TestActivitySupport.class);
        startActivity(intent);
    }

}
