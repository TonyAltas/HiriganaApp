package com.hiriganaapp.monster.hiriganaapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hiriganaapp.monster.hiriganaapp.R;

/**
 * Created by Monster on 01-Mar-15.
 */
public class LibraryGridViewAdapter extends BaseAdapter {
    private Context context;
    private  final String [] hiragana;

    public LibraryGridViewAdapter(Context context, String [] kana){
        this.context = context;
        this.hiragana = kana;
//        Resources res = context.getResources();
//        hiragana = res.getStringArray(R.array.hiragana);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        gridView = inflater.inflate(R.layout.library_gridview_cell, null);
        TextView textView = (TextView) gridView.findViewById(R.id.library_gridView_cell_textView);
        textView.setText(hiragana[position]);
        return gridView;
    }

    @Override
    public int getCount() {
        return hiragana.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}
