package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
//public class ExampleUnitTest {
//    @Test
//    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);
//    }
//}

//Graficos
// LineChart chart = (LineChart) view.findViewById(R.id.chart);
//    List<Entry> entries = new ArrayList<Entry>();
//
//        entries.add(new Entry(10, 3));
//                entries.add(new Entry(14, 9));
//                entries.add(new Entry(7, 10));
//                entries.add(new Entry(1, 2));
//                entries.add(new Entry(5, 8));
//                entries.add(new Entry(9, 5));
//                Collections.sort(entries, new EntryXComparator());
//                LineDataSet dataSet = new LineDataSet(entries, "Label");
//                LineData lineData = new LineData(dataSet);
//                chart.setData(lineData);
//                chart.invalidate();


//Teste com UriMatchers
//
//    Uri financeEntryWithdate = ManagerContract.FinanceEntry.buildEntryWithDate("2017-06-11");
//    Cursor cursor = getAppContext().getContentResolver().query(financeEntryWithdate,null, null, null, null);
//
//        if (cursor.moveToFirst()) {
//                while (!cursor.isAfterLast()) {
//                String data = cursor.getString(cursor.getColumnIndex(COLUMN_ENTRY_DATA));
//                data = data + " " + cursor.getString(cursor.getColumnIndex(COLUMN_ENTRY_CATEGORY));
//                Log.i("banco de dados", "" + data);
//                cursor.moveToNext();
//                }
//                }



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
