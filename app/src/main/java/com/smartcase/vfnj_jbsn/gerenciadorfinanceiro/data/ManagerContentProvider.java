package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.MyApplication;

import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.MyApplication.getAppContext;
import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerContract.PATH_ENTRY;
import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerContract.PATH_ENTRY_DATE;
import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerContract.PATH_ENTRY_ID;

/**
 * Created by Dinho-PC on 15/06/2017.
 */

public class ManagerContentProvider extends ContentProvider {


    //private ManagerDbHelper mOpenHelper = new ManagerDbHelper(getAppContext());
    static final int ENTRY_WITH_DATE = 101;
    static final int ENTRY = 100;
    static final int ENTRY_WITH_ID = 102;
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
       // sURIMatcher.addURI("contacts", "people", PEOPLE);
        matcher.addURI(ManagerContract.CONTENT_AUTHORITY, PATH_ENTRY_DATE+"/*", ENTRY_WITH_DATE);
        matcher.addURI(ManagerContract.CONTENT_AUTHORITY, PATH_ENTRY, ENTRY);
        matcher.addURI(ManagerContract.CONTENT_AUTHORITY, PATH_ENTRY_ID+"/#", ENTRY_WITH_ID);
        return matcher;
    }


      //location.location_setting = ?


    @Override
    public boolean onCreate() {
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor retCursor;
               switch (sUriMatcher.match(uri)) {

                       case ENTRY_WITH_DATE: {
                           Log.i("ENTRY_WITH_DATE", String.valueOf(uri) +" "+sUriMatcher.match(uri));
                           retCursor = ManagerDbUtils.getEntryByDateDay(uri, projection, sortOrder);
                           break;
                       }
                   case ENTRY_WITH_ID: {
                       Log.i("ENTRY_WITH_ID", String.valueOf(uri) +" "+sUriMatcher.match(uri));
                       retCursor = ManagerDbUtils.getEntryByID(uri, projection, sortOrder);
                       break;
                   }
                       default:
                           Log.i("ENTRY_WITH_DATE", String.valueOf(uri) +" "+sUriMatcher.match(uri));
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
