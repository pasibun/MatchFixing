package com.matchfixing.minor.matchfixing;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jylti on 1-11-2016.
 */
public class GridViewAdapter extends BaseAdapter {

    Context ctx;
    List<String> todaysMatches;

    public GridViewAdapter(Context c, List<String> matches)
    {
        ctx = c;
        todaysMatches = matches;
    }

    @Override
    public int getCount() {
        return todaysMatches.size();
    }

    @Override
    public Object getItem(int position) {
        return todaysMatches.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv = new TextView(ctx);
        tv.setText(String.valueOf(todaysMatches.get(position)));
        return tv;
    }
}
