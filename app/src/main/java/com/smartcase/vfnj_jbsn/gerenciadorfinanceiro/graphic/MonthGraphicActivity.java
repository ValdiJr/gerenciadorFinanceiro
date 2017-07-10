package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.graphic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.R;

public class MonthGraphicActivity extends AppCompatActivity {

    public static final String DAYFRAGMENT_TAG = "DFTAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.month_graphic_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {

            // Create the detail fragment and add it to the activity
            // using a fragment transaction.

            Bundle arguments = new Bundle();
            arguments.putParcelable(MonthGraphicActivityFragment.MONTH_URI, getIntent().getData());

            MonthGraphicActivityFragment fragment = new MonthGraphicActivityFragment();
            fragment.setArguments(arguments);




            getSupportFragmentManager().beginTransaction()
                    .add(R.id.graphic_container, fragment)
                    .commit();
        }




        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
