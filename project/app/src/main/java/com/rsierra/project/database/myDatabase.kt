package com.rsierra.project.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rsierra.project.database.dao.TaskDao
import com.rsierra.project.database.entity.Task


const val DATABASE_VERSION = 1
const val DATABASE_NAME = "todolist"

@Database(
    entities = [Task::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getTaskDao() : TaskDao

    companion object {
        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            MyDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}