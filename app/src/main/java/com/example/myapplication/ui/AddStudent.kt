package com.example.myapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.db.Student
import com.example.myapplication.db.StudentDao
import com.example.myapplication.db.StudentDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.add_student.*
import javax.inject.Inject

@AndroidEntryPoint
class AddStudent: Fragment(R.layout.add_student), View.OnClickListener {

    @Inject
    lateinit var studentDao: StudentDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnApply.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (edtNumber.text.isNotEmpty() && edtFirstName.text.isNotEmpty() && edtLastName.text.isNotEmpty()){
            studentDao.addStudent(
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