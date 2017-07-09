package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.graphic;


import android.database.Cursor;
import android.graphics.Color;
import android.hardware.camera2.params.ColorSpaceTransform;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.R;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.DummyData;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerDbUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.MyApplication.getAppContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaysOfMonthFragment extends Fragment  implements LoaderManager.LoaderCallbacks<Cursor>{

    static final String DAYS_MONTH_URI = "URI";
    public DaysOfMonthFragment() {
        // Required empty public constructor
    }

    private Uri mUri;
    private BarChart mChart;

    private static final int loader_id_edit=5;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewRoot = inflater.inflate(R.layout.days_of_month_fragment, container, false);


        getLoaderManager().initLoader(loader_id_edit, null, this);
        Bundle arguments = getArguments();
        if (arguments != null) {
            Log.i("URI Fragment" ,"Arguments Founded!");
            mUri = arguments.getParcelable(DAYS_MONTH_URI);
            Log.i("URI Fragment" ,""+arguments);

        }
        mChart = (BarChart) viewRoot.findViewById(R.id.chart1);
       // mChart.setOnChartValueSelectedListener(this);

        mChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(40);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(true);

        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);

        mChart.setDrawValueAboveBar(false);
        mChart.setHighlightFullBarEnabled(false);
        mChart.setDoubleTapToZoomEnabled(false);

        // change the position of the y-labels
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setValueFormatter(new MyAxisValueFormatter());
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        mChart.getAxisRight().setEnabled(false);

        XAxis xLabels = mChart.getXAxis();
        xLabels.setPosition(XAxis.XAxisPosition.TOP);

        // mChart.setDrawXLabels(false);
        // mChart.setDrawYLabels(false);

        // setting data
//        mSeekBarX.setProgress(12);
//        mSeekBarY.setProgress(100);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);

        // mChart.setDrawLegend(false);





        return viewRoot;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        if ( null != mUri ) {
            Log.i("URI from Latest", "" + mUri);

            mUri = Uri.parse(mUri+"/days");

            return new CursorLoader(getAppContext(),mUri,null,null,null,null);


        }

        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {



        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        Map<Integer, BarEntry> mapStringBar = new HashMap<Integer, BarEntry>();
        String dia;
//
        if (cursor.moveToFirst()) {
            dia = cursor.getString(2);
            //Log.i ("Dia do Mês","Dia: "+Integer.parseInt(cursor.getString(2).substring(8,cursor.getString(2).length())));
            float val1=0;
            float val2=0;
            float val3=0;

            int i = Integer.parseInt(cursor.getString(2).substring(8,cursor.getString(2).length()));
//
            while (!cursor.isAfterLast()) {
////
//
                if (dia.equals(cursor.getString(2))){
                    switch (cursor.getString(1)){
                        case "Casa":
                            val1=(float) DummyData.round(cursor.getDouble(0),2);
                            break;
                        case "Lazer":
                            val2=(float) DummyData.round(cursor.getDouble(0),2);
                            break;
                        case "Trabalho":
                            val3=(float) DummyData.round(cursor.getDouble(0),2);
                            break;
                        default:
                            Log.i("switch case", "nenhuma das opçẽs");
                            break;
                   }
                }else {
//
                    mapStringBar.put(i,(new BarEntry(i, new float[]{val1, val2, val3})));

                    val1=0;
                    val2=0;
                    val3=0;
                    dia=cursor.getString(2);
                    Log.i ("Dia do Mês","Dia: "+i);
                    i=Integer.parseInt(cursor.getString(2).substring(8,cursor.getString(2).length()));
                    switch (cursor.getString(1)){
                        case "Casa":
                            val1=(float) DummyData.round(cursor.getDouble(0),2);
                            break;
                        case "Lazer":
                            val2=(float) DummyData.round(cursor.getDouble(0),2);
                            break;
                        case "Trabalho":
                            val3=(float) DummyData.round(cursor.getDouble(0),2);
                            break;
                        default:
                            Log.i("switch case", "nenhuma das opçẽs");
                            break;
                    }

                }

                cursor.moveToNext();
//
            }
//
            mapStringBar.put(i,(new BarEntry(i, new float[]{val1, val2, val3})));
//            Log.i ("Dia do Mês","Dia: "+i);
//
//
////
//
//
//
//
        }

        for (Map.Entry<Integer,BarEntry> pair : mapStringBar.entrySet()) {
            System.out.println(pair.getKey());
            System.out.println(pair.getValue());

            yVals1.add(pair.getValue());
        }






//        for (int i = 0; i < 31; i++) {
//            float mult = 31;
//            float val1 = (float) (Math.random() * mult) + mult / 3;
//            float val2 = (float) (Math.random() * mult) + mult / 3;
//            float val3 = (float) (Math.random() * mult) + mult / 3;




        BarDataSet set1;
        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "Custos do Mês");
            set1.setDrawIcons(false);
            set1.setColors(getColors());
            set1.setStackLabels(new String[]{"Casa", "Lazer", "Trabalho"});

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);

            data.setValueFormatter(new MonthGraphicActivityFragment.MyValueFormatter());
            data.setValueTextColor(Color.WHITE);

            mChart.setData(data);
        }
        mChart.setFitBars(true);
        mChart.invalidate();




    }
    private int[] getColors() {

        int stacksize = 3;

        // have as many colors as stack-values per entry
        int[] colors = new int[stacksize];

        for (int i = 0; i < colors.length; i++) {
            colors[i] = ColorTemplate.MATERIAL_COLORS[i];
        }

        return colors;
    }
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
class MyAxisValueFormatter implements IAxisValueFormatter
{

    private DecimalFormat mFormat;

    public MyAxisValueFormatter() {
        mFormat = new DecimalFormat("###,###,###,##0.0");
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mFormat.format(value);
    }
}