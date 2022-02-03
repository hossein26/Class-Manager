package com.example.myapplication.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDao {

    @Insert
    fun addStudent(student: Student)

    @Delete
    fun deleteStudent(student: Student)

    @Query("SELECT * FROM student")
    fun getAllStudents(): List<Student>
}