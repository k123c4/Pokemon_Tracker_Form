package com.example.pokemon_tracker_from;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
        //editTexts
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
        //TextViews
        TextView natNumTV = findViewById(R.id.nat_numLB);
        TextView nameTV = findViewById(R.id.NameTV);
        TextView speciesTV = findViewById(R.id.speciesTV);
        TextView genderTV = findViewById(R.id.GenderTV);
        TextView heightTV = findViewById(R.id.HeightTV);
        TextView weightTV = findViewById(R.id.weightTV);
        TextView lvlTV = findViewById(R.id.LevelTV);
        TextView hpTV = findViewById(R.id.hpTV);
        TextView attackTV = findViewById(R.id.attackTV);
        TextView defenceTV = findViewById(R.id.defenceTV);


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
    // save button
        subBut.setOnClickListener(v -> {
            StringBuilder errors = new StringBuilder();
            boolean valid = true;
            // Name
            String name = nameET.getText().toString().trim();
            if (name.isEmpty()) {
                errors.append("Name cannot be empty\n");
                nameTV.setTextColor(Color.RED);
                valid = false;
            } else if (!name.matches("[A-Za-z. ]{3,12}")) {
                errors.append("Name must be 3–12 letters \n");
                nameTV.setTextColor(Color.RED);
                valid = false;
            }else{
                nameTV.setTextColor(Color.BLACK);
            }

            // Gender
            int selectedId = genderGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                errors.append("Gender must be selected (Male or Female)\n");
                genderTV.setTextColor(Color.RED);
                valid = false;
            } else {
                RadioButton selected = findViewById(selectedId);
                String gender = selected.getText().toString();
                if (!(gender.equals("Male") || gender.equals("Female"))) {
                    errors.append("Gender must be Male or Female\n");
                    genderTV.setTextColor(Color.RED);
                    valid = false;
                }else{
                    genderTV.setTextColor(Color.BLACK);
                }
            }

            // HP
            try {
                int hp = Integer.parseInt(hpET.getText().toString().trim());
                if (hp < 1 || hp > 362) {
                    errors.append("HP must be 1–362\n");
                    hpTV.setTextColor(Color.RED);
                    valid = false;
                }else{
                    hpTV.setTextColor(Color.BLACK);
                }
            } catch (NumberFormatException e) {
                errors.append("HP cannot be empty\n");
                hpTV.setTextColor(Color.RED);
                valid = false;
            }

            // --- Attack ---
            try {
                int attack = Integer.parseInt(attackET.getText().toString().trim());
                if (attack < 0 || attack > 526) {
                    errors.append("Attack must be 0–526\n");
                    attackTV.setTextColor(Color.RED);
                    valid = false;
                }else{
                    attackTV.setTextColor(Color.BLACK);
                }
            } catch (NumberFormatException e) {
                errors.append("Attack cannot be empty\n");
                attackTV.setTextColor(Color.RED);
                valid = false;
            }

            // --- Defence ---
            try {
                int defence = Integer.parseInt(defenceET.getText().toString().trim());
                if (defence < 10 || defence > 614) {
                    errors.append("Defence must be 10–614\n");
                    defenceTV.setTextColor(Color.RED);
                    valid = false;
                }else{
                    defenceTV.setTextColor(Color.BLACK);
                }
            } catch (NumberFormatException e) {
                errors.append("Defence cannot be empty\n");
                defenceTV.setTextColor(Color.RED);
                valid = false;
            }

            // --- Height ---
            try {
                double height = Double.parseDouble(heightET.getText().toString().replace(" m", "").trim());
                if (height < 0.2 || height > 169.99) {
                    errors.append("Height must be 0.2–169.99 m\n");
                    heightTV.setTextColor(Color.RED);
                    valid = false;
                }else{
                    heightTV.setTextColor(Color.BLACK);
                }
            } catch (NumberFormatException e) {
                errors.append("Height cannot be empty\n");
                heightTV.setTextColor(Color.RED);
                valid = false;
            }

            // --- Weight ---
            try {
                double weight = Double.parseDouble(weightET.getText().toString().replace(" kg", "").trim());
                if (weight < 0.1 || weight > 992.7) {
                    errors.append("Weight must be 0.1–992.7 kg\n");
                    weightTV.setTextColor(Color.RED);
                    valid = false;
                }else{
                    weightTV.setTextColor(Color.BLACK);
                }
            } catch (NumberFormatException e) {
                errors.append("Weight cannot be empty\n");
                weightTV.setTextColor(Color.RED);
                valid = false;
            }

            // --- Show results ---
            if (errors.length() > 0) {
                Toast.makeText(this, errors.toString().trim(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
            }
        });

    };
}