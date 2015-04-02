package com.kanaflashcard.monster.kanaquiz.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kanaflashcard.monster.kanaquiz.Model.KanaSingleton;
import com.kanaflashcard.monster.kanaquiz.R;

/**
 * Created by Monster on 01-Mar-15.
 */
public class LibraryGridViewAdapter extends BaseAdapter {
    private Context context;
    private final String [] hiragana;
//    private int selection =0;
    private SparseBooleanArray selection;

    public LibraryGridViewAdapter(Context context, String [] kana){
        this.context = context;
        this.hiragana = kana;
        selection = new SparseBooleanArray(hiragana.length);

//        Resources res = context.getResources();
//        hiragana = res.getStringArray(R.array.hiragana);
//        (LibraryGridViewAdapter)context;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
//        gridView = inflater.inflate(R.layout.library_gridview_cell, null);
//            TextView textView = (TextView) gridView.findViewById(R.id.library_gridView_cell_textView);
//            textView.setText(hiragana[position]);

//        try {
//           int selection = (int) gridView.getTag();
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//            selection = 0;
//        }
//        if (selection == 1) {
//            gridView.setBackgroundColor(context.getResources().getColor(R.color.library_selected));
//        }
//        if(selection == 0 ){
//            gridView.setBackgroundColor(context.getResources().getColor(R.color.library_bg));
//        }



        if (convertView == null) {
            gridView = inflater.inflate(R.layout.library_gridview_cell, null);
            TextView textView = (TextView) gridView.findViewById(R.id.library_gridView_cell_textView);
            textView.setText(hiragana[position]);
            gridView.setTag(position);
//            gridView.setBackgroundColor(context.getResources().getColor(R.color.library_bg));
            gridView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
//                    if (selection.get((int)v.getTag()) == true) {
                    if (KanaSingleton.getKanaSingleton().get((int) v.getTag()) == true) {
//                        selection.put((int)v.getTag(),false);
                        KanaSingleton.getKanaSingleton().put((int)v.getTag(),false);

                        v.setBackgroundColor(context.getResources().getColor(R.color.library_bg));
                    } else {
//                        selection.put((int)v.getTag(),true);
                        KanaSingleton.getKanaSingleton().put((int)v.getTag(),true);
                        v.setBackgroundColor(context.getResources().getColor(R.color.library_selected));
//                        v.setBackgroundColor(context.getResources().getColor(R.color.library_bg));
                    }
                }
            });
        } else {
            gridView = (View) convertView;
            TextView textView = (TextView) gridView.findViewById(R.id.library_gridView_cell_textView);
            textView.setText(hiragana[position]);
            gridView.setTag(position);
//            gridView.setBackgroundColor(context.getResources().getColor(R.color.library_selected));

//            if (selection.get(position) == true) {



        }

        if (KanaSingleton.getKanaSingleton().get(position) == true) {
            gridView.setBackgroundColor(context.getResources().getColor(R.color.library_selected));
        } else {
            gridView.setBackgroundColor(context.getResources().getColor(R.color.library_bg));
        }

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
