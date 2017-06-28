package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    // Pega o FragmentManager
    FragmentManager fm = getSupportFragmentManager();
    // Abre uma transação e adiciona

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//                FinanceEntry entry = new FinanceEntry();
//
//                entry.setValueEntry(7.4);
//                entry.setCategoryEntry("Casa");
//                entry.setDataEntry(ManagerDbUtils.convertDate(new Date()));
//                entry.setDescriptionEntry("Feijão");
//                ContentValues values = ManagerDbUtils.writeEntry(entry);
//                Uri financeEntryWriteUri = ManagerContract.FinanceEntry.buildNewEntry();
//                Uri uriResponde = getAppContext().getContentResolver().insert(financeEntryWriteUri,values);
//
//                Log.i("Banco de DADOS", "ID: "+ uriResponde );





//        FragmentTransaction ft = fm.beginTransaction();
//        ft.add(R.id.fragment_content, new LastestsEntryActivity());
//        ft.commit();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), AddEntryActivity.class);
                startActivity(intent);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            FragmentTransaction ft = fm.beginTransaction();
//            ft.remove(getSupportFragmentManager().findFragmentById(R.id.fragment_forecast)).commit();
//            ft = fm.beginTransaction();
//            ft.replace(R.id.fragment_forecast, new GraficActivity());
//            ft.addToBackStack(null);
//            ft.commit();
//            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
