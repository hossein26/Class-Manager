package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.rv_sample.view.*

class StudentsListAdapter(val list: List<String>): RecyclerView.Adapter<StudentsListAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        return StudentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_sample, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val itemList = list[position]
        holder.itemView.apply {
            txtName.text = itemList
            txtGrade.text = position.toString()
            setOnClickListener{
                Toast.makeText(context, "$position Clicked!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount() = list.size
}