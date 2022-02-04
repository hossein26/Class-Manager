package com.example.myapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1)
abstract class StudentDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao
}