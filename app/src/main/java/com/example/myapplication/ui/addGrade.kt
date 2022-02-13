package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.db.Student
import com.example.myapplication.viewModel.StudentViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.add_grade.*

@AndroidEntryPoint
class addGrade : Fragment(R.layout.add_grade) {

    lateinit var stuViewModel: StudentViewModel
    lateinit var student: Student
    private var studentId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        stuViewModel = ViewModelProvider(this)[StudentViewModel::class.java]
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        studentId = arguments?.getInt("studentId")!!

        stuViewModel.retrieveStudent(studentId)
            .observe(this.viewLifecycleOwner) { selectedStudent ->
                student = selectedStudent
                bind(student)
            }

        rbGroup.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rb1 -> {
                    stuViewModel.askQuestion(student)
                }
                R.id.rb2 -> {
                    stuViewModel.askQuestion(student)
                }
                R.id.rb3 -> {
                    stuViewModel.askQuestion(student)
                }
                R.id.rb4 -> {
                    stuViewModel.askQuestion(student)
                }

            }
        }
    }

    private fun bind(students: Student) {
        txtNumber.text = students.number.toString()
        txtFirstName.text = students.firstName
        txtLastName.text = students.lastName
        txtNumberOfAsk.text = students.numberOfAsking.toString()
        btnNext.setOnClickListener { }
        btnPerv.setOnClickListener { }
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
        stuViewModel.deleteStudent(student)
        findNavController().navigateUp()
    }
}