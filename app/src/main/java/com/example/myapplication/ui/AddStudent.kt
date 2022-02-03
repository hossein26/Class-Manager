package com.example.myapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.db.Student
import com.example.myapplication.db.StudentDatabase
import kotlinx.android.synthetic.main.add_student.*

class AddStudent: Fragment(R.layout.add_student), View.OnClickListener {

    private lateinit var database: StudentDatabase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = StudentDatabase.getDatabase(requireContext())

        btnApply.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (edtName.text.isNotEmpty() && edtGrade.text.isNotEmpty()){
            database.studentDao().addStudent(
                Student(name = edtName.text.toString(), grade = edtGrade.text.toString().toInt()))
            Toast.makeText(view?.context, "Saved!", Toast.LENGTH_SHORT).show()
        }
    }
}