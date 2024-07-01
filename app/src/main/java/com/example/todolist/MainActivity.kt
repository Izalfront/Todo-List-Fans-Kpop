package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskAdapter = TaskAdapter(
            onTaskClick = { task ->
                Toast.makeText(this, "Task: ${task.title}", Toast.LENGTH_SHORT).show()
            },
            onEditClick = { task ->
                binding.edtText.setText(task.title)
                binding.edtDesc.setText(task.desc)
                binding.btnSubmit.setOnClickListener {
                    val updatedTitle = binding.edtText.text.toString()
                    val updatedDesc = binding.edtDesc.text.toString()
                    if (updatedTitle.isNotEmpty()) {
                        val updatedTask = task.copy(title = updatedTitle, desc = updatedDesc)
                        taskViewModel.update(updatedTask)
                        binding.edtText.text.clear()
                        binding.edtDesc.text.clear()
                        binding.btnSubmit.setOnClickListener {
                            val taskTitle = binding.edtText.text.toString()
                            val taskDesc = binding.edtDesc.text.toString()
                            if (taskTitle.isNotEmpty()) {
                                val task = Task(title = taskTitle, desc = taskDesc)
                                taskViewModel.insert(task)
                                binding.edtText.text.clear()
                                binding.edtDesc.text.clear()
                            }
                        }
                    }
                }
            },
            onDeleteClick = { task ->
                taskViewModel.delete(task)
                Toast.makeText(this, "Task deleted", Toast.LENGTH_SHORT).show()
            }
        )

        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        binding.recyclerview.adapter = taskAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        taskViewModel.allTasks.observe(this) { tasks ->
            tasks?.let { taskAdapter.submitList(it) }
        }

        binding.btnSubmit.setOnClickListener {
            val taskTitle = binding.edtText.text.toString()
            val taskDesc = binding.edtDesc.text.toString()
            if (taskTitle.isNotEmpty()) {
                val task = Task(title = taskTitle, desc = taskDesc)
                taskViewModel.insert(task)
                binding.edtText.text.clear()
                binding.edtDesc.text.clear()
            }
        }

        binding.judul.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}
