package com.rsierra.project.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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
import com.rsierra.project.viewModel.SettingViewModel
import com.rsierra.project.viewModel.TodoListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoListFragment : Fragment(), TaskListListener {

    private lateinit var binding: FragmentTodoListBinding
    private lateinit var viewModel: TodoListViewModel
    val adapter = TaskAdapter(arrayListOf(),this)
    private val settingViewModel: SettingViewModel by viewModels()

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
                createTaskDialog(getPermissions(),ctx,
                    newTask,
                    { newTask ->
                        viewModel.createTask(newTask,adapter)
                    },
                    {},
                    {}
                ) {}
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

    fun getPermissions(): Array<Boolean> {
        var canEdit: Boolean = false
        var canComplete: Boolean = false
        var canView: Boolean = false

        settingViewModel.canCompleteTask.observe(viewLifecycleOwner, Observer { canCompleteTask ->
            canEdit = canCompleteTask
        })
        settingViewModel.canEditTask.observe(viewLifecycleOwner, Observer { canEditTask ->
            canComplete = canEditTask
        })
        settingViewModel.canViewDetails.observe(viewLifecycleOwner, Observer { canViewDetails ->
            canView = canViewDetails
        })
        return arrayOf(canEdit, canComplete, canView)
    }

    override fun onClick(task: Task) {
        context?.let { ctx ->
            createTaskDialog(getPermissions(),ctx,
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
                    viewModel.deleteTask(deletedTask,adapter)}
            ) {
                val bundle = Bundle()
                bundle.putInt("task_id", task.id)
                findNavController().navigate(R.id.action_todoListFragment_to_detailFragment, bundle)
            }
        }
    }
}