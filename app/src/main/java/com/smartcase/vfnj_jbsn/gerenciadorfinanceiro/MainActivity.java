package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerContract;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerDbUtils;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.models.FinanceEntry;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.sync.ManagerFinanceSyncAdapter;

import java.util.Date;

import static com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.MyApplication.getAppContext;

public class MainActivity extends AppCompatActivity implements LatestsEntryFragment.Callback{

    private static final String ENTRYDETAILFRAGMENT_TAG = "EDFTAG";
    // Pega o FragmentManager
    FragmentManager fm = getSupportFragmentManager();
    // Abre uma transação e adiciona

    boolean mTwoPane;


    @Override
    protected void onResume() {
        super.onResume();
        EntryDetailActivityFragment edf = (EntryDetailActivityFragment) getSupportFragmentManager().findFragmentByTag(ENTRYDETAILFRAGMENT_TAG);

    }


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
                        .replace(R.id.entry_detail_container, new EntryDetailActivityFragment(),
                ENTRYDETAILFRAGMENT_TAG)
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

            ManagerFinanceSyncAdapter.syncImmediately(this);
//            Intent alarmIntent = new Intent(this, FinanceService.AlarmReceiver.class);
//            alarmIntent.putExtra(FinanceService.FINANCE_QUERY_EXTRA,"https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22YHOO%22)&" +
//                           "format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=" );
//
//            //Wrap in a pending intent which only fires once.
//            PendingIntent pi = PendingIntent.getBroadcast(this, 0,alarmIntent,PendingIntent.FLAG_ONE_SHOT);//getBroadcast(context, 0, i, 0);
//
//            AlarmManager am=(AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
//
//            //Set the AlarmManager to wake up the system.
//            am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, pi);

//            Intent intent = new Intent(this, FinanceService.class);
//            intent.putExtra(FinanceService.FINANCE_QUERY_EXTRA,
//                    "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22YHOO%22)&" +
//                            "format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=");
//            this.startService(intent);
//            FragmentTransaction ft = fm.beginTransaction();
//            ft.remove(getSupportFragmentManager().findFragmentById(R.id.fragment_forecast)).commit();
//            ft = fm.beginTransaction();
//            ft.replace(R.id.fragment_forecast, new GraphicActivity());
//            ft.addToBackStack(null);
//            ft.commit();
//            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(Uri contentUri) {
        Log.i("onitemSelected on Main" ,"" + contentUri);
        if (mTwoPane) {
                        // In two-pane mode, show the detail view in this activity by
                               // adding or replacing the detail fragment using a
                                        // fragment transaction.
            Bundle args = new Bundle();
            args.putParcelable(EntryDetailActivityFragment.ENTRYDETAIL_URI, contentUri);

            EntryDetailActivityFragment fragment = new EntryDetailActivityFragment();
                        fragment.setArguments(args);

                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.entry_detail_container, fragment, ENTRYDETAILFRAGMENT_TAG)
                                        .commit();
                    } else {
                        Log.i("One Panel Else: " ,"" + contentUri);
                        Intent intent = new Intent(this, EntryDetailActivity.class).setData(contentUri);
                        startActivity(intent);
                    }
    }
}
