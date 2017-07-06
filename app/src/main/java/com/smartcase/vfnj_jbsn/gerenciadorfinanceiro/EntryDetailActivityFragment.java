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
import android.widget.TextView;

import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.MyApplication.getAppContext;

/**
 * A placeholder fragment containing a simple view.
 */
public class EntryDetailActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    static final String ENTRYDETAIL_URI = "URI";
    private Uri mUri;

    TextView tvDescription;
    TextView tvValue;
    TextView tvCategory;
    TextView tvDate;
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

        View view = inflater.inflate(R.layout.entry_detail_fragment, container, false);

        tvDescription = (TextView) view.findViewById(R.id.viewEntryDescription);
        tvValue = (TextView) view.findViewById(R.id.viewEntryValue);
        tvDate = (TextView) view.findViewById(R.id.viewEntryData);
        tvCategory = (TextView) view.findViewById(R.id.viewEntryCategory);

        return view;




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
        String data="";
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                //data = String.valueOf(cursor.getInt(0));
                tvValue.setText(String.valueOf(cursor.getDouble(1)));
                tvDate.setText(String.valueOf(cursor.getString(2)));
                tvDescription.setText(String.valueOf(cursor.getString(3)));
                tvCategory.setText(String.valueOf(cursor.getString(4)));

                cursor.moveToNext();
            }
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
