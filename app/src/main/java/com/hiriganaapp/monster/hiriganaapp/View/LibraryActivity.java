package com.hiriganaapp.monster.hiriganaapp.View;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.hiriganaapp.monster.hiriganaapp.R;

public class LibraryActivity extends Activity {
    private GridView libraryGrid;
    private ArrayAdapter<String> arrayAdapter;
    private Context context = this;
    private String[] hiragana;
    // hiriganaItems is used to store the selected kana
    private final int[] hiraganaItems = new int[75];
    private final int SELECTED = 1;
    private final int NOT_SELECTED = 0;
    private final String SELECTED_HIRAGANA_PREFFERENCE = "selected_hiragana";
    private final String HIRAGANA_PREFERENCE_FILE = "hiragana_preferences _file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        libraryGrid = (GridView) findViewById(R.id.library_gridView);
        Resources res = getResources();
        hiragana = res.getStringArray(R.array.hiragana);

        arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,hiragana);
        libraryGrid.setAdapter(arrayAdapter);

        libraryGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast toast = Toast.makeText(context, Integer.toString(i), Toast.LENGTH_SHORT);
                toast.show();

                // When a letter from the alphabet is selected, hiraganaItems stores the index.
                if (hiraganaItems[i] == NOT_SELECTED) {
                    view.setBackgroundColor(getResources().getColor(R.color.library_selected));
                    hiraganaItems[i] = SELECTED;
                } else {
                    hiraganaItems[i] = NOT_SELECTED;
                    view.setBackgroundColor(getResources().getColor(R.color.library_bg));
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        SharedPreferences settings = context.
                getSharedPreferences(HIRAGANA_PREFERENCE_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        //Build String to hold all the selected hiragana
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < hiraganaItems.length; i++) {
            if (hiraganaItems[i] == SELECTED) {
                stringBuilder.append(i + ",");
            }
        }
        //The selected hirigana index is used with string.xml array of hirigana
        // to display the selected hirigana symbol
        editor.putString(SELECTED_HIRAGANA_PREFFERENCE, stringBuilder.toString());
        editor.commit();
        Log.v("OnBackPress", stringBuilder.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_library, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();




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
