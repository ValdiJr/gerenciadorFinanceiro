package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.graphic;

import android.database.Cursor;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.R;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.DummyData;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerContract;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerDbUtils;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.utils.MonthAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.MyApplication.getAppContext;

/**
 * A placeholder fragment containing a simple view.
 */
public class MonthActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {


    ArrayAdapter<String> mMonthAdapter;
    public MonthActivityFragment() {
    }

    private ListView listViewMonth;
    private MonthAdapter monthAdapter;
    private static final int loader_id=2;

    public interface Callback {
        /**
         * DetailFragmentCallback for when an item has been selected.
         */
        public void onItemSelected(Uri dateUri);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.month_fragment, container, false);




        getLoaderManager().initLoader(loader_id, null, this);

        monthAdapter = new MonthAdapter(getAppContext(),null,0);


        listViewMonth = (ListView) viewRoot.findViewById(R.id.listview_month);
        listViewMonth .setAdapter(monthAdapter);

        listViewMonth.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView adapterView, View view, int position, long l) {
                // CursorAdapter returns a cursor at the correct position for getItem(), or null
                // if it cannot seek to that position.
                Cursor c = (Cursor) adapterView.getItemAtPosition(position);


                if (c != null) {
                    Log.i("banco de dados", "" + position);
                    Uri month_value_by_category = ManagerContract.FinanceEntry.buildSumCategoriesByMonth(c.getString(5));


                    ((Callback) getActivity()).onItemSelected( month_value_by_category );



                }

            }
        });




        return viewRoot;




    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        //Uri financeEntryWithdate = ManagerContract.FinanceEntry.buildSumCategoriesByMonth("2017-04");
        // Uri financeEntryWithdate = ManagerContract.FinanceEntry.buildEntryAll();

        //eturn new CursorLoader(getAppContext(),financeEntryWithdate,null,null,null,null);




        Uri financeEntryAll = ManagerContract.FinanceEntry.buildSelectAllMonthsDistict();
        String order = ManagerContract.FinanceEntry._ID + " DESC";
        return new CursorLoader(getAppContext(),financeEntryAll,null,null,null,order);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {


        monthAdapter.swapCursor(cursor);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
