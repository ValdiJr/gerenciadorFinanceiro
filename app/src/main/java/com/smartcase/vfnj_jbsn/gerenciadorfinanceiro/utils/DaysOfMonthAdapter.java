package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.utils;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.R;

/**
 * Created by Dinho-PC on 21/06/2017.
 */

public class DaysOfMonthAdapter extends CursorAdapter {


    public static class ViewHolder {
//        public final ImageView iconView;
//        public final TextView dataEntryView;
//        public final TextView descriptionEntryView;
//        public final TextView valueEntryView;
        public final TextView dayView;

        public ViewHolder(View view) {
//            iconView = (ImageView) view.findViewById(R.id.list_item_icon);
//            dataEntryView = (TextView) view.findViewById(R.id.list_item_detail);
//            descriptionEntryView = (TextView) view.findViewById(R.id.list_item_entryfinance_textview);
//            valueEntryView = (TextView) view.findViewById(R.id.list_item_high_textview);
            dayView = (TextView) view.findViewById(R.id.list_item_days_of_month_textview);
        }
    }
    public DaysOfMonthAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {


        View view = LayoutInflater.from(context).inflate(R.layout.days_of_month_view_entry, parent, false);



        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder = (ViewHolder) view.getTag();

//        //ImageView iconView = (ImageView) view.findViewById(R.id.list_item_icon);
//        viewHolder.iconView.setImageResource(R.drawable.default_icon);
//
//        TextView valueEntryView = (TextView) view.findViewById(R.id.list_item_high_textview);
//        viewHolder.valueEntryView.setText(cursor.getDouble(1)+"");
//
//        TextView dataEntryView = (TextView) view.findViewById(R.id.list_item_detail);
//        viewHolder.dataEntryView.setText(cursor.getString(2));
//
//        TextView descriptionEntryView = (TextView) view.findViewById(R.id.list_item_entryfinance_textview);
//        viewHolder.descriptionEntryView.setText(cursor.getString(3));

 //       TextView categoryEntryView = (TextView) view.findViewById(R.id.list_item_month_textview);



                viewHolder.dayView.setText(String.valueOf(cursor.getString(0)));


    }
}
