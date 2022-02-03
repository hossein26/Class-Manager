package com.example.myapplication.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapters.StudentsListAdapter
import kotlinx.android.synthetic.main.list_of_students.*

class ListOfStudents: Fragment(R.layout.list_of_students), View.OnClickListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabAdd.setOnClickListener(this)

        val fakeList = mutableListOf<String>("AAA", "BBB", "CCC", "DDD")

        rvStudents.apply {
            adapter = StudentsListAdapter(fakeList)
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onClick(view: View?) {
        findNavController().navigate(
            R.id.action_listOfStudents_to_addStudent
        )
    }
}