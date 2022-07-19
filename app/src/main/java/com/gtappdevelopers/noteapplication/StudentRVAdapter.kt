package com.gtappdevelopers.noteapplication

import Model.Student
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentRVAdapter(
    val context: Context,
    val studentClickDeleteInterface: StudentClickDeleteInterface,
    val studentClickInterface: StudentClickInterface
) :
    RecyclerView.Adapter<StudentRVAdapter.ViewHolder>() {

    //on below line we are creating a variable for our all notes list.
    private val allStudents = ArrayList<Student>()

    //on below line we are creating a view holder class.
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //on below line we are creating an initializing all our variables which we have added in layout file.
        val st_nameTv = itemView.findViewById<TextView>(R.id.idTVSt_Name)
        val dateTV = itemView.findViewById<TextView>(R.id.idTVDate)
        val deleteIV = itemView.findViewById<ImageView>(R.id.idIVDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //inflating our layout file for each item of recycler view.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.note_rv_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //on below line we are setting data to item of recycler view.
        holder.st_nameTv.setText(allStudents.get(position).student_name)
        holder.dateTV.setText("Last Updated : "+allStudents.get(position).timeStamp)
        //on below line we are adding click listner to our delete image view icon.
        holder.deleteIV.setOnClickListener {
            //on below line we are calling a note click interface and we are passing a position to it.
            studentClickDeleteInterface.onDeleteIconClick(allStudents.get(position))
        }

        //on below line we are adding click listner to our recycler view item.
        holder.itemView.setOnClickListener {
            //on below line we are calling a note click interface and we are passing a position to it.
            studentClickInterface.onStudentClick(allStudents.get(position))
        }
    }

    override fun getItemCount(): Int {
        //on below line we are returning our list size.
        return allStudents.size
    }

    //below method is use to update our list of notes.
    fun updateList(newList: List<Student>) {
        //on below line we are clearing our notes array list/
        allStudents.clear()
        //on below line we are adding a new list to our all notes list.
        allStudents.addAll(newList)
        //on below line we are calling notify data change method to notify our adapter.
        notifyDataSetChanged()
    }

}

interface StudentClickDeleteInterface {
    //creating a method for click action on delete image view.
    fun onDeleteIconClick(student: Student)
}

interface StudentClickInterface {
    //creating a method for click action on recycler view item for updating it.
    fun onStudentClick(student: Student)
}