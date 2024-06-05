package com.rsierra.project.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsierra.project.MyApplication
import com.rsierra.project.adapter.TaskAdapter
import com.rsierra.project.database.entity.Task
import kotlinx.coroutines.launch

class SettingViewModel(val app: Application) : ViewModel() {

}