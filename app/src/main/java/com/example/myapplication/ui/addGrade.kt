package com.example.myapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.db.Student
import com.example.myapplication.db.StudentDao
import com.example.myapplication.db.StudentDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.add_grade.*
import javax.inject.Inject

@AndroidEntryPoint
class addGrade : Fragment(R.layout.add_grade) {

    @Inject
    lateinit var studentDao: StudentDao

    private lateinit var studentDatabase: List<Student>
    private var studentId: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        studentDatabase = studentDao.getAllStudents()

        studentId = arguments?.getInt("studentId")!! - 1

        var numberOfAsk = studentDatabase[studentId].numberOfAsk

        txtNumber.text = studentDatabase[studentId].number.toString()
        txtFirstName.text = studentDatabase[studentId].firstName
        txtLastName.text = studentDatabase[studentId].lastName
        txtNumberOfAsk.text = numberOfAsk.toString()

        rbGroup.setOnCheckedChangeListener { radioGroup, i ->
            numberOfAsk++
            when(i){
                R.id.rb1 ->{
                    studentDao.updateStudent(
                        Student(
                            id = studentDatabase[studentId].id,
                            firstName = txtFirstName.text.toString(),
                            lastName = txtLastName.text.toString(),
                            number = txtNumber.text.toString().toInt(),
                            numberOfAsk = numberOfAsk,
                            grade = 1))
                }
                R.id.rb2 ->{
                    studentDao.updateStudent(
                        Student(
                            id = studentDatabase[studentId].id,
                            firstName = txtFirstName.text.toString(),
                            lastName = txtLastName.text.toString(),
                            number = txtNumber.text.toString().toInt(),
                            numberOfAsk = numberOfAsk++,
                            grade = 2))
                }
                R.id.rb3 ->{
                    studentDao.updateStudent(
                        Student(
                            id = studentDatabase[studentId].id,
                            firstName = txtFirstName.text.toString(),
                            lastName = txtLastName.text.toString(),
                            number = txtNumber.text.toString().toInt(),
                            numberOfAsk = numberOfAsk++,
                            grade = 3))
                }
                R.id.rb4 ->{
                    studentDao.updateStudent(
                        Student(
                            id = studentDatabase[studentId].id,
                            firstName = txtFirstName.text.toString(),
                            lastName = txtLastName.text.toString(),
                            number = txtNumber.text.toString().toInt(),
                            numberOfAsk = numberOfAsk++,
                            grade = 4))
                }

            }
        }
    }
}