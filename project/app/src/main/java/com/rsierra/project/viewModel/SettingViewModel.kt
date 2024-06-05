package com.rsierra.project.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsierra.project.MyApplication
import com.rsierra.project.adapter.TaskAdapter
import com.rsierra.project.database.entity.Task
import kotlinx.coroutines.launch

class SettingViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences = application.getSharedPreferences("app_settings", Context.MODE_PRIVATE)

    private val _canEditTask = MutableLiveData(sharedPreferences.getBoolean(KEY_EDITAR_TAREA, false))
    val canEditTask: LiveData<Boolean> = _canEditTask

    private val _canCompleteTask = MutableLiveData(sharedPreferences.getBoolean(KEY_COMPLETAR_TAREA, false))
    val canCompleteTask: LiveData<Boolean> = _canCompleteTask

    private val _canViewDetails = MutableLiveData(sharedPreferences.getBoolean(KEY_VER_DETALLE, false))
    val canViewDetails: LiveData<Boolean> = _canViewDetails

    fun setCanEditTask(value: Boolean) {
        _canEditTask.value = value
        sharedPreferences.edit().putBoolean(KEY_EDITAR_TAREA, value).apply()
    }

    fun setCanCompleteTask(value: Boolean) {
        _canCompleteTask.value = value
        sharedPreferences.edit().putBoolean(KEY_COMPLETAR_TAREA, value).apply()
    }

    fun setCanViewDetails(value: Boolean) {
        _canViewDetails.value = value
        sharedPreferences.edit().putBoolean(KEY_VER_DETALLE, value).apply()
    }

    companion object {
        private const val KEY_EDITAR_TAREA = "editar_tarea"
        private const val KEY_COMPLETAR_TAREA = "completar_tarea"
        private const val KEY_VER_DETALLE = "ver_detalle"
    }
}