package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}

//Teste com UriMatchers

//    FinanceEntry entry = new FinanceEntry();
//
//        entry.setValueEntry((long) 3.4);
//                entry.setCategoryEntry("Casa");
//                entry.setDataEntry(ManagerDbUtils.convertDate(new Date()));
//                entry.setDescriptionEntry("Conta de água");
//                ContentValues values = ManagerDbUtils.writeEntry(entry);
//                Uri financeEntryWriteUri = ManagerContract.FinanceEntry.CONTENT_URI;
//                Uri uriResponde = getAppContext().getContentResolver().insert(financeEntryWriteUri,values);
//
//                Log.i("Banco de DADOS", "ID: "+ uriResponde );


//    FinanceEntry entry = new FinanceEntry();
//        entry.setValueEntry((long) 3.4);
//                entry.setCategoryEntry("Casa");
//                entry.setDataEntry(ManagerDbUtils.convertDate(new Date()));
//                entry.setDescriptionEntry("Conta de água");
//                long result = ManagerDbUtils.writeEntry(entry);
//
//                Log.i("banco de dados",""+result);

//    FinanceEntry entry = new FinanceEntry();
//        entry.setValueEntry((long) 3.4);
//                entry.setCategoryEntry("Casa");
//                entry.setDataEntry(ManagerDbUtils.convertDate(new Date()));
//                entry.setDescriptionEntry("Conta de água");
//                long result = ManagerDbUtils.writeEntry(entry);
//                result = ManagerDbUtils.writeEntry(entry);
//                result = ManagerDbUtils.writeEntry(entry);
//                result = ManagerDbUtils.writeEntry(entry);
//                result = ManagerDbUtils.writeEntry(entry);
//                result = ManagerDbUtils.writeEntry(entry);
//                result = ManagerDbUtils.writeEntry(entry);
//                result = ManagerDbUtils.writeEntry(entry);
//                result = ManagerDbUtils.writeEntry(entry);
//
//                Cursor cursor = ManagerDbUtils.selectAllEntry();
//                if (cursor.moveToFirst()) {
//                while (!cursor.isAfterLast()) {
//                String data = cursor.getString(cursor.getColumnIndex(COLUMN_ENTRY_DATA));
//                Log.i("banco de dados",""+data);
//                cursor.moveToNext();
//                }
//                }
