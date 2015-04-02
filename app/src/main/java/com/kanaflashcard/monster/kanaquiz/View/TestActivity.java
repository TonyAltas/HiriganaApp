package com.kanaflashcard.monster.kanaquiz.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.kanaflashcard.monster.kanaquiz.Controller.FlipCardButtonListener;
import com.kanaflashcard.monster.kanaquiz.Controller.NoButtonOnClickListener;
import com.kanaflashcard.monster.kanaquiz.Controller.YesButtonOnClickListener;
import com.kanaflashcard.monster.kanaquiz.R;

import java.util.Random;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class TestActivity extends Activity {
    private final String SELECTED_HIRAGANA_PREFFERENCE = "selected_hiragana";
    private final String HIRAGANA_PREFERENCE_FILE = "hiragana_preferences _file";
    private final String TOTAL_FLASH_CARDS = "total_flash_cards";
    private final String TIME_ELAPSED = "time_elapsed";
    private final String MINUTES_ELAPSED = "minutes_elapsed";
    private final String SECONDS_ELAPSED = "seconds_elapsed";
    private String selectedHiraganaString;
    private String[] selectedHiraganaArray = new String[75];
    private String hiraganaArray[];
    private String romanizedArray[];

    private final String SETTINGS_PREFERENCCE_FILE = "settings_file";
    private final String INFINITE_LOOP_SETTINGS = "infinite_loop";
    private final String RANDOMIZE_TEST_SETTINGS = "randomize_test";

    // Counter for the current card being displayed
    private int flashCardCounter = 0;
    // Total number of cards
    private int flashCardMax = 0;
    private int initialFlashCardMax = 0;
    private long startTestTime = 0;
    private long endTestTime = 0;
    private int seconds = 0;
    private int minutes = 0;
    private boolean isVisible = true;

    private boolean infinityLoop = false;
    private boolean randomizeTest = false;
    private boolean romajiOrKana = true;

    private TextView display;
    private TextView yesButton;
    private TextView noButton;
    private TextView flipCardButton;
    private TextView currentCardNumberTextView;
    private TextView totalCardNumber;
    private TextView timerTextView;
    private Context context = this;
    private Random rand = new Random();
    private int randomNumber = 0;
    private String temp;
    private SharedPreferences settings;

    Handler timeHandler = new Handler();

        Runnable timeRunnable = new Runnable() {
            @Override
            public void run() {
                long timeCounter = System.nanoTime() - startTestTime;
                seconds = ((int) TimeUnit.NANOSECONDS.toSeconds(timeCounter)) % 60;
                minutes = (int) TimeUnit.NANOSECONDS.toMinutes(timeCounter);
                timerTextView.setText(String.format("%d:%02d", minutes, seconds));

                timeHandler.postDelayed(this, 500);
            }
        };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //Handle all the shared preferences access
        sharedPreferenceHandler();

        //Start timing how long test takes.
        startTestTime = System.nanoTime();
        timeHandler.postDelayed(timeRunnable, 0);

        display = (TextView) findViewById(R.id.hiraganaDisplay_textView);
        yesButton = (TextView) findViewById(R.id.yes_Button);
        noButton = (TextView) findViewById(R.id.no_Button);
        flipCardButton = (TextView) findViewById(R.id.flipCard_Button);

        currentCardNumberTextView = (TextView) findViewById(R.id.currentCardNumber_textView);
        totalCardNumber = (TextView) findViewById(R.id.totalCardNumber_textView);
        timerTextView = (TextView) findViewById(R.id.timer_TextView);

        // Check infinityLoop setting from shared preferences
        if (infinityLoop) {
            flipCardButton.setText("Show");
        }

        //Retrieve hiraganaArray & romanizedArray array from string.xml
        Resources res = getResources();
        hiraganaArray = res.getStringArray(R.array.hiragana);
        romanizedArray = res.getStringArray(R.array.romanized_kana);
        //Tokenize selected items array
        tokenizeSelected();
        // Randomize the array
        randomizeArray();
        totalCardNumber.setText(Integer.toString(flashCardMax + 1));
        currentCardNumberTextView.setText("1");

        //Display hirigana for flashcard
        display.setText(hiraganaArray[Integer.parseInt(selectedHiraganaArray[flashCardCounter])]);

        yesButton.setOnClickListener(new YesButtonOnClickListener(context));

        noButton.setOnClickListener((new NoButtonOnClickListener(context,flashCardMax,display,flashCardCounter,
                currentCardNumberTextView,hiraganaArray,selectedHiraganaArray)));

        flipCardButton.setOnClickListener(new FlipCardButtonListener(context));

    }

    private void randomizeArray() {
        if (randomizeTest) {

            for (int i = 0; i < initialFlashCardMax; i++) {
                randomNumber = rand.nextInt(initialFlashCardMax);
                Log.v("Flash Cards Length : ", Integer.toString(initialFlashCardMax));
                temp = selectedHiraganaArray[i];
                selectedHiraganaArray[i] = selectedHiraganaArray[randomNumber];
                Log.d("Random Number: ", Integer.toString(randomNumber));
                selectedHiraganaArray[randomNumber] = temp;
            }
        }
    }

    private void tokenizeSelected() {
        StringTokenizer stringTokenizer = new StringTokenizer(selectedHiraganaString, ",");
        int numOfSelectedHiragana = 0;
        while (stringTokenizer.hasMoreElements()) {
            selectedHiraganaArray[numOfSelectedHiragana] = stringTokenizer.nextElement().toString();
            numOfSelectedHiragana++;
        }
        //Set Max Number Of FlashCards
        initialFlashCardMax = numOfSelectedHiragana;
        flashCardMax = initialFlashCardMax - 1;
    }

    private void sharedPreferenceHandler() {

            //Retrieve list of selected hiraganaArray to display
            settings = getSharedPreferences
                    (HIRAGANA_PREFERENCE_FILE, Context.MODE_PRIVATE);
            selectedHiraganaString = settings.getString(SELECTED_HIRAGANA_PREFFERENCE, "Empty");

            //Read in the Settigns from the Settings preference file.
            settings = getSharedPreferences(SETTINGS_PREFERENCCE_FILE, Context.MODE_PRIVATE);
            infinityLoop = settings.getBoolean(INFINITE_LOOP_SETTINGS, false);
            randomizeTest = settings.getBoolean(RANDOMIZE_TEST_SETTINGS, false);

    }

    public void yesButtonImpl(){


        if (infinityLoop) {
            if (flashCardCounter < flashCardMax) {
                flashCardCounter++;
            } else {
                flashCardCounter = 0;
            }
            display.setText(hiraganaArray[Integer.parseInt(selectedHiraganaArray[flashCardCounter])]);
            currentCardNumberTextView.setText(Integer.toString(flashCardCounter + 1));
            return;
        }

        // All flashcards have been checked as correct, finish test.
        if (flashCardMax >= 0) {
            flashCardMax--;
        }
        if (flashCardMax < 0) {

            totalCardNumber.setText("0");
            currentCardNumberTextView.setText("0");

            //End test Time
            endTestTime = System.nanoTime();

            //Send total Number of cards and time elapsed to Finish Activity.
            Intent intent = new Intent(context, TestFinsihActivity.class);
            intent.putExtra(TOTAL_FLASH_CARDS, initialFlashCardMax);
            intent.putExtra(TIME_ELAPSED, endTestTime - startTestTime);
            intent.putExtra(MINUTES_ELAPSED, minutes);
            intent.putExtra(SECONDS_ELAPSED, seconds);
            startActivity(intent);

            return;
        }

        //Removes checked card from array
        System.arraycopy(selectedHiraganaArray, flashCardCounter + 1, selectedHiraganaArray, flashCardCounter, selectedHiraganaArray.length - 1 - flashCardCounter);


        if (flashCardCounter >= flashCardMax) {
            flashCardCounter = 0;
        }
        display.setText(hiraganaArray[Integer.parseInt(selectedHiraganaArray[flashCardCounter])]);
        totalCardNumber.setText(Integer.toString(flashCardMax + 1));
        currentCardNumberTextView.setText(Integer.toString(flashCardCounter + 1));

        noButton.setVisibility(View.GONE);
        yesButton.setVisibility(View.GONE);
        flipCardButton.setVisibility(View.VISIBLE);
        isVisible = true;
    }

    public void noButtonImpl() {


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
        currentCardNumberTextView.setText(Integer.toString(flashCardCounter + 1));

        noButton.setVisibility(View.GONE);
        yesButton.setVisibility(View.GONE);
        flipCardButton.setVisibility(View.VISIBLE);
        isVisible = true;
    }

    public void flipCardButtonImpl() {
//        if (isVisible) {
//            noButton.setVisibility(View.GONE);
//            noButton.setVisibility(View.GONE);
//            isVisible = false;
//        }

        if (infinityLoop) {
            if (romajiOrKana) {
                display.setText(romanizedArray[Integer.parseInt(selectedHiraganaArray[flashCardCounter])]);
                romajiOrKana = false;
            } else {
                if (flashCardCounter < flashCardMax) {
                    flashCardCounter++;
                } else {
                    flashCardCounter = 0;
                }
                display.setText(hiraganaArray[Integer.parseInt(selectedHiraganaArray[flashCardCounter])]);
                currentCardNumberTextView.setText(Integer.toString(flashCardCounter + 1));
                romajiOrKana = true;

            }
            return;
        }

        if (isVisible) {
            noButton.setVisibility(View.VISIBLE);
            yesButton.setVisibility(View.VISIBLE);
            flipCardButton.setVisibility(View.GONE);
            isVisible = false;
            //Add romanized kana
            display.setText(romanizedArray[Integer.parseInt(selectedHiraganaArray[flashCardCounter])]);
        }
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
