package com.rsierra.project.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rsierra.project.R
import com.rsierra.project.adapter.TaskAdapter
import com.rsierra.project.createEditTaskDialog
import com.rsierra.project.createTaskDialog
import com.rsierra.project.database.entity.Task
import com.rsierra.project.databinding.FragmentTodoListBinding
import com.rsierra.project.interfaces.TaskListListener
import com.rsierra.project.viewModel.TodoListViewModel

class TodoListFragment : Fragment(), TaskListListener {

    private lateinit var binding: FragmentTodoListBinding
    private lateinit var viewModel: TodoListViewModel
    val adapter = TaskAdapter(arrayListOf(),this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodoListBinding.inflate(inflater, container, false);
        viewModel = ViewModelProvider(this).get(TodoListViewModel::class.java)
        binding.rvTaskList.adapter = adapter
        binding.rvTaskList.layoutManager = LinearLayoutManager(context)
        viewModel.tasks.observe(viewLifecycleOwner) { newList ->
            Log.d("TEST", "${newList.size}")
            adapter.loadNewItems(newList)
        }
        viewModel.loadTaskList()


        binding.floatingActionButton.setOnClickListener {
            val newTask = Task(0,"","",0)
            context?.let { ctx ->
                createTaskDialog(ctx,
                    newTask,
                    { newTask ->
                        viewModel.createTask(newTask,adapter)
                    },
                    {},
                    {},
                    {})
            }
        }
        binding.fabInfo.setOnClickListener {
            findNavController().navigate(R.id.action_todoListFragment_to_aboutPageFragment)
        }

        binding.fabSettings.setOnClickListener {
            findNavController().navigate(R.id.action_todoListFragment_to_settingFragment)
        }



        return binding.root
    }

    override fun onClick(task: Task) {
        context?.let { ctx ->
            createTaskDialog(ctx,
                task,
                {},
                {updatedTask ->
                    createEditTaskDialog(ctx,
                        updatedTask,
                        { task ->
                            viewModel.updateTask(task,adapter)
                        })
                    },
                {deletedTask ->
                    viewModel.deleteTask(deletedTask,adapter)},
                {})
        }
    }
}