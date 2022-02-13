package com.example.myapplication.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.db.Student
import com.example.myapplication.db.StudentDao
import com.example.myapplication.viewModel.StudentViewModel
import com.example.myapplication.viewModel.StudentViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.add_student.*
import javax.inject.Inject

@AndroidEntryPoint
class AddStudent : Fragment(R.layout.add_student) {

    @Inject
    lateinit var studentDao: StudentDao

    private val studentViewModel: StudentViewModel by activityViewModels {
        StudentViewModelFactory(
            studentDao
        )
    }

    lateinit var student: Student

    private var studentId: Int = 0

    private fun isEntryValid(): Boolean {
        return studentViewModel.isEntryValid(
            edtNumber.text.toString().toInt(),
            edtFirstName.text.toString(),
            edtLastName.text.toString()
        )
    }

    private fun addNewStudent() {
        if (isEntryValid()) {
            studentViewModel.addNewStudent(
                edtNumber.text.toString().toInt(),
                edtFirstName.text.toString(),
                edtLastName.text.toString()
            )
        }
    }

    private fun updateStudent() {
        if (isEntryValid()) {
            studentViewModel.updateStudent(
                studentId, edtNumber.text.toString().toInt(),
                edtFirstName.text.toString(),
                edtLastName.text.toString()
            )
        }
        findNavController().navigate(R.id.action_addStudent_to_listOfStudents)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        studentId = arguments?.getInt("studentId")!!

        if (id > 0) {
            studentViewModel.retrieveStudent(studentId)
                .observe(this.viewLifecycleOwner) { selectedStudent ->
                    student = selectedStudent

                    edtNumber.setText(student.number.toString())
                    edtFirstName.setText(student.firstName)
                    edtLastName.setText(student.lastName)
                    btnApply.setOnClickListener { updateStudent() }
                }
        } else {
            btnApply.setOnClickListener {
                addNewStudent()
                findNavController().navigateUp()
            }
        }

    }
}