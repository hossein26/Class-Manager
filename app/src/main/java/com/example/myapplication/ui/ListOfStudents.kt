package com.example.myapplication.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.myapplication.R
import com.example.myapplication.adapters.StudentsListAdapter
import com.example.myapplication.db.StudentDatabase
import kotlinx.android.synthetic.main.list_of_students.*

class ListOfStudents: Fragment(R.layout.list_of_students), View.OnClickListener {

    private lateinit var database: StudentDatabase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = StudentDatabase.getDatabase(requireContext())

        fabAdd.setOnClickListener(this)

        rvStudents.apply {
            adapter = StudentsListAdapter(database.studentDao().getAllStudents())
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onClick(view: View?) {
        findNavController().navigate(
            R.id.action_listOfStudents_to_addStudent
        )
    }
}