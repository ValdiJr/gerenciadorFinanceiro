package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class EntryDetailActivity extends AppCompatActivity {

    public static final String ENTRYEDITFRAGMENT_TAG = "EEFTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_detail_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (savedInstanceState == null) {

                        // Create the detail fragment and add it to the activity
                                // using a fragment transaction.

                                                Bundle arguments = new Bundle();
                        arguments.putParcelable(EntryDetailActivityFragment.ENTRYDETAIL_URI, getIntent().getData());

            EntryDetailActivityFragment fragment = new EntryDetailActivityFragment();
                        fragment.setArguments(arguments);




            getSupportFragmentManager().beginTransaction()
                    .add(R.id.entry_detail_container, fragment)
                    .commit();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
