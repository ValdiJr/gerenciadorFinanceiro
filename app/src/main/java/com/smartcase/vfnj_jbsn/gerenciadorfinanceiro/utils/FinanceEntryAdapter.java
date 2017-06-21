package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.utils;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.R;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerContract;

import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerContract.FinanceEntry.COLUMN_ENTRY_CATEGORY;

/**
 * Created by Dinho-PC on 21/06/2017.
 */

public class FinanceEntryAdapter extends CursorAdapter {

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
        TextView tv = (TextView)view;
                tv.setText(cursor.getString(cursor.getColumnIndex(COLUMN_ENTRY_CATEGORY)));
    }
}
