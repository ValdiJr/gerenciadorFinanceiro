package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerContract;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerDbUtils;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.models.FinanceEntry;

import java.util.Date;

import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.MyApplication.getAppContext;

/**
 * A placeholder fragment containing a simple view.
 */
public class DaysEditPastFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    static final String ENTRYEDIT_URI = "URI";
    private Uri mUri;

    private int entryID;
    EditText etDescription;
    EditText etValue;
    TextView tvData;

    public DaysEditPastFragment() {

    }


    private static final int loader_id_edit=3;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getLoaderManager().initLoader(loader_id_edit, null, this);



        Bundle arguments = getArguments();
        if (arguments != null) {
            Log.i("URI Fragment" ,"Arguments Founded!");
            mUri = arguments.getParcelable(EntryEditActivityFragment.ENTRYEDIT_URI);
        }

        View view = inflater.inflate(R.layout.entry_edit_fragment, container, false);

        // Captura data atual
        TextView dateTitleTextView = (TextView)  view.findViewById(R.id.dateDetailTextView);


        // date value
        tvData = (TextView) view.findViewById(R.id.dateDetailValueTextView);
        // description value
        etDescription = (EditText) view.findViewById(R.id.descriptionDetailEditText);
        // entry value
        etValue = (EditText) view.findViewById(R.id.valueDetailEditText);

        //spinner categories
        final Spinner spinner = (Spinner) view.findViewById(R.id.spinnerDetailCategory);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        // Botão adicionar
        Button okButton = (Button) view.findViewById(R.id.buttonOk);
        okButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FinanceEntry financeEntry = new FinanceEntry();
                financeEntry.setDataEntry(String.valueOf(tvData.getText()));
                financeEntry.setDescriptionEntry(String.valueOf(etDescription.getText()));
                financeEntry.setValueEntry(Double.parseDouble((String.valueOf(etValue.getText())).replace(",",".")));
                financeEntry.setCategoryEntry(spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString());
                ContentValues values = ManagerDbUtils.writeEntry(financeEntry);
                Uri financeEntryWriteUri = ManagerContract.FinanceEntry.buildNewEntry();
                String selection = ManagerContract.FinanceEntry._ID+ " = ? ";
                getAppContext().getContentResolver().update(financeEntryWriteUri, values,selection,new String[]{String.valueOf(entryID)});

                Bundle args = new Bundle();
                args.putParcelable(DaysPastDetailActivityFragment.ENTRYDETAIL_URI, mUri);

                DaysPastDetailActivityFragment fragment = new DaysPastDetailActivityFragment();
                fragment.setArguments(args);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.entry_detail_container, fragment)
                        .commit();
            }
        });



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
                entryID= cursor.getInt(0);
                etValue.setText(String.valueOf(cursor.getDouble(1)));
                tvData.setText(String.valueOf(cursor.getString(2)));
                etDescription.setText(String.valueOf(cursor.getString(3)));
                //e.setText(String.valueOf(cursor.getString(4)));

                cursor.moveToNext();
            }
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
