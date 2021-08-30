package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);

        Intent intent = getIntent();
        String taskNameValue = intent.getExtras().getString("taskName");
        TextView taskTitleView = findViewById(R.id.titleText);
        taskTitleView.setText(taskNameValue);
    }
}