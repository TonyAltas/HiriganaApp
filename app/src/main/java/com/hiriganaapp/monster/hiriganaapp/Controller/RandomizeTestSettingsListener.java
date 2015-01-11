package com.hiriganaapp.monster.hiriganaapp.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.CheckBox;

/**
 * Created by Monster on 11-Jan-15.
 */
public class RandomizeTestSettingsListener implements View.OnClickListener {
    private final String SETTINGS_PREFERENCCE_FILE = "settings_file";
    private final String RANDOMIZE_TEST_SETTINGS = "randomize_test";

    @Override
    public void onClick(View v) {
        Context context = v.getContext();
        SharedPreferences settings = context.
                getSharedPreferences(SETTINGS_PREFERENCCE_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        if (((CheckBox) v).isChecked()) {
            editor.putBoolean(RANDOMIZE_TEST_SETTINGS, true);
            editor.commit();
        }
        else if (!((CheckBox) v).isChecked()) {
            editor.putBoolean(RANDOMIZE_TEST_SETTINGS, false);
            editor.commit();
        }


    }
}
