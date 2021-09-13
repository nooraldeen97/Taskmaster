package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page2);

        Button saveBtn =findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(SettingsPage.this);
                SharedPreferences.Editor sharedPreferencesEditor=sharedPreferences.edit();

                EditText usernameInput=findViewById(R.id.usernameInput);
                String userName= usernameInput.getText().toString();
                RadioGroup radioGroup=findViewById(R.id.radioGroupId);
                RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                String radioButtonName=radioButton.getText().toString();

                sharedPreferencesEditor.putString("userName",userName);
                sharedPreferencesEditor.putString("teamName",radioButtonName);
                sharedPreferencesEditor.apply();

                Intent goToHome=new Intent(SettingsPage.this,MainActivity.class);
                startActivity(goToHome);

            }
        });
    }
}