package com.example.myapplication.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.db.Student
import com.example.myapplication.db.StudentDao
import com.example.myapplication.viewModel.StudentViewModel
import com.example.myapplication.viewModel.StudentViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.add_grade.*
import javax.inject.Inject

@AndroidEntryPoint
class AddGrade : Fragment(R.layout.add_grade) {

    lateinit var student: Student
    private var studentId: Int = 1

    @Inject
    lateinit var studentDao: StudentDao

    private val studentViewModel: StudentViewModel by activityViewModels {
        StudentViewModelFactory(
            studentDao
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        studentId = arguments?.getInt("studentId")!!

        studentViewModel.retrieveStudent(studentId)
            .observe(this.viewLifecycleOwner) { selectedStudent ->
                student = selectedStudent
                bind(student)
            }

        rbGroup.setOnCheckedChangeListener { radioGroup, i ->

            when (i) {
                R.id.rb1 -> {
                    studentViewModel.addGrade(student, 1.0)
                }
                R.id.rb2 -> {
                    studentViewModel.addGrade(student, 2.0)
                }
                R.id.rb3 -> {
                    studentViewModel.addGrade(student, 3.0)
                }
                R.id.rb4 -> {
                    studentViewModel.addGrade(student, 4.0)
                }
            }
        }
    }

    private fun nextStudent() {
        studentId += 1
        uiUpdate()
    }

    private fun pervStudent() {
        studentId -= 1
        uiUpdate()
    }

    private fun uiUpdate() {
        rbGroup.clearCheck()
        val listSize = studentViewModel.getStudentList.size

        if (studentId <= 0) studentId = 1
        if (studentId > listSize) studentId = 1

        studentViewModel.retrieveStudent(studentId)
            .observe(this.viewLifecycleOwner) { selectedStudent ->
                student = selectedStudent
                bind(student)
            }
    }

    private fun bind(students: Student) {
        txtNumber.text = students.number.toString()
        txtFirstName.text = students.firstName
        txtLastName.text = students.lastName
        txtNumberOfAsk.text = students.numberOfAsking.toString()
        btnNext.setOnClickListener { nextStudent() }
        btnPerv.setOnClickListener { pervStudent() }
        btnDelete.setOnClickListener { showConfirmationDialog() }
        btnEdit.setOnClickListener { editStudent() }
    }

    private fun editStudent() {
        val bundle = bundleOf("studentId" to student.id)
        findNavController().navigate(R.id.action_addGrade_to_addStudent, bundle)
    }

    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(android.R.string.dialog_alert_title))
            .setMessage("Are you sure you want to delete?")
            .setCancelable(false)
            .setNegativeButton("No") { _, _ -> }
            .setPositiveButton("Yes") { _, _ ->
                deleteItem()
            }
            .show()
    }

    private fun deleteItem() {
        studentViewModel.deleteStudent(student)
        findNavController().navigateUp()
    }
}