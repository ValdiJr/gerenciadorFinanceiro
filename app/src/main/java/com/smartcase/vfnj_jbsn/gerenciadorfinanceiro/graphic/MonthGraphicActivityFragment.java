package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.graphic;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.R;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.DummyData;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerContentProvider;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerContract;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.MyApplication.getAppContext;
import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerContract.FinanceEntry.buildDayOfyMonth;

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

        }



        Button editarButton = (Button) rootView.findViewById(R.id.buttonGraphicViewDays);
        editarButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Bundle args = new Bundle();
                args.putParcelable(DaysOfMonthFragment.DAYS_MONTH_URI, mUri);

                DaysOfMonthFragment fragment = new DaysOfMonthFragment ();
                fragment.setArguments(args);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.graphic_container, fragment, MonthGraphicActivity.DAYFRAGMENT_TAG)
                        .commit();


            }});

        final Button daysButton = (Button) rootView.findViewById(R.id.buttonViewDays);
        daysButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String month = ManagerContract.FinanceEntry.getMonthFromUri(mUri);
                Uri daysOfMonth = buildDayOfyMonth(month);
                Intent intent = new Intent(getAppContext(), DaysMonthActivity.class).setData(daysOfMonth);
                startActivity(intent);

//                Log.i("Days of Month",daysOfMonth+"");


//          Cursor cursor = getAppContext().getContentResolver().query(daysOfMonth,null, null, null, null);
//          //Log.i("Cursor value: ",""+cursor.toString());
//
//          if (cursor.moveToFirst()) {
//                while (!cursor.isAfterLast()) {
//                String data = String.valueOf(cursor.getString(0));
//
//
//
//          Log.i("banco de dados", "" + data);
//          cursor.moveToNext();
//          }
//          }


//                Bundle args = new Bundle();
//                args.putParcelable(DaysOfMonthFragment.DAYS_MONTH_URI, mUri);
//
//                DaysOfMonthFragment fragment = new DaysOfMonthFragment ();
//                fragment.setArguments(args);
//
//                getActivity().getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.graphic_container, fragment, MonthGraphicActivity.DAYFRAGMENT_TAG)
//                        .commit();
//

            }});


        pieChart = (PieChart) rootView.findViewById(R.id.piechart);
        return rootView;
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {


        if ( null != mUri ) {

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
                entries.add(new PieEntry((float)soma, cursor.getString(1)));

                cursor.moveToNext();
            }
        }

        PieDataSet set = new PieDataSet(entries, "Despesas");
        set.setValueTextSize(18f);

        PieData data = new PieData(set);
        data.setValueFormatter(new MyValueFormatter());
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


    public static class MyValueFormatter implements IValueFormatter {



        private DecimalFormat mFormat;

        public MyValueFormatter() {
            mFormat = new DecimalFormat("###,###,##0.00"); // use one decimal
        }


        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            return  "R$ " + mFormat.format(value) ; // e.g. append a dollar-sign
        }
    }
}
