package com.example.myapplication.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapters.StudentsListAdapter
import com.example.myapplication.db.StudentDao
import com.example.myapplication.viewModel.StudentViewModel
import com.example.myapplication.viewModel.StudentViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.list_of_students.*
import javax.inject.Inject


@AndroidEntryPoint
class ListOfStudents : Fragment(R.layout.list_of_students), View.OnClickListener {

    @Inject
    lateinit var studentDao: StudentDao

    private val viewModel: StudentViewModel by activityViewModels{
        StudentViewModelFactory(
            studentDao
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = StudentsListAdapter{
            val bundle = bundleOf("studentId" to it.id)
            findNavController().navigate(R.id.action_listOfStudents_to_add_grade, bundle)
        }

        rvStudents.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this.context)
        }

        viewModel.allStudents.observe(this.viewLifecycleOwner){students ->
            students.let {
                adapter.submitList(it)
            }
        }

        fabAdd.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        findNavController().navigate(
            R.id.action_listOfStudents_to_addStudent
        )
    }
}
