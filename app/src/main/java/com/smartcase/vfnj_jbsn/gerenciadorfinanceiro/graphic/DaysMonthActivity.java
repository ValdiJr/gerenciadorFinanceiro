package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.graphic;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.R;

public class DaysMonthActivity extends AppCompatActivity implements DaysMonthActivityFragment.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.days_month_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        if (savedInstanceState == null) {

            // Create the detail fragment and add it to the activity
            // using a fragment transaction.

            Bundle arguments = new Bundle();
            arguments.putParcelable(DaysMonthActivityFragment.DaysOfMonth, getIntent().getData());

            DaysMonthActivityFragment fragment = new DaysMonthActivityFragment();
            fragment.setArguments(arguments);




            getSupportFragmentManager().beginTransaction()
                    .add(R.id.days_of_month_container, fragment)
                    .commit();
        }




        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onItemSelected(Uri dateUri) {

    }
}
