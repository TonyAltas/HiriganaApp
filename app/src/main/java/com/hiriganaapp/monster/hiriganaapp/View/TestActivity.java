package com.hiriganaapp.monster.hiriganaapp.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hiriganaapp.monster.hiriganaapp.R;

import java.util.StringTokenizer;

public class TestActivity extends Activity {
    private final String SELECTED_HIRAGANA_PREFFERENCE = "selected_hiragana";
    private final String HIRAGANA_PREFERENCE_FILE = "hiragana_preferences _file";
    private final String TOTAL_FLASH_CARDS = "total_flash_cards";
    private final String TIME_ELAPSED = "time_elapsed";
    private String selectedHiraganaString;
    private String[] selectedHiraganaArray = new String[75];
    private  String hiraganaArray[];


    // Counter for the current card being displayed
    private int flashCardCounter = 0;
    // Total number of cards
    private int flashCardMax = 0;
    private int initialFlashCardMax = 0;
    private long startTestTime =0;
    private long endTestTime = 0;

    private TextView display;
    private Button yesButton;
    private Button noButton;
    private TextView currentCardNumber;
    private TextView totalCardNumber;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //Start timing how long test takes.
        startTestTime = System.nanoTime();
        //Retrieve list of selected hiraganaArray to display
        SharedPreferences settings = getSharedPreferences
                (HIRAGANA_PREFERENCE_FILE, Context.MODE_PRIVATE);
        selectedHiraganaString = settings.getString(SELECTED_HIRAGANA_PREFFERENCE, "Empty");
        Log.v("Test Activity", selectedHiraganaString);

        display = (TextView) findViewById(R.id.hiraganaDisplay_textView);
        yesButton = (Button) findViewById(R.id.yes_Button);
        noButton = (Button) findViewById(R.id.no_Button);

        currentCardNumber = (TextView) findViewById(R.id.currentCardNumber_textView);
        totalCardNumber = (TextView) findViewById(R.id.totalCardNumber_textView);

        //Retrieve hiraganaArray array
        Resources res = getResources();
        hiraganaArray = res.getStringArray(R.array.hiragana);
        //Tokenize selected items array
        StringTokenizer stringTokenizer = new StringTokenizer(selectedHiraganaString, ",");
        int numSelectedHiragana = 0;
        while (stringTokenizer.hasMoreElements()) {
            selectedHiraganaArray[numSelectedHiragana] = stringTokenizer.nextElement().toString();
            numSelectedHiragana++;
        }

        //Set Max Number Of FlashCards
        initialFlashCardMax = numSelectedHiragana;
        flashCardMax = initialFlashCardMax-1;
        //Display hirigana for flashcard
        display.setText(hiraganaArray[Integer.parseInt(selectedHiraganaArray[flashCardCounter])]);

        totalCardNumber.setText(Integer.toString(flashCardMax+1));
        currentCardNumber.setText("1");

//        noButton.setOnClickListener(new noButtonOnClickListener(flashCardCounter,flashCardMax,
//                display,selectedHiraganaArray,context));

//        yesButton.setOnClickListener(new yesButtonOnClickListener(flashCardCounter,flashCardMax,
//                display,selectedHiraganaArray,context));

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flashCardMax < 0) {
                    display.setText("No More Cards!");
                    return;
                }

                if (flashCardCounter < flashCardMax) {
                    flashCardCounter++;
                } else {
                    flashCardCounter = 0;
                }
                display.setText(hiraganaArray[Integer.parseInt(selectedHiraganaArray[flashCardCounter])]);
                currentCardNumber.setText(Integer.toString(flashCardCounter + 1));
                Log.v("NoClick", Integer.toString(flashCardCounter));
                Log.v("NoClick___MaxCounter IS: ", Integer.toString(flashCardMax));
            }
        });

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // All flashcards have been checked as correct, finish test.
                if (flashCardMax >= 0) {
                    flashCardMax--;
                }
                if(flashCardMax < 0 ){
                    display.setText("No More Cards!");
                    totalCardNumber.setText("0");
                    currentCardNumber.setText("0");

                    //End test Time
                    endTestTime = System.nanoTime();

                    //Send total Number of cards and time elapsed to Finish Activity.
                    Intent intent = new Intent(context, TestFinsihActivity.class);
                    intent.putExtra(TOTAL_FLASH_CARDS, initialFlashCardMax);
                    intent.putExtra(TIME_ELAPSED, endTestTime-startTestTime);
                    startActivity(intent);

                    return;
                }

                // Remove checked cards from deck.
//                for (int i = 0; i < selectedHiraganaArray.length; i++) {
//                    if (flashCardCounter == i) {
//
//                    }
//                }
                Log.v("Selected Hiragana before Remove: ", selectedHiraganaArray.toString());

                //Removes checked card from array
                System.arraycopy(selectedHiraganaArray, flashCardCounter + 1, selectedHiraganaArray, flashCardCounter, selectedHiraganaArray.length - 1 - flashCardCounter);
                Log.v("Selected Hiragana after Remove: ", selectedHiraganaArray.toString());

                if (flashCardCounter >= flashCardMax) {
                    flashCardCounter = 0;
                }
                display.setText(hiraganaArray[Integer.parseInt(selectedHiraganaArray[flashCardCounter])]);
                totalCardNumber.setText(Integer.toString(flashCardMax+1));
                currentCardNumber.setText(Integer.toString(flashCardCounter + 1));
                Log.v("YesClick", Integer.toString(flashCardCounter));
                Log.v("YesClick___MaxCounter IS: ", Integer.toString(flashCardMax));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
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
