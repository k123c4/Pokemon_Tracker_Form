package com.example.pokemon_tracker_from;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_linear);

        EditText natNumET = findViewById(R.id.NationalNumET);
        EditText nameET = findViewById(R.id.nameET);
        EditText speciesET = findViewById(R.id.speciesET);
        RadioGroup genderGroup = findViewById(R.id.genderGroup);
        EditText heightET = findViewById(R.id.heightET);
        EditText weightET = findViewById(R.id.weightET);
        Spinner lvlSp = findViewById(R.id.spinner);
        EditText hpET = findViewById(R.id.HpET);
        EditText attackET = findViewById(R.id.AttackET);
        EditText defenceET = findViewById(R.id.defenceET);
        Button resBut = findViewById(R.id.ResetBt);
        Button subBut = findViewById(R.id.SaveBt);


        // list of numbers from 1 to 50
        List<String> numbers = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            numbers.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                numbers
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lvlSp.setAdapter(adapter);

        //reset button function
        resBut.setOnClickListener(v -> {
            natNumET.setText("");
            nameET.setText("");
            speciesET.setText("");
            heightET.setText("");
            weightET.setText("");
            hpET.setText("");
            attackET.setText("");
            defenceET.setText("");

            genderGroup.clearCheck();
            lvlSp.setSelection(0);
        });
    }
}