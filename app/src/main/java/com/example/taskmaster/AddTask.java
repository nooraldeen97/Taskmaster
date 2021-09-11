package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.media.session.PlaybackState;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.MyTask;
import com.amplifyframework.datastore.generated.model.Tasks;
import com.amplifyframework.datastore.generated.model.Team;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class AddTask extends AppCompatActivity {

    AppDatabase appDatabase;
    TaskDao taskDao;
    String teamName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        RadioButton radioButton1=findViewById(R.id.nerdId);
        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 =radioButton1.getText().toString();
                teamName=name1;
            }
        });

        // getting all teams form the database .
        ArrayList<Team> allTeam=new ArrayList<Team>();
//        Team team1=allTeam.get(0);
        System.out.println(allTeam);

        Amplify.API.query(
                ModelQuery.list(Team.class),
                response -> {
//                    Log.i("responseData", "here is my response"+response.getData());

                    for (Team team  : response.getData()) {
                        Log.i("MyAmplifyApp", team.getName());
                        System.out.println(team);
                        allTeam.add(team);
                    }
                    Log.i("MyAmplifyApp", "outside the loop");
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );



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




//                MyTask tasks = MyTask.builder()
//                        .title(taskName)
//                        .team()
//                        .body(taskBody)
//                        .state(taskState)
//                        .build();
//
//                Amplify.API.mutate(
//                        ModelMutation.create(tasks),
//                        response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
//                        error -> Log.e("MyAmplifyApp", "Create failed", error)
//                );
                // get team name from radio button


//                appDatabase =  Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "TaskDatabase").allowMainThreadQueries().build();
//
//                taskDao = appDatabase.taskDao();
//                taskDao.insertAll(task);
            }
        });

        // calling the action bar
        ActionBar actionBar=getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);


    }



}