package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.db.Student
import kotlinx.android.synthetic.main.rv_sample.view.*

class StudentsListAdapter(val listOfStudents: List<Student>): RecyclerView.Adapter<StudentsListAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        return StudentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_sample, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val itemList = listOfStudents[position]
        holder.itemView.apply {
            txtName.text = itemList.firstName
            txtGrade.text = itemList.grade.toString()
            txtNumberOfAsk.text = itemList.numberOfAsk.toString()
            setOnClickListener{
                val bundle = bundleOf("studentId" to itemList.id)
                it.findNavController().navigate(R.id.action_listOfStudents_to_add_grade, bundle)
            }
        }
    }

    override fun getItemCount() = listOfStudents.size
}