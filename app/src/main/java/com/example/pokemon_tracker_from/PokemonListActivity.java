package com.example.pokemon_tracker_from;

import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class PokemonListActivity extends AppCompatActivity {
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); //returns to MainActivity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.databaselist);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        Cursor cursor = getContentResolver().query(
                PokemonDBProvider.CONTENT_URI,
                new String[] { "_id", "Name", "Species" }, // include _id!
                null, null, null
        );

        String[] from = new String[] {
                "Name",   // line 1
                "Species" // line 2
        };


        int[] to = new int[] {
                android.R.id.text1,
                android.R.id.text2
        };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2,
                cursor,
                from,
                to,
                0
        );
        ListView list = findViewById(R.id.list);
        list.setAdapter(adapter);
    }
}

