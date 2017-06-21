package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro;

import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.content.CursorLoader;

import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerContract;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.utils.FinanceEntryAdapter;

import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.MyApplication.getAppContext;
import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerContract.FinanceEntry.COLUMN_ENTRY_CATEGORY;
import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerContract.FinanceEntry.COLUMN_ENTRY_DATA;

/**
 * A placeholder fragment containing a simple view.
 */
public class LastEntryActivity extends Fragment implements  LoaderManager.LoaderCallbacks<Cursor>{

    public LastEntryActivity() {
    }
    private FinanceEntryAdapter financeEntryAdapter;
    private static final int loader_id=1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.last_entry_fragment_main, container, false);

        getLoaderManager().initLoader(loader_id, null, this);

        financeEntryAdapter = new FinanceEntryAdapter(getAppContext(),null,0);
        ListView listView = (ListView) view.findViewById(R.id.listview_forecast);
        listView.setAdapter(financeEntryAdapter);


        return view;



    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri financeEntryWithdate = ManagerContract.FinanceEntry.buildEntryWithDate("2017-06-11");
        return new CursorLoader(getAppContext(),financeEntryWithdate,null,null,null,null);
    }



    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        financeEntryAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader loader) {
        financeEntryAdapter.swapCursor(null);
    }
}
