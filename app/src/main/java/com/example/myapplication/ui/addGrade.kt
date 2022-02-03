package com.example.myapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.db.Student
import com.example.myapplication.db.StudentDatabase
import kotlinx.android.synthetic.main.add_grade.*

class addGrade : Fragment(R.layout.add_grade) {

    private lateinit var database: StudentDatabase
    private lateinit var studentDatabase: List<Student>
    private var studentId: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = StudentDatabase.getDatabase(requireContext())
        studentDatabase = database.studentDao().getAllStudents()

        studentId = arguments?.getInt("studentId")!! - 1

        txtNumber.text = studentDatabase[studentId].number.toString()
        txtFirstName.text = studentDatabase[studentId].firstName
        txtLastName.text = studentDatabase[studentId].lastName

        rbGroup.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.rb1 ->{
                    database.studentDao().updateStudent(
                        Student(
                            id = studentDatabase[studentId].id,
                            firstName = txtFirstName.text.toString(),
                            lastName = txtLastName.text.toString(),
                            number = txtNumber.text.toString().toInt(),
                            grade = 1))
                }
                R.id.rb2 ->{
                    database.studentDao().updateStudent(
                        Student(
                            id = studentDatabase[studentId].id,
                            firstName = txtFirstName.text.toString(),
                            lastName = txtLastName.text.toString(),
                            number = txtNumber.text.toString().toInt(),
                            grade = 2))
                }
                R.id.rb3 ->{
                    database.studentDao().updateStudent(
                        Student(
                            id = studentDatabase[studentId].id,
                            firstName = txtFirstName.text.toString(),
                            lastName = txtLastName.text.toString(),
                            number = txtNumber.text.toString().toInt(),
                            grade = 3))
                }
                R.id.rb4 ->{
                    database.studentDao().updateStudent(
                        Student(
                            id = studentDatabase[studentId].id,
                            firstName = txtFirstName.text.toString(),
                            lastName = txtLastName.text.toString(),
                            number = txtNumber.text.toString().toInt(),
                            grade = 4))
                }

            }
        }
    }
}