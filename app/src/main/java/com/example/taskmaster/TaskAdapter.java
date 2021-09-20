package com.example.taskmaster;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.api.graphql.PaginatedResult;
import com.amplifyframework.datastore.generated.model.MyTask;
import com.amplifyframework.datastore.generated.model.Tasks;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{

    ArrayList<MyTask> allTask= new ArrayList<>();

    public TaskAdapter(ArrayList<MyTask> allTask) {
        this.allTask = allTask;
    }



    public static class TaskViewHolder extends RecyclerView.ViewHolder{


        public MyTask task;
        View itemView;
     public TaskViewHolder(@NotNull View itemView) {
    super(itemView);
    this.itemView=itemView;
    itemView.findViewById(R.id.goToDetailsFrag).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent goToDetailsPage = new Intent(view.getContext(),DetailsPage.class);
            goToDetailsPage.putExtra("taskName",task.getTitle());
//            goToDetailsPage.putExtra("taskName",task.getLat());
//            goToDetailsPage.putExtra("taskName",task.getLon());
            view.getContext().startActivity(goToDetailsPage);
        }
    });
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

        textTitle.setText(holder.task.getTitle());
        textBody.setText(holder.task.getBody());
        textState.setText(holder.task.getState());
    }

    @Override
    public int getItemCount() {
        return allTask.size();
    }
}
