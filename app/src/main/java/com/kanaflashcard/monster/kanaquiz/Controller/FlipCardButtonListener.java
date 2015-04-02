package com.kanaflashcard.monster.kanaquiz.Controller;

import android.content.Context;
import android.view.View;

import com.kanaflashcard.monster.kanaquiz.View.TestActivity;

/**
 * Created by Monster on 27-Feb-15.
 */
public class FlipCardButtonListener implements View.OnClickListener {
    Context context;
    public FlipCardButtonListener(Context context) {
        this.context = context;
    }
    @Override
    public void onClick(View v) {
        ((TestActivity)context).flipCardButtonImpl();
    }
}
