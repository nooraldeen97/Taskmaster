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
import android.content.BroadcastReceiver;
import android.content.Context;


import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.pinpoint.PinpointConfiguration;
import com.amazonaws.mobileconnectors.pinpoint.PinpointManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    private static PinpointManager pinpointManager;

    public static PinpointManager getPinpointManager(final Context applicationContext) {
        if (pinpointManager == null) {
            final AWSConfiguration awsConfig = new AWSConfiguration(applicationContext);
            AWSMobileClient.getInstance().initialize(applicationContext, awsConfig, new Callback<UserStateDetails>() {
                @Override
                public void onResult(UserStateDetails userStateDetails) {
                    Log.i("INIT", String.valueOf(userStateDetails.getUserState()));
                }

                @Override
                public void onError(Exception e) {
                    Log.e("INIT", "Initialization error.", e);
                }
            });

            PinpointConfiguration pinpointConfig = new PinpointConfiguration(
                    applicationContext,
                    AWSMobileClient.getInstance(),
                    awsConfig);

            pinpointManager = new PinpointManager(pinpointConfig);

            FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(new OnCompleteListener<String>() {
                        @Override
                        public void onComplete(@NonNull Task<String> task) {
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                                return;
                            }
                            final String token = task.getResult();
                            Log.d(TAG, "Registering push notifications token: " + token);
                            pinpointManager.getNotificationClient().registerDeviceToken(token);
                        }
                    });
        }
        return pinpointManager;
    }

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
//            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.configure(getApplicationContext());
//            getPinpointManager(getApplicationContext());
//            Amplify.Auth.signInWithWebUI(
//                    this,
//                    result -> Log.i("AuthQuickStart", result.toString()),
//                    error -> Log.e("AuthQuickStart", error.toString())
//            );

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }



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
            recyclerView.setAdapter(new TaskAdapter(allTasks));



        Amplify.API.query(
                ModelQuery.list(MyTask.class),
                response ->

                {
                    Log.i("hereIS",response.toString());

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