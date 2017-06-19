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

import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.MyApplication.getAppContext;
import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerContract.PATH_ENTRY;

/**
 * Created by Dinho-PC on 15/06/2017.
 */

public class ManagerContentProvider extends ContentProvider {


    //private ManagerDbHelper mOpenHelper = new ManagerDbHelper(getAppContext());
    static final int ENTRY_WITH_DATE = 101;
    static final int ENTRY = 100;
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
       // sURIMatcher.addURI("contacts", "people", PEOPLE);
        matcher.addURI(ManagerContract.CONTENT_AUTHORITY, PATH_ENTRY + "date/*", ENTRY_WITH_DATE);
        matcher.addURI(ManagerContract.CONTENT_AUTHORITY, PATH_ENTRY, ENTRY);
        return matcher;
    }


      //location.location_setting = ?
     private static final String sDateDaySettingSelection =
               ManagerContract.FinanceEntry.TABLE_NAME+
                                       "." + ManagerContract.FinanceEntry.COLUMN_ENTRY_DATA + " = ? ";

    private Cursor getEntryByDateDay(Uri uri, String[] projection, String sortOrder) {

        ManagerDbHelper managerDbHelper = new ManagerDbHelper(getAppContext());
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
        Cursor retCursor;
               switch (sUriMatcher.match(uri)) {

//            // Student: Uncomment and fill out these two cases
////            case WEATHER_WITH_LOCATION_AND_DATE:
////            case WEATHER_WITH_LOCATION:
                       case ENTRY_WITH_DATE: {
                           retCursor = this.getEntryByDateDay(uri, projection, sortOrder);
                           break;
                       }
                       default:
                           throw new UnsupportedOperationException("Unknown uri: " + uri);

                   }
        retCursor.setNotificationUri(getContext().getContentResolver(),uri);
        return retCursor;
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
        ManagerDbHelper managerDbHelper = new ManagerDbHelper(MyApplication.getAppContext());
        SQLiteDatabase db = managerDbHelper.getWritableDatabase();
                final int match = sUriMatcher.match(uri);
                Uri returnUri;

                        switch (match) {
                        case ENTRY: {
                                //Normalizar data de entrada
                                //normalizeDate(values);

                                long _id = db.insert(ManagerContract.FinanceEntry.TABLE_NAME, null, values);
                                if ( _id > 0 )
                                        returnUri = ManagerContract.FinanceEntry.buildEntryUri(_id);
                                else
                                    throw new android.database.SQLException("Failed to insert row into " + uri);
                                break;
                            }
                        default:
                                throw new UnsupportedOperationException("Unknown uri: " + uri);
                        }
                getContext().getContentResolver().notifyChange(uri, null);
                return returnUri;




    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        ManagerDbHelper managerDbHelper = new ManagerDbHelper(MyApplication.getAppContext());
        SQLiteDatabase db = managerDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);

        int rowsDeleted;
        switch (match) {
            case ENTRY: {
               rowsDeleted = db.delete(ManagerContract.FinanceEntry.TABLE_NAME, selection, selectionArgs);

                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if ( rowsDeleted !=0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        ManagerDbHelper managerDbHelper = new ManagerDbHelper(MyApplication.getAppContext());
        SQLiteDatabase db = managerDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);

        int rowsUpdated;
        switch (match) {
            case ENTRY: {
                rowsUpdated = db.update(ManagerContract.FinanceEntry.TABLE_NAME, values, selection, selectionArgs);

                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if ( rowsUpdated !=0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }
}
