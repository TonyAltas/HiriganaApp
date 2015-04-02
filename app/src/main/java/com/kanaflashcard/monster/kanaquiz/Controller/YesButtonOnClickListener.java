package com.kanaflashcard.monster.kanaquiz.Controller;

import android.content.Context;
import android.view.View;

import com.kanaflashcard.monster.kanaquiz.View.TestActivity;

/**
 * Created by Monster on 23-Feb-15.
 */
public class YesButtonOnClickListener implements View.OnClickListener {
    private Context context;

    public YesButtonOnClickListener(Context context) {
    this.context = context;
    }

    @Override
    public void onClick(View v) {
        ((TestActivity)context).yesButtonImpl();
    }
}
