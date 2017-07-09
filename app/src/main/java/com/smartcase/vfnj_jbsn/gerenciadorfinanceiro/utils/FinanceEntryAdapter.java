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
        public final TextView dataEntryView;
        public final TextView descriptionEntryView;
        public final TextView valueEntryView;
        public final TextView categoryEntryView;

        public ViewHolder(View view) {
            iconView = (ImageView) view.findViewById(R.id.list_item_icon);
            dataEntryView = (TextView) view.findViewById(R.id.list_item_detail);
            descriptionEntryView = (TextView) view.findViewById(R.id.list_item_entryfinance_textview);
            valueEntryView = (TextView) view.findViewById(R.id.list_item_high_textview);
            categoryEntryView = (TextView) view.findViewById(R.id.list_item_low_textview);
        }
    }
    public FinanceEntryAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_view_financeentry, parent, false);



        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder = (ViewHolder) view.getTag();

        //ImageView iconView = (ImageView) view.findViewById(R.id.list_item_icon);
        viewHolder.iconView.setImageResource(R.drawable.default_icon);

        TextView valueEntryView = (TextView) view.findViewById(R.id.list_item_high_textview);
        viewHolder.valueEntryView.setText("R$ "+cursor.getDouble(1)+"");

        TextView dataEntryView = (TextView) view.findViewById(R.id.list_item_detail);
        viewHolder.dataEntryView.setText(cursor.getString(2));

        TextView descriptionEntryView = (TextView) view.findViewById(R.id.list_item_entryfinance_textview);
        viewHolder.descriptionEntryView.setText(cursor.getString(3));

        TextView categoryEntryView = (TextView) view.findViewById(R.id.list_item_low_textview);
        viewHolder.categoryEntryView.setText(cursor.getString(4));
    }
}
