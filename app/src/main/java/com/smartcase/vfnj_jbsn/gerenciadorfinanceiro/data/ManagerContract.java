package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Dinho-PC on 11/06/2017.
 */

public class ManagerContract {

    public static final String CONTENT_AUTHORITY = "com.smartcase.vfnj_jbsn.gerenciadorfinanceiro";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_ENTRY = "entry";
    public static final String PATH_ENTRY_DATE = PATH_ENTRY+"/date";
    public static final String PATH_ENTRY_ID = PATH_ENTRY+"/id";
    public static final class FinanceEntry implements BaseColumns {


   public static final Uri CONTENT_URI =
               BASE_CONTENT_URI.buildUpon().build();

        public static final String CONTENT_TYPE =
                           ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ENTRY;
        public static final String CONTENT_ITEM_TYPE =
                            ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ENTRY;

        public static Uri buildEntryUri(long id) {
                   return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildEntryWithDate(String date) {
                        return CONTENT_URI.buildUpon().appendPath(PATH_ENTRY).appendPath("date") .appendPath(date).build();
        }

        public static Uri buildEntryWithID(int id) {
            return CONTENT_URI.buildUpon().appendPath(PATH_ENTRY).appendPath("id").appendPath(Integer.toString(id)).build();
        }
        public static long getIDFromUri(Uri uri) {
            return Long.parseLong(uri.getPathSegments().get(2));
        }





        public static String getDataEntryFromUri(Uri uri) {
            Log.i("Uri Segment", uri.getPathSegments().get(2));
            return uri.getPathSegments().get(2);
        }
        public static String getIDEntryFromUri(Uri uri) {
            Log.i("Uri Segment", uri.getPathSegments().get(2));
            return uri.getPathSegments().get(2);
        }

        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_ENTRY_VALUE = "entry_value";
        public static final String COLUMN_ENTRY_DATA = "entry_data";
        public static final String COLUMN_ENTRY_DESCRIPTION = "entry_description";
        public static final String COLUMN_ENTRY_CATEGORY = "entry_category";
    }
}
