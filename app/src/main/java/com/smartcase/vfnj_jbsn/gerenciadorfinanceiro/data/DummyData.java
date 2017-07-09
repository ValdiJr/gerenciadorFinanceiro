package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.models.FinanceEntry;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.MyApplication.getAppContext;

/**
 * Created by dinho on 05/07/17.
 */

public class DummyData {

    private static FinanceEntry entry = new FinanceEntry();
    private static int i;
    private static int j;
    private static String category;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void  generateDummyEntries() {
        for (j = 4; j <= 6; j++)

        {
            for (i = 0; i <= 500; i++) {
                int randomNum = ThreadLocalRandom.current().nextInt(1, 31 + 1);
                double randomDouble = ThreadLocalRandom.current().nextDouble(0.1, 2.0);
                randomDouble = round(randomDouble, 2);
                entry.setValueEntry(randomDouble);
                int randomCategory = ThreadLocalRandom.current().nextInt(1, 3 + 1);
                switch (randomCategory) {
                    case 1:
                        category="Casa";
                        break;
                    case 2:
                        category="Trabalho";
                        break;
                    case 3:
                        category="Lazer";
                        break;
                    default:
                        category="Casa";
                        break;


                }

                entry.setCategoryEntry(category);
                //  entry.setDataEntry(ManagerDbUtilsEntries.convertDate(new Date()));
                entry.setDataEntry("2017-0" + String.valueOf(j) + "-" + String.valueOf(randomNum));
                entry.setDescriptionEntry("Conta água");
                ContentValues values = ManagerDbUtils.writeEntry(entry);
                Uri financeEntryWriteUri = ManagerContract.FinanceEntry.buildNewEntry();
                Uri uriResponde = getAppContext().getContentResolver().insert(financeEntryWriteUri, values);
                //Log.i("Entries fake", entry.toStringiest() + " "+ entry.getDataEntry().substring(0,7));
                Log.i("Banco de DADOS", "ID: " + uriResponde);
            }
        }
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    }

