package com.rsierra.project.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rsierra.project.MyApplication
import com.rsierra.project.adapter.TaskAdapter
import com.rsierra.project.database.entity.Task
import kotlinx.coroutines.launch

class TodoListViewModel(val app: Application): AndroidViewModel(app) {
    private val _tasks: MutableLiveData<List<Task>> = MutableLiveData(emptyList())
    val tasks: LiveData<List<Task>> = _tasks
    fun createTask(task: Task, adapter: TaskAdapter){
        viewModelScope.launch {
            (app as MyApplication).database.getTaskDao().insertTask(task)
        }
        loadTaskList()
    }

    fun loadTaskList() {
        viewModelScope.launch {
            val arrayData = (app as MyApplication).database.getTaskDao().getAllTasks()
            _tasks.postValue(arrayData)
        }
    }

    fun deleteTask(task: Task, adapter: TaskAdapter) {
        adapter.deleteItem(task)
        viewModelScope.launch {
            (app as MyApplication).database.getTaskDao().deleteTask(task)
        }
    }

    fun updateTask(task: Task, adapter: TaskAdapter) {
        adapter.updateItem(task)
        viewModelScope.launch {
            (app as MyApplication).database.getTaskDao().updateTask(task)
        }
    }
}