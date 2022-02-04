package com.example.myapplication.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(

    val number: Int,
    val firstName: String,
    val lastName: String,
    var numberOfAsk: Int = 0,
    var grade: Int? = null,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)
