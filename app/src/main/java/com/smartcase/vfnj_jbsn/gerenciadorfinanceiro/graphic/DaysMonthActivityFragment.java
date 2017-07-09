package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.graphic;

import android.database.Cursor;
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
import android.widget.AdapterView;
import android.widget.ListView;

import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.R;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerContract;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerDbUtils;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.utils.DaysOfMonthAdapter;

import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.MyApplication.getAppContext;

/**
 * A placeholder fragment containing a simple view.
 */
public class DaysMonthActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>  {

    public DaysMonthActivityFragment() {
    }
    private ListView listViewMonth;
    private DaysOfMonthAdapter monthAdapter;
    private static final int loader_id=2;

    static final String DaysOfMonth = "URI";
    private Uri mUri;

    public interface Callback {
        /**
         * DetailFragmentCallback for when an item has been selected.
         */
        public void onItemSelected(Uri dateUri);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_days_month, container, false);


        getLoaderManager().initLoader(loader_id, null, this);

        Bundle arguments = getArguments();
        if (arguments != null) {
            Log.i("URI Fragment" ,"Arguments Founded!");
            mUri = arguments.getParcelable(DaysOfMonth);
            Log.i("URI Fragment" ,"Arguments Founded! "+mUri);

        }

        monthAdapter = new DaysOfMonthAdapter(getContext(),null,0);


        listViewMonth = (ListView) viewRoot.findViewById(R.id.listview_daysofmonth);
        listViewMonth .setAdapter(monthAdapter);

        listViewMonth.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView adapterView, View view, int position, long l) {
                // CursorAdapter returns a cursor at the correct position for getItem(), or null
                // if it cannot seek to that position.
                Cursor c = (Cursor) adapterView.getItemAtPosition(position);


                if (c != null) {
                    Log.i("banco de dados", "" + position);
                     Uri financeEntryWithdate = ManagerContract.FinanceEntry.buildEntryWithDate(c.getString(0));;


                    Bundle args = new Bundle();
                    args.putParcelable(DaysEntriesPastFragment.DaysEntriesPast, financeEntryWithdate);

                    DaysEntriesPastFragment fragment = new DaysEntriesPastFragment ();
                    fragment.setArguments(args);

                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.days_of_month_container, fragment, DaysEntriesPastFragment.DaysEntriesPast_TAG)
                            .commit();




                }

            }
        });

        return viewRoot;

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        if ( null != mUri ) {


            return new CursorLoader(getAppContext(),mUri,null,null,null,null);


        } else {


            Log.i("URI intent", "Not Itent found");
            return null;
        }
        //Uri financeEntryAll = ManagerContract.FinanceEntry.buildSelectAllMonthsDistict();
        //String order = ManagerContract.FinanceEntry._ID + " DESC";

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                String data = String.valueOf(cursor.getString(0));



          Log.i("banco de dados", "" + data);
          cursor.moveToNext();
          }
          }
         monthAdapter.swapCursor(cursor);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
