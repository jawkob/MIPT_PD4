package com.example.mipt_pd4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.Map;

import static com.example.mipt_pd4.Constants.info_formatted;

public class DeleteNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(this);
        Map<String, ?> allEntries = SP.getAll();
        String keys[] = allEntries.keySet().toArray(new String[0]);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, keys);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Log.i(info_formatted,"DeleteNoteActivity started");
    }

    public void onBtnClickDelete(View view) {
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(this);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String spinnerSelection = spinner.getSelectedItem().toString();
        SP.edit().remove(spinnerSelection).commit();
        Log.i(info_formatted,"Delete button activated, deleted: "+spinnerSelection);
        finish();
    }
}