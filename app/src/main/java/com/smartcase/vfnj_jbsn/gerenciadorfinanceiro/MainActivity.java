package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerContract;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerDbUtils;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.models.FinanceEntry;

import java.util.Date;

import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.MyApplication.getAppContext;

public class MainActivity extends AppCompatActivity {

    // Pega o FragmentManager
    FragmentManager fm = getSupportFragmentManager();
    // Abre uma transação e adiciona
    boolean mTwoPane;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (findViewById(R.id.entry_detail_container) != null) {
            Log.i("Detect double panel","Tem dois paineis" );
            // The detail container view will be present only in the large-screen layouts
            // (res/layout-sw600dp). If this view is present, then the activity should be
            // in two-pane mode.
            mTwoPane = true;
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            if (savedInstanceState == null) {
                fm.beginTransaction()
                        .replace(R.id.entry_detail_container, new GraficActivity())
                        .commit();
            }
        } else {
            mTwoPane = false;
        }







//        FragmentTransaction ft = fm.beginTransaction();
//        ft.add(R.id.fragment_content, new LatestsEntryFragment());
//        ft.commit();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplication(), AddEntryActivity.class);
//                startActivity(intent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                FinanceEntry entry = new FinanceEntry();

                entry.setValueEntry(9.70);
                entry.setCategoryEntry("Lanche");
                entry.setDataEntry(ManagerDbUtils.convertDate(new Date()));
                entry.setDescriptionEntry("Coxinha");
                ContentValues values = ManagerDbUtils.writeEntry(entry);
                Uri financeEntryWriteUri = ManagerContract.FinanceEntry.buildNewEntry();
                Uri uriResponde = getAppContext().getContentResolver().insert(financeEntryWriteUri,values);

                Log.i("Banco de DADOS", "ID: "+ uriResponde );

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
