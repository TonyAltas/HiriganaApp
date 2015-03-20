package com.hiriganaapp.monster.hiriganaapp.View;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;

import com.hiriganaapp.monster.hiriganaapp.Controller.InfinityLoopSettingsListener;
import com.hiriganaapp.monster.hiriganaapp.Controller.RandomizeTestSettingsListener;
import com.hiriganaapp.monster.hiriganaapp.R;

public class SettingsActivity extends Activity {
    private Context context = this;
    private CheckBox infiniteLoopCheckBox;
    private CheckBox randomizeTestCheckBox;
    private final String SETTINGS_PREFERENCCE_FILE = "settings_file";
    private final String INFINITE_LOOP_SETTINGS = "infinite_loop";
    private final String RANDOMIZE_TEST_SETTINGS = "randomize_test";
    private boolean infinityLoop = false;
    private boolean randomizeTest = false;

    private final String INFINITE_LOOP_SETTINGS_PREFERENCCE = "infinite_loop";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        infiniteLoopCheckBox = (CheckBox) findViewById(R.id.infiniteLoopSetting_CheckBox);
        infiniteLoopCheckBox.setOnClickListener(new InfinityLoopSettingsListener());

        randomizeTestCheckBox = (CheckBox) findViewById(R.id.randomizeTestSettings_CheckBox);
        randomizeTestCheckBox.setOnClickListener(new RandomizeTestSettingsListener());

        //Read in the Settigns from the Settings preference file.
        SharedPreferences settings = getSharedPreferences(SETTINGS_PREFERENCCE_FILE, Context.MODE_PRIVATE);
        infinityLoop = settings.getBoolean(INFINITE_LOOP_SETTINGS, false);
        infiniteLoopCheckBox.setChecked(infinityLoop);

        randomizeTest = settings.getBoolean((RANDOMIZE_TEST_SETTINGS), false);
        randomizeTestCheckBox.setChecked((randomizeTest));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
}
