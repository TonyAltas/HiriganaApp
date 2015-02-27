package com.hiriganaapp.monster.hiriganaapp.Controller;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.hiriganaapp.monster.hiriganaapp.View.TestActivity;

/**
 * Created by Monster on 23-Feb-15.
 */
public class NoButtonOnClickListener implements View.OnClickListener {
    private Context context;
    private int flashCardMax;
    private TextView display;
    private int flashCardCounter;
    private TextView currentCardNumberTextView;
    private String hiraganaArray[];
    private String[] selectedHiraganaArray = new String[75];

    public NoButtonOnClickListener (Context context,int flashCardMax, TextView display,int flashCardCounter
    ,TextView currentCardNumberTextView,String[] hiraganaArray,String[] selectedHiraganaArray  ) {
        this.context =context;
        this.flashCardMax = flashCardMax;
        this.display = display;
        this.flashCardCounter = flashCardCounter;
        this.currentCardNumberTextView = currentCardNumberTextView;
        this.hiraganaArray = hiraganaArray;
        this.selectedHiraganaArray = selectedHiraganaArray;

    }

    @Override
    public void onClick(View v) {
        ((TestActivity)context).noButtonImpl();

//        if (flashCardMax < 0) {
//            display.setText("No More Cards!");
//            return;
//        }
//
//        if (flashCardCounter < flashCardMax) {
//            flashCardCounter++;
//        } else {
//            flashCardCounter = 0;
//        }
//        display.setText(hiraganaArray[Integer.parseInt(selectedHiraganaArray[flashCardCounter])]);
//        currentCardNumberTextView.setText(Integer.toString(flashCardCounter + 1));
    }
}
