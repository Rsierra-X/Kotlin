package com.rsierra.project.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsierra.project.MyApplication
import com.rsierra.project.adapter.TaskAdapter
import com.rsierra.project.database.dao.TaskDao
import com.rsierra.project.database.entity.Task
import kotlinx.coroutines.launch

class DetailViewModel(val app: Application): AndroidViewModel(app)  {
    private val taskDao: TaskDao = (app as MyApplication).database.getTaskDao()
    private val _task = MutableLiveData<Task>()
    val task: LiveData<Task> get() = _task

    fun getTaskById(taskId: Int) {
        viewModelScope.launch {
            _task.value = taskDao.getTaskById(taskId)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            (app as MyApplication).database.getTaskDao().deleteTask(task)
        }
    }
}