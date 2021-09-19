package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.content.Intent;
import android.media.session.PlaybackState;
import android.net.Uri;
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

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AddTask extends AppCompatActivity {

    AppDatabase appDatabase;
    TaskDao taskDao;
    Uri uri;
    EditText taskNameInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        // calling the action bar
        ActionBar actionBar=getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);



        // getting all teams form the database .

        List<Team> allTeam=new ArrayList<>();

            Amplify.API.query(
                    ModelQuery.list(Team.class),
                    response -> {

                        for (Team team  : response.getData()) {
                            allTeam.add(team);

                        }
                        Log.i("MyAmplifyApp", "outside the loop");
                    },
                    error -> Log.e("MyAmplifyApp", "Query failure", error)
            );


    Button uploadButton=findViewById(R.id.uploadID);
    uploadButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getFileFromDevice();
        }
    });



        Button addBtn=findViewById(R.id.AddBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadInputStream();

                Toast.makeText(getApplicationContext(),"submitted",Toast.LENGTH_LONG).show();
                 taskNameInput = findViewById(R.id.taskNameId);
                String taskName=taskNameInput.getText().toString();
                EditText taskBodyInput = findViewById(R.id.taskBodyId);
                String taskBody=taskBodyInput.getText().toString();
                EditText taskStateInput = findViewById(R.id.stateId);
                String taskState=taskStateInput.getText().toString();

                RadioButton nerd = findViewById(R.id.nerdId);
                RadioButton noor = findViewById(R.id.noorId);
                RadioButton dg = findViewById(R.id.dgId);

                // get team name from radio button

                String name="";
                if(nerd.isChecked()) {
//                   id = "f7441181-f7a9-42f0-96e0-830c77066f0a";
                    name="Nerd Team";
                }else if(noor.isChecked()){
                    name = "Noor Team";
                }else if(dg.isChecked()){
                    name = "DG Team";
                }
                Team specificTeam=null;
                for (int i = 0; i < allTeam.size(); i++) {
                    if(allTeam.get(i).getName().equals(name)){
                        specificTeam = allTeam.get(i);
                    }
                }


                MyTask tasks = MyTask.builder()
                        .title(taskName)
                        .team(specificTeam)
                        .body(taskBody)
                        .state(taskState)
                        .build();

                Amplify.API.mutate(
                        ModelMutation.create(tasks),
                        response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                        error -> Log.e("MyAmplifyApp", "Create failed", error)
                );


//                appDatabase =  Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "TaskDatabase").allowMainThreadQueries().build();
//
//                taskDao = appDatabase.taskDao();
//                taskDao.insertAll(task);
            }
        });




    }

    private void getFileFromDevice() {
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("*/*");
        chooseFile = Intent.createChooser(chooseFile, "Choose File!");
        startActivityForResult(chooseFile, 2048);
    }

    private void uploadInputStream() {
        if (uri != null) {
            try {
                InputStream exampleInputStream = getContentResolver().openInputStream(uri);
                Amplify.Storage.uploadInputStream(
                        taskNameInput.getText().toString(),
//                        "Example Key",
                        exampleInputStream,
                        result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                        storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
                );
            } catch (FileNotFoundException error) {
                Log.e("MyAmplifyApp", "Could not find file to open for input stream.", error);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uri = data.getData();

    }

}