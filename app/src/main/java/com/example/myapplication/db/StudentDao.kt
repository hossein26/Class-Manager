package com.example.myapplication.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Query("SELECT * FROM student ORDER BY `last name` ASC")
    fun getAllStudents(): Flow<List<Student>>

    @Query("SELECT * FROM student WHERE id = :id")
    fun getStudent(id: Int): Flow<Student>
}