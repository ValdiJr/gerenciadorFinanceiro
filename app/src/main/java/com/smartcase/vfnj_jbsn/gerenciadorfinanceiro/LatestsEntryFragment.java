package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.content.CursorLoader;

import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerContract;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerDbUtils;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.models.FinanceEntry;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.utils.FinanceEntryAdapter;

import java.util.Date;

import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.MyApplication.getAppContext;


/**
 * A placeholder fragment containing a simple view.
 */
public class LatestsEntryFragment extends Fragment implements  LoaderManager.LoaderCallbacks<Cursor>{

    static int LATEST_ENTRIES_URI;

    public LatestsEntryFragment() {
    }

    public interface Callback {
        /**
         * DetailFragmentCallback for when an item has been selected.
         */
        public void onItemSelected(Uri dateUri);
    }

    @Override
    public void onResume(){
        super.onResume();


    }



    private ListView listView;
    private FinanceEntryAdapter financeEntryAdapter;
    private static final int loader_id=1;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lastests_entry_fragment_main, container, false);

        getLoaderManager().initLoader(loader_id, null, this);


        financeEntryAdapter = new FinanceEntryAdapter(getAppContext(),null,0);
        listView = (ListView) view.findViewById(R.id.listview_forecast);
        listView.setAdapter(financeEntryAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView adapterView, View view, int position, long l) {
                // CursorAdapter returns a cursor at the correct position for getItem(), or null
                // if it cannot seek to that position.
                Cursor c = (Cursor) adapterView.getItemAtPosition(position);
                LATEST_ENTRIES_URI=position;

                if (c != null) {
                Log.i("banco de dados", "" + position);
                Uri financeEntryWithID = ManagerContract.FinanceEntry.buildEntryWithID(c.getInt(0));


                ((Callback) getActivity()).onItemSelected( financeEntryWithID );



                }

            }
        });


        return view;



    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putInt("Position", LATEST_ENTRIES_URI);


        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Always call the superclass first

        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            LATEST_ENTRIES_URI = savedInstanceState.getInt("Position");

        } else {
            LATEST_ENTRIES_URI=0;
        }

    }




    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        Uri financeEntryWithdate = ManagerContract.FinanceEntry.buildEntryWithDate(ManagerDbUtils.convertDate(new Date()));
       // Uri financeEntryWithdate = ManagerContract.FinanceEntry.buildEntryAll();
        String order = ManagerContract.FinanceEntry._ID + " DESC";
        return new CursorLoader(getAppContext(),financeEntryWithdate,null,null,null,order);
    }



    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        financeEntryAdapter.swapCursor(data);
        // Check whether we're recreating a previously destroyed instance

            // Restore value of members from saved state
        if(LATEST_ENTRIES_URI!=0) {
            listView.smoothScrollToPosition(LATEST_ENTRIES_URI);
        }




    }

    @Override
    public void onLoaderReset(Loader loader) {
        financeEntryAdapter.swapCursor(null);
    }
}
