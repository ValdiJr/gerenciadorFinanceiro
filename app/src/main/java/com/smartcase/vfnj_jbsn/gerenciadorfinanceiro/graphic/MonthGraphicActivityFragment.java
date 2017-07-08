package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.graphic;

import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.EntryEditActivityFragment;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.R;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.DummyData;

import java.util.ArrayList;
import java.util.List;

import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.MyApplication.getAppContext;

/**
 * A placeholder fragment containing a simple view.
 */
public class MonthGraphicActivityFragment extends Fragment  implements LoaderManager.LoaderCallbacks<Cursor> {

    static final String MONTH_URI = "URI";
    private Uri mUri;
    List<PieEntry> entries;
    PieChart pieChart;

    private static final int loader_id_edit=4;
    public MonthGraphicActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.month_graphic_fragment, container, false);
        getLoaderManager().initLoader(loader_id_edit, null, this);

        Bundle arguments = getArguments();
        if (arguments != null) {
            Log.i("URI Fragment" ,"Arguments Founded!");
            mUri = arguments.getParcelable(MonthGraphicActivityFragment.MONTH_URI);
            Log.i ("URI MONTH", ""+mUri);
        }

         pieChart = (PieChart) rootView.findViewById(R.id.piechart);



        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        if ( null != mUri ) {
            Log.i("URI from Latest" ,""+mUri);

            Uri financeEntryWithID = mUri;
            return new CursorLoader(getAppContext(),financeEntryWithID,null,null,null,null);

        } else {


            Log.i("URI intent", "Not Itent found");
            return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {


        if (cursor.moveToFirst()) {
            entries = new ArrayList<>();
            while (!cursor.isAfterLast()) {

                double soma = DummyData.round(cursor.getDouble(0),2);
                Log.i("valor double", ""+soma);
                Log.i("valor double", ""+(float)soma);
                entries.add(new PieEntry((float)soma, cursor.getString(1)));

//                Double soma = DummyData.round(cursor.getDouble(0),2);
//                String data = String.valueOf(soma);
//                data = data + "\n" + String.valueOf(cursor.getString(1));



               // Log.i("A GROUP", "" + data);
                cursor.moveToNext();
            }
        }

        PieDataSet set = new PieDataSet(entries, "Despesas");
        set.setValueTextSize(18f);

        PieData data = new PieData(set);
        pieChart.animateXY(1400, 1400);
        //data.setValueTextSize(18f);
        Legend l = pieChart.getLegend();
        l.setTextSize(10f);
        pieChart.setEntryLabelTextSize(18f);
        pieChart.setEntryLabelColor(Color.WHITE);
        set.setValueTextColor(Color.WHITE);
        pieChart.setData(data);

        set.setColors(ColorTemplate.VORDIPLOM_COLORS);

        pieChart.invalidate();

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
