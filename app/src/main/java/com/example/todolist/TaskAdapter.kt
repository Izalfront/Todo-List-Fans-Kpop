package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ListItemBinding

class TaskAdapter(
    private val onTaskClick: (Task) -> Unit,
    private val onEditClick: (Task) -> Unit,
    private val onDeleteClick: (Task) -> Unit
) :
    ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task)
        holder.itemView.setOnClickListener { onTaskClick(task) }
        holder.binding.btnEdit.setOnClickListener { onEditClick(task) }
        holder.binding.btnDelete.setOnClickListener { onDeleteClick(task) }
    }

    class TaskViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.checkboxCompleted.isChecked = task.isCompleted
            binding.tvTaskTitle.text = task.title
            binding.tvTaskDesc.text = task.desc
        }
    }

    class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }
}