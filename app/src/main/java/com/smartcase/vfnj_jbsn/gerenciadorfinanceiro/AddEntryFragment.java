package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.EntryXComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddEntryFragment extends Fragment {

    public AddEntryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
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
