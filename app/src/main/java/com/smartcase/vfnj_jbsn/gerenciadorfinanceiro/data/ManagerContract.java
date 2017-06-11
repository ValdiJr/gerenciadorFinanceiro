package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data;

import android.provider.BaseColumns;

/**
 * Created by Dinho-PC on 11/06/2017.
 */

public class ManagerContract {

    public static final class FinanceEntry implements BaseColumns {

        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_ENTRY_VALUE = "entry_value";
        public static final String COLUMN_ENTRY_DATA = "entry_data";
        public static final String COLUMN_ENTRY_DESCRIPTION = "entry_description";
        public static final String COLUMN_ENTRY_CATEGORY = "entry_category";
    }
}
