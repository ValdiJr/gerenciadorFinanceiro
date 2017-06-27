package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.utils;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.R;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerContract;

import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerContract.FinanceEntry.COLUMN_ENTRY_CATEGORY;

/**
 * Created by Dinho-PC on 21/06/2017.
 */

public class FinanceEntryAdapter extends CursorAdapter {

    public static class ViewHolder {
        public final ImageView iconView;
        public final TextView dateView;
        public final TextView descriptionView;
        public final TextView highTempView;
        public final TextView lowTempView;

        public ViewHolder(View view) {
            iconView = (ImageView) view.findViewById(R.id.list_item_icon);
            dateView = (TextView) view.findViewById(R.id.list_item_detail);
            descriptionView = (TextView) view.findViewById(R.id.list_item_entryfinance_textview);
            highTempView = (TextView) view.findViewById(R.id.list_item_high_textview);
            lowTempView = (TextView) view.findViewById(R.id.list_item_low_textview);
        }
    }
    public FinanceEntryAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_view_financeentry, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
//        TextView tv = (TextView)view;
//                tv.setText(cursor.getString(2));
        ImageView iconView = (ImageView) view.findViewById(R.id.list_item_icon);
        iconView.setImageResource(R.drawable.default_icon);

        TextView valueEntryView = (TextView) view.findViewById(R.id.list_item_high_textview);
        valueEntryView.setText(cursor.getDouble(1)+"");

        TextView dataEntryView = (TextView) view.findViewById(R.id.list_item_detail);
        dataEntryView.setText(cursor.getString(2));

        TextView descriptionEntryView = (TextView) view.findViewById(R.id.list_item_entryfinance_textview);
        descriptionEntryView.setText(cursor.getString(3));

        TextView categoryEntryView = (TextView) view.findViewById(R.id.list_item_low_textview);
        categoryEntryView.setText(cursor.getString(4));



    }
}
