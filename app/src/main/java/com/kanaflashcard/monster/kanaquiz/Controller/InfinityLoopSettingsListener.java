package com.kanaflashcard.monster.kanaquiz.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.CheckBox;

/**
 * Created by Monster on 12/29/2014.
 */
public class InfinityLoopSettingsListener implements View.OnClickListener {
    private final String SETTINGS_PREFERENCCE_FILE = "settings_file";
    private final String INFINITE_LOOP_SETTINGS = "infinite_loop";
    @Override
    public void onClick(View v) {
        Context context = v.getContext();
        SharedPreferences settings = context.
                getSharedPreferences(SETTINGS_PREFERENCCE_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        if (((CheckBox) v).isChecked() ) {
            editor.putBoolean(INFINITE_LOOP_SETTINGS, true);
            editor.commit();
        }
        else if (!((CheckBox) v).isChecked()) {
            editor.putBoolean(INFINITE_LOOP_SETTINGS, false);
            editor.commit();
        }

    }
}
