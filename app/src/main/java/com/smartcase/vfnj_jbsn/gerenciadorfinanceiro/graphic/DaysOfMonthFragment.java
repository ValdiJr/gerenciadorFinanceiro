package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.graphic;


import android.database.Cursor;
import android.graphics.Color;
import android.hardware.camera2.params.ColorSpaceTransform;
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

import java.util.ArrayList;

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

        mChart.getDescription().setEnabled(false);

        mChart.setMaxVisibleValueCount(40);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(true);

        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);

        mChart.setDrawValueAboveBar(false);
        mChart.setHighlightFullBarEnabled(false);

        // change the position of the y-labels
        YAxis leftAxis = mChart.getAxisLeft();
        //leftAxis.setValueFormatter(MonthGraphicActivityFragment.MyValueFormatter);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        mChart.getAxisRight().setEnabled(false);

        XAxis xLabels = mChart.getXAxis();
        xLabels.setPosition(XAxis.XAxisPosition.TOP);
        mChart.animateXY(1400, 1400);

        // mChart.setDrawXLabels(false);
        // mChart.setDrawYLabels(false);


        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);








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
        String dia;

        if (cursor.moveToFirst()) {
            dia = cursor.getString(2);
            float val1=0;
            float val2=0;
            float val3=0;

            int i = 1;
            while (!cursor.isAfterLast()) {

                String data="";

                data = data +" "+ DummyData.round(cursor.getDouble(0),2);
                data = data + " " +cursor.getString(2);
                data = data + " " +cursor.getString(1);

                Log.i("data", data);




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
                    yVals1.add(new BarEntry(i, new float[]{val1, val2, val3}));
                    val1=0;
                    val2=0;
                    val3=0;
                    dia=cursor.getString(2);
                    i++;
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

            }
//




        }


        BarDataSet set1;
        set1 = new BarDataSet(yVals1, "Custos do Mês");
        set1.setDrawIcons(false);
        set1.setColors(getColors());
        set1.setStackLabels(new String[]{"Casa", "Lazer", "Trabalho"});

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(dataSets);
        data.setValueFormatter( new MonthGraphicActivityFragment.MyValueFormatter());
        data.setValueTextColor(Color.WHITE);

        mChart.setData(data);
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
