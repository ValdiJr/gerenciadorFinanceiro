package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.graphic;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.R;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerContract;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.utils.FinanceEntryAdapter;

import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.MyApplication.getAppContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaysEntriesPastFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    static final String DaysEntriesPast = "URI";
    static final String DaysEntriesPast_TAG = "PastEntriesTag";
    private ListView listViewEntries;
    private FinanceEntryAdapter financeEntryAdapter;
    private static final int loader_id=6;

    static final String DaysOfMonth = "URI";
    private Uri mUri;


    public DaysEntriesPastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewRoot = inflater.inflate(R.layout.lastests_entry_fragment_main, container, false);

        getLoaderManager().initLoader(loader_id, null, this);

        Bundle arguments = getArguments();
        if (arguments != null) {
            Log.i("URI Fragment" ,"Arguments Founded!");
            mUri = arguments.getParcelable(DaysOfMonth);
            Log.i("URI Fragment" ,"Arguments Founded! "+mUri);

        }

        financeEntryAdapter = new FinanceEntryAdapter(getAppContext(),null,0);

       listViewEntries = (ListView) viewRoot.findViewById(R.id.listview_forecast);
       listViewEntries.setAdapter(financeEntryAdapter);



        return viewRoot;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        // Uri financeEntryWithdate = ManagerContract.FinanceEntry.buildEntryAll();



        if ( null != mUri ) {


            String order = ManagerContract.FinanceEntry._ID + " DESC";
            return new CursorLoader(getAppContext(),mUri,null,null,null,order);


        } else {


            Log.i("URI intent", "Not Itent found");
            return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        financeEntryAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

}