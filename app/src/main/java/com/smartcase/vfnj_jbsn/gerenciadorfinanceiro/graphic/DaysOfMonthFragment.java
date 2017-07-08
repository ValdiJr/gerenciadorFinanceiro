package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.graphic;


import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.R;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.DummyData;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerDbUtils;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaysOfMonthFragment extends Fragment  implements LoaderManager.LoaderCallbacks<Cursor>{

    static final String DAYS_MONTH_URI = "URI";
    public DaysOfMonthFragment() {
        // Required empty public constructor
    }

    private Uri mUri;;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewRoot = inflater.inflate(R.layout.days_of_month_fragment, container, false);

        Bundle arguments = getArguments();
        if (arguments != null) {
            Log.i("URI Fragment" ,"Arguments Founded!");
            mUri = arguments.getParcelable(DAYS_MONTH_URI);
            Log.i("URI Fragment" ,""+arguments);

        }


        return viewRoot;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        if ( null != mUri ) {
            Log.i("URI from Latest", "" + mUri);

            Cursor cursor2 = ManagerDbUtils.selectAllSumCategoriesByDaysofMonth(mUri, null, null);

        }

        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
