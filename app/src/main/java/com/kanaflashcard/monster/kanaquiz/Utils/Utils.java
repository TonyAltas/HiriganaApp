package com.kanaflashcard.monster.kanaquiz.Utils;

import android.content.Context;
import android.view.View;
import android.widget.Adapter;
import android.widget.FrameLayout;

/**
 * Created by Monster on 22-Feb-15.
 */
public final class Utils {

    public static int getWidestView(Context context, Adapter adapter) {
        int maxWidth = 0;
        View view = null;
        FrameLayout fakeParent = new FrameLayout(context);
        for (int i=0, count=adapter.getCount(); i<count; i++) {
            view = adapter.getView(i, view, fakeParent);
            view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            int width = view.getMeasuredWidth();
            if (width > maxWidth) {
                maxWidth = width;
            }
        }
        return maxWidth;
    }
}
