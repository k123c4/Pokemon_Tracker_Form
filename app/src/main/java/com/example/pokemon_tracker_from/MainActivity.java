package com.example.pokemon_tracker_from;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
        // m to height
        heightET.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();

                if (!input.endsWith(" m") && !input.isEmpty()) {
                    heightET.removeTextChangedListener(this);
                    input = input.replaceAll(" m$", ""); // avoid duplicates
                    heightET.setText(input + " m");
                    heightET.setSelection(input.length()); // keep cursor before "m"
                    heightET.addTextChangedListener(this);
                }
            }

            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        // kg to weight
        weightET.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();

                if (!input.endsWith(" kg") && !input.isEmpty()){
                    weightET.removeTextChangedListener(this);
                    input = input.replaceAll(" kg$", "");
                    weightET.setText(input + " kg");
                    weightET.setSelection(input.length());
                    weightET.addTextChangedListener(this);
                }
            }

            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

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

            genderGroup.clearCheck(); //Extra Credit
            lvlSp.setSelection(0);
        });
    }
}