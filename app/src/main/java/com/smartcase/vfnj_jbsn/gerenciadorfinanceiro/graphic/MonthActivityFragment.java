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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.R;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.month_fragment, container, false);

//        String[] data = {
//                "Mon 6/23 - Sunny - 31/17",
//                "Tue 6/24 - Foggy - 21/8",
//                "Wed 6/25 - Cloudy - 22/17",
//                "Thurs 6/26 - Rainy - 18/11",
//                "Fri 6/27 - Foggy - 21/10",
//                "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
//                "Sun 6/29 - Sunny - 20/7"
//        };
//        List<String> weekForecast = new ArrayList<String>(Arrays.asList(data));


        getLoaderManager().initLoader(loader_id, null, this);

        monthAdapter = new MonthAdapter(getAppContext(),null,0);

//        mMonthAdapter =
//                new ArrayAdapter<String>(
//                        getActivity(), // The current context (this activity)
//                        R.layout.month_view_entry, // The name of the layout ID.
//                        R.id.list_item_month_textview, // The ID of the textview to populate.
//                        weekForecast);

        listViewMonth = (ListView) viewRoot.findViewById(R.id.listview_month);
        listViewMonth .setAdapter(monthAdapter);
        return viewRoot;




    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        Uri financeEntryAll = ManagerContract.FinanceEntry.buildSelectAllMonthsDistict();
        String order = ManagerContract.FinanceEntry._ID + " DESC";
        return new CursorLoader(getAppContext(),financeEntryAll,null,null,null,order);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {


        monthAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
