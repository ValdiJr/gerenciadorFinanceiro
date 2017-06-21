package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro;

import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.EntryXComparator;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerContract;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.utils.FinanceEntryAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.MyApplication.getAppContext;
import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerContract.FinanceEntry.COLUMN_ENTRY_CATEGORY;
import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerContract.FinanceEntry.COLUMN_ENTRY_DATA;

/**
 * A placeholder fragment containing a simple view.
 */
public class DayGraficActivity extends Fragment {

    public DayGraficActivity() {
    }
    private FinanceEntryAdapter financeEntryAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);


        Uri financeEntryWithdate = ManagerContract.FinanceEntry.buildEntryWithDate("2017-06-11");
        Cursor cursor = getAppContext().getContentResolver().query(financeEntryWithdate,null, null, null, null);
        financeEntryAdapter = new FinanceEntryAdapter(getAppContext(),cursor,0);
        ListView listView = (ListView) view.findViewById(R.id.listview_forecast);
        listView.setAdapter(financeEntryAdapter);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String data = cursor.getString(cursor.getColumnIndex(COLUMN_ENTRY_DATA));
                data = data + " " + cursor.getString(cursor.getColumnIndex(COLUMN_ENTRY_CATEGORY));
                Log.i("banco de dados", "" + data);
                cursor.moveToNext();
            }
        }



        return view;



    }
}
