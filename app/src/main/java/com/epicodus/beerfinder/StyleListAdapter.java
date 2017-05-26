package com.epicodus.beerfinder;

import android.content.Context;
import android.widget.ArrayAdapter;


/**
 * Created by Guest on 5/26/17.
 */

public class StyleListAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mStyles;

    public StyleListAdapter(Context mContext, int resource, String[] mStyles) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mStyles = mStyles;
    }

    @Override
    public Object getItem(int position) {
        String style = mStyles[position];
        return String.format(style);
    }

    @Override
    public int getCount() {
        return mStyles.length;
    }
}