package com.example.myapplication.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "number")
    val number: Int,
    @ColumnInfo(name = "first name")
    val firstName: String,
    @ColumnInfo(name = "last name")
    val lastName: String,
    @ColumnInfo(name = "number of asking")
    var numberOfAsking: Int = 0,
    @ColumnInfo(name = "grade")
    var grade: Int = 0
)
