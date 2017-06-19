package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.MyApplication;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.models.FinanceEntry;

import java.util.Date;

import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.MyApplication.getAppContext;

/**
 * Created by Dinho-PC on 11/06/2017.
 */

@RequiresApi(api = Build.VERSION_CODES.N)

public class ManagerDbUtils {
    // Insert the new row, returning the primary key value of the new row

    //Query pela data
    private static final String sDateDaySettingSelection =
            ManagerContract.FinanceEntry.TABLE_NAME+
                    "." + ManagerContract.FinanceEntry.COLUMN_ENTRY_DATA + " = ? ";


    public static Cursor getEntryByDateDay(Uri uri, String[] projection, String sortOrder) {

        ManagerDbHelper managerDbHelper = new ManagerDbHelper(MyApplication.getAppContext());
        SQLiteDatabase db = managerDbHelper.getWritableDatabase();


        String dateDaySetting = ManagerContract.FinanceEntry.getDataEntryFromUri(uri);
        Log.i("ManagerUQuery by Date",dateDaySetting);
                //long startDate = WeatherContract.WeatherEntry.getStartDateFromUri(uri);

        String[] selectionArgs;
        String selection;
        selection = ManagerContract.FinanceEntry.COLUMN_ENTRY_DATA + " = ? ";
        selectionArgs = new String[]{dateDaySetting};


        projection = new String []{
                ManagerContract.FinanceEntry._ID,
                ManagerContract.FinanceEntry.COLUMN_ENTRY_VALUE,
                ManagerContract.FinanceEntry.COLUMN_ENTRY_DATA,
                ManagerContract.FinanceEntry.COLUMN_ENTRY_DESCRIPTION,
                ManagerContract.FinanceEntry.COLUMN_ENTRY_CATEGORY
        };

        Cursor c = db.query(
                ManagerContract.FinanceEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        return c;
    }




    public static ContentValues writeEntry (FinanceEntry financeEntry) {
        //ManagerDbHelper managerDbHelper = new ManagerDbHelper(MyApplication.getAppContext());
        //SQLiteDatabase sqLiteDatabase = managerDbHelper.getWritableDatabase();
        Log.i("Entry Data", financeEntry.toStringiest());
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        //String dateBrazilNormalized=convertDate(new Date());
        values.put(ManagerContract.FinanceEntry.COLUMN_ENTRY_VALUE, financeEntry.getValueEntry());
        values.put(ManagerContract.FinanceEntry.COLUMN_ENTRY_DATA, financeEntry.getDataEntry());
        values.put(ManagerContract.FinanceEntry.COLUMN_ENTRY_DESCRIPTION, financeEntry.getDescriptionEntry());
        values.put(ManagerContract.FinanceEntry.COLUMN_ENTRY_CATEGORY, financeEntry.getCategoryEntry());

        return values;
        //return sqLiteDatabase.insert(ManagerContract.FinanceEntry.TABLE_NAME, null, values);
    }

    public static Cursor selectAllEntry (){
        ManagerDbHelper managerDbHelper = new ManagerDbHelper(MyApplication.getAppContext());
        SQLiteDatabase sqLiteDatabase = managerDbHelper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                ManagerContract.FinanceEntry._ID,
                ManagerContract.FinanceEntry.COLUMN_ENTRY_VALUE,
                ManagerContract.FinanceEntry.COLUMN_ENTRY_DATA,
                ManagerContract.FinanceEntry.COLUMN_ENTRY_DESCRIPTION,
                ManagerContract.FinanceEntry.COLUMN_ENTRY_CATEGORY
        };


        Cursor c = sqLiteDatabase.query(
                ManagerContract.FinanceEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        return c;
    }
    public static String convertDate (Date date) {

        TimeZone tz = TimeZone.getTimeZone("Brazil/East");
        SimpleDateFormat destFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        destFormat.setTimeZone(tz);
        String dateString = destFormat.format(date);
        return dateString;
    }
}
