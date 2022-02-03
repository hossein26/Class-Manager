package com.example.myapplication.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.myapplication.R
import com.example.myapplication.adapters.StudentsListAdapter
import com.example.myapplication.db.StudentDao
import com.example.myapplication.db.StudentDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.list_of_students.*
import javax.inject.Inject

@AndroidEntryPoint
class ListOfStudents: Fragment(R.layout.list_of_students), View.OnClickListener {

    @Inject
    lateinit var studentDao: StudentDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabAdd.setOnClickListener(this)

        rvStudents.apply {
            adapter = StudentsListAdapter(studentDao.getAllStudents())
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onClick(view: View?) {
        findNavController().navigate(
            R.id.action_listOfStudents_to_addStudent
        )
    }
}