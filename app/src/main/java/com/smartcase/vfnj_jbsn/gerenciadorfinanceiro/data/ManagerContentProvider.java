package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.MyApplication;

/**
 * Created by Dinho-PC on 15/06/2017.
 */

public class ManagerContentProvider extends ContentProvider {

    static final int ENTRY_WITH_DATE = 101;
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
       // sURIMatcher.addURI("contacts", "people", PEOPLE);
        matcher.addURI(ManagerContract.CONTENT_AUTHORITY, "date/*", ENTRY_WITH_DATE);

        return matcher;
    }


      //location.location_setting = ?
     private static final String sDateDaySettingSelection =
               ManagerContract.FinanceEntry.TABLE_NAME+
                                       "." + ManagerContract.FinanceEntry.COLUMN_ENTRY_DATA + " = ? ";

    private Cursor getEntryByDateDay(Uri uri, String[] projection, String sortOrder) {

        ManagerDbHelper managerDbHelper = new ManagerDbHelper(MyApplication.getAppContext());
        SQLiteDatabase sqLiteDatabase = managerDbHelper.getReadableDatabase();


        String dateDaySetting = ManagerContract.FinanceEntry.getDataEntryFromUri(uri);

        //long startDate = WeatherContract.WeatherEntry.getStartDateFromUri(uri);

        String[] selectionArgs;
        String selection;
        selection = sDateDaySettingSelection;
        selectionArgs = new String[]{dateDaySetting};


        Cursor c = sqLiteDatabase.query(
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

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {


        final int match = sUriMatcher.match(uri);
//
        switch (match) {
//            // Student: Uncomment and fill out these two cases
////            case WEATHER_WITH_LOCATION_AND_DATE:
////            case WEATHER_WITH_LOCATION:
            case ENTRY_WITH_DATE:
                return ManagerContract.FinanceEntry.CONTENT_TYPE;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
//

    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
