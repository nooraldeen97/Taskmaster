package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    AppDatabase appDatabase;
    TaskDao taskDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn2=findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent moveToAllTasks= new Intent(MainActivity.this,AllTasks.class);

                startActivity(moveToAllTasks);
            }
        });

        Button btn1 =findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent moveToAddTask =new Intent(MainActivity.this,AddTask.class);

                startActivity(moveToAddTask);
            }
        });

        Button homeworkBtn = findViewById(R.id.homeworkBtn);
        Button coffeBtn = findViewById(R.id.coffeBtn);
        Button gameBtn = findViewById(R.id.gameBtn);

        homeworkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskNameValue= homeworkBtn.getText().toString();
                Intent goToDetails= new Intent(MainActivity.this,DetailsPage.class);
                goToDetails.putExtra("taskName",taskNameValue);
                startActivity(goToDetails);
            }
        });

        coffeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button taskName= findViewById(R.id.coffeBtn);
                String taskNameValue=taskName.getText().toString();
                Intent goToDetails= new Intent(MainActivity.this,DetailsPage.class);
                goToDetails.putExtra("taskName",taskNameValue);
                startActivity(goToDetails);
            }
        });

        gameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String taskNameValue = gameBtn.getText().toString();
                Intent goToDetails= new Intent(MainActivity.this,DetailsPage.class);
                goToDetails.putExtra("taskName",taskNameValue);
                startActivity(goToDetails);
            }
        });

        Button settingsBtn=findViewById(R.id.settingsBtn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSettingsPage=new Intent(MainActivity.this,SettingsPage.class);
                startActivity(goToSettingsPage);
            }
        });

        appDatabase =  Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "TaskDatabase").allowMainThreadQueries().build();

        taskDao = appDatabase.taskDao();
        List<Task> allTasks =taskDao.getAll();

//        allTasks.add(new Task("Game","playing a Game","complete"));
//        allTasks.add(new Task("Do Homework","solve equations","in progress"));
//        allTasks.add(new Task("walking","one hour walking","assigned"));

        RecyclerView recyclerView = findViewById(R.id.TaskRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskAdapter(allTasks));
    }



    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String owner= "’s tasks";
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userNameFromInput=sharedPreferences.getString("userName","visitor");

        TextView usernameInput= findViewById(R.id.usernameText);
        usernameInput.setText(userNameFromInput+owner);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}