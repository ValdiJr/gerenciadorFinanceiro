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
public class EntryDetailActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    static final String ENTRYDETAIL_URI = "URI";
    private Uri mUri;

    public EntryDetailActivityFragment() {

    }


    private static final int loader_id_edit=2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getLoaderManager().initLoader(loader_id_edit, null, this);



        Bundle arguments = getArguments();
        if (arguments != null) {
            Log.i("URI Fragment" ,"Arguments Founded!");
            mUri = arguments.getParcelable(EntryDetailActivityFragment.ENTRYDETAIL_URI);
        }


        return inflater.inflate(R.layout.entry_detail_fragment, container, false);




    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {


        if ( null != mUri ) {
            Log.i("URI from Latest" ,""+mUri);

            Uri financeEntryWithID = mUri;
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
