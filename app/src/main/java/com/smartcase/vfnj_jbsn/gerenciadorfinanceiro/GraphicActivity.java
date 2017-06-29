package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class GraphicActivity extends Fragment {

    public GraphicActivity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grafic_fragment_main, container, false);

 LineChart chart = (LineChart) view.findViewById(R.id.chart);
    List<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(10, 3));
                entries.add(new Entry(14, 9));
                entries.add(new Entry(7, 10));
                entries.add(new Entry(1, 2));
                entries.add(new Entry(5, 8));
                entries.add(new Entry(9, 5));
                Collections.sort(entries, new EntryXComparator());
                LineDataSet dataSet = new LineDataSet(entries, "Label");
                LineData lineData = new LineData(dataSet);
                chart.setData(lineData);
                chart.invalidate();

        return view;



    }
}
