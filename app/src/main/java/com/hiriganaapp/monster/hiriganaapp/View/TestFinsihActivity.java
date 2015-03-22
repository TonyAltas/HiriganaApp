package com.hiriganaapp.monster.hiriganaapp.View;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.hiriganaapp.monster.hiriganaapp.Controller.ContinueButtonOnClickListener;
import com.hiriganaapp.monster.hiriganaapp.R;

public class TestFinsihActivity extends Activity {
    private final String TOTAL_FLASH_CARDS = "total_flash_cards";
    private final String TIME_ELAPSED = "time_elapsed";
    private final String MINUTES_ELAPSED = "minutes_elapsed";
    private final String SECONDS_ELAPSED = "seconds_elapsed";
    private int totalCards;
    private long timeElapsed= 0;
    private int seconds=0;
    private int minutes=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_finsih);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            totalCards = (int) extras.getInt(TOTAL_FLASH_CARDS);
            timeElapsed = (long) extras.getLong(TIME_ELAPSED);
            seconds = extras.getInt(SECONDS_ELAPSED);
            minutes = extras.getInt(MINUTES_ELAPSED);
        }

        TextView totalFlashCard = (TextView) findViewById(R.id.totalCardNumbersTestFiinish_TextView);
        totalFlashCard.setText("total Cards : " + totalCards);

        TextView timeElapsedTextView = (TextView) findViewById(R.id.timeElapsedTestFinish_TextView);
        timeElapsedTextView.setText(String.format("%d:%02d",minutes,seconds));

        TextView continueButton = (TextView) findViewById(R.id.continue_Button);
        continueButton.setOnClickListener(new ContinueButtonOnClickListener(this));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test_finsih, menu);
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
