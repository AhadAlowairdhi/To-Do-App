package com.example.todoapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.TodoRowBinding

class RVadapter(private var todo : ArrayList<ToDo>) : RecyclerView.Adapter<RVadapter.ViewHolder>() {
    class ViewHolder(val binding: TodoRowBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            TodoRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = todo[position]
        holder.binding.apply {
            tvTask.text = task.title
            checkB.isChecked = task.completed
            checkB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                tvTask.setTextColor(Color.GRAY)
            }else{
                tvTask.setTextColor(Color.BLACK)
            }
                task.completed = !task.completed
            }

        }
    }

    override fun getItemCount(): Int = todo.size

    fun deleteItems() {
        todo.removeAll{task -> task.completed}
        notifyDataSetChanged()
    }

    fun addItem(text : String) {
        todo.add(ToDo(text))
        notifyItemChanged(todo.size - 1)
    }
}
