package com.hiriganaapp.monster.hiriganaapp.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.hiriganaapp.monster.hiriganaapp.R;
import com.hiriganaapp.monster.hiriganaapp.Utils.Utils;


public class MainActivity extends ActionBarActivity {
    private String[] mainListArray = {"Start","Library","Settings","Statistics"};
    private Context context = this;
    private ListView menulistView;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menulistView = (ListView) findViewById(R.id.main_list);
        arrayAdapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1, mainListArray);
        menulistView.getLayoutParams().width = Utils.getWidestView(context, arrayAdapter);
        menulistView.setAdapter(arrayAdapter);
//
        menulistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String) adapterView.getItemAtPosition(i);
                Toast toast = Toast.makeText(context, item, Toast.LENGTH_SHORT);
                toast.show();

                if (item.equals("Start")) {
                    Intent intent = new Intent(context, TestActivity.class);
                    startActivity(intent);
                }
                else if (item.equals("Library")) {
                    Intent intent = new Intent(context, LibraryActivity.class);
                    startActivity(intent);
                }
                else if (item.equals("Settings")) {
                    Intent intent = new Intent((context), SettingsActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
