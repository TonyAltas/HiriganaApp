package com.hiriganaapp.monster.hiriganaapp.Controller;

import android.content.Context;
import android.view.View;

import com.hiriganaapp.monster.hiriganaapp.View.TestFinsihActivity;

/**
 * Created by Monster on 22-Mar-15.
 */
public class ContinueButtonOnClickListener implements View.OnClickListener {
    private Context context;
    public ContinueButtonOnClickListener(Context context) {
        this.context = context;
    }
    @Override
    public void onClick(View v) {
        ((TestFinsihActivity)context).finish();
    }
}
