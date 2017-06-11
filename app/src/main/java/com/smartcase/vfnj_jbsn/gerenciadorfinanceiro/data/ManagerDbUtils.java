package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
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
    // Insert the new row, returning the primary key value of the new row

    public String convertDate (Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(date);
        return dateString;
    }
}
