package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.db.Student
import kotlinx.android.synthetic.main.rv_sample.view.*

class StudentsListAdapter(private val onStudentClicked: (Student) -> Unit) :
    ListAdapter<Student, StudentsListAdapter.StudentViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        return StudentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_sample, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onStudentClicked(current)
        }
        holder.bind(current)
    }

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(student: Student) {
            itemView.txtFirstName.text = student.firstName
            itemView.txtLastName.text = student.lastName
            itemView.txtGrade.text = student.grade.toString()
            itemView.txtNumberOfAsk.text = student.numberOfAsking.toString()
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem.firstName == newItem.lastName
            }
        }
    }

}












