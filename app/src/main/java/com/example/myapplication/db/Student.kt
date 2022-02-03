package com.example.myapplication.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val number: Int,
    val firstName: String,
    val lastName: String,
    var grade: Int? = null
)
