package com.rsierra.project.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rsierra.project.database.entity.Task

@Dao
interface TaskDao {
    @Query("Select * from task")
    suspend fun getAllTasks(): List<Task>

    @Insert
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTask(task: Task)
}