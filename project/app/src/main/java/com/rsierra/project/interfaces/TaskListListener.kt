package com.rsierra.project.interfaces

import com.rsierra.project.database.entity.Task

interface TaskListListener {

    fun onClick(task: Task)
}