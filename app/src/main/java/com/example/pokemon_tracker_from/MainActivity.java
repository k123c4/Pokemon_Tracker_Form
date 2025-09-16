package com.example.pokemon_tracker_from;

import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_table);

        EditText NatNumET = findViewById(R.id.NationalNumET);
        EditText nameET = findViewById(R.id.nameET);
        EditText speciesET = findViewById(R.id.speciesET);
        EditText heightET = findViewById(R.id.heightET);
        EditText weightET = findViewById(R.id.weightET);

    }
}