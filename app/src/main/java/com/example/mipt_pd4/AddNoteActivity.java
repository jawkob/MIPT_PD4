package com.example.mipt_pd4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.mipt_pd4.Constants.info_formatted;

public class AddNoteActivity extends AppCompatActivity {

    EditText edContent;
    EditText edName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        this.edContent = findViewById(R.id.edtxtContent);
        this.edName = findViewById(R.id.edtxtName);
        Log.i(info_formatted,"AddNoteActivity started");
    }

    public void onBtnClickSave(View view) {
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = SP.edit();
        if (this.edName.getText().toString().length() == 0 || this.edContent.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(),"Would you be so kind to fill all fields before pressing the button? Thanks.", Toast.LENGTH_SHORT).show();
        }
        else {
            editor.putString(this.edName.getText().toString(), this.edContent.getText().toString());
            editor.apply();
            Log.i(info_formatted,"Save button activated, saved: " + this.edName.getText().toString());
            finish();
        }

    }

}