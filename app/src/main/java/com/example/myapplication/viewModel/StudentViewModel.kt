package com.example.myapplication.viewModel

import androidx.lifecycle.*
import com.example.myapplication.db.Student
import com.example.myapplication.db.StudentDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StudentViewModel @Inject constructor(
    private val studentDao: StudentDao
) : ViewModel() {

    val allStudents: LiveData<List<Student>> = studentDao.getAllStudents().asLiveData()

    fun addNewStudent(studentNumber: Int, studentFirstName: String, studentLastName: String) {
        val newStudent = getNewStudentEntry(studentNumber, studentFirstName, studentLastName)
        insertStudent(newStudent)
    }

    private fun insertStudent(student: Student) {
        viewModelScope.launch {
            studentDao.addStudent(student)
        }
    }

    private fun getUpdatedStudentEntry(
        studentId: Int,
        studentNumber: Int,
        studentFirstName: String,
        studentLastName: String
    ): Student {
        return Student(
            id = studentId,
            number = studentNumber,
            firstName = studentFirstName,
            lastName = studentLastName
        )
    }

    fun updateStudent(
        studentId: Int,
        studentNumber: Int,
        studentFirstName: String,
        studentLastName: String
    ) {
        val updateStudent =
            getUpdatedStudentEntry(studentId, studentNumber, studentFirstName, studentLastName)
        updateStudent(updateStudent)
    }

    private fun updateStudent(student: Student) {
        viewModelScope.launch {
            studentDao.updateStudent(student)
        }
    }

    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            studentDao.deleteStudent(student)
        }
    }

    private fun getNewStudentEntry(
        studentNumber: Int,
        studentFirstName: String,
        studentLastName: String
    ): Student {
        return Student(
            number = studentNumber,
            firstName = studentFirstName,
            lastName = studentLastName
        )
    }

    fun askQuestion(student: Student) {
        val newStudent = student.copy(numberOfAsking = student.numberOfAsking + 1)
        updateStudent(newStudent)
    }

    fun retrieveStudent(id: Int): LiveData<Student> {
        return studentDao.getStudent(id).asLiveData()
    }

    fun isEntryValid(
        studentNumber: Int,
        studentFirstName: String,
        studentLastName: String
    ): Boolean {
        if (studentNumber.toString()
                .isBlank() || studentFirstName.isBlank() || studentLastName.isBlank()
        ) {
            return false
        }
        return true
    }

}

class StudentViewModelFactory(
    private val studentDao: StudentDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StudentViewModel(studentDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

















