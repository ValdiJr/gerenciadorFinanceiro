package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.data.ManagerDbUtils;
import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.models.Entry;

import java.util.Date;

public class AddEntryActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_entry_activity);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        // Captura data atual
        TextView dateTitleTextView = (TextView)  findViewById(R.id.dateTextView);
        dateTitleTextView.setText(ManagerDbUtils.convertDate(new Date()));

        // date value
        final TextView dateValueTextView = (TextView) findViewById(R.id.dateValueTextView);
        // description value
        final EditText descriptionEditText = (EditText) findViewById(R.id.descriptionEditText);
        // entry value
        final EditText entryValueEditText = (EditText) findViewById(R.id.valueEditText);
        //spinner categories
        final Spinner spinner = (Spinner) findViewById(R.id.spinnerCategory);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // Botão adicionar
        Button addButton = (Button) findViewById(R.id.buttonAdicionar);
        addButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Entry entry = new Entry();
                entry.setDataEntry(ManagerDbUtils.convertDate(new Date()));
                entry.setDescriptionEntry(String.valueOf(descriptionEditText.getText()));
                entry.setValueEntry(Double.parseDouble((String.valueOf(entryValueEditText.getText()))));
                entry.setCategoryEntry(spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString());

            }
        });





    }
}
