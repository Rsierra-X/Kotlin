package com.rsierra.project.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rsierra.project.MyApplication
import com.rsierra.project.database.entity.Task
import kotlinx.coroutines.launch

class TodoListViewModel(val app: Application): AndroidViewModel(app) {
    private val _tasks: MutableLiveData<List<Task>> = MutableLiveData(emptyList())
    val tasks: LiveData<List<Task>> = _tasks
    fun createTask(task: Task){
        viewModelScope.launch {
            (app as MyApplication).database.getTaskDao().insertTask(task)
        }
    }

    fun loadTaskList() {
        viewModelScope.launch {
            val arrayData = (app as MyApplication).database.getTaskDao().getAllTasks()
            _tasks.postValue(arrayData)
        }
    }

    /*fun deleteMascota(mascota: Task, adapter: MascotaAdapter) {
        adapter.deleteItem(mascota)
        viewModelScope.launch {
            (app as MyApplication).database.getMascotaDao().deleteMascota(mascota)
        }
    }

    fun updateMascota(mascota: Mascota, adapter: MascotaAdapter) {
        adapter.updateItem(mascota)
        viewModelScope.launch {
            (app as MyApplication).database.getMascotaDao().updateMascota(mascota)
        }
    }*/
}