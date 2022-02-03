package com.example.myapplication.db

import androidx.room.*

@Dao
interface StudentDao {

    @Insert
    fun addStudent(student: Student)

    @Delete
    fun deleteStudent(student: Student)

    @Update
    fun updateStudent(student: Student)

    @Query("SELECT * FROM student")
    fun getAllStudents(): List<Student>
}