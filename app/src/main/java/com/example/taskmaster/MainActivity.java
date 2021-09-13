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
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.MyTask;
import com.amplifyframework.datastore.generated.model.Tasks;
import com.amplifyframework.datastore.generated.model.Team;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

            Amplify.Auth.signInWithWebUI(
                    this,
                    result -> Log.i("AuthQuickStart", result.toString()),
                    error -> Log.e("AuthQuickStart", error.toString())
            );
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
// this should be on sign up page , and the email and username and password take them from the form.
//        AuthSignUpOptions options = AuthSignUpOptions.builder()
//                .userAttribute(AuthUserAttributeKey.email(), "noor-aldeen97@hotmail.com")
//                .build();
//        Amplify.Auth.signUp("nooraldeen", "Pass12345", options,
//                result -> Log.i("AuthQuickStart", "Result: " + result.toString()),
//                error -> Log.e("AuthQuickStart", "Sign up failed", error)
//        );
//sign up confimation activity to enter the code the he recieved.

//        Amplify.Auth.confirmSignUp(
//                "nooraldeen",
//                "285210",
//                result -> Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete"),
//                error -> Log.e("AuthQuickstart", error.toString())
//        );


//        Amplify.Auth.signIn(
//                "username",
//                "password",
//                result -> Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete"),
//                error -> Log.e("AuthQuickstart", error.toString())
//        );
//        Amplify.Auth.fetchAuthSession(
//                result -> Log.i("AmplifyQuickstart", result.toString()),
//                error -> Log.e("AmplifyQuickstart", error.toString())
//        );

        Button signOutButton=findViewById(R.id.signOutId);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Amplify.Auth.signOut(
                        () -> Log.i("AuthQuickstart", "Signed out successfully"),
                        error -> Log.e("AuthQuickstart", error.toString())
                );
            }
        });


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

        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String teamInputFromSetting=sharedPreferences.getString("teamName","kkk");
//        System.out.println(teamInputFromSetting);

        ArrayList<MyTask> allTasks=new ArrayList<MyTask>();
        ArrayList<MyTask> teamTasks=new ArrayList<>();

            recyclerView = findViewById(R.id.TaskRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new TaskAdapter(teamTasks));



        Amplify.API.query(
                ModelQuery.list(MyTask.class),
                response -> {
                    for (MyTask task : response.getData()) {
//                        Log.i("MyAmplifyApp", task.getBody());
//                        Log.i("MyAmplifyApp", task.getState());
//                        Log.i("MyAmplifyApp", task.getTitle());
                        allTasks.add(task);

                    }
                    for (int i = 0; i < allTasks.size(); i++) {
                        if(allTasks.get(i).getTeam().getName().equals(teamInputFromSetting)){
                            teamTasks.add(allTasks.get(i));
                        }
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