package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerContract.FinanceEntry;
/**
 * Created by Dinho-PC on 11/06/2017.
 */

public class ManagerDbHelper extends SQLiteOpenHelper {


    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "FinanceManager.db";

    public ManagerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_FINANCE_ENTRY_TABLE = "CREATE TABLE " + FinanceEntry.TABLE_NAME + " (" +

                FinanceEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FinanceEntry.COLUMN_ENTRY_VALUE + " REAL NOT NULL, " +
                FinanceEntry.COLUMN_ENTRY_DATA + " TEXT NOT NULL, " +
                FinanceEntry.COLUMN_ENTRY_DESCRIPTION + " TEXT NOT NULL, " +
                FinanceEntry.COLUMN_ENTRY_CATEGORY + " TEXT NOT NULL " +
                ");";
        sqLiteDatabase.execSQL(SQL_CREATE_FINANCE_ENTRY_TABLE);
//
//                // Set up the location column as a foreign key to location table.
//                " FOREIGN KEY (" + WeatherEntry.COLUMN_LOC_KEY + ") REFERENCES " +
//                LocationEntry.TABLE_NAME + " (" + LocationEntry._ID + "), " +
//
//                // To assure the application have just one weather entry per day
//                // per location, it's created a UNIQUE constraint with REPLACE strategy
//                " UNIQUE (" + WeatherEntry.COLUMN_DATE + ", " +
//                WeatherEntry.COLUMN_LOC_KEY + ") ON CONFLICT REPLACE);";
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FinanceEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
