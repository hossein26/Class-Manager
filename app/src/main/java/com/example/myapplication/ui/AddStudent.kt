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
        if (edtNumber.text.isNotEmpty() && edtFirstName.text.isNotEmpty() && edtLastName.text.isNotEmpty()){
            database.studentDao().addStudent(
                Student(number = edtNumber.text.toString().toInt(), firstName = edtFirstName.text.toString(), lastName = edtLastName.text.toString()))
            Toast.makeText(view?.context, "Saved!", Toast.LENGTH_SHORT).show()
        }else{
            when{
                edtNumber.text.isEmpty() -> edtNumber.requestFocus()
                edtFirstName.text.isEmpty() -> edtFirstName.requestFocus()
                edtLastName.text.isEmpty() -> edtLastName.requestFocus()
            }
        }
    }
}