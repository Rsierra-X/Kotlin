package com.rsierra.project

import android.app.Application
import com.rsierra.project.database.MyDatabase

class MyApplication: Application() {
    lateinit var database: MyDatabase
    override fun onCreate() {
        super.onCreate()
        database = MyDatabase.buildDatabase(this)
    }
}