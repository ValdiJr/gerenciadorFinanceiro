package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.MyApplication;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.models.Entry;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerContract.FinanceEntry;

import java.util.Date;

/**
 * Created by Dinho-PC on 11/06/2017.
 */

@RequiresApi(api = Build.VERSION_CODES.N)

public class ManagerDbUtils {
    // Insert the new row, returning the primary key value of the new row
    public static long writeEntry (Entry entry) {
        ManagerDbHelper managerDbHelper = new ManagerDbHelper(MyApplication.getAppContext());
        SQLiteDatabase sqLiteDatabase = managerDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FinanceEntry.COLUMN_ENTRY_VALUE, entry.getValueEntry());
        values.put(FinanceEntry.COLUMN_ENTRY_DATA, entry.getDataEntry());
        values.put(FinanceEntry.COLUMN_ENTRY_DESCRIPTION, entry.getDescriptionEntry());
        values.put(FinanceEntry.COLUMN_ENTRY_CATEGORY, entry.getCategoryEntry());

        return sqLiteDatabase.insert(FinanceEntry.TABLE_NAME, null, values);
    }

    public static Cursor selectAllEntry (){
        ManagerDbHelper managerDbHelper = new ManagerDbHelper(MyApplication.getAppContext());
        SQLiteDatabase sqLiteDatabase = managerDbHelper.getWritableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                FinanceEntry._ID,
                FinanceEntry.COLUMN_ENTRY_VALUE,
                FinanceEntry.COLUMN_ENTRY_DATA,
                FinanceEntry.COLUMN_ENTRY_DESCRIPTION,
                FinanceEntry.COLUMN_ENTRY_CATEGORY
        };

// Filter results WHERE "title" = 'My Title'
//        String selection = FeedEntry.COLUMN_NAME_TITLE + " = ?";
//        String[] selectionArgs = { "My Title" };

// How you want the results sorted in the resulting Cursor
//        String sortOrder =
//                FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor c = sqLiteDatabase.query(
                FinanceEntry.TABLE_NAME,                     // The table to query
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
