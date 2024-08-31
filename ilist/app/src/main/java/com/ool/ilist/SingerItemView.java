package com.ool.ilist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class SingerItemView extends LinearLayout {

    TextView dateView;
    TextView toDoView;

    // Generate > Constructor

    public SingerItemView(Context context) {
        super(context);
        init(context);
    }

    public SingerItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    // singer_item.xml을 inflation
    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singer_item, this, true);

        dateView = (TextView) findViewById(R.id.dateView);
        toDoView = (TextView) findViewById(R.id.toDoView);
    }

    public void setDate(String date) {
        dateView.setText(date);
    }

    public void setToDo(String toDo) {
        toDoView.setText(toDo);
    }


}