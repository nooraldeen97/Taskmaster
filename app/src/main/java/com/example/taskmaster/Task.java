package com.example.taskmaster;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.LinkedList;
@Entity

public class Task {
    
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "title-column")
    public String title;
    @ColumnInfo(name = "body-column")
    public String body;
    @ColumnInfo(name = "state-column")
    public String state;

    public Task(String title, String body, String state) {
        this.title = title;
        this.body = body;
        this.state = state;
    }


}
