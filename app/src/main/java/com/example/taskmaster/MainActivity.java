package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.MyTask;
import com.amplifyframework.datastore.generated.model.Tasks;
import com.amplifyframework.datastore.generated.model.Team;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    AppDatabase appDatabase;
    TaskDao taskDao;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());
// Adding three hard coded teams by running mutuation three times to the database.

            //            Team team1 = Team.builder()
//                    .name("DG Team")
//                    .build();
//
//            Amplify.API.mutate(
//                    ModelMutation.create(team1),
//                    response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
//                    error -> Log.e("MyAmplifyApp", "Create failed", error)
//            );
            //     Team team2 = Team.builder()
//                    .name("Nerd Team")
//                    .build();
//
//            Amplify.API.mutate(
//                    ModelMutation.create(team2),
//                    response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
//                    error -> Log.e("MyAmplifyApp", "Create failed", error)
//            );

//            Team team3 = Team.builder()
//                    .name("DG Team")
//                    .build();
//
//            Amplify.API.mutate(
//                    ModelMutation.create(team3),
//                    response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
//                    error -> Log.e("MyAmplifyApp", "Create failed", error)
//            );

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }


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






//        appDatabase =  Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "TaskDatabase").allowMainThreadQueries().build();
//
//        taskDao = appDatabase.taskDao();
//        List<Task> allTasks =taskDao.getAll();

//        allTasks.add(new Task("Game","playing a Game","complete"));
//        allTasks.add(new Task("Do Homework","solve equations","in progress"));
//        allTasks.add(new Task("walking","one hour walking","assigned"));


    }



    @Override
    protected void onStart() {
        super.onStart();

        Handler handler=new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                recyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });
        ArrayList<MyTask> allTasks=new ArrayList<MyTask>();
        recyclerView = findViewById(R.id.TaskRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskAdapter(allTasks));

        Amplify.API.query(
                ModelQuery.list(MyTask.class),
                response -> {
                    Log.i("responseData", "here is my response"+response.toString());
                    for (MyTask task : response.getData()) {
                        Log.i("MyAmplifyApp", task.getTitle());
                        Log.i("MyAmplifyApp", task.getBody());
                        Log.i("MyAmplifyApp", task.getState());
                        allTasks.add(task);
                    }
                    handler.sendEmptyMessage(1);
                    Log.i("MyAmplifyApp", "outside the loop");
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        String owner= "'s tasks";
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