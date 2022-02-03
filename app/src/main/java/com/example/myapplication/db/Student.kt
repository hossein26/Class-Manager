package com.example.myapplication.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val grade: Int
)
