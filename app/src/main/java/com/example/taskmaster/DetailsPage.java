package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;

import java.io.File;

public class DetailsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);

        Intent intent = getIntent();
        String taskNameValue = intent.getExtras().getString("taskName");
        TextView taskTitleView = findViewById(R.id.titleText);
        taskTitleView.setText(taskNameValue);


        ImageView detailsImage = findViewById(R.id.detailsImgID);

        Amplify.Storage.downloadFile(
                taskNameValue,
                new File(getApplicationContext().getFilesDir() + "/noorImg Key.jpg"),
                result ->{
                    detailsImage.setImageBitmap(BitmapFactory.decodeFile(result.getFile().getPath()));
                },
                error -> Log.e("MyAmplifyApp",  "Download Failure", error)
        );

    }
}