package com.rsierra.project.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rsierra.project.R
import com.rsierra.project.database.entity.Task
import com.rsierra.project.databinding.TaskItemBinding
import com.rsierra.project.interfaces.TaskListListener

class TaskAdapter(val list: ArrayList<Task>, val taskListener: TaskListListener) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    class TaskViewHolder(val binding: TaskItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TaskViewHolder(TaskItemBinding
            .inflate(LayoutInflater.from(parent.context),parent, false))


    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.binding.taskTitle.text = list.get(position).title
        holder.binding.statusCircle.background = getPriorityColor(holder.itemView.context,list.get(position).priority)
    }

    private fun getPriorityColor(context: Context, priority: Int): Drawable? {
        return when (priority) {
            1 -> ContextCompat.getDrawable(context, R.drawable.circle_status_green)
            2 -> ContextCompat.getDrawable(context, R.drawable.circle_status_yellow)
            else -> ContextCompat.getDrawable(context, R.drawable.circle_status_red)
        }
    }


}