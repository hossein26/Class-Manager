package com.example.myapplication.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_of_students.*

class ListOfStudents: Fragment(R.layout.list_of_students), View.OnClickListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabAdd.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        findNavController().navigate(
            R.id.action_listOfStudents_to_addStudent
        )
    }
}