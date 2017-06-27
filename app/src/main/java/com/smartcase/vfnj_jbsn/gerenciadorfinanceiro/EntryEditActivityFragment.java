package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro;

import android.content.Intent;
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

import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.MyApplication.getAppContext;

/**
 * A placeholder fragment containing a simple view.
 */
public class EntryEditActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {



    public EntryEditActivityFragment() {

    }
    private static final int loader_id_edit=2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getLoaderManager().initLoader(loader_id_edit, null, this);

        return inflater.inflate(R.layout.fragment_entry_edit, container, false);




    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            Log.i("URI intent" ,intent.getDataString());

            Uri financeEntryWithID = intent.getData();
            return new CursorLoader(getAppContext(),financeEntryWithID,null,null,null,null);
        }
        Log.i("URI intent" ,"Not Itent found");
        return null;

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String data = String.valueOf(cursor.getInt(0));
                data = data +"\n"+String.valueOf(cursor.getLong(1));
                data = data +"\n"+String.valueOf(cursor.getString(2));
                data = data +"\n"+String.valueOf(cursor.getString(3));
                data = data +"\n"+String.valueOf(cursor.getString(4));


                Log.i("banco de dados", "" + data);
                cursor.moveToNext();
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
