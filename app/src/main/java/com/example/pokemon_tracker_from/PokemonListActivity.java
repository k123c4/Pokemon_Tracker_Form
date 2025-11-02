package com.example.pokemon_tracker_from;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

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
    private SimpleCursorAdapter adapter;
    private ListView list;
    private void refresh() {
        Cursor c = getContentResolver().query(
                PokemonDBProvider.CONTENT_URI,
                new String[]{"_id", "Name", "Species"},
                null, null, "_id DESC"
        );
        Cursor old = adapter.getCursor();
        adapter.changeCursor(c);
        if (old != null) old.close();
    }
        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.databaselist);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Pokédex");
        }
        list = findViewById(R.id.list);

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


        adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2,
                null,
                new String[] { "Name", "Species" },
                new int[] { android.R.id.text1, android.R.id.text2 },
                0
        );
        list.setAdapter(adapter);

        // long-press delete
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int deleted = getContentResolver().delete(
                        PokemonDBProvider.CONTENT_URI,
                        "_id = ?",
                        new String[]{String.valueOf(id)}
                );
                if (deleted > 0) {
                    refresh();
                }
                return true;
            }

        });
        }
    @Override protected void onResume() {
        super.onResume();
        refresh();
    }

}