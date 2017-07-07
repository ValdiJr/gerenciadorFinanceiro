package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.graphic;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.R;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.DummyData;

import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerDbUtils.selectAllSumCategoriesMonths;

public class MonthActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.month_activity);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        Cursor cursor= selectAllSumCategoriesMonths();
        if (cursor.moveToFirst()) {
              while (!cursor.isAfterLast()) {


                  Double soma = DummyData.round(cursor.getDouble(0),2);
                  String data = String.valueOf(soma);
                  data = data + "\n" + String.valueOf(cursor.getString(1));



                  Log.i("A GROUP", "" + data);
                  cursor.moveToNext();
              }
          }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
