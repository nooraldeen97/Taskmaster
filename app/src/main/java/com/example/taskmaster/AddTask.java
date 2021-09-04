package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.media.session.PlaybackState;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.security.PublicKey;

public class AddTask extends AppCompatActivity {

    AppDatabase appDatabase;
    TaskDao taskDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Button addBtn=findViewById(R.id.AddBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"submitted",Toast.LENGTH_LONG).show();
                EditText taskNameInput = findViewById(R.id.taskNameId);
                String taskName=taskNameInput.getText().toString();
                EditText taskBodyInput = findViewById(R.id.taskBodyId);
                String taskBody=taskBodyInput.getText().toString();
                EditText taskStateInput = findViewById(R.id.stateId);
                String taskState=taskStateInput.getText().toString();

                Task task = new Task(taskName,taskBody,taskState);


                appDatabase =  Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "TaskDatabase").allowMainThreadQueries().build();

                taskDao = appDatabase.taskDao();
                taskDao.insertAll(task);
            }
        });

        // calling the action bar
        ActionBar actionBar=getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);


    }



}