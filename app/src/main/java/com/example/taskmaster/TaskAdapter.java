package com.example.taskmaster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{

    List<Task> allTask=new ArrayList<Task>();

    public TaskAdapter(List<Task> allTask) {
        this.allTask = allTask;
    }



    public static class TaskViewHolder extends RecyclerView.ViewHolder{


        public Task task;
        View itemView;
     public TaskViewHolder(@NotNull View itemView) {
    super(itemView);
    this.itemView=itemView;
    }

    }


    @NotNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task,parent,false);
        TaskViewHolder taskViewHolder= new TaskViewHolder(view);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NotNull TaskAdapter.TaskViewHolder holder, int position) {
    holder.task=allTask.get(position);

        TextView textTitle = holder.itemView.findViewById(R.id.textTitle);
        TextView textBody = holder.itemView.findViewById(R.id.textBody);
        TextView textState = holder.itemView.findViewById(R.id.textState);

        textTitle.setText(holder.task.title);
        textBody.setText(holder.task.body);
        textState.setText(holder.task.state);
    }

    @Override
    public int getItemCount() {
        return allTask.size();
    }
}
