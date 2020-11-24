package com.example.mipt_pd4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

import static com.example.mipt_pd4.Constants.info_formatted;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> listNoteItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView viewerNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.viewerNotes = findViewById(R.id.viewerNotes);
        this.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.listNoteItems);
        this.viewerNotes.setAdapter(adapter);
        Log.i(info_formatted,"MainActivity created");
    }
    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(this);
        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-NOTES DISPLAYER=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        this.adapter.clear();
        Map<String, ?> allEntries = SP.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet())
        {
            this.listNoteItems.add(entry.getKey() + " : " + entry.getValue().toString());
        }
        this.adapter.notifyDataSetChanged();
        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-NOTES DISPLAYER END=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        Log.i(info_formatted,"MainActivity started");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i(info_formatted,"menu created");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notes_dropdown_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(info_formatted,"menu opened");
        switch (item.getItemId()){
            case R.id.add_note:
                Intent add = new Intent(this, AddNoteActivity.class);
                startActivity(add);
                return true;
            case R.id.del_note:
                if (this.listNoteItems.isEmpty()){
                    Toast.makeText(getApplicationContext(),"There is nothing to delete. ¯\\_(ツ)_/¯ ", Toast.LENGTH_SHORT).show();
                    return false;
                }
                else {
                    Intent del = new Intent(this, DeleteNoteActivity.class);
                    startActivity(del);
                    return true;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}